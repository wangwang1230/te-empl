package com.empl.mgr.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateTimeUtil {

	/*
	 * 获取当前时间
	 * te5l.com [K]
	 */
	public static Date getCurrentTime() {
		return new Date();
	}

	/*
	 * 转换时间
	 * te5l.com [K]
	 */
	public static String conversionTime(Date date, String format) {
		if (CompareUtil.isEmpty(date) || StringUtils.isEmpty(format))
			return "";
		return new SimpleDateFormat(format).format(date);
	}

}
