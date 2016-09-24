package com.joythink.xk.phonetools.view;

import com.joythink.xk.phonetools.phone.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TitleBarView extends LinearLayout {

	ImageView iv_left, iv_right;
	TextView tv_title;

	public TitleBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		inflate(context, R.layout.layout_titlebar, this);
		if (isInEditMode()) {
			return;
		}
		findViews();
	}

	private void findViews() {
		// TODO Auto-generated method stub
		iv_left = (ImageView) findViewById(R.id.iv_left);
		iv_right = (ImageView) findViewById(R.id.iv_right);
		tv_title = (TextView) findViewById(R.id.tv_title);
	}

	public void inti(int leftImgId, String text, int rightImgId,
			View.OnClickListener listener) {
		if (leftImgId > 0) {
			iv_left.setImageResource(leftImgId);
			iv_left.setOnClickListener(listener);
		} else {
			iv_left.setVisibility(View.INVISIBLE);
		}

		if (rightImgId > 0) {
			iv_right.setImageResource(rightImgId);
			iv_right.setOnClickListener(listener);
		} else {
			iv_right.setVisibility(View.INVISIBLE);
		}

		tv_title.setText(text);

	}

}
