package info.diegoramos.smartjobs.model;

import java.io.Serializable;

public class TypeOfService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer _id;
	private String name;
	
	
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
	
	public TypeOfService(Integer _id, String name) {
		super();
		this._id = _id;
		this.name = name;
	}
	
	public TypeOfService(String name) {
		super();
		this.name = name;
	}
	
	
	
}
