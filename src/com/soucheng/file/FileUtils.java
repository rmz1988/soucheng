package com.soucheng.file;

import android.os.Environment;

/**
 * 文件操作工具类
 *
 * @author lichen
 */
public final class FileUtils {

	public static boolean hasSdCard() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}

}
