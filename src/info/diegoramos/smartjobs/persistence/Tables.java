package info.diegoramos.smartjobs.persistence;

public class Tables {

	//TABLE cad_type_of_service
	private static final String TBL_cad_type_of_service = "cad_type_of_service";
	//FIELDS cad_type_of_service	
	private static final String FIELD_id_type_of_service = "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT";
	private static final String FIELD_name_type_of_service = "name CHAR(30) NOT NULL CONSTRAINT [name_type_of_service] UNIQUE";
	
	//TABLE cad_client
	private static final String TBL_cad_client = "cad_client";
	//FIELDS cad_client
	private static final String FIELD_id_client = "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT";
	private static final String FIELD_name_client = "name CHAR(50) NOT NULL CONSTRAINT [name_client] UNIQUE";	
	private static final String FIELD_address_client = "address CHAR(50)";
	private static final String FIELD_phone_client = "phone CHAR(10)";
	private static final String FIELD_celular_client = "celular CHAR(10)";
	private static final String FIELD_email_client = "email CHAR(100)";

	
	//TABLE cad_service
	private static final String TBL_cad_service = "cad_service";	
	//FIELDS cad_service
	private static final String FIELD_id_service = "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT";
	private static final String FIELD_id_client_service = "_id_client INTEGER NOT NULL";
	private static final String FIELD_id_type_of_service_service = "_id_type_of_service INTEGER NOT NULL";
	private static final String FIELD_name_service = "name CHAR(20) NOT NULL";	
	private static final String FIELD_date_service = "date long NOT NULL";
	private static final String FIELD_price_service = "price real";
	private static final String FIELD_details_service = "details CHAR(50)";
	
	
	
	private String cad_client = "CREATE TABLE " +
			TBL_cad_client + " (" +
			FIELD_id_client + ", " +
			FIELD_name_client + ", " +
			FIELD_address_client + ", " +
			FIELD_phone_client + ", " +
			FIELD_celular_client + ", " +
			FIELD_email_client + ") ";
	
	private String cad_type_of_service = "CREATE TABLE " +
			TBL_cad_type_of_service + " (" +
			FIELD_id_type_of_service + " , " + 
			FIELD_name_type_of_service + ") ";	
	

	private String cad_service = "CREATE TABLE " +
			TBL_cad_service + " (" +
			FIELD_id_service + " , " +
			FIELD_id_client_service + " , " +
			FIELD_id_type_of_service_service + " , " +
			FIELD_name_service + " , " +
			FIELD_date_service + " , " +
			FIELD_price_service + " , " +
			FIELD_details_service + ") " ;	
	
	public String getCreateClient() {
		return this.cad_client;
	}
	
	public String getCreateTypeOfService() {
		return this.cad_type_of_service;
	}

	public String getCreateService() {
		return this.cad_service;
	}
	
	
	public Tables() {
		
	}
	
}
