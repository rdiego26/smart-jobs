package info.diegoramos.smartjobs.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO extends SQLiteOpenHelper{

	private static final String DB_NAME = "db_smart_jobs";
	
	//TABLES
	static final String TBL_TYPE_OF_SERVICE = "cad_type_of_service";
	static final String TBL_CLIENT = "cad_client";
	static final String TBL_SERVICE = "cad_service";
	
	private static final Integer VERSION = 1;
	private Tables tables = new Tables();
	
	public DAO(Context context)
	{
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(tables.getCreateTypeOfService()); //CREATE TABLE TypeOfService
		db.execSQL(tables.getCreateClient()); //CREATE TABLE Client
		db.execSQL(tables.getCreateService()); //CREATE TABLE Service
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}



}
