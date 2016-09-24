package com.joythink.xk.phonetools.db;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.joythink.xk.phonetools.utils.LogUtil;


import android.content.Context;
import android.os.Environment;

public class AssetsFileManager {
	public static String copyFile(Context context,String path){
		String newPath = path.substring(path.lastIndexOf("/"));
		String filePath = Environment.getDataDirectory()+"/data/"+context.getPackageName()+"/"+newPath;
		File file = new File(filePath);
		if (file.exists()) {
			LogUtil.d(AssetsFileManager.class, "文件已存在，无需复制");
			return filePath;
		}
		try {
			InputStream stream = context.getAssets().open(path);
			BufferedInputStream bis = new BufferedInputStream(stream); 
			OutputStream out = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			int len = 0;
			byte [] b = new byte[1024];
			while ((len = bis.read(b))!=-1) {
				bos.write(b,0,len);
			}
			bos.flush();
			bos.close();
			bis.close();
			return filePath;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
