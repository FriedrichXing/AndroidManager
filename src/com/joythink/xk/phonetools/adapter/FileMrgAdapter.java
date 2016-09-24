package com.joythink.xk.phonetools.adapter;

import android.content.Context;

import com.joythink.xk.phonetools.base.BaseHolder;
import com.joythink.xk.phonetools.base.MyBaseAdapter;
import com.joythink.xk.phonetools.entity.FileMgrMenu;
import com.joythink.xk.phonetools.holder.FileMgrMenuHolder;

public class FileMrgAdapter extends MyBaseAdapter<FileMgrMenu> {

	@Override
	public BaseHolder<FileMgrMenu> getHolder(Context context) {
		// TODO Auto-generated method stub
		return new FileMgrMenuHolder(context);
	}

}
