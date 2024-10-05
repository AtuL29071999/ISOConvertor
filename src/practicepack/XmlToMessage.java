package practicepack;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import isoconvetercontroller.PacsN04IncommingController;

public class XmlToMessage {

	public static void main(String[] args) {
		String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<RequestPayload>\r\n"
				+ "<AppHdr xmlns=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.02\" xmlns:sig=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:xsi=\"urn:iso:std:iso:20022:tech:xsd:Header\">\r\n"
				+ "    <Fr>\r\n"
				+ "        <FIId>\r\n"
				+ "            <FinInstnId>\r\n"
				+ "                <ClrSysMmbId>\r\n"
				+ "                    <MmbId>RBIP0NEFTSC</MmbId>\r\n"
				+ "                </ClrSysMmbId>\r\n"
				+ "            </FinInstnId>\r\n"
				+ "        </FIId>\r\n"
				+ "    </Fr>\r\n"
				+ "    <To>\r\n"
				+ "        <FIId>\r\n"
				+ "            <FinInstnId>\r\n"
				+ "                <ClrSysMmbId>\r\n"
				+ "                    <MmbId>HDFC0000001</MmbId>\r\n"
				+ "                </ClrSysMmbId>\r\n"
				+ "            </FinInstnId>\r\n"
				+ "        </FIId>\r\n"
				+ "    </To>\r\n"
				+ "    <BizMsgIdr>RBIP202101146147295848</BizMsgIdr>\r\n"
				+ "    <MsgDefIdr>pacs.004.001.10</MsgDefIdr>\r\n"
				+ "    <BizSvc>NEFTPaymentReturn</BizSvc>\r\n"
				+ "    <CreDt>2021-01-14T16:01:00Z</CreDt>\r\n"
				+ "</AppHdr>\r\n"
				+ "<Document xmlns=\"urn:iso:std:iso:20022:tech:xsd:pacs.004.001.10\">\r\n"
				+ "    <PmtRtr>\r\n"
				+ "        <GrpHdr>\r\n"
				+ "            <MsgId>RBIP202101146147295848</MsgId>\r\n"
				+ "            <CreDtTm>2021-01-14T16:01:00</CreDtTm>\r\n"
				+ "            <NbOfTxs>10</NbOfTxs>\r\n"
				+ "            <TtlRtrdIntrBkSttlmAmt Ccy=\"INR\">1000.00</TtlRtrdIntrBkSttlmAmt>\r\n"
				+ "            <IntrBkSttlmDt>2021-04-20</IntrBkSttlmDt>\r\n"
				+ "            <SttlmInf>\r\n"
				+ "                <SttlmMtd>CLRG</SttlmMtd>\r\n"
				+ "            </SttlmInf>\r\n"
				+ "			<InstgAgt>\r\n"
				+ "                <FinInstnId>\r\n"
				+ "                    <ClrSysMmbId>\r\n"
				+ "                        <MmbId>RBIP0NEFTSC</MmbId>\r\n"
				+ "                    </ClrSysMmbId>\r\n"
				+ "                </FinInstnId>\r\n"
				+ "            </InstgAgt>\r\n"
				+ "            <InstdAgt>\r\n"
				+ "                <FinInstnId>\r\n"
				+ "                    <ClrSysMmbId>\r\n"
				+ "                        <MmbId>HDFC0000001</MmbId>\r\n"
				+ "                    </ClrSysMmbId>\r\n"
				+ "                </FinInstnId>\r\n"
				+ "            </InstdAgt>\r\n"
				+ "        </GrpHdr>\r\n"
				+ "        <TxInf>\r\n"
				+ "            <RtrId>ICICN52013110104801831</RtrId> //2020\r\n"
				+ "            <OrgnlEndToEndId>/XUTR/HDFCH22194004232</OrgnlEndToEndId>\r\n"
				+ "			<OrgnlTxId>HDFCN52022062824954013</OrgnlTxId> // 2006\r\n"
				+ "            <RtrdIntrBkSttlmAmt Ccy=\"INR\">100.00</RtrdIntrBkSttlmAmt>    \r\n"
				+ "            <RtrRsnInf>\r\n"
				+ "                <Rsn>\r\n"
				+ "                    <Cd>AC01</Cd>\r\n"
				+ "                </Rsn>\r\n"
				+ "            </RtrRsnInf>\r\n"
				+ "            <OrgnlTxRef>\r\n"
				+ "                <UndrlygCstmrCdtTrf>\r\n"
				+ "                    <Dbtr>\r\n"
				+ "                        <Nm>praveen</Nm>\r\n"
				+ "						<PstlAdr>\r\n"
				+ "							<AdrLine>IFTAS,HYDERABAD</AdrLine>\r\n"
				+ "							<AdrLine>sssssssssssssssssssssssssssssssssss</AdrLine>\r\n"
				+ "							<AdrLine>sssssssssssssssssssssssssssssssssss</AdrLine>\r\n"
				+ "						</PstlAdr>\r\n"
				+ "						<Id>\r\n"
				+ "							<OrgId>\r\n"
				+ "								<LEI>HB7FFAZ10OMZ8PP8OE26</LEI>\r\n"
				+ "							</OrgId>\r\n"
				+ "						</Id>\r\n"
				+ "                    </Dbtr>\r\n"
				+ "                    <DbtrAcct>\r\n"
				+ "                        <Id>\r\n"
				+ "                            <Othr>\r\n"
				+ "								<Id>12445345</Id>\r\n"
				+ "                            </Othr>\r\n"
				+ "                        </Id>\r\n"
				+ "						 <Tp>\r\n"
				+ "							<Cd>10</Cd>\r\n"
				+ "						</Tp>\r\n"
				+ "                    </DbtrAcct>\r\n"
				+ "                    <DbtrAgt>\r\n"
				+ "                        <FinInstnId>\r\n"
				+ "                            <ClrSysMmbId>\r\n"
				+ "								<MmbId>HDFC0065012</MmbId>\r\n"
				+ "                            </ClrSysMmbId>\r\n"
				+ "                        </FinInstnId>\r\n"
				+ "                    </DbtrAgt>\r\n"
				+ "                    <CdtrAgt>\r\n"
				+ "                        <FinInstnId>\r\n"
				+ "                            <ClrSysMmbId>\r\n"
				+ "								<MmbId>ICIC0000002</MmbId>\r\n"
				+ "                            </ClrSysMmbId>\r\n"
				+ "                        </FinInstnId>\r\n"
				+ "                    </CdtrAgt>\r\n"
				+ "                    <Cdtr>\r\n"
				+ "                        <Nm>maniwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww</Nm>\r\n"
				+ "						<PstlAdr>\r\n"
				+ "							<AdrLine>IFTAS,HYDERABAD</AdrLine>\r\n"
				+ "							<AdrLine>sssssssssssssssssssssssssssssssssss</AdrLine>\r\n"
				+ "							<AdrLine>sssssssssssssssssssssssssssssssssss</AdrLine>\r\n"
				+ "						</PstlAdr>\r\n"
				+ "						<Id>\r\n"
				+ "							<OrgId>\r\n"
				+ "								<LEI>/BL/HB7FFAZ10OMZ8PP8OE26/</LEI>\r\n"
				+ "							</OrgId>\r\n"
				+ "						</Id>\r\n"
				+ "\r\n"
				+ "                    </Cdtr>\r\n"
				+ "                    <CdtrAcct>\r\n"
				+ "                        <Id>\r\n"
				+ "                            <Othr>\r\n"
				+ "								<Id>333333</Id>\r\n"
				+ "                            </Othr>\r\n"
				+ "                        </Id>\r\n"
				+ "						<Tp>\r\n"
				+ "							<Cd>10</Cd>\r\n"
				+ "						</Tp>\r\n"
				+ "                    </CdtrAcct>\r\n"
				+ "                    <InstrForCdtrAgt>\r\n"
				+ "                        <InstrInf>Credit to tcssssssssssssssssssssss\r\n"
				+ "ssssssssssssssssssssssssssssssssss\r\n"
				+ "ssssssssssssssssssssssssssssssssss\r\n"
				+ "ssssssssssssssssssssssssssssssssss</InstrInf>\r\n"
				+ "                    </InstrForCdtrAgt>\r\n"
				+ "					<RmtInf>\r\n"
				+ "						<Ustrd>BatchId:0002</Ustrd>\r\n"
				+ "						<Ustrd>sdfffffffffffffffffffffffffffffffff</Ustrd>\r\n"
				+ "						<Ustrd>sdfffffffffffffffffffffffffffffffff</Ustrd>\r\n"
				+ "						<Ustrd>sdfffffffffffffffffffffffffffffffff</Ustrd>\r\n"
				+ "					</RmtInf>\r\n"
				+ "                </UndrlygCstmrCdtTrf>\r\n"
				+ "            </OrgnlTxRef>\r\n"
				+ "        </TxInf>\r\n"
				+ "    </PmtRtr>\r\n"
				+ "</Document>\r\n"
				+ "</RequestPayload>";
		XmlToMessage example = new XmlToMessage();

//        example.pacsN02Incomming(xmlData);
        example.pacsN04Incomming(xmlData);
//        example.pacsN10Incomming(xmlData);

//		example.camt052XML(xmlData);
	}

