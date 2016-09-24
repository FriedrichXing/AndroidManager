package com.joythink.xk.phonetools.phone;

import com.joythink.xk.phonetools.adapter.TelMenuAdapter;
import com.joythink.xk.phonetools.base.BaseActivity;
import com.joythink.xk.phonetools.db.CommonDBManager;
import com.joythink.xk.phonetools.entity.TelMenu;
import com.joythink.xk.phonetools.phone.R;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;

public class MenuActivity extends BaseActivity implements OnItemClickListener {
	public static final String IDX = "idx";
	private GridView gridView1;
	private TelMenuAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		gridView1 = (GridView) findViewById(R.id.gridView1);
		adapter=new TelMenuAdapter();
		adapter.setList(CommonDBManager.getTelMenu(this));
		gridView1.setAdapter(adapter);
		
		gridView1.setOnItemClickListener(this);
	
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		TelMenu item = adapter.getItem(position);
		int idx = item.getIdx();
		Intent intent = new Intent(this, TelListActivity.class);
		intent.putExtra(IDX, idx);
		startActivity(intent);
	}

}
