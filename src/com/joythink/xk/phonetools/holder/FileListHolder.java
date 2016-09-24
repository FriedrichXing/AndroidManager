package com.joythink.xk.phonetools.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.joythink.xk.phonetools.base.BaseHolder;
import com.joythink.xk.phonetools.entity.FileInfo;
import com.joythink.xk.phonetools.phone.R;
import com.joythink.xk.phonetools.utils.BitmapUtil;
import com.joythink.xk.phonetools.utils.BitmapUtil.SizeMessage;
import com.joythink.xk.phonetools.utils.CommonUtil;
import com.joythink.xk.phonetools.utils.FileTypeUtil;

public class FileListHolder extends BaseHolder<FileInfo> {
	CheckBox cb_check;
	ImageView iv_icon;
	TextView tv_name, tv_time, tv_size;

	public FileListHolder(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		View v = View.inflate(context, R.layout.layout_item_fileinfo, null);
		cb_check = (CheckBox) v.findViewById(R.id.cb_check);
		iv_icon = (ImageView) v.findViewById(R.id.iv_icon);
		tv_name = (TextView) v.findViewById(R.id.tv_name);
		tv_time = (TextView) v.findViewById(R.id.tv_time);
		tv_size = (TextView) v.findViewById(R.id.tv_size);
		return v;
	}

	@Override
	public void setData(final FileInfo t, boolean isFling) {
		// TODO Auto-generated method stub
		cb_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				t.setChecked(isChecked);
			}
		});
		cb_check.setChecked(t.isChecked());
		
		if (t.getFileType().equals(FileTypeUtil.TYPE_IMAGE)) {

			Bitmap bitmap = BitmapUtil.loadBitmap(t.getFile().getAbsolutePath(), new SizeMessage(context, false, 60, 60));
			iv_icon.setImageBitmap(bitmap);
		} else {
			String fileIcon = t.getFileIcon();
			int resId = context.getResources().getIdentifier(fileIcon, "drawable", context.getPackageName());
			if (resId != 0) {
				iv_icon.setImageResource(resId);
			}
		}
		
		String name = t.getFile().getName();
		tv_name.setText(name);
		
		long size = t.getFile().length();
		tv_size.setText(CommonUtil.getFileSize(size)+"");
		
		long lastModified = t.getFile().lastModified();
		tv_time.setText(CommonUtil.getStrTime(lastModified)+"");
		
		
	}

}
