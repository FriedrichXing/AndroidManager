package com.joythink.xk.phonetools.phone;

import com.joythink.xk.phonetools.adapter.PhoneInfoAdapter;
import com.joythink.xk.phonetools.base.BaseActivity;
import com.joythink.xk.phonetools.entity.PhoneInfo;
import com.joythink.xk.phonetools.mgr.MemoryManager;
import com.joythink.xk.phonetools.mgr.PhoneManager;
import com.joythink.xk.phonetools.utils.CommonUtil;

import android.os.BatteryManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PhoneInfoActivity extends BaseActivity implements OnClickListener {
	ListView listView1;
	RelativeLayout relativeLayout1;
	ProgressBar progressBar1;
	TextView tv_battery;
	PhoneInfoAdapter adapter;
	private int checkedItem = -1;
	private BatteryReceiver receiver = null;
	PhoneManager pm;
	String str1_1,str1_2,str3_1,str3_2,str4_1,str4_2,str5_1,str5_2,str6_1,str6_2;
	long str2_1,str2_2;
	private int progress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_info);
		intiSimpleTitleBar();
		findViews();
		receiver = new BatteryReceiver();
		IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(receiver, filter);
		tv_battery.setText(null);
		
		pm = PhoneManager.getPhoneManage(this);
		initStrs();
		adapter = new PhoneInfoAdapter();
		adapter.addData(new PhoneInfo(R.drawable.setting_info_icon_version, "�豸���ƣ�"+str1_1,"ϵͳ�汾��"+str1_2));
		adapter.addData(new PhoneInfo(R.drawable.setting_info_icon_space,"ȫ�������ڴ棺"+str6_1 ,"ʣ�������ڴ棺"+str6_2));
		adapter.addData(new PhoneInfo(R.drawable.setting_info_icon_cpu,"CPU���ƣ�"+str3_1,"CPU������"+str3_2));
		adapter.addData(new PhoneInfo(R.drawable.setting_info_icon_camera,"�ֻ��ֱ��ʣ�"+str4_1,"����ֱ��ʣ�"+str4_2));
		adapter.addData(new PhoneInfo(R.drawable.setting_info_icon_root,"�����汾��"+str5_1,"�Ƿ�ROOT��"+str5_2));
		listView1.setAdapter(adapter);
		relativeLayout1.setOnClickListener(this);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}
	
	class BatteryReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
				//��ǰ����
				int level = intent.getExtras().getInt("level");
				//����̶ܿ�
				int scale = intent.getExtras().getInt("scale");
				//�ٷֱ�
				progress = level*100/scale;
				progressBar1.setProgress(progress);
				tv_battery.setText(progress+"%");
			}
		}
		
	}

	private void initStrs() {
		// TODO Auto-generated method stub
		str1_1 = pm.getPhoneName1();
		str1_2 = pm.getPhoneSystemVersion();
		str2_1 = MemoryManager.getPhoneSelfSize();
		str2_2 = MemoryManager.getPhoneSelfFreeSize();
		str6_1 = CommonUtil.getFileSize(str2_1);
		str6_2 = CommonUtil.getFileSize(str2_2);
		str3_1 = pm.getPhoneCpuName();
		str3_2 = pm.getPhoneCpuNumber()+"";
		str4_1 = pm.getResolution();
		str4_2 = pm.getCameraResolution();
		str5_1 = pm.getPhoneSystemBasebandVersion();
		if (pm.isRoot()) {
			str5_2 = "��";
		} else {
			str5_2 = "��";
		}
		
	}

	private void findViews() {
		// TODO Auto-generated method stub
		listView1 = (ListView) findViewById(R.id.listView1);
		relativeLayout1 = (RelativeLayout) findViewById(R.id.relativeLayout1);
		progressBar1 = (ProgressBar) findViewById(R.id.pb_pb);
		tv_battery = (TextView) findViewById(R.id.tv_battery);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.phone_info, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		AlertDialog.Builder d = new AlertDialog.Builder(this);
		String[] items = { "��ǰ������"+progress+"%", "����¶ȣ�0"};
		DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				switch (which) {
				case 0:
					showToast("��ǰ������"+progress+"%");
					break;
				case 1:
					showToast("����¶ȣ�0");
					break;
				default:
					break;
				}
			}
		};
		d.setTitle("��ص�����Ϣ").setItems(items, listener);

		d.show();
	}

	
	
	
}
