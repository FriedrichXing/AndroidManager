package com.joythink.xk.phonetools.entity;

import android.graphics.drawable.Drawable;

public class SpeedUpMenu {
	private boolean isChecked;
	private Drawable icon;
	private String label;
	private long memory;
	private boolean isSystem;
	private String packageName;
	
	public SpeedUpMenu() {
		super();
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
	public long getMemory() {
		return memory;
	}
	public void setMemory(long memory) {
		this.memory = memory;
	}
	public boolean isSystem() {
		return isSystem;
	}
	public void setSystem(boolean isSystem) {
		this.isSystem = isSystem;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public SpeedUpMenu(boolean isChecked, Drawable icon, String label,
			long memory, boolean isSystem, String packageName) {
		super();
		this.isChecked = isChecked;
		this.icon = icon;
		this.label = label;
		this.memory = memory;
		this.isSystem = isSystem;
		this.packageName = packageName;
	}
	
}
