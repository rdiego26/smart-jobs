package info.diegoramos.smartjobs.persistence;

import info.diegoramos.smartjobs.model.Client;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAOClient {
	private static final String[] FIELD_CLIENT = {"_id", "name", "address", "phone", "celular", "email"};
	protected SQLiteDatabase db;
	
	private static DAOClient instance = new DAOClient(); //Singleton		
	
	
	public static DAOClient getInstance(Context ctx)
	{
		if(instance.db == null || !instance.db.isOpen())
		{
			instance.db = new DAO(ctx).getWritableDatabase();
		}
		return instance;
	}	
	
	//CRUD Methods
	/*
	 * Save function
	 * @param Client
	 * @return indicator to success(row id) or fail(-1) transation.
	 * */	
	public long save(Client c)
	{
		long result = -1;
		
		db.beginTransaction();
		
		try
		{
			ContentValues cv = new ContentValues();
			cv.put(FIELD_CLIENT[1], c.getName());
			cv.put(FIELD_CLIENT[2], c.getAddress());
			cv.put(FIELD_CLIENT[3], c.getPhone());
			cv.put(FIELD_CLIENT[4], c.getCelular());
			cv.put(FIELD_CLIENT[5], c.getEmail());
			
			//Save data
			result = db.insert(DAO.TBL_CLIENT, null, cv);
		
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

	/*
	 * Update function
	 * @param Client
	 * @return indicator to success(0) or fail(-1) transation.
	 * */	
	public int update(Client cli)
	{
		int result = 0;
		
		if(checkBeforeUpdate(cli)) {
		
			try
			{
				db.beginTransaction();
				ContentValues cv = new ContentValues();
				cv.put(FIELD_CLIENT[1], cli.getName());
				cv.put(FIELD_CLIENT[2], cli.getAddress());
				cv.put(FIELD_CLIENT[3], cli.getPhone());
				cv.put(FIELD_CLIENT[4], cli.getCelular());
				cv.put(FIELD_CLIENT[5], cli.getEmail());
				
				db.update(DAO.TBL_CLIENT, cv, FIELD_CLIENT[0] + " = ?", new String[]{cli.get_id().toString()});
				db.setTransactionSuccessful();

				return 0;
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
		else {
			result = -1;
		}
		
		return result;
	}		
	
<<<<<<< HEAD
	public long delete(int id, Context ctx)
=======
	public Integer delete(int id, Context ctx)
>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
	{
		
		Integer result = 0;
		
		DAOClient daoC;
		daoC = DAOClient.getInstance(ctx);
		
<<<<<<< HEAD
		if( !daoC.findById(id) ) {
			result = -1;
		}
		else {
			Client c = daoC.getById(id);
			
			if(beforeDelete(c, ctx)) {
				try
				{
					
					db.beginTransaction();
					db.delete(DAO.TBL_CLIENT, FIELD_CLIENT[0] + " = ?", new String[]{String.valueOf(id)});
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
			else {
				result = -1;
			}
=======
		if( !daoC.findById(id) )
		{
			result = -1;
		}
		else
		{
			try
			{
				
				db.beginTransaction();
				db.delete(DAO.TBL_CLIENT, FIELD_CLIENT[0] + " = ?", new String[]{String.valueOf(id)});
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
>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
			
		}

		return result;
	}	
	
	/* Search Methods  */
	public boolean findById(int id)
	{		
		Cursor c = db.query(DAO.TBL_CLIENT, null, FIELD_CLIENT[0] + " = " + id, null, null, null, "name ASC");
		
		if(c.getCount() > 0)
		{
			c.close();
			return true;
		}
		c.close();
		return false;
	}
	
	public Client getById(int id) {
		Client cli = null;
		Cursor c = db.query(DAO.TBL_CLIENT, null, FIELD_CLIENT[0] + " = " + String.valueOf(id), null, null, null, "name ASC LIMIT 1");
	
		if(c.moveToFirst()){

			cli = loadClient(c);
			c.close();
			
			return cli;			
		}
			c.close();
		
		return cli;
	}	
	
	public Client getByName(String name) {
		Client cli = null;
		Cursor c = db.query(DAO.TBL_CLIENT, null, FIELD_CLIENT[1] + " = '" + name + "' ", null, null, null, "name ASC LIMIT 1");
	
		if(c.moveToFirst()){

			cli = loadClient(c);
			c.close();
			
			return cli;			
		}
			c.close();
		
		return cli;
	}	
	
	public List<Client> getAll()
	{
		List<Client> list = new ArrayList<Client>();
			
		Cursor c = db.query(DAO.TBL_CLIENT, null, null, null, null, null, "name ASC");
		
		c.moveToFirst();
		while(!c.isAfterLast())
		{
			Client cli = loadClient(c);
			
			c.moveToNext();
			list.add(cli);
		}
		c.close();
		
		return list;
	}
	
	public List<String> getNameAll()
	{
		List<String> list = new ArrayList<String>();
			
		Cursor c = db.query(DAO.TBL_CLIENT, null, null, null, null, null, "name ASC");
		
		c.moveToFirst();
		while(!c.isAfterLast())
		{
			Client cli = loadClient(c);
			
			c.moveToNext();
			list.add(cli.getName());
		}
		c.close();
		
		return list;
	}
	
	
	private Client loadClient(Cursor c)
	{
		Client cli = null;
		
		if(c.getCount() > 0) {
			Integer id = c.getInt(c.getColumnIndexOrThrow(FIELD_CLIENT[0]));
			String  name     =  c.getString(c.getColumnIndexOrThrow(FIELD_CLIENT[1]));
			String address = c.getString(c.getColumnIndexOrThrow(FIELD_CLIENT[2]));
			String phone = c.getString(c.getColumnIndexOrThrow(FIELD_CLIENT[3]));
			String celular = c.getString(c.getColumnIndexOrThrow(FIELD_CLIENT[4]));
			String email = c.getString(c.getColumnIndexOrThrow(FIELD_CLIENT[5]));
		
			cli = new Client(id, name, address, phone, celular, email);
		}
		return cli;
	}
	
	public boolean findByName(String n) {
		Cursor c = db.query(DAO.TBL_CLIENT, null, FIELD_CLIENT[1] + " = '" + n + "' ", null, null, null, "name ASC LIMIT 1");
		
		if(c.getCount() > 0) {
			c.close();
			return true;
		}
		c.close();
		return false;
	}
	
	/* Method ensures data integrity
	 * @param Client
	 * @return boolean indicates integrity(true) or not(false)
	 * */	
	public boolean checkBeforeUpdate(Client c) {
		Cursor cur = db.query(DAO.TBL_CLIENT, null, FIELD_CLIENT[1] + " = '" + c.getName() + "' AND "+ FIELD_CLIENT[0] + " <> " + c.get_id(), null, null, null, "name ASC LIMIT 1");
		
		if(cur.getCount() > 0) {
			cur.close();
			return false;
		}
		cur.close();
		return true;
	}
	
	public int count() {
		Cursor c = db.rawQuery("SELECT COUNT(*) FROM " + DAO.TBL_CLIENT, null);
		
		return c.getInt(0);
	}
<<<<<<< HEAD

	
	/**
	 * Verifica se o Objeto passado está sendo usado em algum serviço
	 * @param Client
	 * @return false quando não puder ser feita a exclusão
	 */
	private boolean beforeDelete(Client client, Context ctx) {
		DAOService daoService = DAOService.getInstance(ctx);
		
		if(daoService.findByClient(client)) {
			return false;
		}
		
		return true;
	}	
	
}
=======
	
}
>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
