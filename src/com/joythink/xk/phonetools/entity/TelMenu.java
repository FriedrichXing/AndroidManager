package com.joythink.xk.phonetools.entity;

public class TelMenu {
//属性必须和数据库的表中的列是对应的。
private String name;
private int idx;
public TelMenu(String name, int idx) {
	super();
	this.name = name;
	this.idx = idx;
}
/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}
/**
 * @return the idx
 */
public int getIdx() {
	return idx;
}
/**
 * @param idx the idx to set
 */
public void setIdx(int idx) {
	this.idx = idx;
}
}
