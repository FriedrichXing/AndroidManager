package com.joythink.xk.phonetools.app;

import android.app.Application;

public class GlobalApplication extends Application {
	public static GlobalApplication app;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		app = this;
	}
	
	public static Application getContext(){
		return app;
	}
}
