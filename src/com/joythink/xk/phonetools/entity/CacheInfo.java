package com.joythink.xk.phonetools.entity;

import android.graphics.drawable.Drawable;

public class CacheInfo {
	private int _id;
	private String softChinesename,softEnglishname,apkname,filepath;
	private Drawable icon;
	private long size;
	private boolean isChecked;
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getSoftChinesename() {
		return softChinesename;
	}
	public void setSoftChinesename(String softChinesename) {
		this.softChinesename = softChinesename;
	}
	public String getSoftEnglishname() {
		return softEnglishname;
	}
	public void setSoftEnglishname(String softEnglishname) {
		this.softEnglishname = softEnglishname;
	}
	public String getApkname() {
		return apkname;
	}
	public void setApkname(String apkname) {
		this.apkname = apkname;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public CacheInfo(int _id, String softChinesename, String softEnglishname,
			String apkname, String filepath, Drawable icon, long size,
			boolean isChecked) {
		super();
		this._id = _id;
		this.softChinesename = softChinesename;
		this.softEnglishname = softEnglishname;
		this.apkname = apkname;
		this.filepath = filepath;
		this.icon = icon;
		this.size = size;
		this.isChecked = isChecked;
	}
	
}
