package com.joythink.xk.phonetools.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joythink.xk.phonetools.base.BaseHolder;
import com.joythink.xk.phonetools.entity.PhoneInfo;
import com.joythink.xk.phonetools.phone.R;

public class PhoneInfoHolder extends BaseHolder<PhoneInfo> {
	ImageView img;
	TextView tv1,tv2;
	public PhoneInfoHolder(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		View v = View.inflate(context, R.layout.layout_item_phoneinfo, null);
		img = (ImageView) v.findViewById(R.id.iv_icon);
		tv1 = (TextView) v.findViewById(R.id.tv_label);
		tv2 = (TextView) v.findViewById(R.id.tv_packageName);
		return v;
	}

	@Override
	public void setData(PhoneInfo t,boolean isFling) {
		// TODO Auto-generated method stub
		img.setImageResource(t.getImgResId());
		tv1.setText(t.getTv1());
		tv2.setText(t.getTv2());
	}

}
