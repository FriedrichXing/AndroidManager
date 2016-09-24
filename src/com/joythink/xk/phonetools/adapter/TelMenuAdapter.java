package com.joythink.xk.phonetools.adapter;


import com.joythink.xk.phonetools.base.MySimpleBaseAdapter;
import com.joythink.xk.phonetools.entity.TelMenu;
import com.joythink.xk.phonetools.phone.R;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TelMenuAdapter extends MySimpleBaseAdapter<TelMenu> {

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		View v = View.inflate(parent.getContext(), R.layout.layout_item_telmenu, null);

		switch (position % 3) {
		case 0:
			v.setBackgroundResource(R.drawable.selector_red);
			break;
		case 1:
			v.setBackgroundResource(R.drawable.selector_green);
			break;
		case 2:
			v.setBackgroundResource(R.drawable.selector_yellow);
			break;
		default:
			break;
		}
		TextView textView = (TextView) v.findViewById(R.id.tv_menu);
		String name = getItem(position).getName();
		textView.setText(name);

		return v;
	}

}
