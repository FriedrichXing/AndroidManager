package com.joythink.xk.phonetools.phone;

import java.util.List;

import com.joythink.xk.phonetools.adapter.AppAdapter;
import com.joythink.xk.phonetools.base.BaseActivity;
import com.joythink.xk.phonetools.entity.AppInfo;
import com.joythink.xk.phonetools.entity.SpeedUpMenu;
import com.joythink.xk.phonetools.mgr.AppManager;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.ProgressBar;

public class AppListActivity extends BaseActivity implements OnCheckedChangeListener, OnClickListener, OnItemClickListener {
	ListView listView1;
	View layout_appList_bottom;
	CheckBox checkBox1;
	Button btn_onekeyUninstall;
	ProgressBar pb;
	AppAdapter adapter;
	int currentPosition;
	AppManager appManager;
	List<AppInfo> list;
	UninstallReceiver ur;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_list);
		intiSimpleTitleBar();
		currentPosition = getIntent().getIntExtra("p", 0);
		findViews();
		adapter = new AppAdapter();
		appManager = AppManager.getInstance(this);
		listView1.setAdapter(adapter);
		listView1.setOnScrollListener(listener);
		listView1.setOnItemClickListener(this);
		checkBox1.setOnCheckedChangeListener(this);
		btn_onekeyUninstall.setOnClickListener(this);
		initDate(false);
		ur = new UninstallReceiver();
		IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_REMOVED);
		filter.addDataScheme("package");
		registerReceiver(ur, filter);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(ur);
	}
	
	private OnScrollListener listener = new OnScrollListener() {
		
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			// TODO Auto-generated method stub
			switch (scrollState) {
			case SCROLL_STATE_FLING:
				adapter.setFling(true);
				break;
			case SCROLL_STATE_TOUCH_SCROLL:
				adapter.setFling(false);
				break;
			case SCROLL_STATE_IDLE:
				adapter.setFling(false);
				adapter.update();
				break;

			default:
				break;
			}
		}
		
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			// TODO Auto-generated method stub
			
		}
	};

	private void initDate(final boolean b) {
		// TODO Auto-generated method stub
		listView1.setVisibility(View.INVISIBLE);
		pb.setVisibility(View.VISIBLE);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				switch (currentPosition) {
				case 0:
					list = appManager.getAllList(b);
					break;
				case 1:
					list = appManager.getSystemList(b);
					break;
				case 2:
					list = appManager.getUserList(b);
					break;

				default:
					break;
				}
				
				
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						adapter.setList(list);
						adapter.update();
						listView1.setVisibility(View.VISIBLE);
						pb.setVisibility(View.INVISIBLE);
						if (currentPosition == 2) {
							layout_appList_bottom.setVisibility(View.VISIBLE);
						}
						restUI();
					}

				});
				
				
			}
		}).start();
	}

	private void restUI() {
		// TODO Auto-generated method stub
		checkBox1.setOnCheckedChangeListener(null);
		checkBox1.setChecked(false);
		checkBox1.setOnCheckedChangeListener(this);
		list = adapter.getList();
		for (AppInfo appInfos : list) {
			appInfos.setChecked(false);
		}
		adapter.update();
		
	}
	
	private void findViews() {
		listView1 = (ListView) findViewById(R.id.listView1);
		layout_appList_bottom = findViewById(R.id.layout_appList_bottom);
		checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
		btn_onekeyUninstall = (Button) findViewById(R.id.btn_onekeyUninstall);
		pb = (ProgressBar) findViewById(R.id.progressBar1);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.app_list, menu);
		return true;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		list = adapter.getList();
		for (AppInfo appInfos : list) {
			appInfos.setChecked(isChecked);
		}
		adapter.update();
		
	}

	@Override
	public void onClick(View v) {
		list = adapter.getList();
		for (AppInfo appInfos : list) {
			if (appInfos.isChecked()) {
				if (appInfos.getPackageName().equals(getPackageName())) {
					showToast("亲！卸载不了自己哦~");
					continue;
				}
				unInstall(appInfos.getPackageName());
			}
		}
	}

	private void unInstall(String packageName) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Intent.ACTION_DELETE);
		intent.setData(Uri.parse("package:"+packageName));
		startActivity(intent);
//		adapter.update();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		final String packageName = adapter.getItem(position).getPackageName();
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("确认启动").setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
				if (intent == null) {
					showToast("无法启动该应用");
				} else {
					startActivity(intent);
				}
			}
		}).setNegativeButton("取消", null);
		b.show();
	}
	
	public class UninstallReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			initDate(true);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
