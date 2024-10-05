package isoconveterdto;

public class PacsN04Incomming {

	private int id;
	private String transaction_reference_number1;
	private String batch_time;
	private String total_transaction;
	private String total_amount;
	private String transaction_reference_number2;
	private String related_reference_number;
	private String sender_ifsc;
	private String sender_account_type;
	private String sender_account_number;
	private String sender_account_name;
	private String originator_of_remittance;
	private String debeter_ifsc;
	private String debeter_account_type;
	private String debeter_account_number;
	private String debeter_account_name;
	private String debeter_addess;
	private String remitance_information;
	private String reason_code;
	private String rejection_reason;
	private String amount;
	private String value_date;
	private String remitance_date;
	
	
	public PacsN04Incomming() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PacsN04Incomming(int id, String transaction_reference_number1, String batch_time, String total_transaction,
			String total_amount, String transaction_reference_number2, String related_reference_number,
			String sender_ifsc, String sender_account_type, String sender_account_number, String sender_account_name,
			String originator_of_remittance, String debeter_ifsc, String debeter_account_type,
			String debeter_account_number, String debeter_account_name, String debeter_addess,
			String remitance_information, String reason_code, String rejection_reason, String amount, String value_date,
			String remitance_date) {
		super();
		this.id = id;
		this.transaction_reference_number1 = transaction_reference_number1;
		this.batch_time = batch_time;
		this.total_transaction = total_transaction;
		this.total_amount = total_amount;
		this.transaction_reference_number2 = transaction_reference_number2;
		this.related_reference_number = related_reference_number;
		this.sender_ifsc = sender_ifsc;
		this.sender_account_type = sender_account_type;
		this.sender_account_number = sender_account_number;
		this.sender_account_name = sender_account_name;
		this.originator_of_remittance = originator_of_remittance;
		this.debeter_ifsc = debeter_ifsc;
		this.debeter_account_type = debeter_account_type;
		this.debeter_account_number = debeter_account_number;
		this.debeter_account_name = debeter_account_name;
		this.debeter_addess = debeter_addess;
		this.remitance_information = remitance_information;
		this.reason_code = reason_code;
		this.rejection_reason = rejection_reason;
		this.amount = amount;
		this.value_date = value_date;
		this.remitance_date = remitance_date;
	}


	public PacsN04Incomming(String transaction_reference_number1, String batch_time, String total_transaction,
			String total_amount, String transaction_reference_number2, String related_reference_number,
			String sender_ifsc, String sender_account_type, String sender_account_number, String sender_account_name,
			String originator_of_remittance, String debeter_ifsc, String debeter_account_type,
			String debeter_account_number, String debeter_account_name, String debeter_addess,
			String remitance_information, String reason_code, String rejection_reason, String amount, String value_date,
			String remitance_date) {
		super();
		this.transaction_reference_number1 = transaction_reference_number1;
		this.batch_time = batch_time;
		this.total_transaction = total_transaction;
		this.total_amount = total_amount;
		this.transaction_reference_number2 = transaction_reference_number2;
		this.related_reference_number = related_reference_number;
		this.sender_ifsc = sender_ifsc;
		this.sender_account_type = sender_account_type;
		this.sender_account_number = sender_account_number;
		this.sender_account_name = sender_account_name;
		this.originator_of_remittance = originator_of_remittance;
		this.debeter_ifsc = debeter_ifsc;
		this.debeter_account_type = debeter_account_type;
		this.debeter_account_number = debeter_account_number;
		this.debeter_account_name = debeter_account_name;
		this.debeter_addess = debeter_addess;
		this.remitance_information = remitance_information;
		this.reason_code = reason_code;
		this.rejection_reason = rejection_reason;
		this.amount = amount;
		this.value_date = value_date;
		this.remitance_date = remitance_date;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTransaction_reference_number1() {
		return transaction_reference_number1;
	}


	public void setTransaction_reference_number1(String transaction_reference_number1) {
		this.transaction_reference_number1 = transaction_reference_number1;
	}


	public String getBatch_time() {
		return batch_time;
	}


	public void setBatch_time(String batch_time) {
		this.batch_time = batch_time;
	}


	public String getTotal_transaction() {
		return total_transaction;
	}


	public void setTotal_transaction(String total_transaction) {
		this.total_transaction = total_transaction;
	}


	public String getTotal_amount() {
		return total_amount;
	}


	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}


	public String getTransaction_reference_number2() {
		return transaction_reference_number2;
	}


