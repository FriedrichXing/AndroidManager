package com.joythink.xk.phonetools.phone;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.joythink.xk.phonetools.adapter.FileListAdapter;
import com.joythink.xk.phonetools.base.BaseActivity;
import com.joythink.xk.phonetools.entity.AppInfo;
import com.joythink.xk.phonetools.entity.FileInfo;
import com.joythink.xk.phonetools.mgr.FileManager;
import com.joythink.xk.phonetools.utils.CommonUtil;
import com.joythink.xk.phonetools.utils.FileTypeUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FileListActivity extends BaseActivity implements OnClickListener, OnItemClickListener, OnCheckedChangeListener {
	ListView listView1;
	FileListAdapter adapter;
	TextView tv_totalNums, tv_totalSize;
	FileManager fm;
	private ArrayList<FileInfo> arrayList;
	private int count;
	private long totalSize;
	Button btn_delete;
	CheckBox cb_checkall;
	private ArrayList<ArrayList<FileInfo>> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_list);
		intiSimpleTitleBar();
		findViews();
		
		initUI();
		
		listView1.setAdapter(adapter);
		listView1.setOnItemClickListener(this);
		cb_checkall.setOnCheckedChangeListener(this);
		btn_delete.setOnClickListener(this);
	}
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		for (FileInfo fileInfo : adapter.getList()) {
			fileInfo.setChecked(isChecked);
		}
		adapter.update();
	}
	
	@Override
	public void onClick(View v) {
		
		long sum_len = 0;
		ArrayList<FileInfo> toBeDeleted = new ArrayList<FileInfo>();
		for (FileInfo fileInfo : adapter.getList()) {
			if (fileInfo.isChecked()) {
				toBeDeleted.add(fileInfo);
				sum_len += fileInfo.getFile().length();
			}
		}
		for (FileInfo fileInfo : toBeDeleted) {
			fileInfo.getFile().delete();
		}
		adapter.removeDataList(toBeDeleted);
		adapter.update();
		Intent data = getIntent();
		data.putExtra("d", sum_len);
		setResult(1, data );
		setTopUI();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		try {
			File file = adapter.getItem(position).getFile();
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(file), FileTypeUtil.getMIMEType(file));
			startActivity(intent);
		} catch (Exception e) {
			showToast("无法打开该类型的文件");
		}
	}

	private void findViews() {
		// TODO Auto-generated method stub
		listView1 = (ListView) findViewById(R.id.listView1);
		tv_totalNums = (TextView) findViewById(R.id.tv_totalNums);
		tv_totalSize = (TextView) findViewById(R.id.tv_totalSize);
		btn_delete = (Button) findViewById(R.id.btn_delete);
		cb_checkall = (CheckBox) findViewById(R.id.cb_checkall);
	}


	private void initUI() {
		// TODO Auto-generated method stub
		adapter = new FileListAdapter();
		fm = FileManager.getInstance();
		list = fm.getList();
		arrayList = list.get(getIntent().getIntExtra("p", 0));
		adapter.setList(arrayList);
		setTopUI();
	}

	private void setTopUI() {
		count = arrayList.size();
		totalSize = 0;
		for (FileInfo fileInfo : arrayList) {
			totalSize += fileInfo.getFile().length();
		}
		tv_totalNums.setText(count + "");
		tv_totalSize.setText(CommonUtil.getFileSize(totalSize) + "");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.file_list, menu);
		return true;
	}

	



}
