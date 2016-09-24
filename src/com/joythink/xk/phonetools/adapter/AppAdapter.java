package com.joythink.xk.phonetools.adapter;


import com.joythink.xk.phonetools.base.BaseHolder;
import com.joythink.xk.phonetools.base.MyBaseAdapter;
import com.joythink.xk.phonetools.entity.AppInfo;
import com.joythink.xk.phonetools.holder.AppHolder;

import android.content.Context;

public class AppAdapter extends MyBaseAdapter<AppInfo> {

	@Override
	public BaseHolder<AppInfo> getHolder(Context context) {
		// TODO Auto-generated method stub
		return new AppHolder(context);
	}

}
