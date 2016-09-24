package com.joythink.xk.phonetools.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joythink.xk.phonetools.base.BaseHolder;
import com.joythink.xk.phonetools.entity.Menus;
import com.joythink.xk.phonetools.phone.R;

public class MenuHolder extends BaseHolder<Menus> {
	
	ImageView iv_menu;
	TextView tv_menu;
	public MenuHolder(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		View v = View.inflate(context, R.layout.layout_item_menu, null);
		iv_menu = (ImageView) v.findViewById(R.id.iv_menu);
		tv_menu = (TextView) v.findViewById(R.id.tv_menu);
		return v;
	}

	@Override
	public void setData(Menus t,boolean isFling) {
		// TODO Auto-generated method stub
		iv_menu.setImageResource(t.getImgResId());
		tv_menu.setText(t.getName());
	}

}