	String[] CdtrAdrLine = new String[4];

	public void CdtrextractAddressLines(String cdtrSection) {
		int adrStart = 0;
		int adrEnd = 0;
		int lineCount = 0;

		while ((adrStart = cdtrSection.indexOf("<AdrLine>", adrEnd)) != -1) {
			adrEnd = cdtrSection.indexOf("</AdrLine>", adrStart);
			if (adrEnd != -1) {
				String addressLine = cdtrSection.substring(adrStart + "<AdrLine>".length(), adrEnd);
//                System.out.println("Address Line " + lineCount + ": " + addressLine);
				CdtrAdrLine[lineCount] = addressLine;
//                System.out.println( "cdtr : "+CdtrAdrLine[lineCount]);
				lineCount++;
			}
		}
	}

	String[] DbtrAdrLine = new String[4];

	public void DbtrextractAddressLines(String cdtrSection) {
		int adrStart = 0;
		int adrEnd = 0;
		int lineCount = 0;

		while ((adrStart = cdtrSection.indexOf("<AdrLine>", adrEnd)) != -1) {
			adrEnd = cdtrSection.indexOf("</AdrLine>", adrStart);
			if (adrEnd != -1) {
				String addressLine = cdtrSection.substring(adrStart + "<AdrLine>".length(), adrEnd);
//                System.out.println("Address Line " + lineCount + ": " + addressLine);
				DbtrAdrLine[lineCount] = addressLine;
				lineCount++;
			}
		}
	}

