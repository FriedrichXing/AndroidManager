package com.joythink.xk.phonetools.adapter;

import android.content.Context;

import com.joythink.xk.phonetools.base.BaseHolder;
import com.joythink.xk.phonetools.base.MyBaseAdapter;
import com.joythink.xk.phonetools.entity.FileInfo;
import com.joythink.xk.phonetools.holder.FileListHolder;

public class FileListAdapter extends MyBaseAdapter<FileInfo> {

	@Override
	public BaseHolder<FileInfo> getHolder(Context context) {
		// TODO Auto-generated method stub
		return new FileListHolder(context);
	}
	
}
