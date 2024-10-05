package isoconveterservice;

import isoconveterdao.PacsN04IncommingDao;
import isoconveterdto.PacsN04Incomming;

public class PacsN04IncommingService {
	
	PacsN04IncommingDao pacsN04IncommingDao = new PacsN04IncommingDao();
	
	public void insertPacsN04IncommingService(PacsN04Incomming pacsN04Incomming) {
		
		pacsN04IncommingDao.insertPacsN04IncommingDao(pacsN04Incomming);
	}
	
}
