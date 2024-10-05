package Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class MessageToXmlFormate {

	public static void messageToXmlFormate(HashMap<String, String> map) {

//		System.out.println("messageToXmlFormate");
//		map.forEach((key, value) -> System.out.println(key + " --> " + value));
		String transactionReferenceNumber1 = map.get("F20201");
		String numberOfTransaction = map.get("F11061");
		String sumOfAmounts = map.get("F40631");
		String transactionReferenceNumber2 = map.get("F20202");
		String amount = map.get("F40381");

		String stringDate = map.get("F33801");
		String formattedDateIso = null;
		try {
//			System.out.println(stringDate);
			SimpleDateFormat originalFormat = new SimpleDateFormat("yyyymmdd");
			Date date = originalFormat.parse(stringDate);
			SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-mm-dd");
			String formattedDate = newFormat.format(date);
//			System.out.println("formattedDate  ++++ "+formattedDate);

			SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss'Z'");
			isoFormat.setTimeZone(TimeZone.getTimeZone("IST"));
			formattedDateIso = isoFormat.format(date);
//			System.out.println(formattedDateIso);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sendingBranchIFSC = map.get("F57561");
		String sendingCustomerAccountNumber = map.get("F60211");
		String sendingCustomerAccountName = map.get("F60911");
		String originatorOfRemittance = map.get("F70021");
		String beneficiaryBranchIFSC = map.get("F55691");
		String beneficiaryCustomerAccountNumber = map.get("F60611");
		String beneficiaryCustomerAccountName = map.get("F60811");

		String bizMsgIdrValue = sendingBranchIFSC.substring(0, 4) + stringDate
				+ transactionReferenceNumber2.substring(transactionReferenceNumber2.length() - 10);

//		System.out.println("=================");

		String appHdr = "<RequestPayload>\r\n<AppHdr " + "xmlns:sig=\"http://www.w3.org/2000/09/xmldsig#\""
				+ " xmlns:xsi=\"urn:iso:std:iso:20022:tech:xsd:Header\" "
				+ "xmlns=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.02\">\r\n";

		String fr = "<Fr>\r\n<FIId>\r\n<FinInstnId>\r\n<ClrSysMmbId>\r\n<MmbId>" + "   Properties File Se Aayegi   "
				+ "</MmbId>\r\n</ClrSysMmbId>\r\n</FinInstnId>\r\n</FIId>\r\n</Fr>\r\n";

		String to = "<To>\r\n<FIId>\r\n<FinInstnId>\r\n<ClrSysMmbId>\r\n<MmbId>" + "RBIP0NEFTSC"
				+ "</MmbId>\r\n</ClrSysMmbId>\r\n</FinInstnId>\r\n</FIId>\r\n</To>\r\n";

		String bizMsgIdr = "<BizMsgIdr>" + bizMsgIdrValue + "</BizMsgIdr>\r\n";

		String msgDefIdr = "<MsgDefIdr>" + "pacs.008.001.09" + "</MsgDefIdr>\r\n";

		String bizSvc = "<BizSvc>" + "NEFTFIToFICustomerCredit" + "</BizSvc>\r\n";

		// Here AppHeader Close
		String creDt = "<CreDt>" + formattedDateIso + "</CreDt>\r\n</AppHdr>\r\n";

		String creDtTm = formattedDateIso.substring(0, formattedDateIso.length() - 1);
//		System.out.println("creDtTm:  "+creDtTm);

		String document = "<Document xmlns=\"urn:iso:std:iso:20022:tech:xsd:pacs.008.001.09\">\r\n<FIToFICstmrCdtTrf>\r\n";

		// Group Header Start
		String grpHdr = "<GrpHdr>\r\n<MsgId>" + bizMsgIdrValue + "</MsgId>\r\n" + "<CreDtTm>" + creDtTm
				+ "</CreDtTm>\r\n" + "<NbOfTxs>" + numberOfTransaction + "</NbOfTxs>\r\n"
				+ "<TtlIntrBkSttlmAmt Ccy=\"INR\">" + sumOfAmounts + "</TtlIntrBkSttlmAmt>\r\n" + "<IntrBkSttlmDt>"
				+ LocalDate.now() + "</IntrBkSttlmDt>\r\n" + "<SttlmInf>\r\n<SttlmMtd>" + "CLRG" + "</SttlmMtd>\r\n"
				+ "</SttlmInf>\r\n";

		String instgAgt1 = "<InstgAgt>\r\n<FinInstnId>\r\n<ClrSysMmbId>\r\n<MmbId>" + "   HDFC0000001  file  "
				+ "</MmbId>\r\n</ClrSysMmbId>\r\n</FinInstnId>\r\n</InstgAgt>\r\n";

		String instgAgt2 = "<InstgAgt>\r\n<FinInstnId>\r\n<ClrSysMmbId>\r\n<MmbId>" + "RBIP0NEFTSC"
				+ "</MmbId>\r\n</ClrSysMmbId>\r\n</FinInstnId>\r\n</InstgAgt>\r\n</GrpHdr>\r\n";
		// Group Header End

		String txIdvalue = sendingBranchIFSC.substring(0, 4) + "N5" + stringDate
				+ transactionReferenceNumber2.substring(transactionReferenceNumber2.length() - 8);
//		System.out.println(txIdvalue);

		String pmtId = "<CdtTrfTxInf>\r\n<PmtId>\r\n<EndToEndId>/XUTR/" + transactionReferenceNumber1
				+ "</EndToEndId>\r\n" + "<TxId>" + txIdvalue + "</TxId>\r\n</PmtId>\r\n";

		String pmtTpInf = "<PmtTpInf>\r\n<InstrPrty>" + "HIGH" + "</InstrPrty>\r\n" + "<SvcLvl>\r\n<Cd>" + "SDVA"
				+ "</Cd>\r\n</SvcLvl>\r\n" + "<LclInstrm>\r\n<Cd>" + "TRF" + "</Cd>\r\n</LclInstrm>\r\n"
				+ "<CtgyPurp>\r\n<Cd>" + "EFT" + "</Cd>\r\n</CtgyPurp>\r\n</PmtTpInf>\r\n";

		String intrBkSttlmAmt = "<IntrBkSttlmAmt Ccy=\"INR\">" + amount + "</IntrBkSttlmAmt>\r\n";

		String chrgBr = "<ChrgBr>" + "SLEV" + "</ChrgBr>\r\n";

		String dbtr = "<Dbtr>\r\n<Nm>" + sendingCustomerAccountName + "</Nm>\r\n";

		String dbtrId = map.get("F74951");
//		System.out.println();
//		System.out.println(dbtrId);
//		System.out.println();

		StringBuilder orgId = new StringBuilder();
		if (dbtrId != null && !dbtrId.isEmpty()) {
			orgId.append("<Id>\r\n<OrgId>\r\n<LEI>").append(dbtrId).append("</LEI>\r\n</OrgId>\r\n</Id>\r\n");
		}

		String mobileNumber = map.get("F56291");
//		System.out.println("mobileNumber "+mobileNumber);		
		StringBuilder ctctDtls = new StringBuilder();
		String email = map.get("F56292");

		if ((mobileNumber != null && !mobileNumber.isEmpty()) || (email != null && !email.isEmpty())) {
			Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");
			Matcher match = ptrn.matcher(mobileNumber);
			if (match.find() && match.group().equals(mobileNumber)) {
				ctctDtls.append("<CtctDtls>\r\n<MobNb>").append(mobileNumber).append("</MobNb>\r\n");
			}
//				String email = map.get("F56292");
//				System.out.println("email "+email);
			if (email != null && !email.isEmpty()) {
				String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(email);
				if (matcher.matches()) {
					ctctDtls.append("<EmailAdr>").append(email).append("</EmailAdr>\r\n</CtctDtls>\r\n");
				}
			}

		}
		ctctDtls.append("</Dbtr>\r\n");

		String dbtrAcct = "<DbtrAcct>\r\n<Id>\r\n<Othr>\r\n<Id>" + sendingCustomerAccountNumber
				+ "</Id>\r\n</Othr>\r\n</Id>\r\n";

		String senderAccountType = map.get("F63051");

		StringBuilder dbtrAcctTp = new StringBuilder();

		if (senderAccountType != null && !senderAccountType.isEmpty()) {
			dbtrAcctTp.append("<Tp>\r\n<Cd>").append(senderAccountType).append("</Cd>\r\n</Tp>\r\n");
		}

		dbtrAcctTp.append("</DbtrAcct>\r\n");

		String dbtrAgt = "<DbtrAgt>\r\n<FinInstnId>\r\n<ClrSysMmbId>\r\n<MmbId>" + sendingBranchIFSC
				+ "</MmbId>\r\n</ClrSysMmbId>\r\n</FinInstnId>\r\n</DbtrAgt>\r\n";

		String cdtrAgt = "<CdtrAgt>\r\n<FinInstnId>\r\n<ClrSysMmbId>\r\n<MmbId>" + beneficiaryBranchIFSC
				+ "</MmbId>\r\n</ClrSysMmbId>\r\n</FinInstnId>\r\n</CdtrAgt>\r\n";

		String cdtr = "<Cdtr>\r\n<Nm>" + beneficiaryCustomerAccountName + "</Nm>\r\n";

		String recieverAdrLine1 = map.get("F55651");
		String recieverAdrLine2 = map.get("F55652");
		String recieverAdrLine3 = map.get("F55653");
		String recieverAdrLine4 = map.get("F55654");

		StringBuilder creditorAddress = new StringBuilder();

		if (recieverAdrLine1 != null && !recieverAdrLine1.isEmpty()) {
			creditorAddress.append("<PstlAdr>\r\n<AdrLine>").append(recieverAdrLine1).append("</AdrLine>\r\n");
			if (recieverAdrLine2 != null && !recieverAdrLine2.isEmpty()) {
				creditorAddress.append("<AdrLine>").append(recieverAdrLine2).append("</AdrLine>\r\n");
			}
			if (recieverAdrLine3 != null && !recieverAdrLine3.isEmpty()) {
				creditorAddress.append("<AdrLine>").append(recieverAdrLine3).append("</AdrLine>\r\n");
			}
			if (recieverAdrLine4 != null && !recieverAdrLine4.isEmpty()) {
				creditorAddress.append("<AdrLine>").append(recieverAdrLine4).append("</AdrLine>\r\n</PstlAdr>\r\n");
			}
			creditorAddress.append("</PstlAdr>\r\n");
		}

		creditorAddress.append("</Cdtr>\r\n");

		String cdtrAcct = "<CdtrAcct>\r\n<Id>\r\n<Othr>\r\n<Id>" + beneficiaryCustomerAccountNumber
				+ "</Id>\r\n</Othr>\r\n</Id>\r\n";

		String recieverAccountType = map.get("F63101");

		StringBuilder cdtrAcctTp = new StringBuilder();

		if (recieverAccountType != null && !recieverAccountType.isEmpty()) {
			cdtrAcctTp.append("<Tp>\r\n<Cd>").append(recieverAccountType).append("<Cd>\r\n</Tp>\r\n");
		}

		cdtrAcctTp.append("</CdtrAcct>\r\n");

		String instrForCdtrAgt = "<InstrForCdtrAgt>\r\n<InstrInf>" + beneficiaryCustomerAccountName
				+ "</InstrInf>\r\n</InstrForCdtrAgt>\r\n";

//		String senderToReceiverInformation1 = map.get("F74951");
		String senderToReceiverInformation2 = map.get("F74952");
//		System.out.println("------------------------------------");
//		System.out.println(senderToReceiverInformation2);
//		System.out.println("------------------------------------");
		String senderToReceiverInformation3 = map.get("F74953");
		String senderToReceiverInformation4 = map.get("F74954");
		String senderToReceiverInformation5 = map.get("F74955");
		String senderToReceiverInformation6 = map.get("F74956");
		String batchTime = map.get("F35351");

		StringBuilder rmtInf = new StringBuilder();

		if (batchTime != null && !batchTime.isEmpty()) {
			rmtInf.append("<RmtInf>\r\n<Ustrd>").append("BatchId:").append(batchTime).append("</Ustrd>\r\n");

//				if (senderToReceiverInformation1 != null && !senderToReceiverInformation1.isEmpty()) {
//				rmtInf.append("<Ustrd>").append(senderToReceiverInformation1).append("<Ustrd>\r\n");
//			}

			if (senderToReceiverInformation2 != null && !senderToReceiverInformation2.isEmpty()) {
				rmtInf.append("<Ustrd>").append(senderToReceiverInformation2).append("<Ustrd>\r\n");
			}

			if (senderToReceiverInformation3 != null && !senderToReceiverInformation3.isEmpty()) {
				rmtInf.append("<Ustrd>").append(senderToReceiverInformation3).append("<Ustrd>\r\n");
			}

			if (senderToReceiverInformation4 != null && !senderToReceiverInformation4.isEmpty()) {
				rmtInf.append("<Ustrd>").append(senderToReceiverInformation4).append("</Ustrd>\r\n");
			}

			if (senderToReceiverInformation5 != null && !senderToReceiverInformation5.isEmpty()) {
				rmtInf.append("<Ustrd>").append(senderToReceiverInformation5).append("</Ustrd>\r\n");
			}

			if (senderToReceiverInformation6 != null && !senderToReceiverInformation6.isEmpty()) {
				rmtInf.append("<Ustrd>").append(senderToReceiverInformation6).append("</Ustrd>\r\n");

			}

			rmtInf.append("</RmtInf>\r\n");
		}

		rmtInf.append("</CdtTrfTxInf>\r\n</FIToFICstmrCdtTrf>\r\n</Document>\r\n</RequestPayload>\r\n");

//		rmtInf = "<RmtInf>\r\n<Ustrd>" + "   BatchId:0002   " + "</Ustrd>\r\n<Ustrd>"
//				+ "   sdfffffffffffffffffffffffffffffffff   " + "</Ustrd>\r\n" + "<Ustrd>"
//				+ "   sdfffffffffffffffffffffffffffffffff   " + "</Ustrd>\r\n" + "<Ustrd>"
//				+ "   sdfffffffffffffffffffffffffffffffff   "
//				+ "</Ustrd>\r\n</RmtInf>\r\n</CdtTrfTxInf>\r\n</FIToFICstmrCdtTrf>\r\n</Document>\r\n</RequestPayload>\r\n";

		String finalString = appHdr + fr + to + bizMsgIdr + msgDefIdr + bizSvc + creDt + document + grpHdr + instgAgt1
				+ instgAgt2 + pmtId + pmtTpInf + intrBkSttlmAmt + chrgBr + dbtr + orgId + ctctDtls + dbtrAcct
				+ dbtrAcctTp + dbtrAgt + cdtrAgt + cdtr + creditorAddress + cdtrAcct + cdtrAcctTp + instrForCdtrAgt
				+ rmtInf;

		System.out.println(finalString);

	}

	public static void generatePacs004OutGoingXML(HashMap<String, String> map) {

//		map.forEach((key, value) -> System.out.println(key + " --> " + value));
//		System.out.println();

		String requestPayloadStart = "<RequestPayload>\r\n";

		String appHdr = "<AppHdr xmlns=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.02\"  xmlns:xsi=\"urn:iso:std:iso:20022:tech:xsd:Header\" xmlns:sig=\"http://www.w3.org/2000/09/xmldsig#\">\r\n";

		String fr = "<Fr>\r\n<FIId>\r\n<FinInstnId>\r\n<ClrSysMmbId>\r\n<MmbId>\r\n" + "   ICIC0000001  file  "
				+ "</MmbId>\r\n</ClrSysMmbId>\r\n</FinInstnId>\r\n</FIId>\r\n</Fr>\r\n";

		String to = "<To>\r\n<FIId>\r\n<FinInstnId>\r\n<ClrSysMmbId>\r\n<MmbId>" + "RBIP0NEFTSC"
				+ "</MmbId>\r\n</ClrSysMmbId>\r\n</FinInstnId>\r\n</FIId>\r\n</To>\r\n";

		String transactionReferenceNumber1 = map.get("F20201");
		String transactionReferenceNumber2 = map.get("F20202");
		String sendingBranchIFSC = map.get("F57561");
		String stringDate = map.get("F33801");
		System.out.println("comming date : " + stringDate);
		String formattedDateIso = null;
		String formattedDate = null;
		try {
//			System.out.println(stringDate);
			SimpleDateFormat originalFormat = new SimpleDateFormat("yyyymmdd");
			Date date = originalFormat.parse(stringDate);
			System.out.println("date :  " + date);
			SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-mm-dd");
//			System.out.println("newFormat "+newFormat);
			formattedDate = newFormat.format(date);
//			System.out.println("formattedDate === "+formattedDate);

			SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss'Z'");
			isoFormat.setTimeZone(TimeZone.getTimeZone("IST"));
			formattedDateIso = isoFormat.format(date);
			System.out.println("formatted date Iso: " + formattedDateIso);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String bizMsgIdrValue = sendingBranchIFSC.substring(0, 4) + stringDate
				+ transactionReferenceNumber2.substring(transactionReferenceNumber2.length() - 10);

		String bizMsgIdr = "<BizMsgIdr>" + bizMsgIdrValue + "</BizMsgIdr>\r\n";

		String msgDefIdr = "<MsgDefIdr>" + "pacs.004.001.10" + "</MsgDefIdr>\r\n";

		String bizSvc = "<BizSvc>" + "NEFTPaymentReturn" + "</BizSvc>\r\n";

		String creDt = "<CreDt>" + formattedDateIso + "</CreDt>\r\n</AppHdr>\r\n";

		/*
		 * app header close Group header Start
		 */

		String document = "<Document xmlns=\"urn:iso:std:iso:20022:tech:xsd:pacs.004.001.10\">\r\n<PmtRtr>\r\n<GrpHdr>\r\n";

		String msgId = "<MsgId>" + bizMsgIdrValue + "</MsgId>\r\n";

		String creDtTmVal = formattedDateIso.substring(0, formattedDateIso.length() - 1);

		String creDtTm = "<CreDtTm>" + creDtTmVal + "</CreDtTm>\r\n";

		String noOftxsVal = map.get("F11061");

		String nbOfTxs = "<NbOfTxs>" + noOftxsVal + "</NbOfTxs>\r\n";

		String amount = map.get("F40381");

		String ttlRtrdIntrBkSttlmAmt = "<TtlRtrdIntrBkSttlmAmt Ccy=\"INR\">" + amount + "</TtlRtrdIntrBkSttlmAmt>\r\n";

		String intrBkSttlmDt = "<IntrBkSttlmDt>" + formattedDate + "</IntrBkSttlmDt>\r\n";

		String sttlmInf = "<SttlmInf>\r\n<SttlmMtd>CLRG</SttlmMtd>\r\n</SttlmInf>";

		String instgAgt1 = "<InstgAgt>\r\n<FinInstnId>\r\n<ClrSysMmbId>\r\n<MmbId>" + "   ICIC0000001 file  "
				+ "</MmbId>\r\n</ClrSysMmbId>\r\n</FinInstnId>\r\n</InstgAgt>";

		String instdAgt2 = "<InstdAgt>\r\n" + "FinInstnId>\r\n" + "<ClrSysMmbId>\r\n" + "<MmbId>RBIP0NEFTSC</MmbId>\r\n"
				+ "</ClrSysMmbId>\r\n" + "</FinInstnId>\r\n" + "</InstdAgt>\r\n" + "</GrpHdr>\r\n";

		String rtrIdvalue = sendingBranchIFSC.substring(0, 4) + "N5" + stringDate
				+ transactionReferenceNumber2.substring(transactionReferenceNumber2.length() - 8);

		String rtrId = "<TxInf>\r\n" + "<RtrId>" + rtrIdvalue + "</RtrId>\r\n";

		String orgnlEndToEndId = "<OrgnlEndToEndId>/XUTR/" + transactionReferenceNumber1 + "</OrgnlEndToEndId>\r\n";

		String orgnlTxId = "<OrgnlTxId>" + "   HDFCN52022062824954013   " + "</OrgnlTxId>\r\n";

		String rtrdIntrBkSttlmAmt = "<RtrdIntrBkSttlmAmt Ccy=\"INR\">" + amount + "</RtrdIntrBkSttlmAmt>\r\n";

		String rtrRsnInf = "<RtrRsnInf>\r\n" + "<Rsn>\r\n" + "<Cd>" + "AC01" + "</Cd>\r\n" + "</Rsn>\r\n"
				+ "</RtrRsnInf>\r\n";

		String sendingCustomerAccountName = map.get("F60911");

		String dbtr = "<OrgnlTxRef>\r\n" + "<UndrlygCstmrCdtTrf>\r\n" + "<Dbtr>\r\n" + "<Nm>"
				+ sendingCustomerAccountName + "</Nm>\r\n";

		String leiValue = map.get("F74951");
//	   System.out.println("lei "+leiValue);

		StringBuilder dbtrId = new StringBuilder();

		if (leiValue != null && !leiValue.isEmpty()) {
			dbtrId.append("<Id>\r\n<OrgId>\r\n<LEI>").append(leiValue).append("</LEI>\r\n</OrgId>\r\n</Id>\r\n");
		}
		dbtrId.append("</Dbtr>\r\n");

//	   String pstlAdr = "<PstlAdr>\r\n"
//		   		+ "<AdrLine>"+"   IFTAS,HYDERABAD   "+"</AdrLine>\r\n"
//		   		+ "<AdrLine>"+"   sssssssssssssssssssssssssssssssssss   "+"</AdrLine>\r\n"
//		   		+ "<AdrLine>"+"   sssssssssssssssssssssssssssssssssss   "+"</AdrLine>\r\n"
//		   		+ "</PstlAdr>\r\n"
//		   		+ "<Id>\r\n"
//		   		+ "<OrgId>\r\n"
//		   		+ "<LEI>"+"   HB7FFAZ10OMZ8PP8OE26   "+"</LEI>\r\n"
//		   		+ "</OrgId>\r\n"
//		   		+ "</Id>\r\n"
//		   		+ "</Dbtr>\r\n";

		String sendingCustomerAccountNumber = map.get("F60211");

		String dbtrAcct = "<DbtrAcct>\r\n<Id>\r\n<Othr>\r\n<Id>" + sendingCustomerAccountNumber
				+ "</Id>\r\n</Othr>\r\n</Id>\r\n";

		String senderAccountType = map.get("F63051");

		StringBuilder dbtrAcctTp = new StringBuilder();

		if (senderAccountType != null && !senderAccountType.isEmpty()) {
			dbtrAcctTp.append("<Tp>\r\n<Cd>").append(senderAccountType).append("<Cd>\r\n</Tp>\r\n");
		}

		dbtrAcctTp.append("</DbtrAcct>\r\n");

		String dbtrAgt = "<DbtrAgt>\r\n<FinInstnId>\r\n<ClrSysMmbId>\r\n<MmbId>" + sendingBranchIFSC
				+ "</MmbId>\r\n</ClrSysMmbId>\r\n</FinInstnId>\r\n</DbtrAgt>\r\n";

		String beneficiaryBranchIFSC = map.get("F55691");

		String cdtrAgt = "<CdtrAgt>\r\n<FinInstnId>\r\n<ClrSysMmbId>\r\n<MmbId>" + beneficiaryBranchIFSC
				+ "</MmbId>\r\n</ClrSysMmbId>\r\n</FinInstnId>\r\n</CdtrAgt>\r\n";

		String beneficiaryCustomerAccountName = map.get("F60811");

		String cdtr = "<Cdtr>\r\n<Nm>" + beneficiaryCustomerAccountName + "</Nm>\r\n";

		String recieverAdrLine1 = map.get("F55651");
		String recieverAdrLine2 = map.get("F55652");
		String recieverAdrLine3 = map.get("F55653");
		String recieverAdrLine4 = map.get("F55654");

		StringBuilder creditorAddress = new StringBuilder();

		if (recieverAdrLine1 != null && !recieverAdrLine1.isEmpty()) {
			creditorAddress.append("<PstlAdr>\r\n<AdrLine>").append(recieverAdrLine1).append("</AdrLine>\r\n");
			if (recieverAdrLine2 != null && !recieverAdrLine2.isEmpty()) {
				creditorAddress.append("<AdrLine>").append(recieverAdrLine2).append("</AdrLine>\r\n");
			}
			if (recieverAdrLine3 != null && !recieverAdrLine3.isEmpty()) {
				creditorAddress.append("<AdrLine>").append(recieverAdrLine3).append("</AdrLine>\r\n");
			}
			if (recieverAdrLine4 != null && !recieverAdrLine4.isEmpty()) {
				creditorAddress.append("<AdrLine>").append(recieverAdrLine4).append("</AdrLine>\r\n</PstlAdr>\r\n");
			}
			creditorAddress.append("</PstlAdr>\r\n");
		}

		creditorAddress.append("</Cdtr>\r\n");

		String beneficiaryCustomerAccountNumber = map.get("F60611");

		String cdtrAcct = "<CdtrAcct>\r\n<Id>\r\n<Othr>\r\n<Id>" + beneficiaryCustomerAccountNumber
				+ "</Id>\r\n</Othr>\r\n</Id>\r\n";

		String recieverAccountType = map.get("F63101");

		StringBuilder cdtrAcctTp = new StringBuilder();

		if (recieverAccountType != null && !recieverAccountType.isEmpty()) {
			cdtrAcctTp.append("<Tp>\r\n<Cd>").append(recieverAccountType).append("<Cd>\r\n</Tp>\r\n");
		}

		cdtrAcctTp.append("</CdtrAcct>\r\n");

		String instrForCdtrAgt = "<InstrForCdtrAgt>\r\n<InstrInf>" + beneficiaryCustomerAccountName
				+ "</InstrInf>\r\n</InstrForCdtrAgt>\r\n";

//		String senderToReceiverInformation1 = map.get("F74951");
		String senderToReceiverInformation2 = map.get("F74952");
//		System.out.println("------------------------------------");
//		System.out.println(senderToReceiverInformation2);
//		System.out.println("------------------------------------");
		String senderToReceiverInformation3 = map.get("F74953");
		String senderToReceiverInformation4 = map.get("F74954");
		String senderToReceiverInformation5 = map.get("F74955");
		String senderToReceiverInformation6 = map.get("F74956");
		String batchTime = map.get("F35351");

		StringBuilder rmtInf = new StringBuilder();

		if (batchTime != null && !batchTime.isEmpty()) {
			rmtInf.append("<RmtInf>\r\n<Ustrd>").append("BatchId:").append(batchTime).append("</Ustrd>\r\n");

			if (senderToReceiverInformation2 != null && !senderToReceiverInformation2.isEmpty()) {
				rmtInf.append("<Ustrd>").append(senderToReceiverInformation2).append("<Ustrd>\r\n");

				if (senderToReceiverInformation3 != null && !senderToReceiverInformation3.isEmpty()) {
					rmtInf.append("<Ustrd>").append(senderToReceiverInformation3).append("<Ustrd>\r\n");

					if (senderToReceiverInformation4 != null && !senderToReceiverInformation4.isEmpty()) {
						rmtInf.append("<Ustrd>").append(senderToReceiverInformation4).append("</Ustrd>\r\n");

						if (senderToReceiverInformation5 != null && !senderToReceiverInformation5.isEmpty()) {
							rmtInf.append("<Ustrd>").append(senderToReceiverInformation5).append("</Ustrd>\r\n");

							if (senderToReceiverInformation6 != null && !senderToReceiverInformation6.isEmpty()) {
								rmtInf.append("<Ustrd>").append(senderToReceiverInformation6).append("</Ustrd>\r\n");
							}
						}
					}
				}
			}
			rmtInf.append("</RmtInf>\r\n");

		}
//		if (senderToReceiverInformation1 != null && !senderToReceiverInformation1.isEmpty()) {
//			rmtInf.append("<Ustrd>").append(senderToReceiverInformation1).append("<Ustrd>\r\n");
//		}

//		rmtInf.append("</CdtTrfTxInf>\r\n</FIToFICstmrCdtTrf>\r\n</Document>\r\n</RequestPayload>\r\n");
		rmtInf.append(
				"</UndrlygCstmrCdtTrf>\r\n</OrgnlTxRef>\r\n</TxInf>\r\n</PmtRtr>\r\n</Document>\r\n</RequestPayload>\r\n");

		String resultString = requestPayloadStart + appHdr + fr + to + bizMsgIdr + msgDefIdr + bizSvc + creDt + document
				+ msgId + creDtTm + nbOfTxs + ttlRtrdIntrBkSttlmAmt + intrBkSttlmDt + sttlmInf + instgAgt1 + instdAgt2
				+ rtrId + orgnlEndToEndId + orgnlTxId + rtrdIntrBkSttlmAmt + rtrRsnInf + dbtr + dbtrId + dbtrAcct
				+ dbtrAcctTp + dbtrAgt + cdtrAgt + cdtr + creditorAddress + cdtrAcct + cdtrAcctTp + instrForCdtrAgt
				+ rmtInf;

//	   System.out.println(resultString);
	}

	public static void generateN10Xml(HashMap<String, String> map) {

		map.forEach((key, value) -> System.out.println(key + " --> " + value));

		String start = "<RequestPayload>\r\n"
				+ "<AppHdr xmlns=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.02\"   xmlns:xsi=\"urn:iso:std:iso:20022:tech:xsd:Header\" xmlns:sig=\"http://www.w3.org/2000/09/xmldsig#\">\r\n";

		String fr = "<Fr>\r\n" + "<FIId>\r\n" + "<FinInstnId>\r\n" + "<ClrSysMmbId>\r\n" + "<MmbId> "
				+ "   ICIC0000005 file  " + "</MmbId>\r\n" + "</ClrSysMmbId>\r\n" + "</FinInstnId>\r\n" + "</FIId>\r\n"
				+ "</Fr>\r\n";

		String to = "<To>\r\n" + "<FIId>\r\n" + "<FinInstnId>\r\n" + "<ClrSysMmbId>\r\n" + "<MmbId>" + "RBIP0NEFTSC"
				+ "</MmbId>\r\n" + "</ClrSysMmbId>\r\n" + "</FIId>\r\n" + "</To>\r\n";

		String transactionReferenceNumber1 = map.get("F20201");
		String transactionReferenceNumber2 = map.get("F20202");

		String sendingBranchIFSC = map.get("F55181");
		String stringDate = map.get("F35011");
		String formattedDateIso = null;
		String formattedDate = null;
		try {
//			System.out.println(stringDate);
			SimpleDateFormat originalFormat = new SimpleDateFormat("yyyymmdd");
			Date date = originalFormat.parse(stringDate);
			SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-mm-dd");
			formattedDate = newFormat.format(date);
//			System.out.println("formattedDate  ++++ "+formattedDate);

			SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss'Z'");
			isoFormat.setTimeZone(TimeZone.getTimeZone("IST"));
			formattedDateIso = isoFormat.format(date);
//			System.out.println(formattedDateIso);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String bizMsgIdrValue = sendingBranchIFSC.substring(0, 4) + stringDate
				+ transactionReferenceNumber2.substring(transactionReferenceNumber2.length() - 10);

		String bizMsgIdr = "<BizMsgIdr>" + bizMsgIdrValue + "</BizMsgIdr>\r\n";

		String msgDefIdr = "<MsgDefIdr>" + "camt.059.001.06" + "</MsgDefIdr>\r\n";

		String bizSvc = "<BizSvc>" + "NEFTCustomerCreditNotification" + "</BizSvc>\r\n";

		String creDt = "<CreDt>" + formattedDateIso + "</CreDt>\r\n</AppHdr>\r\n";

		String grpHdr = "<Document xmlns=\"urn:iso:std:iso:20022:tech:xsd:camt.059.001.06\">\r\n<NtfctnToRcvStsRpt>\r\n<GrpHdr>\r\n";

		String msgId = "<MsgId>" + bizMsgIdrValue + "</MsgId>\r\n";

		String creDtTmval = formattedDateIso.substring(0, formattedDateIso.length() - 1);

		String creDtTm = "<CreDtTm>" + creDtTmval + "</CreDtTm>\r\n</GrpHdr>\r\n";

		String dbtrAgtVal = map.get("F55181");

		String dbtrAgt = "<OrgnlNtfctnAndSts>\r\n" + "<OrgnlNtfctnRef>\r\n" + "<DbtrAgt>\r\n" + "<FinInstnId>\r\n"
				+ "<ClrSysMmbId>\r\n" + "<MmbId>" + dbtrAgtVal + "</MmbId>\r\n" + "</ClrSysMmbId>\r\n"
				+ "</FinInstnId>\r\n" + "</DbtrAgt>\r\n";

		String orgnlItmIdVal = sendingBranchIFSC.substring(0, 4) + "N5" + stringDate
				+ transactionReferenceNumber2.substring(transactionReferenceNumber2.length() - 8);

		String orgnlItmId = "<OrgnlItmAndSts>\r\n" + "<OrgnlItmId>" + orgnlItmIdVal + "</OrgnlItmId>\r\n";

		String orgnlEndToEndId = "<OrgnlEndToEndId>/XUTR/" + transactionReferenceNumber2 + "</OrgnlEndToEndId>\r\n";

//	  String amount = map.get("amount");
		String amt = "<Amt Ccy=\"INR\">" + "   100.00   " + "</Amt>\r\n";

		String xpctdValDt = "<XpctdValDt>" + formattedDate + "</XpctdValDt>\r\n";

		String itmSts = "<ItmSts>" + "RCVD"
				+ "</ItmSts>\r\n</OrgnlItmAndSts>\r\n</OrgnlNtfctnRef>\r\n</OrgnlNtfctnAndSts>\r\n</NtfctnToRcvStsRpt>\r\n</Document>\r\n</RequestPayload>";

		String finalString = start + fr + to + bizMsgIdr + msgDefIdr + bizSvc + creDt + grpHdr + msgId + creDtTm
				+ dbtrAgt + orgnlItmId + orgnlEndToEndId + amt + xpctdValDt + itmSts;

		System.out.println(finalString);

	}
}
