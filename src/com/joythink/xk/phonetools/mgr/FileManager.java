package com.joythink.xk.phonetools.mgr;

import java.io.File;
import java.util.ArrayList;

import com.joythink.xk.phonetools.entity.FileInfo;
import com.joythink.xk.phonetools.utils.FileTypeUtil;
import com.joythink.xk.phonetools.utils.LogUtil;

public class FileManager {
	ArrayList<ArrayList<FileInfo>> list;

	public static final int TYPE_TXT = 0;
	public static final int TYPE_IMAGE = 1;
	public static final int TYPE_VIDEO = 2;
	public static final int TYPE_AUDIO = 3;
	public static final int TYPE_ZIP = 4;
	public static final int TYPE_APK = 5;
	public static final int TYPE_OTHER = 6;
	private int currentType = -1;
	private boolean isStarted = false;
	
	public ArrayList<ArrayList<FileInfo>> getList() {
		return list;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public FileManager() {
		super();
		// TODO Auto-generated constructor stub
		list = new ArrayList<ArrayList<FileInfo>>();
		for (int i = 0; i < 7; i++) {
			list.add(new ArrayList<FileInfo>());
		}
	}

	private static FileManager manager;

	public static FileManager getInstance() {
		if (manager == null) {
			synchronized (FileManager.class) {
				if (manager == null) {
					manager = new FileManager();
				}
			}
		}
		return manager;
	}

	String inSDpath = MemoryManager.getPhoneInSDCardPath();

	public void searchFile() {
		resetData();
		searchFile(new File(inSDpath), true);
	}

	private void resetData() {
		// TODO Auto-generated method stub
		isStarted = true;
		for (int i = 0; i < 7; i++) {
			list.get(i).clear();
		}
	}

	public void searchFile(File f, boolean isFirstRun) {
		if (!isStarted) {
			if (isFirstRun) {
				if (listener != null) {
					listener.onSearchFinished();
				}
			}
			return;
		}
		if (!f.isDirectory()) {
			classify(f);
		} else {
			File[] files = f.listFiles();
			for (File file : files) {
				searchFile(file, false);
			}
		}

		if (isFirstRun) {
			if (listener != null) {
				listener.onSearchFinished();
			}
		}

	}

	private void classify(File f) {
//		try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		LogUtil.d(this, f.getName());
		String[] fileIconAndTypeName = FileTypeUtil.getFileIconAndTypeName(f);
		String icon = fileIconAndTypeName[0];
		String type = fileIconAndTypeName[1];
		FileInfo fileInfo = new FileInfo(icon, type, f, false);
		if (type == FileTypeUtil.TYPE_APK) {
			currentType = TYPE_APK;
		} else if (type == FileTypeUtil.TYPE_AUDIO) {
			currentType = TYPE_AUDIO;
		} else if (type == FileTypeUtil.TYPE_IMAGE) {
			currentType = TYPE_IMAGE;
		} else if (type == FileTypeUtil.TYPE_TXT) {
			currentType = TYPE_TXT;
		} else if (type == FileTypeUtil.TYPE_VIDEO) {
			currentType = TYPE_VIDEO;
		} else if (type == FileTypeUtil.TYPE_ZIP) {
			currentType = TYPE_ZIP;
		} else {
			currentType = TYPE_OTHER;
		}
		list.get(currentType).add(fileInfo);

		if (listener != null) {
			listener.onSearching(currentType, f.length());
		}
	}
	
	public static void deleteFile(File file){
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				deleteFile(f);
			}
		}
		
		
		
		
		file.delete();
	}
	

	public void setListener(SearchListener listener) {
		this.listener = listener;
	}

	private SearchListener listener;

	public interface SearchListener {
		void onSearching(int currentType, long fileSize);

		void onSearchFinished();
	}

}
