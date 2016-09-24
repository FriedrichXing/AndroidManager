package com.joythink.xk.phonetools.holder;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.joythink.xk.phonetools.base.BaseHolder;
import com.joythink.xk.phonetools.entity.CacheInfo;
import com.joythink.xk.phonetools.phone.R;
import com.joythink.xk.phonetools.utils.CommonUtil;

public class CacheClearHolder extends BaseHolder<CacheInfo> {
	CheckBox cb_check;
	TextView tv_name,tv_path,tv_size;
	ImageView iv_icon;
	public CacheClearHolder(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		View v = View.inflate(context, R.layout.layout_item_cacheinfo, null);
		cb_check = (CheckBox) v.findViewById(R.id.cb_check);
		tv_name = (TextView) v.findViewById(R.id.tv_name);
		tv_path = (TextView) v.findViewById(R.id.tv_path);
		tv_size = (TextView) v.findViewById(R.id.tv_size);
		iv_icon = (ImageView) v.findViewById(R.id.iv_icon);
		return v;
	}

	@Override
	public void setData(final CacheInfo t, boolean isFling) {
		// TODO Auto-generated method stub
		cb_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				t.setChecked(isChecked);
			}
		});
		cb_check.setChecked(t.isChecked());
		tv_name.setText(t.getSoftChinesename());
		tv_path.setText(t.getApkname());
		tv_size.setText(CommonUtil.getFileSize(t.getSize()));
		iv_icon.setImageDrawable(t.getIcon());
	}

}
