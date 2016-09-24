package com.joythink.xk.phonetools.base;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter<T> extends BaseAdapter {
	protected List<T> list;
	protected boolean isFling;
	
	public boolean isFling() {
		return isFling;
	}

	public void setFling(boolean isFling) {
		this.isFling = isFling;
	}

	public MyBaseAdapter() {
		super();
		list = new ArrayList<T>();
	}

	// �򼯺����������
	public void addData(T t) {
		if (list == null) {
			return;
		}
		list.add(t);
	}

	// ���һ������
	public void addDataList(List<T> l) {
		list.addAll(l);
	}

	// ɾ��һ������
	public void removeDataList(List<T> l) {
		list.removeAll(l);
	}

	// ɾ��һ������
	public void remove(T t) {
		if (list == null) {
			return;
		}
		list.remove(t);
	}

	// �������
	public void clear() {
		if (list == null) {
			return;
		}
		list.clear();
	}
	public void update(){
		notifyDataSetChanged();
	}
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public MyBaseAdapter(List<T> list) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
		BaseHolder<T> holder = null;
		if (convertView == null) {
			holder = getHolder(parent.getContext());
			convertView = holder.getmRootView();
		} else {
			holder = (BaseHolder<T>) convertView.getTag();
		}

		// �޸����ݣ�
		holder.setData(getItem(position),isFling);

		return convertView;

	};

	public abstract BaseHolder<T> getHolder(Context context);
}
