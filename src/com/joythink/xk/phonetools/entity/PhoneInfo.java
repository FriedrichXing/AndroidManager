package com.joythink.xk.phonetools.entity;

public class PhoneInfo {
	private int imgResId;
	private String tv1,tv2;
	public int getImgResId() {
		return imgResId;
	}
	public void setImgResId(int imgResId) {
		this.imgResId = imgResId;
	}
	public String getTv1() {
		return tv1;
	}
	public void setTv1(String tv1) {
		this.tv1 = tv1;
	}
	public String getTv2() {
		return tv2;
	}
	public void setTv2(String tv2) {
		this.tv2 = tv2;
	}
	public PhoneInfo(int imgResId, String tv1, String tv2) {
		super();
		this.imgResId = imgResId;
		this.tv1 = tv1;
		this.tv2 = tv2;
	}
	
}
