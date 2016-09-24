package com.joythink.xk.phonetools.utils;

import com.joythink.xk.phonetools.phone.GuideActivity;
import com.joythink.xk.phonetools.phone.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

public class NotificationUtil {
	private Context context;
	private static NotificationUtil nutil;
	private NotificationManager nm;
	private int id = 1;
	
	private NotificationUtil(Context context) {
		super();
		this.context = context;
		nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	}
	
	public static NotificationUtil getInstance(Context context){
		if (nutil == null) {
			synchronized (context) {
				if (nutil == null) {
					nutil = new NotificationUtil(context);
				}
			}
		}
		return nutil;
	}
	
	public void show(){
		NotificationCompat.Builder b = new NotificationCompat.Builder(context);
		Intent in = new Intent(context, GuideActivity.class);
		PendingIntent intent = PendingIntent.getActivity(context, 0, in , PendingIntent.FLAG_UPDATE_CURRENT);
		b.setAutoCancel(true).setContentInfo("内容信息").setContentIntent(intent);
		b.setContentText("内容文本").setContentTitle("内容标题").setDefaults(Notification.DEFAULT_SOUND);
		b.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher));
		b.setSmallIcon(R.drawable.azy);
		b.setProgress(100, 40, true);
		b.setSubText("subText").setTicker("您有新的通知");
		
		Notification n = b.build();
		nm.notify(id, n );
	}
	
	public void cancel(){
		nm.cancel(id);
	}
}
