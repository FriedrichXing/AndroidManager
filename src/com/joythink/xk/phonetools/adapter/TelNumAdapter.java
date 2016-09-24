package com.joythink.xk.phonetools.adapter;

import com.joythink.xk.phonetools.base.BaseHolder;
import com.joythink.xk.phonetools.base.MyBaseAdapter;
import com.joythink.xk.phonetools.entity.TelNum;
import com.joythink.xk.phonetools.holder.TelNumHolder;

import android.content.Context;


public class TelNumAdapter extends MyBaseAdapter<TelNum> {

	@Override
	public BaseHolder<TelNum> getHolder(Context context) {
		// TODO Auto-generated method stub
		return new TelNumHolder(context);
	}

}
