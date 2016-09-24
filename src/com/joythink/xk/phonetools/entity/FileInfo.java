package com.joythink.xk.phonetools.entity;

import java.io.File;

import android.graphics.drawable.Drawable;

public class FileInfo {
	private String fileIcon;
	private File file;
	private String fileType;
	private boolean isChecked;

	public String getFileIcon() {
		return fileIcon;
	}

	public void setFileIcon(String fileIcon) {
		this.fileIcon = fileIcon;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public FileInfo(String fileIcon, String fileType, File file,
			boolean isChecked) {
		super();
		this.fileIcon = fileIcon;
		this.file = file;
		this.fileType = fileType;
		this.isChecked = isChecked;
	}

}
