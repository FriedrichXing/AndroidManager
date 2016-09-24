package com.joythink.xk.phonetools.db;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.joythink.xk.phonetools.entity.CacheInfo;
import com.joythink.xk.phonetools.mgr.MemoryManager;
import com.joythink.xk.phonetools.phone.R;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

public class ClearDBManager {
	private Context context;
	public ClearDBManager(Context context, ClearListener listener) {
		super();
		this.context = context;
		this.setListener(listener);
	}

	public List<CacheInfo> getCacheList() {
		String path = AssetsFileManager.copyFile(context, "db/clearpath.db");
		ArrayList<CacheInfo> list = new ArrayList<CacheInfo>();
		if (path != null) {
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(path, null);
			Cursor c = db.rawQuery("select * from softdetail", null);
			while (c.moveToNext()) {
				int _id = c.getInt(c.getColumnIndex("_id"));
				String softChinesename = c.getString(c
						.getColumnIndex("softChinesename"));
				String softEnglishname = c.getString(c
						.getColumnIndex("softEnglishname"));
				String apkname = c.getString(c.getColumnIndex("apkname"));
				String filepath = c.getString(c.getColumnIndex("filepath"));
				filepath = MemoryManager.getPhoneInSDCardPath() + filepath;
				File file = new File(filepath);
				if (file.exists()) {
					Drawable icon = null;
					try {
						icon = context.getPackageManager().getApplicationIcon(
								apkname);
					} catch (NameNotFoundException e) {
						icon = context.getResources().getDrawable(
								R.drawable.ic_launcher);
					}

					long size = getFileSize(file);
					if (listener!=null) {
						listener.publishSize(size);
					}
					list.add(new CacheInfo(_id, softChinesename,
							softEnglishname, apkname, filepath, icon, size,
							false));
				}
			}
		}
		return list;
	}

	private ClearListener listener;
	public interface ClearListener{
		void publishSize(long size);
	}
	
	private static long getFileSize(File file) {
		// TODO Auto-generated method stub
		long size = 0;
		if (!file.isDirectory()) {
			size = file.length();
		} else {
			File[] listFiles = file.listFiles();
			for (File f : listFiles) {
				size += getFileSize(f);
			}
		}
		return size;
	}

	public ClearListener getListener() {
		return listener;
	}

	public void setListener(ClearListener listener) {
		this.listener = listener;
	}
}
