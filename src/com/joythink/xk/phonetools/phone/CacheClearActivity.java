package com.joythink.xk.phonetools.phone;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.joythink.xk.phonetools.adapter.CacheClearAdapter;
import com.joythink.xk.phonetools.base.BaseActivity;
import com.joythink.xk.phonetools.db.AssetsFileManager;
import com.joythink.xk.phonetools.db.ClearDBManager;
import com.joythink.xk.phonetools.db.ClearDBManager.ClearListener;
import com.joythink.xk.phonetools.entity.CacheInfo;
import com.joythink.xk.phonetools.entity.FileInfo;
import com.joythink.xk.phonetools.mgr.FileManager;
import com.joythink.xk.phonetools.utils.CommonUtil;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CacheClearActivity extends BaseActivity
		implements OnCheckedChangeListener, OnItemClickListener, OnClickListener {
	ListView listView1;
	TextView tv_totalSize;
	CheckBox cb_checkall;
	Button btn_clear;
	ProgressBar pb_load;
	CacheClearAdapter adapter;
	ClearDBManager cm;
	long totalSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cache_clear);
		intiSimpleTitleBar();
		findViews();
		adapter = new CacheClearAdapter();
		listView1.setAdapter(adapter);
		cb_checkall.setOnCheckedChangeListener(this);
		btn_clear.setOnClickListener(this);
		asyncLoad();
		listView1.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View v) {
		AlertDialog.Builder b = new Builder(this);
		b.setMessage("确定删除吗？删除后不可恢复！").setTitle("删除确认").setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				for (CacheInfo c : adapter.getList()) {
					if (c.isChecked()) {
						FileManager.deleteFile(new File(c.getFilepath()));
					}
				}
				asyncLoad();
			}
		}).setNegativeButton("取消", null);
		b.show();
		adapter.update();
	}

	ClearListener listener = new ClearListener() {

		@Override
		public void publishSize(final long size) {
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					totalSize += size;
					tv_totalSize.setText(CommonUtil.getFileSize(totalSize));
				}
			});
		}
	};

	private void asyncLoad() {
		totalSize = 0;
		tv_totalSize.setText("0B");
		pb_load.setVisibility(View.VISIBLE);
		listView1.setVisibility(View.INVISIBLE);
		new Thread(new Runnable() {

			@Override
			public void run() {

				cm = new ClearDBManager(CacheClearActivity.this, listener);
				final List<CacheInfo> cacheList = cm.getCacheList();
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						showToast("搜索完毕！");
						adapter.setList(cacheList);
						adapter.update();
						pb_load.setVisibility(View.INVISIBLE);
						listView1.setVisibility(View.VISIBLE);
					}
				});
			}
		}).start();
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		for (CacheInfo cacheInfo : adapter.getList()) {
			cacheInfo.setChecked(isChecked);
		}
		adapter.update();
	}

	private void findViews() {
		// TODO Auto-generated method stub
		listView1 = (ListView) findViewById(R.id.listView1);
		tv_totalSize = (TextView) findViewById(R.id.tv_totalSize);
		cb_checkall = (CheckBox) findViewById(R.id.cb_checkall);
		btn_clear = (Button) findViewById(R.id.btn_clear);
		pb_load = (ProgressBar) findViewById(R.id.pb_load);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub

	}

}
