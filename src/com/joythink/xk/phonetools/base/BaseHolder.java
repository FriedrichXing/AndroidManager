package com.joythink.xk.phonetools.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Camera.Size;
import android.support.v4.util.LruCache;
import android.view.View;

public abstract class BaseHolder<T> {
	private static LruCache<String, Bitmap> cache;
	private View mRootView;
	protected Context context;

	public View getmRootView() {
		return mRootView;
	}

	public BaseHolder(Context context) {
		super();
		this.context = context;
		// TODO Auto-generated constructor stub
		mRootView = initView();
		mRootView.setTag(this);
		
		cache = new LruCache<String, Bitmap>(5*1024*1024){
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// TODO Auto-generated method stub
				return value.getByteCount();
			}
		};
		
	}
	
	protected void put(String key,Bitmap value) {
		cache.put(key, value);
	}
	
	protected Bitmap get(String key){
		return cache.get(key);
	}

	// ��ʼ�����ֵķ����������������
	public abstract View initView();

	// �������Ե��ؼ��ķ����� �����������
	public abstract void setData(T t, boolean isFling);

}
