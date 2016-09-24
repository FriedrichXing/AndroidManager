package com.joythink.xk.phonetools.phone;



import java.util.Timer;
import java.util.TimerTask;

import com.joythink.xk.phonetools.base.BaseActivity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class LogoActivity extends BaseActivity {
	ImageView img_logo;
	private int duration;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		
		img_logo = (ImageView) findViewById(R.id.img_logo);
		AnimationDrawable ad = (AnimationDrawable) img_logo.getDrawable();
		for (int i = 0; i < ad.getNumberOfFrames(); i++) {
			duration += ad.getDuration(i);
		}
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				jumpTo(MainActivity.class);
				finish();
			}
		};
		timer.schedule(task, duration);
	}


}
