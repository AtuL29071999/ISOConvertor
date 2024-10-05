package Test;

import java.util.HashMap;

public class MessageToXMLConverter {

    public static void main(String[] args) {
        String msg = "{A:CBSF01O298N06KANG00000SCRBIP0NEFTSC121002XXXXXXXXXXXXXXXX2EFT2015112018342000000003XXXXXXXXXXXXXXXXXXXXXXXXX11}{4:\r\n"
        		+ ":2020:4380628615G0003\r\n"
        		+ ":1106:2\r\n"
        		+ ":4063:2000,00\r\n"
        		+ ":2020:4380628615G0003\r\n"
        		+ ":4038:2000,00\r\n"
        		+ ":3380:20151120\r\n"
        		+ ":5756:KANG0000004\r\n"
        		+ ":6305:10\r\n"
        		+ ":6021:4380118078\r\n"
        		+ ":6091:PRINCIPAL.G.G.SR.SEC.SCHOOL PORTM\r\n"
        		+ ":5629:SMS\r\n"
        		+ "7742299321\r\n"
        		+ ":7002:other\r\n"
        		+ ":5569:BARB0JHOTWA\r\n"
        		+ ":6310:10\r\n"
        		+ ":6061:0029570100005917\r\n"
        		+ ":6081:meghraj jhalani\r\n"
        		+ ":5565:meghraj jhalani\r\n"
        		+ ":7495:/OTH/other04/12/2016\r\n"
        		+ "-}";

        // Step 1: Parse the message and store field values in a map
        HashMap<String, String> messageFields = parseMessage(msg);

        // Step 2: Generate the XML format
        String xml = generateXML(messageFields);

        // Step 3: Print the resulting XML
        System.out.println(xml);
    }

    // Method to parse the message and extract key-value pairs
    private static HashMap<String, String> parseMessage(String msg) {
        HashMap<String, String> fields = new HashMap<>();
        
        // Example: Splitting based on field identifiers
        fields.put("2020", extractField(msg, "2020"));
        fields.put("4063", extractField(msg, "4063"));
        fields.put("6091", extractField(msg, "6091"));
        fields.put("4038", extractField(msg, "4038"));
        fields.put("6021", extractField(msg, "6021"));
        fields.put("7002", extractField(msg, "7002"));
        fields.put("5569", extractField(msg, "5569"));
        fields.put("6061", extractField(msg, "6061"));
        fields.put("6081", extractField(msg, "6081"));
        fields.put("3380", extractField(msg, "3380"));
        
        // Add more fields as required.
        
        return fields;
    }

    // Method to extract a specific field value from the message
    private static String extractField(String msg, String field) {
        String result = "";
        int index = msg.indexOf(":" + field + ":");
        if (index != -1) {
            result = msg.substring(index + field.length() + 2); // Skip ":field:"
            int endIndex = result.indexOf(":");
            if (endIndex != -1) {
                result = result.substring(0, endIndex).trim();
            }
        }
        return result;
    }

    // Method to generate the final XML string based on the extracted fields
    private static String generateXML(HashMap<String, String> fields) {
        StringBuilder xml = new StringBuilder();

        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<RequestPayload>\n");
        xml.append("    <AppHdr xmlns=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.02\" xmlns:xsi=\"urn:iso:std:iso:20022:tech:xsd:Header\" xmlns:sig=\"http://www.w3.org/2000/09/xmldsig#\">\n");
        xml.append("        <Fr>\n");
        xml.append("            <FIId>\n");
        xml.append("                <FinInstnId>\n");
        xml.append("                    <ClrSysMmbId>\n");
        xml.append("                        <MmbId>").append("HDFC0000001").append("</MmbId>\n");
        xml.append("                    </ClrSysMmbId>\n");
        xml.append("                </FinInstnId>\n");
        xml.append("            </FIId>\n");
        xml.append("        </Fr>\n");
        xml.append("        <To>\n");
        xml.append("            <FIId>\n");
        xml.append("                <FinInstnId>\n");
        xml.append("                    <ClrSysMmbId>\n");
        xml.append("                        <MmbId>").append("RBIP0NEFTSC").append("</MmbId>\n");
        xml.append("                    </ClrSysMmbId>\n");
        xml.append("                </FinInstnId>\n");
        xml.append("            </FIId>\n");
        xml.append("        </To>\n");
        xml.append("        <BizMsgIdr>").append(fields.getOrDefault("2020", "N/A")).append("</BizMsgIdr>\n");
        xml.append("        <MsgDefIdr>pacs.008.001.09</MsgDefIdr>\n");
        xml.append("        <BizSvc>NEFTFIToFICustomerCredit</BizSvc>\n");
        xml.append("        <CreDt>2020-10-21T16:44:00Z</CreDt>\n");
        xml.append("    </AppHdr>\n");

        xml.append("    <Document xmlns=\"urn:iso:std:iso:20022:tech:xsd:pacs.008.001.09\">\n");
        xml.append("        <FIToFICstmrCdtTrf>\n");
        xml.append("            <GrpHdr>\n");
        xml.append("                <MsgId>").append(fields.getOrDefault("2020", "N/A")).append("</MsgId>\n");
        xml.append("                <CreDtTm>2020-10-21T16:44:00</CreDtTm>\n");
        xml.append("                <NbOfTxs>1</NbOfTxs>\n");
        xml.append("                <TtlIntrBkSttlmAmt Ccy=\"INR\">").append(fields.getOrDefault("4063", "0.00")).append("</TtlIntrBkSttlmAmt>\n");
        xml.append("                <IntrBkSttlmDt>").append(fields.getOrDefault("3380", "2020-10-21")).append("</IntrBkSttlmDt>\n");
        xml.append("                <SttlmInf>\n");
        xml.append("                    <SttlmMtd>CLRG</SttlmMtd>\n");
        xml.append("                </SttlmInf>\n");
        xml.append("            </GrpHdr>\n");

        // Add other sections as needed, using extracted field values.

        xml.append("        </FIToFICstmrCdtTrf>\n");
        xml.append("    </Document>\n");
        xml.append("</RequestPayload>\n");

        return xml.toString();
    }
}

