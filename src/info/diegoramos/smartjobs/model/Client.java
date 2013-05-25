package info.diegoramos.smartjobs.model;

import java.io.Serializable;

public class Client implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer _id;
	private String name;
	private String address;
	private String phone;
	private String celular;
	private String email;
		
	public Client(Integer _id, String name, String address, String phone,
			String celular, String email) {
		super();
		this._id = _id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.celular = celular;
		this.email = email;
	}

	public Client(String name, String address, String phone,
			String celular, String email) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.celular = celular;
		this.email = email;
	}	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer get_id() {
		return _id;
	}
	public void set_id(Integer _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	
	
}
