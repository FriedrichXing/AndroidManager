package com.joythink.xk.phonetools.utils;

import android.util.Log;

public class LogUtil {

	private LogUtil() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private static boolean isDebug = true;
	
	public static void stopLog(){
		isDebug = false;
	}
	
	public static void startLog(){
		isDebug = true;
	}
	
	public static void d(Object obj,String msg){
		if (isDebug) {
			Log.d(obj.getClass().getSimpleName(), msg);
		}
	}
}
