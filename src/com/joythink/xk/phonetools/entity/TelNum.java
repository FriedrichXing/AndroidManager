package com.joythink.xk.phonetools.entity;

public class TelNum {
	private int _id;
	private String number,name;
	public TelNum(int _id, String number, String name) {
		super();
		this._id = _id;
		this.number = number;
		this.name = name;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
	
}
