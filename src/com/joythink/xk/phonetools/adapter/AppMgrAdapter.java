package com.joythink.xk.phonetools.adapter;

import android.content.Context;

import com.joythink.xk.phonetools.base.BaseHolder;
import com.joythink.xk.phonetools.base.MyBaseAdapter;
import com.joythink.xk.phonetools.entity.AppMgrMenu;
import com.joythink.xk.phonetools.holder.AppMgrMenuHolder;

public class AppMgrAdapter extends MyBaseAdapter<AppMgrMenu> {

	@Override
	public BaseHolder<AppMgrMenu> getHolder(Context context) {
		// TODO Auto-generated method stub
		return new AppMgrMenuHolder(context);
	}

}
