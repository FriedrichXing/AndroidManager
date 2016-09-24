package com.joythink.xk.phonetools.adapter;

import android.content.Context;

import com.joythink.xk.phonetools.base.BaseHolder;
import com.joythink.xk.phonetools.base.MyBaseAdapter;
import com.joythink.xk.phonetools.entity.PhoneInfo;
import com.joythink.xk.phonetools.holder.PhoneInfoHolder;

public class PhoneInfoAdapter extends MyBaseAdapter<PhoneInfo> {

	@Override
	public BaseHolder<PhoneInfo> getHolder(Context context) {
		// TODO Auto-generated method stub
		return new PhoneInfoHolder(context);
	}

}
