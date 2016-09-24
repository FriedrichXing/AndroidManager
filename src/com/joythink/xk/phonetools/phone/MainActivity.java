package com.joythink.xk.phonetools.phone;

import com.joythink.xk.phonetools.adapter.MenuAdapter;
import com.joythink.xk.phonetools.base.BaseActivity;
import com.joythink.xk.phonetools.entity.Menus;
import com.joythink.xk.phonetools.mgr.MemoryManager;
import com.joythink.xk.phonetools.mgr.RunningAppMgr;
import com.joythink.xk.phonetools.view.ClearView;
import com.joythink.xk.phonetools.view.ClearView.AngleChangeListener;
import com.joythink.xk.phonetools.view.TitleBarView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements OnClickListener, OnItemClickListener, AngleChangeListener {
	TitleBarView titleBarView1;
	GridView gridView1;
	MenuAdapter adapter;
	Button btn_mainButton;
	ImageButton ib_imgButton;
	ClearView clearView;
	Handler handler;
	TextView tv_mainText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		titleBarView1 = (TitleBarView) findViewById(R.id.titleBarView1);
		titleBarView1.inti(R.drawable.ic_launcher, "°²×¿¹Ü¼Ò",
				R.drawable.ic_child_configs, this);
		
		gridView1 = (GridView) findViewById(R.id.gridView1);
		tv_mainText = (TextView) findViewById(R.id.tv_mainText);
		btn_mainButton = (Button) findViewById(R.id.btn_mainButton);
		ib_imgButton = (ImageButton) findViewById(R.id.ib_mainImgButton);
		clearView = (ClearView) findViewById(R.id.clearView1);
		handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == 1) {
					int sweepAngle = msg.arg1;
					tv_mainText.setText(sweepAngle+"");
				}
			}
		};
		clearView.setListener(this);
		ib_imgButton.setOnClickListener(this);
		btn_mainButton.setOnClickListener(this);
		adapter = new MenuAdapter();
		adapter.addData(new Menus(R.drawable.icon_rocket, R.string.main_rocket));
		adapter.addData(new Menus(R.drawable.icon_softmgr, R.string.main_softmgr));
		adapter.addData(new Menus(R.drawable.icon_phonemgr, R.string.main_phonemgr));
		adapter.addData(new Menus(R.drawable.icon_telmgr, R.string.main_telmgr));
		adapter.addData(new Menus(R.drawable.icon_filemgr, R.string.main_filemgr));
		adapter.addData(new Menus(R.drawable.icon_sdclean, R.string.main_sdclean));
		gridView1.setAdapter(adapter);
		gridView1.setOnItemClickListener(this);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setMemory();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_left:
			jumpTo(AboutUsActivity.class);
			break;
		case R.id.iv_right:
			jumpTo(SettingActivity.class);
			break;
		case R.id.btn_mainButton:
			clearMemory();
			break;
		case R.id.ib_mainImgButton:
			clearMemory();
			break;
		default:
			break;
		}
	}

	private void clearMemory() {
		// TODO Auto-generated method stub
		RunningAppMgr.getInstance(this).killAll();
		setMemory();
	}

	private void setMemory() {
		// TODO Auto-generated method stub
		long allMemory = MemoryManager.getPhoneTotalRamMemory();
		long freeMemory = MemoryManager.getPhoneFreeRamMemory(this);
		long usedMemory = allMemory - freeMemory;
		int angle = (int) (usedMemory*360/allMemory);
		clearView.setAngleWithAnim(angle);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Bundle bundle = new Bundle();
		bundle.putInt("name", adapter.getItem(position).getName());
		switch (position) {
		case 0:
			jumpTo(SpeedUpActivity.class, bundle);
			break;
		case 1:
			jumpTo(AppMgrActivity.class, bundle);
			break;
		case 2:
			jumpTo(PhoneInfoActivity.class, bundle);
			break;
		case 3:
			jumpTo(TelMenuActivity.class, bundle);
			break;
		case 4:
			jumpTo(FileMgrActivity.class, bundle);
			break;
		case 5:
			jumpTo(CacheClearActivity.class, bundle);
			break;
		default:
			break;
		}
	}

	@Override
	public void setAngle(float sweepAngle) {
		// TODO Auto-generated method stub
		Message msg = handler.obtainMessage();
		msg.arg1 = (int) (sweepAngle/360*100);
		msg.what = 1;
		handler.sendMessage(msg);
	}

}
