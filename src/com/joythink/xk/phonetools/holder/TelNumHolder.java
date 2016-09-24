package com.joythink.xk.phonetools.holder;

import com.joythink.xk.phonetools.base.BaseHolder;
import com.joythink.xk.phonetools.entity.TelNum;
import com.joythink.xk.phonetools.phone.R;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


public class TelNumHolder extends BaseHolder<TelNum> {
	TextView tv_name, tv_number;
	
	public TelNumHolder(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		View v = View.inflate(context, R.layout.layout_item_telnum, null);
		tv_name = (TextView) v.findViewById(R.id.tv_name);
		tv_number = (TextView) v.findViewById(R.id.tv_number);
		return v;
	}

	@Override
	public void setData(TelNum t,boolean isFling) {
		tv_name.setText(t.getName());
		tv_number.setText(t.getNumber());
	}

}
