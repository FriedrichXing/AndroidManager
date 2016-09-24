package com.joythink.xk.phonetools.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.joythink.xk.phonetools.base.MySimpleBaseAdapter;
import com.joythink.xk.phonetools.entity.PhoneSetting;
import com.joythink.xk.phonetools.phone.R;
import com.joythink.xk.phonetools.phone.SettingActivity;
import com.joythink.xk.phonetools.utils.NotificationUtil;
import com.joythink.xk.phonetools.utils.PrefUtil;

public class SettingAdapter extends MySimpleBaseAdapter<PhoneSetting> {

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = View.inflate(parent.getContext(),
					R.layout.layout_item_setting1, null);
		}
		TextView tv_title = (TextView) convertView
				.findViewById(R.id.tv_settingName);
		ImageView iv_arrow = (ImageView) convertView
				.findViewById(R.id.iv_settingImg);
		ToggleButton toggle1 = (ToggleButton) convertView
				.findViewById(R.id.toggle1);

		final PhoneSetting t = getItem(position);
		toggle1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				t.setChecked(isChecked);
				switch (position) {
				case 0:
					PrefUtil.save(parent.getContext(), SettingActivity.IS_BOOT, isChecked);
					break;
				case 1:
					PrefUtil.save(parent.getContext(), SettingActivity.IS_ANNONCE, isChecked);
					if (isChecked) {
						NotificationUtil.getInstance(parent.getContext()).show();
					} else {
						NotificationUtil.getInstance(parent.getContext()).cancel();
					}
					break;
				case 2:
					PrefUtil.save(parent.getContext(), SettingActivity.IS_PUSH, isChecked);
					break;

				default:
					break;
				}
			}
		});

		tv_title.setText(t.getTitle());
		if (t.isShowArrow()) {
			iv_arrow.setVisibility(View.VISIBLE);
			toggle1.setVisibility(View.INVISIBLE);
		} else {
			iv_arrow.setVisibility(View.INVISIBLE);
			toggle1.setVisibility(View.VISIBLE);

			toggle1.setChecked(t.isChecked());
		}
		return convertView;
	}

}
