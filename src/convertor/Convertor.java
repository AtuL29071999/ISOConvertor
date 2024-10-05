package convertor;

import com.ibm.mq.MQC;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
//import com.converter.MsgConvertor; 

public class Convertor {

	private static final Logger logger = Logger.getLogger(Convertor.class.getName());

	static MQConnection mqConnection = new MQConnection();
	static MessageSigner mSigner;
	static HashMap<String, String> details;
	static Properties prop = new Properties();
	static MQQueueManager qMgr = null;
	static MQQueue source = null;
	static String destination = "";
	static boolean running = false;
	// static MsgConvertor msgConvert = null;
	static Connection con = null;
	static String rtgsMSGDoc = null;
	static String msgRTGS = null;
	static String neftBlock4 = null;
	static String lcBlock4 = null;

	public static void main(String[] args) {
		configureLogger();
		boolean checkFlag = initializeMQConnection();

		if (checkFlag) {
			running = true;
			logger.info("Initialization check done. Starting message processing...");

			processMessages();
		} else {
			logger.severe("Initialization failed. Exiting...");
		}

		System.out.println("Enter the String: ");
	}

	private static void configureLogger() {
		try {
			logger.setLevel(Level.ALL);
			FileHandler fh = new FileHandler(".//log//app.log");
			logger.addHandler(fh);
			fh.setFormatter(new SimpleFormatter());
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error creating log file", e);
		}
	}

	private static boolean initializeMQConnection() {
		try {
			logger.info("Loading MQConfig File...");
			details = mqConnection.getMQDetails();
			logger.info("MQConfig loaded successfully");

			logger.info("Loading AppConfig File...");
			prop.load(new FileInputStream(".//AppConfig.cnfg"));
			logger.info("AppConfig loaded successfully");

			logger.info("Connecting to MQ...");
			qMgr = mqConnection.getQueueManager(details.get("Host"), details.get("FromQmgr"), details.get("PortNumber"),
					details.get("Channel"));
			source = mqConnection.getQueue(qMgr, details.get("QfromCBSNEFT"));
			destination = details.get("QtoSFMSNEFT");
			logger.info("MQ Connection established successfully");

			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error initializing MQ connection", e);
			return false;
		}
	}

	private static void processMessages() {
		while (running) {
			try {
				int msgCount = source.getCurrentDepth();

				if (msgCount > 0) {
					logger.info("Messages found in queue: " + msgCount);
					MQGetMessageOptions mgo = new MQGetMessageOptions();
					mgo.options = MQC.MQGMO_WAIT | MQC.MQGMO_CONVERT;

					for (int x = 0; x < msgCount; x++) {
						MQMessage retrievedMessage = new MQMessage();
						source.get(retrievedMessage, mgo);
						byte[] msgBytes = new byte[retrievedMessage.getMessageLength()];
						retrievedMessage.readFully(msgBytes);
						String msg = new String(msgBytes);
						logger.info("Retrieved Message: " + msg);

						processMessage(msg);

						boolean putMessage = mqConnection.putMessageToQueue(destination, msg, qMgr);

						if (putMessage) {
							logger.info("Message sent to " + destination + " successfully");
							qMgr.commit();
						} else {
							logger.severe("Failed to send message to " + destination);
							qMgr.backout();
						}

						if (x == msgCount - 1) {
							reconnectMQConnection();
						}
					}
				} else {
					logger.info("No messages found in queue. Waiting...");
					Thread.sleep(1000);
				}
			} catch (MQException mqe) {
				logger.log(Level.SEVERE, "MQ Exception: " + mqe.getMessage(), mqe);
				reconnectMQConnection();
			} catch (Exception e) {
				logger.log(Level.SEVERE, "Exception occurred during message processing", e);
			}
		}
	}

