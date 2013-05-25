package info.diegoramos.smartjobs.model;

import java.io.Serializable;

public class Service implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer _id;
	private Integer _id_client;
	private Integer _id_type_of_service;
	private String name;
	private long date;
	private float price;
	private String details;
	
	public Service(Integer _id_client, Integer _id_type_of_service, String name, long date,
			float price, String details) {
		super();
		this._id_client = _id_client;
		this._id_type_of_service = _id_type_of_service;
		this.name = name;
		this.date = date;
		this.price = price;
		this.details = details;
	}

	public Service(Integer _id, Integer _id_client, Integer _id_type_of_service, String name, long date,
			float price, String details) {
		super();
		this._id = _id;
		this._id_client = _id_client;
		this._id_type_of_service = _id_type_of_service;
		this.name = name;
		this.date = date;
		this.price = price;
		this.details = details;
	}	
	
	public Integer get_id() {
		return _id;
	}
	public void set_id(Integer _id) {
		this._id = _id;
	}
	public Integer get_id_client() {
		return _id_client;
	}
	public void set_id_client(Integer _id_client) {
		this._id_client = _id_client;
	}
	public Integer get_id_type_of_service() {
		return _id_type_of_service;
	}
	public void set_id_type_of_service(Integer _id_type_of_service) {
		this._id_type_of_service = _id_type_of_service;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	
	
	
	
}
