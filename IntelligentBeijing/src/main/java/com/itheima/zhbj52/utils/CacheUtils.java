package com.itheima.zhbj52.utils;

import android.content.Context;

/**
 * 缓存工具类
 * 
 * @author Kevin
 * 
 */
public class CacheUtils {

	/**
	 * 设置缓存 key 是url, value是json
	 */
	public static void setCache(String key, String value, Context ctx) {
		PrefUtils.setString(ctx, key, value);
		//可以将缓存放在文件中, 文件名就是Md5(url), 文件内容是json
	}

	/**
	 * 获取缓存 key 是url
	 */
	public static String getCache(String key, Context ctx) {
		return PrefUtils.getString(ctx, key, null);
	}
}
