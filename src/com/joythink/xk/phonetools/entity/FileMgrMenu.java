package com.joythink.xk.phonetools.entity;

public class FileMgrMenu {
	private String name;
	private long size;
	private boolean isShowArrow;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public boolean isShowArrow() {
		return isShowArrow;
	}
	public void setShowArrow(boolean isShowArrow) {
		this.isShowArrow = isShowArrow;
	}
	public FileMgrMenu(String name, long size, boolean isShowArrow) {
		super();
		this.name = name;
		this.size = size;
		this.isShowArrow = isShowArrow;
	}
	
}
