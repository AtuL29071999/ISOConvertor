package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

//		System.out.println("Enter the number: ");
//		String msg = sc.nextLine();

//		System.out.println("ENtered string is: ");
//		while(sc.hasNextLine())  
//		{  
//		//takes the string input and prints the same string  
//		System.out.println(sc.nextLine());  
//		} 

		processMessage();
	}

	/*
	 * calling form processMessage(), getField method extract the value form message
	 * formate.
	 */
	public static HashMap getField(String msg, String field) {

		String fieldValue = null;
		int linecount = 0;
		HashMap fieldvalue = new HashMap();

		// String test=":2020:/D";

		//
		String test = msg;
		String test1 = msg;
//		 System.out.println("Count:"+test1.indexOf(":2020:"));

		if (test.indexOf(":" + field + ":") != -1)

		{
//			System.out.println(test);
			test = test.trim();
//			System.out.println(test);
			fieldValue = test.substring(test.indexOf(":" + field + ":") + 6, test.length());
//			System.out.println(fieldValue);

			fieldValue = fieldValue.trim();
//			System.out.println("----");
//			System.out.println(fieldValue);
//			System.out.println("------");

			if (fieldValue.indexOf(":") != -1) {
				fieldValue = fieldValue.substring(0, fieldValue.indexOf(":"));
				fieldValue = fieldValue.trim();
//				System.out.println("------if---------");
//				System.out.println(fieldValue);
//				System.out.println("---------------");
//				
			} else {
				fieldValue = fieldValue.substring(0, fieldValue.indexOf("-}"));
				fieldValue = fieldValue.trim();

//				System.out.println("------else---------");
//				System.out.println(fieldValue);
//				System.out.println("---------------");
			}

			BufferedReader br = new BufferedReader(new StringReader(fieldValue));

			try {
				while ((test = br.readLine()) != null) {
					test = test.trim();
					linecount = linecount + 1;
					// System.out.println("Line:"+field+'#'+linecount+","+test);
					if (!test.equals(null)) {

						if (field.equals("5629")) {
							test = test.trim();

						    // Initialize line counter to differentiate between multiple values
						    int counter = 1;

						    // Start a loop to find each occurrence of :5629:
						    int currentIndex = 0;
						    while ((currentIndex = test1.indexOf(":" + field + ":", currentIndex)) != -1) {
						        // Move to the position after :5629:
						        currentIndex += (":" + field + ":").length();

						        // Find the next delimiter (either the next ':' or the end of the string)
						        int nextFieldIndex = test1.indexOf(":", currentIndex);
						        if (nextFieldIndex == -1) {
						            nextFieldIndex = test1.length();
						        }

						        // Extract the value
						        String value = test1.substring(currentIndex, nextFieldIndex).trim();

						        // Check if the value is a mobile number (10 digits)
						        if (value.length() == 10 && value.matches("\\d+")) {
						            fieldvalue.put("F" + field + counter, value);
						        }
						        // Check if it's an email (contains @ and .)
						        else if (value.contains("@") && value.contains(".")) {
						            fieldvalue.put("F" + field + counter, value);
						        }

						        // Increment the counter for the next occurrence
						        counter++;
						    }

						} else if (field.equals("7495")) {
							test = test1.trim();

//							System.out.println("=========================");
//							System.out.println(test);
//							System.out.println("=========================");
					        Pattern pattern = Pattern.compile(":7495:([^\r\n]+)");
					        Matcher matcher = pattern.matcher(test);
					            int counter = 1;
					            while (matcher.find()) {
					                String value = matcher.group(1); 
					                fieldvalue.put("F" + field + counter, value);
					                if(counter <=6) {
					                	counter++;
					                }
					                
					            }
					       
//					    }

						}else if (field.equals("7002")) {
							test = test.trim();
//							System.out.println("from 7002: "+ test);

							for (int i = 1; i <= 4; i++) {
								int start = (i - 1) * 35;
								int end = Math.min(start + 35, test.length()); // Ensure end doesn't exceed string
																				// length

								if (start < test.length()) {
									fieldvalue.put('F' + field + i, test.substring(start, end));
								} else {
									fieldvalue.put('F' + field + i, "");
								}
							}

						} 
						else if (field.equals("5565")) {
//							System.out.println("from 5565 test : "+test);
//							System.out.println(test1);
							
							int lineCounter = 1;
							int currentIndex = 0;

							while ((currentIndex = test1.indexOf(":" + field + ":", currentIndex)) != -1) {
							    currentIndex += (":" + field + ":").length(); 
							    int nextFieldIndex = test1.indexOf(":", currentIndex);
//							    System.out.println(lineCounter);
							    if (nextFieldIndex == -1) {
							        nextFieldIndex = test1.length(); 
							        
							    }
							    String value = test1.substring(currentIndex, nextFieldIndex).trim();
							    
							    fieldvalue.put("F" + field + lineCounter, value);
							    lineCounter++;
							    
							    currentIndex = nextFieldIndex;
							}

						  
						}
						else if (field.equals("4038")) {
							fieldvalue.put('F' + field + linecount, test.replace(',', '.'));
							test = test.trim();
						} else if (field.equals("2020")) {
							test = test.trim();
							test1 = test1.trim();
							String value = get2020fromMsgN02(test1);
//							Pattern pattern = Pattern.compile(":2020:([^\r\n]+)");
//					        Matcher matcher = pattern.matcher(test1);
//					            int counter = 1;
//					            while (matcher.find()) {
//					                String value = matcher.group(1); 
//					                fieldvalue.put("F" + field + counter, value);
//					                if(counter <=6) {
//					                	counter++;
//					                }
//					                
//					            }
//							 System.out.println("Value of Test:"+field+linecount+","+test);
							fieldvalue.put('F' + field + linecount, test.trim());
							fieldvalue.put('F' + field + 2, value);
							// test=fieldValue.substring(test.length(),fieldValue.length());
							// System.out.println("Value of Test length+++:"+test.length());
							// System.out.println("Value of
							// Test+++:"+test1.substring(test.length(),test1.length()));
							String rest = test1.substring(150, test1.length());
							rest = rest.trim();
							if (rest.indexOf(":" + field + ":") != -1) {
								String v2020 = rest.substring(rest.indexOf(":" + field + ":") + 6, rest.length());
								linecount = linecount + 1;
								v2020 = v2020.trim();
								String temp = v2020.substring(0, v2020.indexOf(":") - 1);
								// System.out.println("Value of
								// rest:"+field+linecount+","+v2020.substring(0,v2020.indexOf(":")-3));
								fieldvalue.put('F' + field + linecount, temp.trim());
								// System.out.println("V2020 value is="+v2020);
							}
							test = test.trim();
						} else {

							// System.out.println("Value of Test:"+test);
							fieldvalue.put('F' + field + linecount, test.trim());
						}
					}

				}
			} catch (IOException ex) {
				System.out.println("Java exception: writing in message buffer" + ex);

			}
		}
//		System.out.println("=============================");
//		System.out.println(fieldValue);

		return fieldvalue;

	}
	
	public static String get2020fromMsgN02(String msg) 
	{
		
			String  relref="";
			String remainMsg="";
			BufferedReader br=new BufferedReader(new StringReader(msg));
			//String test=":2020:/D";
					
			if(msg.indexOf(":2020:")!=-1)

			{
			//System.out.println("+++++++++++++++++++"+test.substring(6,test.length()));
			//Double Amount=new Double(test.substring(17,test.length()).replace(',','.'));
			remainMsg=msg.substring(msg.indexOf(":2020:")+6,msg.length());
			
			//System.out.println("Remaing Message"+remainMsg);
			
			if(remainMsg.indexOf(":2020:")!=-1)
				{
				//System.out.println(":2020::::::"+remainMsg.indexOf(":2020:"));
				relref=remainMsg.substring(remainMsg.indexOf(":2020:")+6,remainMsg.length()); //remainMsg.indexOf(":"));
				relref=relref.substring(0,relref.indexOf(":"));
				}
			
			
			}
		 
  return relref;


			}


	

	private static void processMessage() {

//		HashMap finalMessage=new HashMap<String,String>();
		HashMap<String, String> Message = new HashMap<>();
		HashMap finalResult = new HashMap<String, String>();

		String msg = "{A:CBSF01O298N06CLBL00000SCRBIP0NEFTSC222000CLBLN152653888472EFT2015092200002003493487XXXXXXXXXCLBLN1526538884799}{4:\r\n"
				+ ":2020:CLBLN15265388847\r\n"
				+ ":1106:1        \r\n"
				+ ":4063:5000,00\r\n"
				+ ":2020:000004103420\r\n"
				+ ":4038:5000,00\r\n"
				+ ":3380:20150922\r\n"
				+ ":5756:CLBL0000001\r\n"
				+ ":6305:12\r\n"
				+ ":6021:309000778220\r\n"
				+ ":6091:FIROJ AHMAD KHAN\r\n"
				+ ":7002:309000778220\r\n"
				+ "FIROJ AHMAD KHAN\r\n"
				+ ":5569:SBIN0004902\r\n"
				+ ":6061:11140652716\r\n"
				+ ":6081:Zahid Ali\r\n"
				+ ":5629:9142648162\r\n"
				+ "STATE BANK OF INDIA JAGDISHPUR JAGD\r\n"
				+ "-}";

		// Starting index for block4
		int start = msg.indexOf("{4:");
		// Ending index for block4
		int end = msg.indexOf("-}");

//		System.out.println("start: " + start);
//		System.out.println("end: " + end);
		// here all the message are stored in
		String m1 = msg.substring(start + 3, end);
//		System.out.println("m1 "+m1);

		String[] fileds = { "2020", "4488", "2006", "5500", "5516", "5517", "5518", "6717", "5521", "5561", "6500",
				"6718", "5526", "6511", "5546", "6516", "6719", "5551", "6116", "6521", "5556", "7495", "5561", "7023",
				"7028", "1076", "6346", "6450", "3525", "3535", "5756", "6010", "3381", "6305", "6021", "6091", "7002",
				"5569", "6310", "6061", "6081", "4038", "3380", "3375", "5629", "5565", "6366", "5180", "4110", "5518",
				"3501", "1106", "4063", "3385", "5175", "4105", "5180", "5185", "4115", "5267", "4410", "5047", "4460",
				"6712", "6312" };

		for (int i = 0; i < fileds.length; i++) {

			Message = getField(msg, (String) fileds[i]);

//			System.out.println(Message);

			if (!Message.isEmpty()) {
				Message.put(Message.get(fileds[i]), Message.get(fileds[i]));
				for (Map.Entry m : Message.entrySet()) {
//				    System.out.println(m.getKey()+" "+m.getValue());    
					finalResult.put(m.getKey(), m.getValue());
				}

//				System.out.println();
//				finalMessage.put((String) fileds[i], Message);
//				System.out.println(finalMessage);
			}

		}

//		finalResult.forEach((key, value) -> System.out.println(key+ " --> " + value));
		
		/*
		 * call the method from MessageToXmlFormate
		 */

		MessageToXmlFormate.messageToXmlFormate(finalResult);
//		MessageToXmlFormate.generatePacs004OutGoingXML(finalResult);
//		MessageToXmlFormate.generateN10Xml(finalResult);
//		System.out.println("-----------------------------------");
//		System.out.println(finalMessage.entrySet());
		
		
	}
}
