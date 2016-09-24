package com.joythink.xk.phonetools.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.util.LruCache;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.joythink.xk.phonetools.base.BaseHolder;
import com.joythink.xk.phonetools.entity.AppInfo;
import com.joythink.xk.phonetools.phone.R;
import com.joythink.xk.phonetools.utils.CommonUtil;

public class AppHolder extends BaseHolder<AppInfo> {
	CheckBox cb_check1;
	ImageView iv_icon;
	TextView tv_label, tv_memory, tv_system;

	public AppHolder(Context context) {
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
	public void setData(final AppInfo t,boolean isFling) {
		// TODO Auto-generated method stub
		cb_check1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				t.setChecked(isChecked);
			}
		});
		
		cb_check1.setChecked(t.isChecked());
		if (isFling) {
			iv_icon.setImageResource(R.drawable.ic_launcher);
		} else {
			Bitmap bitmap = null;
			bitmap = get(t.getPackageName());
			if (bitmap==null) {
				Drawable drawable = t.getIcon();
				BitmapDrawable bd = (BitmapDrawable) drawable;
				bitmap = bd.getBitmap();
				put(t.getPackageName(), bitmap); 
			}
			iv_icon.setImageBitmap(bitmap);
		}
		tv_label.setText(t.getLabel());
		tv_memory.setText(t.getPackageName());
		tv_system.setText(CommonUtil.getStrTime(t.getFirstInstallTime()));
		

	}

}
