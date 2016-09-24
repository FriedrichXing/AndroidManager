package com.joythink.xk.phonetools.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.joythink.xk.phonetools.base.BaseHolder;
import com.joythink.xk.phonetools.entity.FileMgrMenu;
import com.joythink.xk.phonetools.phone.R;
import com.joythink.xk.phonetools.utils.CommonUtil;

public class FileMgrMenuHolder extends BaseHolder<FileMgrMenu> {
	
	TextView tv_name,tv_size;
	ImageView iv_arrowRight;
	ProgressBar progressBar1;
	public FileMgrMenuHolder(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		View v = View.inflate(context, R.layout.layout_filemgrmenu, null);
		tv_name	= (TextView) v.findViewById(R.id.tv_name);
		tv_size	= (TextView) v.findViewById(R.id.tv_size);
		iv_arrowRight = (ImageView) v.findViewById(R.id.iv_arrowRight);
		progressBar1 = (ProgressBar) v.findViewById(R.id.progressBar1);
		return v;
	}

	@Override
	public void setData(FileMgrMenu t,boolean isFling) {
		// TODO Auto-generated method stub
		tv_name.setText(t.getName());
		tv_size.setText(CommonUtil.getFileSize(t.getSize()));
		if (t.isShowArrow()) {
			iv_arrowRight.setVisibility(View.VISIBLE);
			progressBar1.setVisibility(View.INVISIBLE);
		} else {
			iv_arrowRight.setVisibility(View.INVISIBLE);
			progressBar1.setVisibility(View.VISIBLE);
		}
	}

}
