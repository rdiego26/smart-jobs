package info.diegoramos.smartjobs.persistence;

<<<<<<< HEAD
import info.diegoramos.smartjobs.model.Client;
import info.diegoramos.smartjobs.model.Service;
import info.diegoramos.smartjobs.model.TypeOfService;
=======
import info.diegoramos.smartjobs.model.Service;
>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DAOService {

	private static final String[] FIELD_SERVICE = {"_id", "_id_client", "_id_type_of_service", "name", "date", "price", "details"};
	protected SQLiteDatabase db;
	
	private static DAOService instance = new DAOService();

	public static DAOService getInstance(Context ctx)
	{
		if(instance.db == null || !instance.db.isOpen())
		{
			instance.db = new DAO(ctx).getWritableDatabase();
		}
		return instance;
	}
	
	
	/*
	 * Save function
	 * @param Service
	 * @return indicator to success(row id) or fail(-1) transation.
	 * */
	public long save(Service sRV)
	{
		long result = -1;
		
		db.beginTransaction();
		
		try
		{
			ContentValues cv = new ContentValues();
			cv.put(FIELD_SERVICE[1], sRV.get_id_client());
			cv.put(FIELD_SERVICE[2], sRV.get_id_type_of_service());
			cv.put(FIELD_SERVICE[3], sRV.getName());
			cv.put(FIELD_SERVICE[4], sRV.getDate());
			cv.put(FIELD_SERVICE[5], sRV.getPrice());
			cv.put(FIELD_SERVICE[5], sRV.getDetails());
			
			//Save data
			result = db.insert(DAO.TBL_SERVICE, null, cv);
		
			db.setTransactionSuccessful();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			db.endTransaction();
		}
		
		return result;
	}	
	
	public Integer delete(int id, Context ctx)
	{
		
		Integer result = 0;
		
		DAOService daoS;
		daoS = DAOService.getInstance(ctx);
		
		if( !daoS.findById(id) )
		{
			result = -1;
		}
		else
		{
			try
			{
				
				db.beginTransaction();
				db.delete(DAO.TBL_SERVICE, FIELD_SERVICE[0] + " = ?", new String[]{String.valueOf(id)});
				db.setTransactionSuccessful();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				result = -1;
			}
			finally
			{
				db.endTransaction();
			}
			
		}

		return result;
	}		
	
	/*
	 * Update function
	 * @param Service
	 * @return indicator to success(0) or fail(-1) transation.
	 * */	
	public int update(Service s)
	{
		
		int result = 0;
		
			try
			{
				db.beginTransaction();
				ContentValues cv = new ContentValues();
				cv.put(FIELD_SERVICE[1], s.get_id_client());
				cv.put(FIELD_SERVICE[2], s.get_id_type_of_service());
				cv.put(FIELD_SERVICE[3], s.getName());
				cv.put(FIELD_SERVICE[4], s.getDate());
				cv.put(FIELD_SERVICE[5], s.getPrice());
				cv.put(FIELD_SERVICE[6], s.getDetails());
				
				db.update(DAO.TBL_SERVICE, cv, FIELD_SERVICE[0] + " = ?", new String[]{s.get_id().toString()});
				db.setTransactionSuccessful();
				
				return 0;
			}
			catch (Exception e)
			{
				Log.i("updateService", e.getMessage().toString());
				result = -1;
			}
			finally
			{
				db.endTransaction();
			}
		return result;
	}	
	
	
	
	
	private Service loadService(Cursor c)
	{
		Service srv = null;
		
		if(c.getCount() > 0) {
			Integer id = c.getInt(c.getColumnIndexOrThrow(FIELD_SERVICE[0]));
			Integer id_client = c.getInt(c.getColumnIndexOrThrow(FIELD_SERVICE[1]));
			Integer id_type_of_service = c.getInt(c.getColumnIndexOrThrow(FIELD_SERVICE[2]));
			String  name     =  c.getString(c.getColumnIndexOrThrow(FIELD_SERVICE[3]));
			long date = c.getLong(c.getColumnIndexOrThrow(FIELD_SERVICE[4]));
			float price = c.getFloat(c.getColumnIndexOrThrow(FIELD_SERVICE[5]));
			String details = c.getString(c.getColumnIndexOrThrow(FIELD_SERVICE[6]));
		
			srv = new Service(id, id_client, id_type_of_service, name, date, price, details);
		}
		return srv;
	}	

	
	/* Search Methods  */
	public boolean findById(int id)
	{		
		Cursor c = db.query(DAO.TBL_SERVICE, null, FIELD_SERVICE[0] + " = " + id, null, null, null, "name ASC");
		
		if(c.getCount() > 0)
		{
			c.close();
			return true;
		}
		c.close();
		return false;
	}	
	
<<<<<<< HEAD
	/**
	 * Verifica se o Tipo de Serviço é utilizado em algum Serviço
	 * @param typeOfService
	 * @return true caso exista algum Serviço que utilize o Tipo de Serviço recebido
	 */
	public boolean findByTypeOfService(TypeOfService typeOfService) {
		Cursor c = db.query(DAO.TBL_SERVICE, null, FIELD_SERVICE[2] + "=" + typeOfService.get_id(), null, null, null, null);
		
		if(c.moveToFirst()){
			return true;
		}
		return false;
	}
	
	/**
	 * Verifica se o Client é utilizado em algum Serviço
	 * @param client
	 * @return true caso exista algum Serviço que utilize o Tipo de Serviço recebido
	 */
	public boolean findByClient(Client client) {
		Cursor c = db.query(DAO.TBL_SERVICE, null, FIELD_SERVICE[1] + "=" + client.get_id(), null, null, null, null);
		
		if(c.moveToFirst()){
			return true;
		}
		return false;
	}	
	
=======
>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
	public List<Service> findAll()
	{
		List<Service> list = new ArrayList<Service>();
			
		Cursor c = db.query(DAO.TBL_SERVICE, null, null, null, null, null, "_id_client ASC, date DESC, name ASC");
		
		c.moveToFirst();
		while(!c.isAfterLast())
		{
			Service srv = loadService(c);
			
			c.moveToNext();
			list.add(srv);
		}
		c.close();
		
		return list;
	}
	
	
	
	
}
