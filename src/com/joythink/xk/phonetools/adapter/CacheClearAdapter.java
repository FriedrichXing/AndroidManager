package com.joythink.xk.phonetools.adapter;

import android.content.Context;

import com.joythink.xk.phonetools.base.BaseHolder;
import com.joythink.xk.phonetools.base.MyBaseAdapter;
import com.joythink.xk.phonetools.entity.CacheInfo;
import com.joythink.xk.phonetools.holder.CacheClearHolder;

public class CacheClearAdapter extends MyBaseAdapter<CacheInfo> {

	@Override
	public BaseHolder<CacheInfo> getHolder(Context context) {
		// TODO Auto-generated method stub
		return new CacheClearHolder(context);
	}

}
