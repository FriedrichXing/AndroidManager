package com.joythink.xk.phonetools.entity;

public class PhoneSetting {
	private boolean isShowArrow;//�Ƿ�����ʾ��ͷ
	private String title;//�˵��ı�������
	private boolean isChecked;//togglebutton�Ƿ���ѡ����
	public PhoneSetting(boolean isShowArrow, String title) {
		super();
		this.isShowArrow = isShowArrow;
		this.title = title;
	}
		public PhoneSetting(boolean isShowArrow, String title, boolean isChecked) {
		super();
		this.isShowArrow = isShowArrow;
		this.title = title;
		this.isChecked = isChecked;
	}
	public boolean isShowArrow() {
		return isShowArrow;
	}
	public void setShowArrow(boolean isShowArrow) {
		this.isShowArrow = isShowArrow;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	
}
