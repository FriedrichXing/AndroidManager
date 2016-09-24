package com.joythink.xk.phonetools.entity;

import android.graphics.drawable.Drawable;

public class AppInfo {
	private boolean isChecked;
	private Drawable icon;
	private String label,packageName;
	private long firstInstallTime;
	
	public AppInfo(boolean isChecked, Drawable icon, String label,
			String packageName, long firstInstallTime) {
		super();
		this.isChecked = isChecked;
		this.icon = icon;
		this.label = label;
		this.packageName = packageName;
		this.firstInstallTime = firstInstallTime;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public long getFirstInstallTime() {
		return firstInstallTime;
	}
	public void setFirstInstallTime(long firstInstallTime) {
		this.firstInstallTime = firstInstallTime;
	}
	
}
