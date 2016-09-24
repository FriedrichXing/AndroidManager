package com.joythink.xk.phonetools.phone;

import com.joythink.xk.phonetools.adapter.AppMgrAdapter;
import com.joythink.xk.phonetools.base.BaseActivity;
import com.joythink.xk.phonetools.entity.AppMgrMenu;
import com.joythink.xk.phonetools.mgr.MemoryManager;
import com.joythink.xk.phonetools.utils.CommonUtil;
import com.joythink.xk.phonetools.view.PieChartView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AppMgrActivity extends BaseActivity implements OnItemClickListener {
	public static final String IDX = "idx";
	ListView listView1;
	AppMgrAdapter adapter;
	ProgressBar pb1,pb2;
	TextView tv1,tv2;
	PieChartView pieChartView1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_mgr);
		intiSimpleTitleBar();
		findViews();
		initUI();
		initData();

		listView1.setOnItemClickListener(this);
	}

	private void initData() {
		// TODO Auto-generated method stub
		adapter = new AppMgrAdapter();
		adapter.addData(new AppMgrMenu("所有软件"));
		adapter.addData(new AppMgrMenu("系统软件"));
		adapter.addData(new AppMgrMenu("用户软件"));
		listView1.setAdapter(adapter);
	}

	private void findViews() {
		// TODO Auto-generated method stub
		listView1 = (ListView) findViewById(R.id.listView1);
		pb1 = (ProgressBar) findViewById(R.id.progressBar1);
		pb2 = (ProgressBar) findViewById(R.id.progressBar2);
		tv1 = (TextView) findViewById(R.id.textView4);
		tv2 = (TextView) findViewById(R.id.textView6);
		pieChartView1 = (PieChartView) findViewById(R.id.pieChartView1);
	}

	private void initUI() {
		// TODO Auto-generated method stub
		long phoneAllSize = MemoryManager.getPhoneAllSize();
		long phoneAllFreeSize = MemoryManager.getPhoneAllFreeSize();
		long phoneUsedSize = phoneAllSize - phoneAllFreeSize;
		int persent = (int) (phoneUsedSize*1.0/phoneAllSize*100);
		pb1.setProgress(persent);
		tv1.setText("可用："+CommonUtil.getFileSize(phoneAllFreeSize)+"/"+CommonUtil.getFileSize(phoneAllSize));
		long phoneOutSDCardSize = MemoryManager.getPhoneOutSDCardSize(this);
		long phoneOutSDCardFreeSize = MemoryManager.getPhoneOutSDCardFreeSize(this);
		long phoneOutSDCardUsedSize = phoneOutSDCardSize - phoneOutSDCardFreeSize;
		if (phoneOutSDCardSize!=0) {
			int persent2 = (int) (phoneOutSDCardUsedSize*1.0/phoneOutSDCardSize*100);
			pb2.setProgress(persent2);
			tv2.setText("可用："+CommonUtil.getFileSize(phoneOutSDCardFreeSize)+"/"+CommonUtil.getFileSize(phoneOutSDCardSize));
		}
		
		int targetAngle = (int) (phoneAllSize*1.0/(phoneAllSize+phoneOutSDCardSize)*360);
		int targetAngle2 = (int) (phoneOutSDCardSize*1.0/(phoneAllSize+phoneOutSDCardSize)*360);
		pieChartView1.setAngle(targetAngle, targetAngle2);
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Bundle bundle = new Bundle();
		bundle.putInt("p", position);
		switch (position) {
		case 0:
			bundle.putInt("name", R.string.app_all);
			break;
		case 1:
			bundle.putInt("name", R.string.app_system);
			break;
		case 2:
			bundle.putInt("name", R.string.app_user);
			break;

		default:
			break;
		}
		jumpTo(AppListActivity.class, bundle);
	}

}
