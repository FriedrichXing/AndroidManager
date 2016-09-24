package com.joythink.xk.phonetools.phone;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.joythink.xk.phonetools.adapter.FileMrgAdapter;
import com.joythink.xk.phonetools.base.BaseActivity;
import com.joythink.xk.phonetools.entity.FileMgrMenu;
import com.joythink.xk.phonetools.mgr.FileManager;
import com.joythink.xk.phonetools.mgr.FileManager.SearchListener;
import com.joythink.xk.phonetools.utils.CommonUtil;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class FileMgrActivity extends BaseActivity implements
		OnItemClickListener {
	protected static final int ON_SEARCHING = 1;
	protected static final int SEARCH_FINISHED = 0;
	ListView listView1;
	FileMrgAdapter adapter;
	// String str1,str2,str3,str4,str5,str6,str7;
	TextView tv_text2;
	FileManager fm;
	MyHandler handler;
	private long totalSize;
	private SearchListener listener = new SearchListener() {
		
		@Override
		public void onSearching(int currentType, long fileSize) {
			Message msg=handler.obtainMessage();
			msg.arg1 = currentType;
			Bundle bundle =new Bundle();
			bundle.putLong("fileSize", fileSize);
			msg.setData(bundle);
			msg.what = ON_SEARCHING;
			handler.sendMessage(msg);
		}
		
		@Override
		public void onSearchFinished() {
			handler.sendEmptyMessage(SEARCH_FINISHED);
		}
	};
	private long fileSize;
	public class MyHandler extends Handler{

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ON_SEARCHING:
				int currentType = msg.arg1;
				fileSize = msg.getData().getLong("fileSize");
				adapter.getItem(currentType ).setSize(adapter.getItem(currentType).getSize()+fileSize );
//				adapter.addData(adapter.getItem(currentType));
				totalSize+=fileSize;
				tv_text2.setText(CommonUtil.getFileSize(totalSize));
				break;
			case SEARCH_FINISHED:
				for (FileMgrMenu f : adapter.getList()) {
					f.setShowArrow(true);
				}
				
				break;

			default:
				break;
			}
			adapter.update();
		
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_mgr);
		intiSimpleTitleBar();
		listView1 = (ListView) findViewById(R.id.listView1);
		tv_text2 = (TextView) findViewById(R.id.tv_text2);
		adapter = new FileMrgAdapter();
		adapter.addData(new FileMgrMenu("文档", 0, false));
		adapter.addData(new FileMgrMenu("图像", 0, false));
		adapter.addData(new FileMgrMenu("视频", 0, false));
		adapter.addData(new FileMgrMenu("音频", 0, false));
		adapter.addData(new FileMgrMenu("压缩包", 0, false));
		adapter.addData(new FileMgrMenu("安装包", 0, false));
		adapter.addData(new FileMgrMenu("未分类", 0, false));
		listView1.setAdapter(adapter);
		
		handler = new MyHandler();
		fm = FileManager.getInstance();
		fm.setListener(listener);
		asyncSearch();

		listView1.setOnItemClickListener(this);
	}

	private void asyncSearch() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				fm.searchFile();
			}
		}).start();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Bundle bundle = new Bundle();
		bundle.putInt("p", position);
		
		switch (position) {
		case 0:
			bundle.putInt("name", R.string.filetype_text);
			break;
		case 1:
			bundle.putInt("name", R.string.filetype_img);
			break;
		case 2:
			bundle.putInt("name", R.string.filetype_video);
			break;
		case 3:
			bundle.putInt("name", R.string.filetype_mp3);
			break;
		case 4:
			bundle.putInt("name", R.string.filetype_zip);
			break;
		case 5:
			bundle.putInt("name", R.string.filetype_program);
			break;
		case 6:
			bundle.putInt("name", R.string.filetype_others);
			break;
		default:
			break;
		}
		
		Intent intent  = new Intent(this, FileListActivity.class);
		intent.putExtras(bundle);
		startActivityForResult(intent, 1);
//		jumpTo(FileListActivity.class, bundle);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data != null) {
			int currentType = data.getIntExtra("p", 0);
			long deletedSize = data.getLongExtra("d", 0);
			adapter.getItem(currentType).setSize(adapter.getItem(currentType).getSize() - deletedSize);
			adapter.update();
			totalSize-=deletedSize;
			tv_text2.setText(CommonUtil.getFileSize(totalSize));
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.file_mgr, menu);
		return true;
	}

}
