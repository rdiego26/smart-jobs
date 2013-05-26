package info.diegoramos.smartjobs.persistence;

import info.diegoramos.smartjobs.model.TypeOfService;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAOTypeOfService {

	private static final String[] FIELD_TYPE_OF_SERVICE = {"_id", "name"};
	protected SQLiteDatabase db;
	private static DAOTypeOfService instance = new DAOTypeOfService(); //Singleton		
	
	/* 
	 * Implement Singleton to instance db
	 * 
	 * @param Context Application
	 * @return Instance db
	 * */
	public static DAOTypeOfService getInstance(Context ctx)
	{
		if(instance.db == null || !instance.db.isOpen())
		{
			instance.db = new DAO(ctx).getWritableDatabase();
		}
		return instance;
	}	
	
	/*
	 * Save function
	 * @param TypeOfService
	 * @return indicator to success(row id) or fail(-1) transation.
	 * */
	public long save(TypeOfService TOS)
	{
		long result = -1;
		
		db.beginTransaction();
		
		try
		{
			ContentValues cv = new ContentValues();
			cv.put(FIELD_TYPE_OF_SERVICE[1], TOS.getName());
			
			//Save data
			result = db.insert(DAO.TBL_TYPE_OF_SERVICE, null, cv);
		
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
	 * Delete function
	 * @param TypeOfService id, Application Context
	 * @return indicator to success(rows affected) or fail(-1) transation.
	 * */
	public Integer delete(int id, Context ctx)
	{
		
		Integer result = 0;
		
		DAOTypeOfService daoTOS;
		daoTOS = DAOTypeOfService.getInstance(ctx);
		
		if( !daoTOS.findById(id) )
		{
			result = -1;
		} else {
			TypeOfService typeOfService = daoTOS.getById(id);
			
			if(daoTOS.beforeDelete(typeOfService, ctx)) {
				try
				{
					
					db.beginTransaction();
					db.delete(DAO.TBL_TYPE_OF_SERVICE, FIELD_TYPE_OF_SERVICE[0] + " = ?", new String[]{String.valueOf(id)});
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
			}else {
				result = -1;
			}
		}

		return result;
	}	
	
	/*
	 * Update function
	 * @param TypeOfService
	 * @return indicator to success(0) or fail(-1) transation.
	 * */	
	public int update(TypeOfService t)
	{
		
		int result = 0;
		
		if(checkBeforeUpdate(t)) {
		
			try
			{
				db.beginTransaction();
				ContentValues cv = new ContentValues();
				cv.put(FIELD_TYPE_OF_SERVICE[1], t.getName());
				
				db.update(DAO.TBL_TYPE_OF_SERVICE, cv, FIELD_TYPE_OF_SERVICE[0] + " = ?", new String[]{t.get_id().toString()});
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
	
	
	/* Search Methods  */
	
	private TypeOfService loadTypeOfService(Cursor c)
	{
		TypeOfService TOS = null;
		
		if(c.getCount() > 0) {
			Integer idTypeOfService = c.getInt(c.getColumnIndexOrThrow(FIELD_TYPE_OF_SERVICE[0]));
			String  name     =  c.getString(c.getColumnIndexOrThrow(FIELD_TYPE_OF_SERVICE[1]));
	
			TOS = new TypeOfService(idTypeOfService, name);
		}
		return TOS;
	}
	
	public TypeOfService loadTypeOfServiceById(int id) {
		Cursor c = db.query(DAO.TBL_TYPE_OF_SERVICE, null, FIELD_TYPE_OF_SERVICE[0] + " = " + id, null, null, null, "name ASC");
		TypeOfService typeOfService = null;

		if(c.moveToFirst() ) {
			typeOfService = loadTypeOfService(c);
		}
		
		return typeOfService;
	}
	
	public List<TypeOfService> getAll()
	{
		List<TypeOfService> list = new ArrayList<TypeOfService>();
		
		Cursor c = db.query(DAO.TBL_TYPE_OF_SERVICE, null, null, null, null, null, "name ASC");
		
		c.moveToFirst();
		while(!c.isAfterLast())
		{
			TypeOfService TOS = loadTypeOfService(c);
			
			c.moveToNext();
			list.add(TOS);
		}
		c.close();
		
		return list;
	}
	
	public List<String> getNameAll()
	{
		List<String> list = new ArrayList<String>();
		
		Cursor c = db.query(DAO.TBL_TYPE_OF_SERVICE, null, null, null, null, null, "name ASC");
		
		c.moveToFirst();
		while(!c.isAfterLast())
		{
			TypeOfService TOS = loadTypeOfService(c);
			
			c.moveToNext();
			list.add(TOS.getName());
		}
		c.close();
		
		return list;
	}	
	
	public boolean findById(int id)
	{		
		Cursor c = db.query(DAO.TBL_TYPE_OF_SERVICE, null, FIELD_TYPE_OF_SERVICE[0] + " = " + id, null, null, null, "name ASC");
		
		if(c.getCount() > 0)
		{
			c.close();
			return true;
		}
		c.close();
		return false;
	}	

	public boolean findByName(String n) {
		Cursor c = db.query(DAO.TBL_TYPE_OF_SERVICE, null, FIELD_TYPE_OF_SERVICE[1] + " = '" + n + "' ", null, null, null, "name ASC LIMIT 1");
		
		if(c.getCount() > 0) {
			c.close();
			return true;
		}
		c.close();
		return false;
	}

	/* Method ensures data integrity
	 * @param TypeOfService
	 * @return boolean indicates integrity(true) or not(false)
	 * */
	public boolean checkBeforeUpdate(TypeOfService ts) {
		Cursor c = db.query(DAO.TBL_TYPE_OF_SERVICE, null, FIELD_TYPE_OF_SERVICE[1] + " = '" + ts.getName() + "' AND "+ FIELD_TYPE_OF_SERVICE[0] + " <> " + ts.get_id(), null, null, null, "name ASC LIMIT 1");
		
		if(c.getCount() > 0) {
			c.close();
			return false;
		}
		c.close();
		return true;
	}	

	public TypeOfService getById(int id) {
		TypeOfService ts = null;
		Cursor c = db.query(DAO.TBL_TYPE_OF_SERVICE, null, FIELD_TYPE_OF_SERVICE[0] + " = " + String.valueOf(id), null, null, null, "name ASC LIMIT 1");
	
		if(c.moveToFirst()){

			ts = loadTypeOfService(c);
			c.close();
			
			return ts;			
		}
			c.close();
		
		return ts;
	}

	public TypeOfService getByName(String name) {
		TypeOfService ts = null;
		
		Cursor c = db.query(DAO.TBL_TYPE_OF_SERVICE, null, FIELD_TYPE_OF_SERVICE[1] + " = '" + name + "' ", null, null, null, "name ASC LIMIT 1");
	
		if(c.moveToFirst()){

			ts = loadTypeOfService(c);
			c.close();
			
			return ts;			
		}
			c.close();
		
		return ts;
	}
	
	public int count() {
		Cursor c = db.rawQuery("SELECT COUNT(*) FROM " + DAO.TBL_TYPE_OF_SERVICE, null);
		
		return c.getInt(0);
	}
	
	/**
	 * Verifica se o Objeto passado está sendo usado em algum serviço
	 * @param typeOfService
	 * @return false quando não puder ser feita a exclusão
	 */
	private boolean beforeDelete(TypeOfService typeOfService, Context ctx) {
		DAOService daoService = DAOService.getInstance(ctx);
		
		if(daoService.findByTypeOfService(typeOfService)) {
			return false;
		}
		
		return true;
	}
	
}