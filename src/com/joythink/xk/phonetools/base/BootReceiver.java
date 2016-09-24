package com.joythink.xk.phonetools.base;

import com.joythink.xk.phonetools.phone.SettingActivity;
import com.joythink.xk.phonetools.utils.NotificationUtil;
import com.joythink.xk.phonetools.utils.PrefUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (PrefUtil.load(context, SettingActivity.IS_BOOT, false)) {
			NotificationUtil.getInstance(context).show();
		}
	}

}
