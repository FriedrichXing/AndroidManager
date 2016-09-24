package com.joythink.xk.phonetools.phone;

import com.joythink.xk.phonetools.base.BaseActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AboutUsActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
		intiSimpleTitleBar("关于我们");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about_us, menu);
		return true;
	}

}
