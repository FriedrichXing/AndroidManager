package com.joythink.xk.phonetools.mgr;

import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Debug.MemoryInfo;

import com.joythink.xk.phonetools.entity.SpeedUpMenu;

public class RunningAppMgr {
	private List<SpeedUpMenu> userList;
	private List<SpeedUpMenu> systemList;
	ActivityManager am;
	PackageManager pm;
	private static RunningAppMgr manager;
	
	
	private RunningAppMgr(Context context) {
		am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		pm = context.getPackageManager();
	}

	public static RunningAppMgr getInstance(Context context) {
		if (manager == null) {
			manager = new RunningAppMgr(context);
		}
		return manager;
	}

	public List<SpeedUpMenu> getUserList(boolean isUpdate) {
		if (userList == null||isUpdate) {
			initLists();
		}
		return userList;
	}

	public List<SpeedUpMenu> getSystemList(boolean isUpdate) {
		if (systemList == null||isUpdate) {
			initLists();
		}
		return systemList;
	}
	
	public void killProcess(String packageName){
		am.killBackgroundProcesses(packageName);
	}
	
	public void killAll(){
		List<RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
		for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
			killProcess(runningAppProcessInfo.processName);
		}
	}

	public void initLists() {
		if (userList != null) {
			userList.clear();
		} else {
			userList = new ArrayList<SpeedUpMenu>();
		}
		if (systemList != null) {
			systemList.clear();
		} else {
			systemList = new ArrayList<SpeedUpMenu>();
		}
		
		List<RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
		for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
			SpeedUpMenu app = new SpeedUpMenu();
			String packagename = runningAppProcessInfo.processName;
			app.setPackageName(packagename);
			int pid = runningAppProcessInfo.pid;
			MemoryInfo[] memoryInfo = am.getProcessMemoryInfo(new int[]{pid});
			long memory = memoryInfo[0].getTotalPrivateDirty()*1024;
			app.setMemory(memory);
			try {
				Drawable icon = pm.getApplicationIcon(packagename);
				app.setIcon(icon);
				ApplicationInfo info = pm.getApplicationInfo(packagename, PackageManager.GET_META_DATA|PackageManager.GET_SHARED_LIBRARY_FILES|PackageManager.GET_UNINSTALLED_PACKAGES);
				String label = pm.getApplicationLabel(info).toString();
				app.setLabel(label);
				if ((info.flags&ApplicationInfo.FLAG_SYSTEM)==1) {
					app.setSystem(true);
					systemList.add(app);
				} else{
					app.setChecked(true);
					userList.add(app);
				}
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