	public void pacsN02Incomming(String xmlData) {
		String[] remitance = new String[4];
		Element element = null;
		try {
			// Parse the XML content
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(xmlData.getBytes());
			Document doc = builder.parse(is);

			// Normalize the document
			doc.getDocumentElement().normalize();

			// Fetch Ustrd elements
			NodeList ustrdList = doc.getElementsByTagName("Ustrd");
//            System.out.println("AdrLine "+AdrLine);

			// Print out the values
			for (int i = 0; i < ustrdList.getLength(); i++) {
				Node node = ustrdList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					element = (Element) node;

					remitance[i] = element.getTextContent();
//                    System.out.println(remitance[i]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		int start = xmlData.indexOf("<To>");
		int end = xmlData.indexOf("</To>");

		String recPart = xmlData.substring(start, end);
//    	System.out.println("recPart : "+recPart);
		// System.out.println(end);
		String recIFSC = recPart.substring(recPart.indexOf("<MmbId>") + 7, recPart.indexOf("</MmbId>"));
//    	System.out.println(recIFSC);

		start = xmlData.indexOf("<BizMsgIdr>");
		end = xmlData.indexOf("</BizMsgIdr>");
//    	System.out.println(start);
		String bizMsgIdr = xmlData.substring(start + 11, end);
//    	System.out.println(BizMsgIdr);

		start = xmlData.indexOf("<MsgDefIdr>");
		end = xmlData.indexOf("</MsgDefIdr>");
		String msgDefIdr = xmlData.substring(start + 11, end);
//    	System.out.println(MsgDefIdr);

		start = xmlData.indexOf("<BizSvc>");
		end = xmlData.indexOf("</BizSvc>");
		String bizSvc = xmlData.substring(start + 8, end);
//    	System.out.println(BizSvc);

		start = xmlData.indexOf("<CreDt>");
		end = xmlData.indexOf("</CreDt>");
		String creDt = xmlData.substring(start + 7, end);
//    	System.out.println(CreDt);

//    	MsgId
		start = xmlData.indexOf("<MsgId>");
		end = xmlData.indexOf("</MsgId>");
		String msgId = xmlData.substring(start + 7, end);
//    	System.out.println(MsgId);

//    	CreDtTm
		start = xmlData.indexOf("<CreDtTm>");
		end = xmlData.indexOf("</CreDtTm>");
		String creDtTm = xmlData.substring(start + 9, end);
		String credtTmHeaderTime = xmlData.substring(start + 20, end - 6);
		credtTmHeaderTime += xmlData.substring(start + 23, end - 3);
//    	System.out.println("credtTmHeaderTime "+credtTmHeaderTime);
//    	System.out.println(CreDtTm);

//    	NbOfTxs
		start = xmlData.indexOf("<NbOfTxs>");
		end = xmlData.indexOf("</NbOfTxs>");
		String nbOfTxs = xmlData.substring(start + 9, end);
//    	System.out.println(NbOfTxs);

//    	<TtlIntrBkSttlmAmt Ccy="INR">
		start = xmlData.indexOf("<TtlIntrBkSttlmAmt Ccy=\"INR\">");
		end = xmlData.indexOf("</TtlIntrBkSttlmAmt>");
		String ttlIntrBkSttlmAmt = xmlData.substring(start + 29, end);
//    	System.out.println(TtlIntrBkSttlmAmt);

//    	IntrBkSttlmDt
		start = xmlData.indexOf("<IntrBkSttlmDt>");
		end = xmlData.indexOf("</IntrBkSttlmDt>");
		String intrBkSttlmDt = xmlData.substring(start + 15, end);
		String finaldate = intrBkSttlmDt.substring(0, 4);
		finaldate += intrBkSttlmDt.substring(5, 7);
		finaldate += intrBkSttlmDt.substring(8);
//    	System.out.println("final "+finaldate);
//    	System.out.println(IntrBkSttlmDt);

//    	SttlmMtd
		start = xmlData.indexOf("<SttlmMtd>");
		end = xmlData.indexOf("</SttlmMtd>");
		String sttlmMtd = xmlData.substring(start + 10, end);
//    	System.out.println(SttlmMtd);

//    	InstgAgt
		start = xmlData.indexOf("<InstgAgt>");
		end = xmlData.indexOf("</InstgAgt>");
		String instgAgt1 = xmlData.substring(start + 10, end);
//    	System.out.println(InstgAgt);
		String InstgAgtValue = instgAgt1.substring(instgAgt1.indexOf("<MmbId>") + 7, instgAgt1.indexOf("</MmbId>"));
//    	System.out.println(InstgAgtValue);

//    	EndToEndId
		start = xmlData.indexOf("<EndToEndId>");
		end = xmlData.indexOf("</EndToEndId>");
		String endToEndId = xmlData.substring(start + 12, end);
//    	System.out.println(EndToEndId);

//    	TxId
		start = xmlData.indexOf("<TxId>");
		end = xmlData.indexOf("</TxId>");
		String txId = xmlData.substring(start + 6, end);
//    	System.out.println(TxId.length());
		String value2020 = txId.substring(0, 6);
//    	System.out.println("val  "+value2020);
		value2020 = value2020 + txId.substring(txId.length() - 8);
//    	System.out.println("value2020   "+value2020);

		String value2020Header = value2020.substring(value2020.length() - 9);
//    	System.out.println("value2020Header   "+value2020Header);

//    	InstrPrty
		start = xmlData.indexOf("<IntrBkSttlmAmt Ccy=\"INR\">");
		end = xmlData.indexOf("</IntrBkSttlmAmt>");
		String intrBkSttlmAmt = xmlData.substring(start + 26, end);
//    	System.out.println(IntrBkSttlmAmt);

//    	Dbtr
		start = xmlData.indexOf("<Dbtr>");
		end = xmlData.indexOf("</Dbtr>");
		String dbtr = xmlData.substring(start + 6, end);
//    	System.out.println(InstgAgt);
		String dbtrName = dbtr.substring(dbtr.indexOf("<Nm>") + 4, dbtr.indexOf("</Nm>"));
//    	System.out.println(DbtrValue);
		String dbtrLEI = dbtr.substring(dbtr.indexOf("<LEI>") + 5, dbtr.indexOf("</LEI>"));
//    	System.out.println(DbtrLEI);

//    	DbtrAcct
		start = xmlData.indexOf("<DbtrAcct>");
		end = xmlData.indexOf("</DbtrAcct>");
		String dbtrAcct = xmlData.substring(start + 10, end);
//    	System.out.println(DbtrAcct);
		String othr = dbtrAcct.substring(dbtrAcct.indexOf("<Othr>") + 6, dbtrAcct.indexOf("</Othr>"));
//    	System.out.println(Othr);
		String debitorAccountNumber = othr.substring(othr.indexOf("<Id>") + 4, othr.indexOf("</Id>"));
//    	System.out.println(IdValue);
		String cd = dbtrAcct.substring(dbtrAcct.indexOf("<Cd>") + 4, dbtrAcct.indexOf("</Cd>"));
//    	System.out.println(Cd);

//    	DbtrAgt
		start = xmlData.indexOf("<DbtrAgt>");
		end = xmlData.indexOf("</DbtrAgt>");
		String DbtrAgt = xmlData.substring(start + 9, end);
//    	System.out.println(InstgAgt);
		String DbtrAgtValue = DbtrAgt.substring(DbtrAgt.indexOf("<MmbId>") + 7, DbtrAgt.indexOf("</MmbId>"));
//    	System.out.println(DbtrAgtValue);

//    	CdtrAgt
		start = xmlData.indexOf("<CdtrAgt>");
		end = xmlData.indexOf("</CdtrAgt>");
		String CdtrAgt = xmlData.substring(start + 9, end);
//    	System.out.println(InstgAgt);
		String CreditarIFSC = CdtrAgt.substring(CdtrAgt.indexOf("<MmbId>") + 7, CdtrAgt.indexOf("</MmbId>"));
//    	System.out.println(CdtrAgtValue);

//    	Cdtr
		start = xmlData.indexOf("<Cdtr>");
		end = xmlData.indexOf("</Cdtr>");
		String Cdtr = xmlData.substring(start + 6, end);
		String CdtrName = Cdtr.substring(Cdtr.indexOf("<Nm>") + 4, Cdtr.indexOf("</Nm>"));

		if (start != -1 && end != -1) {
			String cdtrSection = xmlData.substring(start, end + "</Cdtr>".length());

			// Extract individual address lines
			CdtrextractAddressLines(cdtrSection);
		}

//    	System.out.println(PstlAdr);
		String CdtrLEI = Cdtr.substring(Cdtr.indexOf("<LEI>") + 5, Cdtr.indexOf("</LEI>"));
//    	System.out.println(CdtrLEI);

//    	CdtrAcct
		start = xmlData.indexOf("<CdtrAcct>");
		end = xmlData.indexOf("</CdtrAcct>");
		String CdtrAcct = xmlData.substring(start + 10, end);
//    	System.out.println(DbtrAcct);
		String CdtrAcctOthr = CdtrAcct.substring(CdtrAcct.indexOf("<Othr>") + 6, CdtrAcct.indexOf("</Othr>"));
//    	System.out.println(CdtrAcctOthr);
		String CreditarAccountNumber = CdtrAcctOthr.substring(CdtrAcctOthr.indexOf("<Id>") + 4,
				CdtrAcctOthr.indexOf("</Id>"));
//    	System.out.println(CdtrAcctIdValue);
		String CdtrAcctCd = CdtrAcct.substring(CdtrAcct.indexOf("<Cd>") + 4, CdtrAcct.indexOf("</Cd>"));
//    	System.out.println(CdtrAcctCd);

//    	InstrInf
		start = xmlData.indexOf("<InstrInf>");
		end = xmlData.indexOf("</InstrInf>");
		String InstrInf = xmlData.substring(start + 10, end);
//    	System.out.println(InstrInf);

//    	RmtInf
		start = xmlData.indexOf("<RmtInf>");
		end = xmlData.indexOf("</RmtInf>");
		String RmtInf = xmlData.substring(start + 8, end);
//    	System.out.println(RmtInf);
		String BatchId = RmtInf.substring(RmtInf.indexOf("<Ustrd>") + 7, RmtInf.indexOf("</Ustrd>"));
//    	System.out.println(BatchId); 

		StringBuilder builder = new StringBuilder();
		builder.append("{A:XXXF01I298N02RBIP0NEFTSC").append(CreditarIFSC).append("222002XXXXXXXXXXXXXXXX2EFT")
				.append(finaldate).append(credtTmHeaderTime).append("2").append(value2020Header)
				.append("XXXXXXXXXXXXXXXXXXXXXXXXX99}{4:").append("\r\n");
		builder.append(":2020:").append(value2020).append("\r\n");
		builder.append(":3535:").append(remitance[0].substring(remitance[0].length() - 4)).append("\r\n");
		builder.append(":5180:").append(nbOfTxs).append("\r\n");
		builder.append(":4110:").append(ttlIntrBkSttlmAmt.replace('.', ',')).append("\r\n");
		builder.append(":2020:").append(value2020).append("\r\n");
		builder.append(":5756:").append(DbtrAgtValue).append("\r\n");
		builder.append(":6305:").append(cd).append("\r\n");
		builder.append(":6021:").append(debitorAccountNumber).append("\r\n");
		builder.append(":6091:").append(dbtrName).append("\r\n");
		builder.append(":7002:").append(debitorAccountNumber).append("\r\n");
		builder.append(dbtrName).append("\r\n");
		builder.append(":5569:").append(CreditarIFSC).append("\r\n");
		if (CdtrAcctCd != null && !CdtrAcctCd.isEmpty()) {
			builder.append(":6310:").append(CdtrAcctCd).append("\r\n");
		}

		builder.append(":6061:").append(CreditarAccountNumber).append("\r\n");
		builder.append(":6081:").append(CdtrName).append("\r\n");

//    	System.out.println("CdtrAdrLine.length "+CdtrAdrLine.length);
		if (CdtrAdrLine.length > 0) {
			builder.append(":5565:").append(CdtrAdrLine[0]).append("\r\n");
			for (int i = 1; i < CdtrAdrLine.length - 1; i++) {
				builder.append(CdtrAdrLine[i]).append("\r\n");
			}
		}

		if (dbtrLEI != null && !dbtrLEI.isEmpty()) {
			builder.append(":7495:").append(dbtrLEI).append("\r\n");
		}

		builder.append(":4038:").append(intrBkSttlmAmt.replace('.', ',')).append("\r\n");
		builder.append(":3380:").append(finaldate).append("\r\n");
		builder.append(":3375:").append(finaldate).append("\r\n");
		builder.append("-}");
		System.out.println(builder);
	}

//    Pacs004_incomming.xml
	public void pacsN04Incomming(String xmlData) {

		int start = xmlData.indexOf("<To>");
		int end = xmlData.indexOf("</To>");

		String recPart = xmlData.substring(start, end);
//    	System.out.println("recPart : "+recPart);
		// System.out.println(end);
		String recIFSC = recPart.substring(recPart.indexOf("<MmbId>") + 7, recPart.indexOf("</MmbId>"));
//    	System.out.println(recIFSC);

		start = xmlData.indexOf("<BizMsgIdr>");
		end = xmlData.indexOf("</BizMsgIdr>");
//    	System.out.println(start);
		String BizMsgIdr = xmlData.substring(start + 11, end);
//    	System.out.println(BizMsgIdr);

		start = xmlData.indexOf("<MsgDefIdr>");
		end = xmlData.indexOf("</MsgDefIdr>");
		String MsgDefIdr = xmlData.substring(start + 11, end);
//    	System.out.println(MsgDefIdr);
//    	System.out.println("--------------");

		start = xmlData.indexOf("<BizSvc>");
		end = xmlData.indexOf("</BizSvc>");
		String BizSvc = xmlData.substring(start + 8, end);
//    	System.out.println(BizSvc);

		start = xmlData.indexOf("<CreDt>");
		end = xmlData.indexOf("</CreDt>");
		String CreDt = xmlData.substring(start + 7, end);
//    	System.out.println(CreDt);

//    	MsgId
		start = xmlData.indexOf("<MsgId>");
		end = xmlData.indexOf("</MsgId>");
		String MsgId = xmlData.substring(start + 7, end);
//    	System.out.println(MsgId);

//    	CreDtTm
		start = xmlData.indexOf("<CreDtTm>");
		end = xmlData.indexOf("</CreDtTm>");
		String CreDtTm = xmlData.substring(start + 9, end);
		String credtTmHeaderTime = xmlData.substring(start + 20, end - 6);
		credtTmHeaderTime += xmlData.substring(start + 23, end - 3);
//    	System.out.println("credtTmHeaderTime "+credtTmHeaderTime);
//    	System.out.println(CreDtTm);

//    	NbOfTxs
		start = xmlData.indexOf("<NbOfTxs>");
		end = xmlData.indexOf("</NbOfTxs>");
		String NbOfTxs = xmlData.substring(start + 9, end);
//    	System.out.println(NbOfTxs);

//    	<TtlIntrBkSttlmAmt Ccy="INR">
		start = xmlData.indexOf("<TtlRtrdIntrBkSttlmAmt Ccy=\"INR\">");
		end = xmlData.indexOf("</TtlRtrdIntrBkSttlmAmt>");
		String TtlRtrdIntrBkSttlmAmt = xmlData.substring(start + 33, end);
//    	System.out.println(TtlRtrdIntrBkSttlmAmt);
//    	System.out.println("--------------");

//    	IntrBkSttlmDt
		start = xmlData.indexOf("<IntrBkSttlmDt>");
		end = xmlData.indexOf("</IntrBkSttlmDt>");
		String IntrBkSttlmDt = xmlData.substring(start + 15, end);
		String finaldate = IntrBkSttlmDt.substring(0, 4);
		finaldate += IntrBkSttlmDt.substring(5, 7);
		finaldate += IntrBkSttlmDt.substring(8);
//    	System.out.println("final "+finaldate);
//    	System.out.println(IntrBkSttlmDt);

//    	SttlmMtd
		start = xmlData.indexOf("<SttlmMtd>");
		end = xmlData.indexOf("</SttlmMtd>");
		String SttlmMtd = xmlData.substring(start + 10, end);
//    	System.out.println(SttlmMtd);

//    	InstgAgt
		start = xmlData.indexOf("<InstgAgt>");
		end = xmlData.indexOf("</InstgAgt>");
		String InstgAgt1 = xmlData.substring(start + 10, end);
//    	System.out.println(InstgAgt1);
		String InstgAgtValue = InstgAgt1.substring(InstgAgt1.indexOf("<MmbId>") + 7, InstgAgt1.indexOf("</MmbId>"));
//    	System.out.println(InstgAgtValue);

//    	RtrId
		start = xmlData.indexOf("<RtrId>");
		end = xmlData.indexOf("</RtrId>");
		String RtrId = xmlData.substring(start + 7, end);
		String value2020 = RtrId.substring(0, 6);
//    	System.out.println(value2020);
		value2020 = value2020 + RtrId.substring(RtrId.length() - 8);
//    	System.out.println(value2020);

//    	EndToEndId
		start = xmlData.indexOf("<OrgnlEndToEndId>");
		end = xmlData.indexOf("</OrgnlEndToEndId>");
		String OrgnlEndToEndId = xmlData.substring(start + 17, end);
//    	System.out.println(OrgnlEndToEndId);

//    	OrgnlTxId
		start = xmlData.indexOf("<OrgnlTxId>");
		end = xmlData.indexOf("</OrgnlTxId>");
		String OrgnlTxId = xmlData.substring(start + 11, end);
		System.out.println(OrgnlTxId);
		String value2006 = OrgnlTxId.substring(0, 6);
//    	System.out.println("val  "+value2020);
		value2006 = value2006 + OrgnlTxId.substring(OrgnlTxId.length() - 8);
//    	System.out.println("value2020   "+value2020);

//    	String value2020Header = value2006.substring(value2006.length()-9);
//    	System.out.println("value2020Header   "+value2020Header);

//    	InstrPrty
		start = xmlData.indexOf("<RtrdIntrBkSttlmAmt Ccy=\"INR\">");
		end = xmlData.indexOf("</RtrdIntrBkSttlmAmt>");
		String RtrdIntrBkSttlmAmt = xmlData.substring(start + 30, end);
//    	System.out.println(RtrdIntrBkSttlmAmt);

//    	Rsn
		start = xmlData.indexOf("<RtrRsnInf>");
		end = xmlData.indexOf("</RtrRsnInf>");
		String RtrRsnInf = xmlData.substring(start, end);
//    	System.out.println(RtrRsnInf);
		String RsnCd = RtrRsnInf.substring(RtrRsnInf.indexOf("<Cd>") + 4, RtrRsnInf.indexOf("</Cd>"));
//    	System.out.println(RsnCd);

//    	Dbtr
		start = xmlData.indexOf("<Dbtr>");
		end = xmlData.indexOf("</Dbtr>");
		String Dbtr = xmlData.substring(start + 6, end);
//    	System.out.println(InstgAgt);
		String DbtrName = Dbtr.substring(Dbtr.indexOf("<Nm>") + 4, Dbtr.indexOf("</Nm>"));
//    	System.out.println(DbtrName);
		String DbtrLEI = Dbtr.substring(Dbtr.indexOf("<LEI>") + 5, Dbtr.indexOf("</LEI>"));
//    	System.out.println(DbtrLEI);
		String DbtrPstlAdr = null;

//    	DbtrAcct
		start = xmlData.indexOf("<DbtrAcct>");
		end = xmlData.indexOf("</DbtrAcct>");
		String DbtrAcct = xmlData.substring(start + 10, end);
//    	System.out.println(DbtrAcct);
		String Othr = DbtrAcct.substring(DbtrAcct.indexOf("<Othr>") + 6, DbtrAcct.indexOf("</Othr>"));
//    	System.out.println(Othr);
		String DebitorAccountNumber = Othr.substring(Othr.indexOf("<Id>") + 4, Othr.indexOf("</Id>"));
//    	System.out.println(DebitorAccountNumber);
		String DebitorAccountType = DbtrAcct.substring(DbtrAcct.indexOf("<Cd>") + 4, DbtrAcct.indexOf("</Cd>"));
//    	System.out.println(DebitorAccountType);

//    	DbtrAgt
		start = xmlData.indexOf("<DbtrAgt>");
		end = xmlData.indexOf("</DbtrAgt>");
		String DbtrAgt = xmlData.substring(start + 9, end);
//    	System.out.println(InstgAgt);
		String DbtrAgtIFSC = DbtrAgt.substring(DbtrAgt.indexOf("<MmbId>") + 7, DbtrAgt.indexOf("</MmbId>"));
//    	System.out.println(DbtrAgtIFSC);

//    	CdtrAgt
		start = xmlData.indexOf("<CdtrAgt>");
		end = xmlData.indexOf("</CdtrAgt>");
		String CdtrAgt = xmlData.substring(start + 9, end);
//    	System.out.println(InstgAgt);
		String CreditarIFSC = CdtrAgt.substring(CdtrAgt.indexOf("<MmbId>") + 7, CdtrAgt.indexOf("</MmbId>"));
//    	System.out.println(CreditarIFSC);

//    	Cdtr
		start = xmlData.indexOf("<Cdtr>");
		end = xmlData.indexOf("</Cdtr>");
		String Cdtr = xmlData.substring(start + 6, end);
		String CdtrName = Cdtr.substring(Cdtr.indexOf("<Nm>") + 4, Cdtr.indexOf("</Nm>"));
//    	System.out.println(CdtrName);

		if (start != -1 && end != -1) {
			String cdtrSection = xmlData.substring(start, end + "</Cdtr>".length());

			// Extract individual address lines
			CdtrextractAddressLines(cdtrSection);
		}

//    	System.out.println(PstlAdr);
		String CdtrLEI = Cdtr.substring(Cdtr.indexOf("<LEI>") + 5, Cdtr.indexOf("</LEI>"));
//    	System.out.println(CdtrLEI);

//    	CdtrAcct
		start = xmlData.indexOf("<CdtrAcct>");
		end = xmlData.indexOf("</CdtrAcct>");
		String CdtrAcct = xmlData.substring(start + 10, end);
//    	System.out.println(DbtrAcct);
		String CdtrAcctOthr = CdtrAcct.substring(CdtrAcct.indexOf("<Othr>") + 6, CdtrAcct.indexOf("</Othr>"));
//    	System.out.println(CdtrAcctOthr);
		String CreditarAccountNumber = CdtrAcctOthr.substring(CdtrAcctOthr.indexOf("<Id>") + 4,
				CdtrAcctOthr.indexOf("</Id>"));
		System.out.println(CreditarAccountNumber);
		String CdtrAcctCd = CdtrAcct.substring(CdtrAcct.indexOf("<Cd>") + 4, CdtrAcct.indexOf("</Cd>"));
//    	System.out.println(CdtrAcctCd);

//    	InstrInf
		start = xmlData.indexOf("<InstrInf>");
		end = xmlData.indexOf("</InstrInf>");
		String InstrInf = xmlData.substring(start + 10, end);
//    	System.out.println(InstrInf);

//    	RmtInf
		start = xmlData.indexOf("<RmtInf>");
		end = xmlData.indexOf("</RmtInf>");
		String RmtInf = xmlData.substring(start + 8, end);
//    	System.out.println(RmtInf);
		String BatchId = RmtInf.substring(RmtInf.indexOf("<Ustrd>") + 7, RmtInf.indexOf("</Ustrd>"));
//    	System.out.println(BatchId); 

		String[] remitance = new String[5];
		Element element = null;
		try {
			// Parse the XML content
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(xmlData.getBytes());
			Document doc = builder.parse(is);

			// Normalize the document
			doc.getDocumentElement().normalize();

			// Fetch Ustrd elements
			NodeList ustrdList = doc.getElementsByTagName("Ustrd");
//            System.out.println("AdrLine "+AdrLine);

			// Print out the values
			for (int i = 0; i < ustrdList.getLength(); i++) {
				Node node = ustrdList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					element = (Element) node;

					remitance[i] = element.getTextContent();
//                    System.out.println(remitance[i]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		StringBuilder builder = new StringBuilder();
		builder.append("{A:XXXF01I298N02RBIP0NEFTSC").append(CreditarIFSC).append("222002XXXXXXXXXXXXXXXX2EFT")
				.append(finaldate).append(credtTmHeaderTime).append("2").append(value2020)
				.append("XXXXXXXXXXXXXXXXXXXXXXXXX99}{4:").append("\r\n");
		builder.append(":2020:").append(value2020).append("\r\n");
		builder.append(":3535:").append(remitance[0].substring(remitance[0].length() - 4)).append("\r\n");
		builder.append(":5180:").append(NbOfTxs).append("\r\n");
		builder.append(":4110:").append(TtlRtrdIntrBkSttlmAmt.replace('.', ',')).append("\r\n");
		builder.append(":2020:").append(value2020).append("\r\n");
		builder.append(":2006:").append(value2006).append("\r\n");
		builder.append(":5756:").append(DbtrAgtIFSC).append("\r\n");
		if (DebitorAccountType != null && !DebitorAccountType.isEmpty()) {
			builder.append(":6305:").append(DebitorAccountType).append("\r\n");
		}
		builder.append(":6021:").append(DebitorAccountNumber).append("\r\n");
		builder.append(":6091:").append(DbtrName).append("\r\n");
		builder.append(":7002:").append(DebitorAccountNumber).append("\r\n");
		builder.append(DbtrName).append("\r\n");
		builder.append(":5569:").append(CreditarIFSC).append("\r\n");
		if (CdtrAcctCd != null && !CdtrAcctCd.isEmpty()) {
			builder.append(":6310:").append(CdtrAcctCd).append("\r\n");
		}

		builder.append(":6061:").append(CreditarAccountNumber).append("\r\n");
		builder.append(":6081:").append(CdtrName).append("\r\n");

//    	System.out.println("CdtrAdrLine.length "+CdtrAdrLine.length);
		if (CdtrAdrLine.length > 0) {
			builder.append(":5565:").append(CdtrAdrLine[0]).append("\r\n");
			for (int i = 1; i < CdtrAdrLine.length - 1; i++) {
				builder.append(CdtrAdrLine[i]).append("\r\n");
			}
		}

		if (DbtrLEI != null && !DbtrLEI.isEmpty()) {
			builder.append(":7495:").append(DbtrLEI).append("\r\n");
		}

		if (RsnCd != null && !RsnCd.isEmpty()) {
			builder.append(":6346:").append(RsnCd).append("\r\n");
		}

//    	builder.append(":6366:").append("Account Does Not Exit!!!").append("\r\n");

		builder.append(":4038:").append(RtrdIntrBkSttlmAmt).append("\r\n");
		builder.append(":3380:").append(finaldate).append("\r\n");
		builder.append(":3375:").append(finaldate).append("\r\n");
		builder.append("-}");
		System.out.println(builder);
		

	}

	public void pacsN10Incomming(String xmlData) {

		int start = xmlData.indexOf("<To>");
		int end = xmlData.indexOf("</To>");

		String recPart = xmlData.substring(start, end);
//    	System.out.println("recPart : "+recPart);
		// System.out.println(end);
		String recIFSC = recPart.substring(recPart.indexOf("<MmbId>") + 7, recPart.indexOf("</MmbId>"));
//    	System.out.println(recIFSC);

		start = xmlData.indexOf("<BizMsgIdr>");
		end = xmlData.indexOf("</BizMsgIdr>");
//    	System.out.println(start);
		String BizMsgIdr = xmlData.substring(start + 11, end);
//    	System.out.println(BizMsgIdr);

		start = xmlData.indexOf("<MsgDefIdr>");
		end = xmlData.indexOf("</MsgDefIdr>");
		String MsgDefIdr = xmlData.substring(start + 11, end);
//    	System.out.println(MsgDefIdr);
//    	System.out.println("--------------");

		start = xmlData.indexOf("<BizSvc>");
		end = xmlData.indexOf("</BizSvc>");
		String BizSvc = xmlData.substring(start + 8, end);
//    	System.out.println(BizSvc);

		start = xmlData.indexOf("<CreDt>");
		end = xmlData.indexOf("</CreDt>");
		String CreDt = xmlData.substring(start + 7, end);
//    	System.out.println(CreDt);

//    	MsgId
		start = xmlData.indexOf("<MsgId>");
		end = xmlData.indexOf("</MsgId>");
		String MsgId = xmlData.substring(start + 7, end);
//    	System.out.println(MsgId);

//    	CreDtTm
		start = xmlData.indexOf("<CreDtTm>");
		end = xmlData.indexOf("</CreDtTm>");
		String CreDtTm = xmlData.substring(start + 9, end);
		String credtTmHeaderTime = xmlData.substring(start + 20, end - 6);
		credtTmHeaderTime += xmlData.substring(start + 23, end - 3);
//    	System.out.println("credtTmHeaderTime "+credtTmHeaderTime);
//    	System.out.println(CreDtTm);

//    	DbtrAgt
		start = xmlData.indexOf("<DbtrAgt>");
		end = xmlData.indexOf("</DbtrAgt>");
		String DbtrAgt = xmlData.substring(start + 9, end);
//    	System.out.println(InstgAgt);
		String DbtrAgtIFSC = DbtrAgt.substring(DbtrAgt.indexOf("<MmbId>") + 7, DbtrAgt.indexOf("</MmbId>"));
//    	System.out.println(DbtrAgtIFSC);

//    	OrgnlItmAndSts
		start = xmlData.indexOf("<OrgnlItmId>");
		end = xmlData.indexOf("</OrgnlItmId>");
		String OrgnlItmId = xmlData.substring(start + 12, end);
//    	System.out.println(OrgnlItmId);

//    	OrgnlEndToEndId
		start = xmlData.indexOf("<OrgnlEndToEndId>");
		end = xmlData.indexOf("</OrgnlEndToEndId>");
		String OrgnlEndToEndId = xmlData.substring(start + 23, end);

//    	System.out.println(OrgnlEndToEndId);

//    	Amt
		start = xmlData.indexOf("<Amt Ccy=\"INR\">");
		end = xmlData.indexOf("</Amt>");
		String Amt = xmlData.substring(start + 15, end);
//    	System.out.println(Amt);

//    	XpctdValDt
		start = xmlData.indexOf("<XpctdValDt>");
		end = xmlData.indexOf("</XpctdValDt>");
		String XpctdValDt = xmlData.substring(start + 12, end);
		String finaldate = XpctdValDt.substring(0, 4);
		finaldate += XpctdValDt.substring(5, 7);
		finaldate += XpctdValDt.substring(8);
//    	System.out.println("finaldate "+finaldate);

//    	ItmSts
		start = xmlData.indexOf("<ItmSts>");
		end = xmlData.indexOf("</ItmSts>");
		String ItmSts = xmlData.substring(start + 8, end);
//    	System.out.println(ItmSts);

		StringBuilder builder = new StringBuilder();
//    	{A:XXXF01I298N10RBIP0NEFTSC NICB0000001222002 RBIPMUR7622533712EFT 20190522 0149 2 001754676XXXXXXXXXXXXXXXXXXXXXXXXX99}{4:
		builder.append("{A:XXXF01I298N02RBIP0NEFTSC").append(recIFSC).append("222002XXXXXXXXXXXXXXXX2EFT")
				.append(finaldate).append(credtTmHeaderTime).append("2").append(OrgnlEndToEndId)
				.append("XXXXXXXXXXXXXXXXXXXXXXXXX99}{4:").append("\r\n");
		builder.append(":2020:").append(OrgnlEndToEndId).append("\r\n");
		builder.append(":2020:").append(OrgnlEndToEndId).append("\r\n");
		builder.append(":5518:").append(DbtrAgtIFSC).append("\r\n");
		builder.append(":2006:").append(OrgnlItmId).append("\r\n");
		builder.append(":3501:").append(Amt.replace('.', ',')).append("\r\n");
		builder.append("-}");
		System.out.println(builder);

	}

	public void camt052XML(String xmlData) {

		int start = xmlData.indexOf("<Fr>");
		int end = xmlData.indexOf("</Fr>");

		String Fr = xmlData.substring(start, end);
//    	System.out.println("recPart : "+recPart);
		// System.out.println(end);
		String FrValue = Fr.substring(Fr.indexOf("<MmbId>") + 7, Fr.indexOf("</MmbId>"));
//    	System.out.println(FrValue);

		start = xmlData.indexOf("<To>");
		end = xmlData.indexOf("</To>");
		String To = xmlData.substring(start, end);
		String ToValue = To.substring(To.indexOf("<MmbId>") + 7, To.indexOf("</MmbId>"));
//    	System.out.println(ToValue);

		start = xmlData.indexOf("<BizMsgIdr>");
		end = xmlData.indexOf("</BizMsgIdr>");
//    	System.out.println(start);
		String BizMsgIdr = xmlData.substring(start + 11, end);
//    	System.out.println(BizMsgIdr);

		start = xmlData.indexOf("<MsgDefIdr>");
		end = xmlData.indexOf("</MsgDefIdr>");
		String MsgDefIdr = xmlData.substring(start + 11, end);
//    	System.out.println(MsgDefIdr);
//    	System.out.println("--------------");

		start = xmlData.indexOf("<BizSvc>");
		end = xmlData.indexOf("</BizSvc>");
		String BizSvc = xmlData.substring(start + 8, end);
//    	System.out.println(BizSvc);

		start = xmlData.indexOf("<CreDt>");
		end = xmlData.indexOf("</CreDt>");
		String CreDt = xmlData.substring(start + 7, end);
//    	System.out.println(CreDt);

//    	MsgId
		start = xmlData.indexOf("<MsgId>");
		end = xmlData.indexOf("</MsgId>");
		String MsgId = xmlData.substring(start + 7, end);
//    	System.out.println(MsgId);

//    	CreDtTm
		start = xmlData.indexOf("<CreDtTm>");
		end = xmlData.indexOf("</CreDtTm>");
		String CreDtTm = xmlData.substring(start + 9, end);
		String credtTmHeaderTime = xmlData.substring(start + 20, end - 6);
		credtTmHeaderTime += xmlData.substring(start + 23, end - 3);
//    	System.out.println("credtTmHeaderTime "+credtTmHeaderTime);
//    	System.out.println(CreDtTm);

//    	AddtInf
		start = xmlData.indexOf("<AddtInf>");
		end = xmlData.indexOf("</AddtlInf>");
		String AddtlInf = xmlData.substring(start + 17, end);

//    	System.out.println(AddtlInf);

//    	ElctrncSeqNb
		start = xmlData.indexOf("<ElctrncSeqNb>");
		end = xmlData.indexOf("</ElctrncSeqNb>");
		String ElctrncSeqNb = xmlData.substring(start + 14, end);
//    	System.out.println(ElctrncSeqNb);

//    	Sts
		start = xmlData.indexOf("<Sts>");
		end = xmlData.indexOf("</Sts>");
		String Sts = xmlData.substring(start + 5, end);
		String StsValue = Sts.substring(Sts.indexOf("<Cd>") + 4, Sts.indexOf("</Cd>"));
//    	System.out.println(StsValue);

//    	ValDt
		start = xmlData.indexOf("<ValDt>");
		end = xmlData.indexOf("</ValDt>");
		String ValDt = xmlData.substring(start + 7, end);
		String ValDtValue = ValDt.substring(ValDt.indexOf("<Dt>") + 4, ValDt.indexOf("</Dt>"));
		String finalDate = ValDtValue.substring(0, 4);
		finalDate += ValDtValue.substring(5, 7);
		finalDate += ValDtValue.substring(8);
//    	System.out.println("finalDate "+finalDate);

//    	TxDtls

		start = xmlData.indexOf("<TxDtls>");
		end = xmlData.indexOf("</TxDtls>");
//    	String TxDtls = xmlData.substring(start+8,end);
//    	String TxDtlsAmt = TxDtls.substring(TxDtls.indexOf("<Amt Ccy=\"INR\">")+15,TxDtls.indexOf("</Amt>"));
//    	System.out.println(TxDtlsAmt);
//    	String CdtDbtInd = TxDtls.substring(TxDtls.indexOf("<CdtDbtInd>")+11,TxDtls.indexOf("</CdtDbtInd>"));
//    	System.out.println(CdtDbtInd);
//    	String Ustrd = TxDtls.substring(TxDtls.indexOf("<Ustrd>")+7,TxDtls.indexOf("</Ustrd>"));
//    	System.out.println(Ustrd);

		String[] amtValue = new String[4];
		String[] cdtDbtInd = new String[4];
		String[] ustrd = new String[4];
		int i = 0, j = 0, k = 0;

		while (start != -1) {
			end = xmlData.indexOf("</TxDtls>", start);
			if (end == -1)
				break;

			String TxDtls = xmlData.substring(start + 8, end);
//             System.out.println(TxDtls);

			String amt = TxDtls.substring(TxDtls.indexOf("<Amt") + 5, TxDtls.indexOf("</Amt>"));
			amtValue[i++] = amt.substring(amt.indexOf(">") + 1);

			cdtDbtInd[j++] = TxDtls.substring(TxDtls.indexOf("<CdtDbtInd>") + 11, TxDtls.indexOf("</CdtDbtInd>"));

			ustrd[k++] = TxDtls.substring(TxDtls.indexOf("<Ustrd>") + 7, TxDtls.indexOf("</Ustrd>"));

//             
//             System.out.println(amtValue[0]);
////             System.out.println(currency);
//             System.out.println(cdtDbtInd[0]);
//             System.out.println(ustrd[0]);
//             System.out.println("-----------------------------------");

			// Look for the next <TxDtls> tag
			start = xmlData.indexOf("<TxDtls>", end);
		}

		StringBuilder builder = new StringBuilder();
//    	
//		hardcode													    date   time  2020last9digit
//{A:XXXF01I298N04RBIP0NEFTSC HBK0000002  222002 RBIPMUR4063873432NFT 20191216 1250 2 001146414 XXXXXXXXXXXXXXXXXXXXXXXXX99}{4:   
		builder.append("{A:XXXF01I298N02RBIP0NEFTSC").append(ToValue).append("222002XXXXXXXXXXXXXXXX2EFT")
				.append(finalDate).append(credtTmHeaderTime).append("2000000000")
				.append("XXXXXXXXXXXXXXXXXXXXXXXXX99}{4:").append("\r\n");
		builder.append(":3385:").append(finalDate).append("\r\n");
//    	3535 optional
		if (AddtlInf != null && !AddtlInf.isEmpty()) {
			builder.append(":3535:").append(AddtlInf).append("\r\n");
		}
//    	if()
//    	Integer numberOfAcceptedRejected = Integer.parseInt(ustrd[0]) + Integer.parseInt(ustrd[1]) ;
		builder.append(":5175:").append(ustrd[0]).append("\r\n");

//    	Double totalAmountAcceptedRejected = Double.parseDouble(amtValue[0]) + Double.parseDouble(amtValue[1]);
		builder.append(":4105:").append(amtValue[0].replace('.', ',')).append("\r\n");
		builder.append(":5180:").append(ustrd[0]).append("\r\n");
		builder.append(":4110:").append(amtValue[0].replace('.', ',')).append("\r\n");
		builder.append(":5185:").append(ustrd[1]).append("\r\n");
		builder.append(":4115:").append(amtValue[1].replace('.', ',')).append("\r\n");
		builder.append(":5267:").append(ustrd[2]).append("\r\n");
		builder.append(":4410:").append(amtValue[2].replace('.', ',')).append("\r\n");
		builder.append(":5047:").append(ustrd[3]).append("\r\n");
		builder.append(":4460:").append(amtValue[3].replace('.', ',')).append("\r\n");
		builder.append("-}");
		System.out.println(builder);

	}
}
