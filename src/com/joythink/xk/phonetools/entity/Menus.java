package com.joythink.xk.phonetools.entity;

public class Menus {
	private int imgResId;
	private int name;
	public int getImgResId() {
		return imgResId;
	}
	public void setImgResId(int imgResId) {
		this.imgResId = imgResId;
	}
	public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	public Menus(int imgResId, int name) {
		super();
		this.imgResId = imgResId;
		this.name = name;
	}
	
	
}
