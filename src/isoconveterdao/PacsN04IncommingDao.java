package isoconveterdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import isoconveterconnection.JdbcConnection;
import isoconveterdto.PacsN04Incomming;

public class PacsN04IncommingDao {

	Connection connection = null;
	
	public void insertPacsN04IncommingDao(PacsN04Incomming pacsN04Incomming) {
		
		connection = JdbcConnection.getConnection();
		
		String insert = "insert into pacsn04incomming values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(insert);
			
			preparedStatement.setString(1, pacsN04Incomming.getTransaction_reference_number1());
			preparedStatement.setString(2, pacsN04Incomming.getBatch_time());
			preparedStatement.setString(3, pacsN04Incomming.getTotal_transaction());
			preparedStatement.setString(4, pacsN04Incomming.getTotal_amount());
			preparedStatement.setString(5, pacsN04Incomming.getTransaction_reference_number2());
			preparedStatement.setString(6, pacsN04Incomming.getRelated_reference_number());
			preparedStatement.setString(7, pacsN04Incomming.getSender_ifsc());
			preparedStatement.setString(8, pacsN04Incomming.getSender_account_type());
			preparedStatement.setString(9, pacsN04Incomming.getSender_account_number());
			preparedStatement.setString(10, pacsN04Incomming.getSender_account_name());
			preparedStatement.setString(11, pacsN04Incomming.getOriginator_of_remittance());
			preparedStatement.setString(12, pacsN04Incomming.getDebeter_ifsc());
			preparedStatement.setString(13, pacsN04Incomming.getDebeter_account_type());
			preparedStatement.setString(14, pacsN04Incomming.getDebeter_account_number());
			preparedStatement.setString(15, pacsN04Incomming.getDebeter_account_name());
			preparedStatement.setString(16, pacsN04Incomming.getDebeter_addess());
			preparedStatement.setString(17, pacsN04Incomming.getRemitance_information());
			preparedStatement.setString(18, pacsN04Incomming.getReason_code());
			preparedStatement.setString(19, pacsN04Incomming.getRejection_reason());
			preparedStatement.setString(20, pacsN04Incomming.getAmount());
			preparedStatement.setString(21, pacsN04Incomming.getValue_date());
			preparedStatement.setString(22, pacsN04Incomming.getRemitance_date());
			
			preparedStatement.execute();
			System.out.println("Data inserted Successfully....");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
}
