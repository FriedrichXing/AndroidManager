package com.joythink.xk.phonetools.adapter;

import android.content.Context;

import com.joythink.xk.phonetools.base.BaseHolder;
import com.joythink.xk.phonetools.base.MyBaseAdapter;
import com.joythink.xk.phonetools.entity.Menus;
import com.joythink.xk.phonetools.holder.MenuHolder;

public class MenuAdapter extends MyBaseAdapter<Menus> {

	@Override
	public BaseHolder<Menus> getHolder(Context context) {
		// TODO Auto-generated method stub
		return new MenuHolder(context);
	}

}
