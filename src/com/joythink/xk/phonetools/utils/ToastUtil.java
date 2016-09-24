package com.joythink.xk.phonetools.utils;


import com.joythink.xk.phonetools.app.GlobalApplication;

import android.widget.Toast;

public class ToastUtil {
	private static Toast toast;
	private ToastUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void show(String text){
		if (toast == null) {
			toast = Toast.makeText(GlobalApplication.getContext(), text, Toast.LENGTH_SHORT);
		} else {
			toast.setText(text);
		}
		toast.show();
	}
}
