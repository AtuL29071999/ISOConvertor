package convertor;

import com.ibm.mq.*;
import java.io.*;
import java.util.*;

public class MQConnection {

    public HashMap<String, String> getMQDetails() {
        HashMap<String, String> mqDetails = new HashMap<>();

        try {
            File file = new File(".//MQConnection.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] splitField = line.split("=");
                mqDetails.put(splitField[0].trim(), splitField[1].trim());
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("IOException: " + ex.getMessage());
        }

        return mqDetails;
    }

    public synchronized boolean putMessageToQueue(String queueName, String message, MQQueueManager qMgr) {
        boolean messageSent = false;
        MQQueue queue = null;

        try {
            int openOptions = MQC.MQOO_OUTPUT;
            queue = qMgr.accessQueue(queueName, openOptions);

            MQMessage mqMessage = new MQMessage();
            mqMessage.format = MQC.MQFMT_STRING;
            mqMessage.writeString(message);

            MQPutMessageOptions pmo = new MQPutMessageOptions();
            pmo.options += MQC.MQPMO_SYNCPOINT;

            queue.put(mqMessage, pmo);
            System.out.println("Message has been put to queue: " + queueName);
            queue.close();

            messageSent = true;
        } catch (MQException ex) {
            System.err.println("MQException: CC = " + ex.completionCode + " RC = " + ex.reasonCode);
        } catch (IOException ex) {
            System.err.println("IOException: " + ex.getMessage());
        } finally {
            try {
                if (queue != null)
                    queue.close();
            } catch (MQException ex) {
                System.err.println("Error closing queue: " + ex.getMessage());
            }
        }

        return messageSent;
    }

    public synchronized MQQueueManager getQueueManager(String hostName, String qManagerName, String portNumber, String channel) {
        MQQueueManager qMgr = null;

        try {
            MQEnvironment.hostname = hostName;
            MQEnvironment.channel = channel;
            MQEnvironment.port = Integer.parseInt(portNumber);
            MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY, MQC.TRANSPORT_MQSERIES);

            qMgr = new MQQueueManager(qManagerName);
            System.out.println("Connected with Queue Manager: " + qManagerName);
        } catch (MQException ex) {
            System.err.println("Exception:= " + ex);
        }
        return qMgr;
    }

    public MQQueue getQueue(MQQueueManager qMgr, String queueName) {
        MQQueue queue = null;

        try {
            int openOptions = MQC.MQOO_INQUIRE + MQC.MQOO_FAIL_IF_QUIESCING + MQC.MQOO_INPUT_SHARED;
            queue = qMgr.accessQueue(queueName, openOptions);
            System.out.println("Connected to queue: " + queueName);
        } catch (MQException ex) {
            System.err.println("MQException during GET: " + ex.getMessage());
        }

        return queue;
    }
}
