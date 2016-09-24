package com.joythink.xk.phonetools.phone;

import java.util.ArrayList;
import java.util.List;

import com.joythink.xk.phonetools.adapter.MyPagerAdapter;
import com.joythink.xk.phonetools.base.BaseActivity;
import com.joythink.xk.phonetools.utils.PrefUtil;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;

public class GuideActivity extends BaseActivity {
	
	ViewPager viewPager;
	MyPagerAdapter adapter;
	List<View> list;
	LinearLayout ll_dotsLayout;
	Button btn_jumpToLogo;
	ImageView moveDot;
	int len;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (PrefUtil.load("isFirstRun", true)) {
			PrefUtil.save("isFirstRun", false);
		setContentView(R.layout.activity_guide);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		ll_dotsLayout = (LinearLayout) findViewById(R.id.ll_dotsLayout);
		btn_jumpToLogo = (Button) findViewById(R.id.btn_jumpToLogo);
		moveDot = (ImageView) findViewById(R.id.move_dot);
		
		list = new ArrayList<View>();
		intiViews();
		adapter = new MyPagerAdapter(list);
		//计算布局中两个控件的实际距离的像素个数，要等这个视图绘制完毕之后才能获得。
		ll_dotsLayout.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				len = ll_dotsLayout.getChildAt(1).getLeft()-ll_dotsLayout.getChildAt(0).getLeft();
			}
		});
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(listener);
		} else {
			jumpTo(LogoActivity.class); 
			finish();
		}
	}
	
	private void intiViews() {
		// TODO Auto-generated method stub
		list = new ArrayList<View>();
		int[] resID = { R.drawable.img111, R.drawable.img222, R.drawable.img333,
				R.drawable.img444, R.drawable.img555 };
		for (int i = 0; i < resID.length; i++) {
			ImageView img = new ImageView(this);
			img.setScaleType(ScaleType.FIT_XY);
			img.setImageResource(resID[i]);
			list.add(img);
		}
	}
	
	private OnPageChangeListener listener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			for (int i = 0; i < ll_dotsLayout.getChildCount(); i++) {
				((ImageView) ll_dotsLayout.getChildAt(i))
						.setImageResource(R.drawable.dot_choosed);
			}
			((ImageView) ll_dotsLayout.getChildAt(arg0))
					.setImageResource(R.drawable.dot_normal);

			if (arg0 == adapter.getCount() - 1) {
				btn_jumpToLogo.setVisibility(View.VISIBLE);
				btn_jumpToLogo.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						jumpTo(LogoActivity.class);
						finish();
					}
				});
			} else {
				btn_jumpToLogo.setVisibility(View.INVISIBLE);
			}
		}

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			LayoutParams layoutParams = (LayoutParams) moveDot.getLayoutParams();
			layoutParams.leftMargin = (int) (len*(position+positionOffset));
			moveDot.setLayoutParams(layoutParams);
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}
	};
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.guide, menu);
		return true;
	}

}
