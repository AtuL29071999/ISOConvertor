package isoconvetercontroller;

import isoconveterservice.PacsN04IncommingService;

public class PacsN04IncommingController {

	public static void main(String[] args) {
		
		PacsN04IncommingService pacsN04IncommingService = new PacsN04IncommingService();
		
		String str = "{A:XXXF01I298N02RBIP0NEFTSCICIC0000002222002XXXXXXXXXXXXXXXX2EFT2021042016012ICICN504801831XXXXXXXXXXXXXXXXXXXXXXXXX99}{4:\r\n"
				+ ":2020:ICICN504801831\r\n"
				+ ":3535:0002\r\n"
				+ ":5180:10\r\n"
				+ ":4110:1000,00\r\n"
				+ ":2020:ICICN504801831\r\n"
				+ ":2006:HDFCN524954013\r\n"
				+ ":5756:HDFC0065012\r\n"
				+ ":6305:10\r\n"
				+ ":6021:12445345\r\n"
				+ ":6091:praveen\r\n"
				+ ":7002:12445345\r\n"
				+ "praveen\r\n"
				+ ":5569:ICIC0000002\r\n"
				+ ":6310:10\r\n"
				+ ":6061:333333\r\n"
				+ ":6081:maniwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\r\n"
				+ ":5565:IFTAS,HYDERABAD\r\n"
				+ "sssssssssssssssssssssssssssssssssss\r\n"
				+ "sssssssssssssssssssssssssssssssssss\r\n"
				+ ":7495:HB7FFAZ10OMZ8PP8OE26\r\n"
				+ ":6346:AC01\r\n"
				+ ":4038:100.00\r\n"
				+ ":3380:20210420\r\n"
				+ ":3375:20210420\r\n"
				+ "-}\r\n";
		
//		PacsN04Incomming pacsN04Incomming = str;
//		
//		
//		pacsN04IncommingService.insertPacsN04IncommingService(pacsN04Incomming);
	
		
	}
	

}
