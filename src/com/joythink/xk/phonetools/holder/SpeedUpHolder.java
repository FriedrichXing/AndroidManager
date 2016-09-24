package com.joythink.xk.phonetools.holder;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.joythink.xk.phonetools.base.BaseHolder;
import com.joythink.xk.phonetools.entity.SpeedUpMenu;
import com.joythink.xk.phonetools.phone.R;
import com.joythink.xk.phonetools.utils.CommonUtil;

public class SpeedUpHolder extends BaseHolder<SpeedUpMenu> {
	CheckBox cb_check1;
	ImageView iv_icon;
	TextView tv_label,tv_memory,tv_system;
	public SpeedUpHolder(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		View v = View.inflate(context, R.layout.layout_item_speedup, null);
		cb_check1 = (CheckBox) v.findViewById(R.id.cb_check);
		iv_icon = (ImageView) v.findViewById(R.id.iv_icon);
		tv_label = (TextView) v.findViewById(R.id.tv_label);
		tv_memory = (TextView) v.findViewById(R.id.tv_memory);
		tv_system = (TextView) v.findViewById(R.id.tv_system);
		return v;
	}

	@Override
	public void setData(final SpeedUpMenu t,boolean isFling) {
		// TODO Auto-generated method stub
		cb_check1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				t.setChecked(isChecked);
			}
		});
		
		cb_check1.setChecked(t.isChecked());
		iv_icon.setImageDrawable(t.getIcon());
		tv_label.setText(t.getLabel());
		tv_memory.setText(CommonUtil.getFileSize(t.getMemory()));
		if (t.isSystem()) {
			tv_system.setVisibility(View.VISIBLE);
		} else {
			tv_system.setVisibility(View.INVISIBLE);
		}
	}

}