	public void setTransaction_reference_number2(String transaction_reference_number2) {
		this.transaction_reference_number2 = transaction_reference_number2;
	}


	public String getRelated_reference_number() {
		return related_reference_number;
	}


	public void setRelated_reference_number(String related_reference_number) {
		this.related_reference_number = related_reference_number;
	}


	public String getSender_ifsc() {
		return sender_ifsc;
	}


	public void setSender_ifsc(String sender_ifsc) {
		this.sender_ifsc = sender_ifsc;
	}


	public String getSender_account_type() {
		return sender_account_type;
	}


	public void setSender_account_type(String sender_account_type) {
		this.sender_account_type = sender_account_type;
	}


	public String getSender_account_number() {
		return sender_account_number;
	}


	public void setSender_account_number(String sender_account_number) {
		this.sender_account_number = sender_account_number;
	}


	public String getSender_account_name() {
		return sender_account_name;
	}


	public void setSender_account_name(String sender_account_name) {
		this.sender_account_name = sender_account_name;
	}


	public String getOriginator_of_remittance() {
		return originator_of_remittance;
	}


	public void setOriginator_of_remittance(String originator_of_remittance) {
		this.originator_of_remittance = originator_of_remittance;
	}


	public String getDebeter_ifsc() {
		return debeter_ifsc;
	}


	public void setDebeter_ifsc(String debeter_ifsc) {
		this.debeter_ifsc = debeter_ifsc;
	}


	public String getDebeter_account_type() {
		return debeter_account_type;
	}


	public void setDebeter_account_type(String debeter_account_type) {
		this.debeter_account_type = debeter_account_type;
	}


	public String getDebeter_account_number() {
		return debeter_account_number;
	}


	public void setDebeter_account_number(String debeter_account_number) {
		this.debeter_account_number = debeter_account_number;
	}


	public String getDebeter_account_name() {
		return debeter_account_name;
	}


	public void setDebeter_account_name(String debeter_account_name) {
		this.debeter_account_name = debeter_account_name;
	}


	public String getDebeter_addess() {
		return debeter_addess;
	}


	public void setDebeter_addess(String debeter_addess) {
		this.debeter_addess = debeter_addess;
	}


	public String getRemitance_information() {
		return remitance_information;
	}


	public void setRemitance_information(String remitance_information) {
		this.remitance_information = remitance_information;
	}


	public String getReason_code() {
		return reason_code;
	}


	public void setReason_code(String reason_code) {
		this.reason_code = reason_code;
	}


	public String getRejection_reason() {
		return rejection_reason;
	}


	public void setRejection_reason(String rejection_reason) {
		this.rejection_reason = rejection_reason;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getValue_date() {
		return value_date;
	}


	public void setValue_date(String value_date) {
		this.value_date = value_date;
	}


	public String getRemitance_date() {
		return remitance_date;
	}


	public void setRemitance_date(String remitance_date) {
		this.remitance_date = remitance_date;
	}


	@Override
	public String toString() {
		return "PacsN04Incomming [id=" + id + ", transaction_reference_number1=" + transaction_reference_number1
				+ ", batch_time=" + batch_time + ", total_transaction=" + total_transaction + ", total_amount="
				+ total_amount + ", transaction_reference_number2=" + transaction_reference_number2
				+ ", related_reference_number=" + related_reference_number + ", sender_ifsc=" + sender_ifsc
				+ ", sender_account_type=" + sender_account_type + ", sender_account_number=" + sender_account_number
				+ ", sender_account_name=" + sender_account_name + ", originator_of_remittance="
				+ originator_of_remittance + ", debeter_ifsc=" + debeter_ifsc + ", debeter_account_type="
				+ debeter_account_type + ", debeter_account_number=" + debeter_account_number
				+ ", debeter_account_name=" + debeter_account_name + ", debeter_addess=" + debeter_addess
				+ ", remitance_information=" + remitance_information + ", reason_code=" + reason_code
				+ ", rejection_reason=" + rejection_reason + ", amount=" + amount + ", value_date=" + value_date
				+ ", remitance_date=" + remitance_date + "]";
	}
	
	
	
//    private String messageData;
//
//    // Constructor to accept the raw string
//    public PacsN04Incomming(String messageData) {
//        this.messageData = messageData;
//        // You can add logic here to parse the data and store it in different fields
//    }
//
//    // Getter for the message data
//    public String getMessageData() {
//        return messageData;
//    }
	
	
	
	
}
