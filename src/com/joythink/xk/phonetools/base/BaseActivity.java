package com.joythink.xk.phonetools.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.joythink.xk.phonetools.phone.R;
import com.joythink.xk.phonetools.utils.LogUtil;
import com.joythink.xk.phonetools.view.TitleBarView;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class BaseActivity extends Activity {
	private static List<BaseActivity> activities = new ArrayList<BaseActivity>();

	// 关闭集合中的所有Activity
	public void finishAll() {
		Iterator<BaseActivity> iterator = activities.iterator();
		while (iterator.hasNext()) {
			iterator.next().finish();
		}
	}
	
	//初始化标题栏的方法
	public void intiSimpleTitleBar(String text){
		TitleBarView titleBarView1 = (TitleBarView) findViewById(R.id.titleBarView1);
		titleBarView1.inti(R.drawable.btn_homeasup_default, text, -1, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
	//初始化标题栏的方法不带参数
		public void intiSimpleTitleBar(){
			TitleBarView titleBarView1 = (TitleBarView) findViewById(R.id.titleBarView1);
			int name = getIntent().getIntExtra("name", R.string.main_filemgr);
			String text = getResources().getString(name);
			titleBarView1.inti(R.drawable.btn_homeasup_default, text, -1, new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});
		}
	
	//跳转  不传值
	public void jumpTo(Class<?> cls) {
		Intent intent = new Intent(this, cls);
		startActivity(intent);
	}

	//跳转  传值
	public void jumpTo(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	// 吐司方法
	public void showToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LogUtil.d(this, "onCreate");
		activities.add(this);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		LogUtil.d(this, "onStart");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		LogUtil.d(this, "onResume");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		LogUtil.d(this, "onPause");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		LogUtil.d(this, "onStop");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		LogUtil.d(this, "onDestroy");
		activities.remove(this);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		LogUtil.d(this, "onRestart");
	}
}
