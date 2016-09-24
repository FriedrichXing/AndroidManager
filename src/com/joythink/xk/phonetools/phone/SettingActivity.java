package com.joythink.xk.phonetools.phone;

import com.joythink.xk.phonetools.adapter.SettingAdapter;
import com.joythink.xk.phonetools.base.BaseActivity;
import com.joythink.xk.phonetools.entity.PhoneSetting;
import com.joythink.xk.phonetools.utils.PrefUtil;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

public class SettingActivity extends BaseActivity implements
		OnItemClickListener {
	public static final String IS_BOOT = "isboot";
	public static final String IS_ANNONCE = "isannonce";
	public static final String IS_PUSH = "ispush";
	ListView listView1;
	SettingAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		intiSimpleTitleBar("����");

		listView1 = (ListView) findViewById(R.id.listView1);
		adapter = new SettingAdapter();
		boolean isBoot = PrefUtil.load(this, IS_BOOT, false);
		boolean isAnnonce = PrefUtil.load(this, IS_ANNONCE, false);
		boolean isPush = PrefUtil.load(this, IS_PUSH, false);
		adapter.addData(new PhoneSetting(false, "��������", isBoot));
		adapter.addData(new PhoneSetting(false, "֪ͨͼ��", isAnnonce));
		adapter.addData(new PhoneSetting(false, "��Ϣ����", isPush));
		adapter.addData(new PhoneSetting(true, "����˵��"));
		adapter.addData(new PhoneSetting(true, "�������"));
		adapter.addData(new PhoneSetting(true, "���ѷ���"));
		adapter.addData(new PhoneSetting(true, "�汾����"));
		adapter.addData(new PhoneSetting(true, "��������"));
		adapter.addData(new PhoneSetting(true, "�˳�����"));
		listView1.setAdapter(adapter);

		listView1.setOnItemClickListener(this);
		
//		Notification n = new Notification();
//		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//		nm.notify(3, n);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch (position) {
		case 3:

			break;
		case 4:

			break;
		case 5:

			break;
		case 6:

			break;
		case 7:

			break;
		case 8:
			showExitDialog();
			break;

		default:
			break;
		}
	}

	private void showExitDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder b = new Builder(this);

		b.setTitle("ȷ���˳���").setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finishAll();
			}
		}).setNegativeButton("ȡ��", null).show();
	}

}
