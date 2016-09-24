package com.joythink.xk.phonetools.db;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.joythink.xk.phonetools.entity.TelMenu;
import com.joythink.xk.phonetools.entity.TelNum;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CommonDBManager {
	public static String filePath;

	public static List<TelMenu> getTelMenu(Context context) {

		filePath = AssetsFileManager.copyFile(context, "db/commonnum.db");
		List<TelMenu> list = new ArrayList<TelMenu>();
		if (filePath != null) {
			SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(
					filePath, null);
			String sql = "select * from classlist";
			Cursor c = database.rawQuery(sql, null);
			while (c.moveToNext()) {
				String name = c.getString(c.getColumnIndex("name"));
				int idx = c.getInt(c.getColumnIndex("idx"));
				list.add(new TelMenu(name, idx));
			}
		}
		return list;
	}

	public static List<TelNum> getTableList(int idx) {
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(filePath, null);
		String sql = "select * from table" + idx;
		Cursor c = db.rawQuery(sql, null);
		List<TelNum> list = new ArrayList<TelNum>();
		while (c.moveToNext()) {
			int _id = c.getInt(c.getColumnIndex("_id"));
			String name = c.getString(c.getColumnIndex("name"));
			String number = c.getString(c.getColumnIndex("number"));
			list.add(new TelNum(_id, number, name));
		}

		return list;
	}

	public static int delete(int idx, int _id) {
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(filePath, null);
		return db.delete("table" + idx, "_id=?", new String[] { _id + "" });
	}

	public static void reset(Context context) {
		SQLiteDatabase.deleteDatabase(new File(filePath));
		AssetsFileManager.copyFile(context, "db/commonnum.db");
	}

	public static void edit(int idx, TelNum telNum) {
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(filePath, null);
		ContentValues values = new ContentValues();
		values.put("name", telNum.getName());
		values.put("number", telNum.getNumber());
		db.update("table" + idx, values, "_id=?",
				new String[] { telNum.get_id() + "" });
	}

	public static void add(int idx, String name, String number) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(filePath, null);
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("number", number);
		db.insert("table" + idx, null, values);
	}

	public static List<TelNum> getTableByName(int idx, String str) {
		// TODO Auto-generated method stub
//		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(filePath, null);
//		String sql = "select * from table" + idx +" where name like '%"+str+"'%";
//		Cursor c = db.rawQuery(sql, null);
//		List<TelNum> list = new ArrayList<TelNum>();
//		while (c.moveToNext()) {
//			int _id = c.getInt(c.getColumnIndex("_id"));
//			String name = c.getString(c.getColumnIndex("name"));
//			String number = c.getString(c.getColumnIndex("number"));
//			list.add(new TelNum(_id, number, name));
//		}
//
//		return list;
		String sql = "select * from table" + idx + " where name like '%" + str + "%'";

		return getTableBySql(sql);
	}

	private static List<TelNum> getTableBySql(java.lang.String sql) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(filePath, null);
		Cursor c = db.rawQuery(sql, null);
		List<TelNum> list = new ArrayList<TelNum>();
		while (c.moveToNext()) {
			int _id = c.getInt(c.getColumnIndex("_id"));
			String name = c.getString(c.getColumnIndex("name"));
			String number = c.getString(c.getColumnIndex("number"));
			list.add(new TelNum(_id, number, name));
		}
		return list;
	}

	public static List<TelNum> getTableByNumber(int idx, String str) {
		// TODO Auto-generated method stub
		String sql = "select * from table" + idx + " where number like '%" + str + "%'";
		return getTableBySql(sql);
	}

}
