package com.empl.mgr.utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class VerifyUtils implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 验证日期, 传入yyyy-MM-dd格式字符串, 返回格式是否正确
	 * @param date
	 * @return
	 */
	public static boolean verifyDate(String date) {
		if (StringUtils.isEmpty(date))
			return false;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			format.setLenient(false);
			format.parse(date);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 判断两个时间字符串大小
	 * @param minDate
	 * @param maxDate
	 * @return
	 * @throws ParseException 
	 */
	public static boolean verdictSize(String minDate, String maxDate, boolean equals) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date min = format.parse(minDate);
			Date max = format.parse(maxDate);
			return equals ? min.getTime() <= max.getTime() : min.getTime() < max.getTime();
		} catch (ParseException e) {
			// TODO: handle exception
			return false;
		}
	}

}