	private static void processMessage(String msg) {

		// ---------------------------------------------
		try {
			if (msg != null && !msg.isEmpty()) {
				if (msg.contains("<RequestPayload>")) {
					

					try {

					} catch (Exception e) {

					}

				}
			}
		} catch (Exception e) {

		}

		// ---------------------------------------------
		try {
			if (msg != null && !msg.isEmpty()) {
				if (msg.contains("<RequestPayload")) {
					msg = msg.replace("\r\n", "\n");
					rtgsMSGDoc = msg.substring(msg.indexOf("<Document"), msg.indexOf("</RequestPay"));
					logger.info("rtgsMSGDoc: " + rtgsMSGDoc);
					try {
						msgRTGS = MessageSigner.getRtgsSignature(msg.trim(), (String) prop.get("password"));
					} catch (Exception e) {
						logger.log(Level.SEVERE, "Error signing message", e);
					}
					String signedMsg = "-----BEGIN PKCS7-----" + msgRTGS + "-----END PKCS7-----";
					String rep = "<Sgntr><XMLSgntrs xmlns=\"http://www.w3.org/2000/09/xmldsig#\">" + signedMsg
							+ "</XMLSgntrs></Sgntr></AppHdr>";
					msg = msg.replace(
							"<Sgntr><XMLSgntrs xmlns=\"http://www.w3.org/2000/09/xmldsig#\"></XMLSgntrs></Sgntr>", rep);
					msg = msg.replace("</AppHdr>", rep);
				} else if (msg.substring(7, 11).equals("F01O7")) {
					try {

						System.out.println("Org Msg: " + msg);
						int start = msg.indexOf("{4:");
						int end = msg.indexOf("-}");

						System.out.println("start: " + start);
						System.out.println("end: " + end);
						lcBlock4 = msg.substring(start + 3, end);

						System.out.println("LCBlock4: " + lcBlock4);
						logger.info("Browsed Block4: " + lcBlock4);
					} catch (Exception e) {
						logger.log(Level.SEVERE, "Message Format Exception", e);
					}
					try {
						String signedBlock4 = MessageSigner.getLcSignature(lcBlock4.trim(),
								(String) prop.get("password"));
						System.out.println("signedBlock4: " + signedBlock4);
						logger.info("Signed Block4:\n" + signedBlock4);
						msg = msg.trim() + "\n{UMAC:-----BEGIN PKCS7-----" + signedBlock4.trim()
								+ "-----END PKCS7-----}";
						System.out.println("Final Message:\n " + msg);
						logger.info("Final Message:\n" + msg);
					} catch (Exception e) {
						logger.log(Level.SEVERE, "Error signing NEFT block", e);
					}
				} else {

					try {
						System.out.println("Org Msg: " + msg);
						int start = msg.indexOf("{4:");
						int end = msg.indexOf("-}");

						System.out.println("start: " + start);
						System.out.println("end: " + end);
						neftBlock4 = msg.substring(start + 3, end);

						System.out.println("neftBlock4: " + neftBlock4);
						logger.info("Browsed Block4: " + neftBlock4);
					} catch (Exception e) {
						logger.log(Level.SEVERE, "Message Format Exception", e);
					}
					try {
						String signedBlock4 = MessageSigner.getNeftSignature(neftBlock4.trim(),
								(String) prop.get("password")); // Assuming signNEFT is a static method in MessageSigner
						System.out.println("signedBlock4: " + signedBlock4);
						logger.info("Signed Block4:\n" + signedBlock4);
						msg = msg.trim() + "\n{UMAC:-----BEGIN PKCS7-----" + signedBlock4.trim()
								+ "-----END PKCS7-----}";
						System.out.println("Final Message:\n " + msg);
						logger.info("Final Message:\n" + msg);
					} catch (Exception e) {
						logger.log(Level.SEVERE, "Error signing NEFT block", e);
					}

				}
			} else {
				logger.warning("Message is null or empty");
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error processing message", e);
		}
	}

	private static void reconnectMQConnection() {
		try {
			qMgr.disconnect();
			qMgr = mqConnection.getQueueManager(details.get("Host"), details.get("FromQmgr"), details.get("PortNumber"),
					details.get("Channel"));
			source = mqConnection.getQueue(qMgr, details.get("QfromCBSNEFT"));
		} catch (MQException e) {
			logger.log(Level.SEVERE, "Error reconnecting to MQ", e);
		}
	}
}
