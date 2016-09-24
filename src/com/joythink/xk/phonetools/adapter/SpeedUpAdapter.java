package com.joythink.xk.phonetools.adapter;

import android.content.Context;

import com.joythink.xk.phonetools.base.BaseHolder;
import com.joythink.xk.phonetools.base.MyBaseAdapter;
import com.joythink.xk.phonetools.entity.Menus;
import com.joythink.xk.phonetools.entity.SpeedUpMenu;
import com.joythink.xk.phonetools.holder.SpeedUpHolder;

public class SpeedUpAdapter extends MyBaseAdapter<SpeedUpMenu> {

	@Override
	public BaseHolder<SpeedUpMenu> getHolder(Context context) {
		// TODO Auto-generated method stub
		return new SpeedUpHolder(context);
	}

}
