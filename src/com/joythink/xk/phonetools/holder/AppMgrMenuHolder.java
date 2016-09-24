package com.joythink.xk.phonetools.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.joythink.xk.phonetools.base.BaseHolder;
import com.joythink.xk.phonetools.entity.AppMgrMenu;
import com.joythink.xk.phonetools.phone.R;

public class AppMgrMenuHolder extends BaseHolder<AppMgrMenu> {
	TextView tv_name;
	public AppMgrMenuHolder(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		View v = View.inflate(context, R.layout.layout_appmgrmenu, null);
		tv_name = (TextView) v.findViewById(R.id.tv_name);
		return v;
	}

	@Override
	public void setData(AppMgrMenu t,boolean isFling) {
		// TODO Auto-generated method stub
		tv_name.setText(t.getName());
	}

}
