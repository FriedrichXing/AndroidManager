package com.joythink.xk.phonetools.phone;

import java.util.List;

import com.joythink.xk.phonetools.adapter.SpeedUpAdapter;
import com.joythink.xk.phonetools.base.BaseActivity;
import com.joythink.xk.phonetools.entity.SpeedUpMenu;
import com.joythink.xk.phonetools.mgr.MemoryManager;
import com.joythink.xk.phonetools.mgr.PhoneManager;
import com.joythink.xk.phonetools.mgr.RunningAppMgr;
import com.joythink.xk.phonetools.utils.CommonUtil;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SpeedUpActivity extends BaseActivity implements
		OnCheckedChangeListener, OnClickListener {
	ListView listView1;
	SpeedUpAdapter adapter;
	TextView tv_device, tv_version, tv_memory;
	ProgressBar progressBar1, progressBar2;
	Button btn_clearAll;
	CheckBox checkBox1;
	ToggleButton toggle_show;
	RunningAppMgr ram;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_speed_up);
		intiSimpleTitleBar();
		ram = RunningAppMgr.getInstance(this);
		findViews();
		initDeviceInfo();
		initMemoryInfo();
		adapter = new SpeedUpAdapter();
		listView1.setAdapter(adapter);
		initData(false);

		setListeners();
	}

	private void setListeners() {
		toggle_show.setOnCheckedChangeListener(this);
		btn_clearAll.setOnClickListener(this);
		checkBox1.setOnCheckedChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		List<SpeedUpMenu> list = adapter.getList();
		if (isNoChecked(list)) {
			return;
		}
		for (SpeedUpMenu speedUpMenu : list) {
			if (speedUpMenu.isChecked()) {
				ram.killProcess(speedUpMenu.getPackageName());
			}
		}
		initData(true);
	}

	private boolean isNoChecked(List<SpeedUpMenu> list) {
		// TODO Auto-generated method stub
		for (SpeedUpMenu speedUpMenu : list) {
			if (speedUpMenu.isChecked()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch (buttonView.getId()) {
		case R.id.toggle1:
			if (isChecked) {
				adapter.addDataList(ram.getSystemList(false));
			} else {
				adapter.removeDataList(ram.getSystemList(false));
			}
			adapter.update();
			checkBox1.setOnCheckedChangeListener(null);
			checkBox1.setChecked(false);
			checkBox1.setOnCheckedChangeListener(this);
			break;

		case R.id.checkBox1:
			List<SpeedUpMenu> list = adapter.getList();
			for (SpeedUpMenu speedUpMenu : list) {
				speedUpMenu.setChecked(isChecked);
			}
			adapter.update();
			break;

		default:
			break;
		}

	}

	private void findViews() {
		// TODO Auto-generated method stub
		listView1 = (ListView) findViewById(R.id.listView1);
		tv_device = (TextView) findViewById(R.id.tv_device);
		tv_version = (TextView) findViewById(R.id.tv_version);
		tv_memory = (TextView) findViewById(R.id.tv_memory);
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		btn_clearAll = (Button) findViewById(R.id.btn_clearall);
		checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
		toggle_show = (ToggleButton) findViewById(R.id.toggle1);
		progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
	}

	private void initData(final boolean isUpdate) {
		// TODO Auto-generated method stub
		listView1.setVisibility(View.INVISIBLE);
		progressBar2.setVisibility(View.VISIBLE);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				final List<SpeedUpMenu> list = ram.getUserList(isUpdate);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						adapter.setList(list);
						adapter.update();
						listView1.setVisibility(View.VISIBLE);
						progressBar2.setVisibility(View.INVISIBLE);
						restUI();
					}

				});
			}
		}).start();

	}

	private void restUI() {
		checkBox1.setOnCheckedChangeListener(null);
		checkBox1.setChecked(false);
		checkBox1.setOnCheckedChangeListener(this);
		toggle_show.setOnCheckedChangeListener(null);
		toggle_show.setChecked(false);
		toggle_show.setOnCheckedChangeListener(this);
		initMemoryInfo();
	}

	private void initMemoryInfo() {
		// TODO Auto-generated method stub
		long allMemory = MemoryManager.getPhoneTotalRamMemory();
		long freeMemory = MemoryManager.getPhoneFreeRamMemory(this);
		long usedMemory = allMemory - freeMemory;
		int persent = (int) (usedMemory * 100 / allMemory);
		progressBar1.setProgress(persent);
		progressBar1.setMax(100);
		tv_memory.setText("ÒÑÓÃÄÚ´æ£º" + CommonUtil.getFileSize(usedMemory) + "/"
				+ CommonUtil.getFileSize(allMemory));

	}

	private void initDeviceInfo() {
		// TODO Auto-generated method stub
		PhoneManager pm = PhoneManager.getPhoneManage(this);
		String brand = pm.getPhoneName1();
		tv_device.setText(brand);
		String version = pm.getPhoneSystemVersion();
		tv_version.setText(version);
	}

}
