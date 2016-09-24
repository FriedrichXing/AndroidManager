package com.joythink.xk.phonetools.phone;

import com.joythink.xk.phonetools.adapter.TelNumAdapter;
import com.joythink.xk.phonetools.base.BaseActivity;
import com.joythink.xk.phonetools.db.CommonDBManager;
import com.joythink.xk.phonetools.entity.TelNum;

import android.net.Uri;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TelListActivity extends BaseActivity implements OnItemClickListener {
	ListView listView1;
	TelNumAdapter adapter;
	private int idx;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tel_list);
		intiSimpleTitleBar("电话列表");
		listView1 = (ListView) findViewById(R.id.listView1);
		adapter = new TelNumAdapter();
		idx = getIntent().getIntExtra(MenuActivity.IDX, 1);
		adapter.setList(CommonDBManager.getTableList(idx));
		listView1.setAdapter(adapter);
		listView1.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.tel_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		switch (id) {
		case R.id.action_reset:
			CommonDBManager.reset(this);
			// adapter.notifyDataSetChanged();
			updateData();
			return true;
		case R.id.action_add:
			showAddDialog();
			return true;
		case R.id.action_check:
			showCheckDialog();
			return true;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void showCheckDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		String[] items = {"按名称查询","按号码查询","取消"};
		b.setItems(items, new DialogInterface.OnClickListener(
				) {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final EditText ed1 = new EditText(TelListActivity.this);
				switch (which) {
				case 0:
					editNameDialog(ed1, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							String str = ed1.getText().toString();
							adapter.setList(CommonDBManager.getTableByName(idx, str));
							adapter.notifyDataSetChanged();
						}
					});
					break;
				case 1:
					editNameDialog(ed1, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							String str = ed1.getText().toString();
							adapter.setList(CommonDBManager.getTableByNumber(idx, str));
							adapter.notifyDataSetChanged();
						}
					});
					break;
				case 2:
					
					break;

				default:
					break;
				}
			}
		}).show();
	}

	private void editNameDialog(EditText ed,DialogInterface.OnClickListener listener) {
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setView(ed).setPositiveButton("确定", listener).setNegativeButton("取消", null).show();
	}
	
	private void showAddDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		View v = View.inflate(this, R.layout.layout_dialog7, null);
		final EditText ed_name = (EditText) v.findViewById(R.id.ed_name);
		final EditText ed_number = (EditText) v.findViewById(R.id.ed_number);
		b.setView(v)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						String name = ed_name.getText().toString();
						String number = ed_number.getText().toString();
						// TelNum telNum = new TelNum(_id, number, name);
						CommonDBManager.add(idx, name, number);
						updateData();
					}
				}).setNegativeButton("取消", null);
		b.show();
	}

	protected void updateData() {
		// TODO Auto-generated method stub
		adapter.setList(CommonDBManager.getTableList(idx));
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		showChooseDialog(position);
	}

	private void showChooseDialog(final int position) {
		// TODO Auto-generated method stub
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		CharSequence[] items = { "编辑", "删除","拨打", "取消" };
		b.setItems(items, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				switch (which) {
				case 0:
					showEditDialog(position);
					break;
				case 1:
					int _id = adapter.getItem(position).get_id();
					CommonDBManager.delete(idx, _id);
					updateData();
					break;
				case 2:
					Intent intent = new Intent(Intent.ACTION_CALL);
					intent.setData(Uri.parse("tel:"+adapter.getItem(position).getNumber()));
					startActivity(intent);
					break;

				default:
					break;
				}

			}

		});
		b.show();
	}

	protected void showEditDialog(final int position) {
		// TODO Auto-generated method stub
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		View v = View.inflate(this, R.layout.layout_dialog7, null);
		final EditText ed_name = (EditText) v.findViewById(R.id.ed_name);
		final EditText ed_number = (EditText) v.findViewById(R.id.ed_number);
		b.setView(v)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String name = ed_name.getText().toString();
						String number = ed_number.getText().toString();
						int _id = adapter.getItem(position).get_id();
						TelNum telNum = new TelNum(_id, number, name);
						CommonDBManager.edit(idx, telNum);
						updateData();
					}
				}).setNegativeButton("取消", null);
		b.show();
	}

}
