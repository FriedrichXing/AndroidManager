package com.joythink.xk.phonetools.base;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MySimpleBaseAdapter<T> extends BaseAdapter {

	protected List<T> list;

	public MySimpleBaseAdapter() {
		super();
		list = new ArrayList<T>();
	}

	// 向集合中添加数据
	public void addData(T t) {
		if (list == null) {
			return;
		}
		list.add(t);
	}

	// 添加一个集合
	public void addDataList(List<T> l) {
		list.addAll(l);
	}

	// 删掉一个集合
	public void removeDataList(List<T> l) {
		list.removeAll(l);
	}

	// 删除一个数据
	public void remove(T t) {
		if (list == null) {
			return;
		}
		list.remove(t);
	}

	// 清空所有
	public void clear() {
		if (list == null) {
			return;
		}
		list.clear();
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public MySimpleBaseAdapter(List<T> list) {
		super();
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list == null ? 0 : list.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return list == null ? null : list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressWarnings("unchecked")
	@Override
	public abstract View getView(int position, View convertView,
			ViewGroup parent);
}
