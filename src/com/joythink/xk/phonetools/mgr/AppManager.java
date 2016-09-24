package com.joythink.xk.phonetools.mgr;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.joythink.xk.phonetools.entity.AppInfo;

public class AppManager {
	private List<AppInfo> allList,systemList,userList;
	private PackageManager pm;
	private static AppManager manager;
	
	public static AppManager getInstance(Context context){
		if (manager == null) {
			synchronized (context) {
				if (manager == null) {
					manager = new AppManager(context);
				}
			}
		}
		return manager;
		
	}
	
	public AppManager(Context context) {
		super();
		pm = context.getPackageManager();
	}

	public List<AppInfo> getAllList(boolean isUpdate) {
		if (allList==null||isUpdate) {
			initAllData();
		}
		return allList;
	}

	public List<AppInfo> getSystemList(boolean isUpdate) {
		if (systemList==null||isUpdate) {
			initAllData();
		}
		return systemList;
	}

	public List<AppInfo> getUserList(boolean isUpdate) {
		if (userList==null||isUpdate) {
			initAllData();
		}
		
		return userList;
	}

	private void initAllData() {
		if (allList != null) {
			allList.clear();
		} else {
			allList = new ArrayList<AppInfo>();
		}
		if (userList != null) {
			userList.clear();
		} else {
			userList = new ArrayList<AppInfo>();
		}
		if (systemList != null) {
			systemList.clear();
		} else {
			systemList = new ArrayList<AppInfo>();
		}
		
		List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_ACTIVITIES|PackageManager.GET_UNINSTALLED_PACKAGES);
		for (PackageInfo packageInfo : packages) {
			Drawable icon = packageInfo.applicationInfo.loadIcon(pm);
			String label = packageInfo.applicationInfo.loadLabel(pm).toString();
			String packageName = packageInfo.packageName;
			long firstInstallTime = packageInfo.firstInstallTime;
			AppInfo appInfo = new AppInfo(false, icon, label, packageName, firstInstallTime);
			allList.add(appInfo);
			if((packageInfo.applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)==1){
				systemList.add(appInfo);
			} else{
				userList.add(appInfo);
			}
		}
	}
	
	
	
	
}
