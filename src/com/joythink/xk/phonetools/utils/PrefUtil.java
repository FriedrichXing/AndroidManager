package com.joythink.xk.phonetools.utils;


import com.joythink.xk.phonetools.app.GlobalApplication;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtil {
	private static SharedPreferences sp =GlobalApplication.getContext().getSharedPreferences("setting", Context.MODE_PRIVATE);
	
	public static void save(Context context,String key,boolean value){
		sp.edit().putBoolean(key, value).commit();
	}
	
	public static boolean load(Context context,String key,boolean defValue){
		return sp.getBoolean(key, defValue);
	}
	public static void save(String key,boolean value){
		sp.edit().putBoolean(key, value).commit();
	}
	
	public static boolean load(String key,boolean defValue){
		return sp.getBoolean(key, defValue);
	}
}
