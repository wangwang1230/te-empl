package com.empl.mgr.utils;

import java.io.Serializable;

public class CompareUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 判断对象是否为空
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null)
			return true;
		return false;
	}

	public static boolean isNotEmpty(Object obj) {
		if (obj != null)
			return true;
		return false;
	}

	/**
	 * 判断一个int的包装类型是否为空, 如果为空, 为其默认赋值为0
	 * @param val
	 * @return
	 */
	public static int isEmptyInteger(Integer val) {
		if (val == null)
			return 0;
		return val;
	}

	/**
	 * 
	 * @param val
	 * @return
	 */
	public static long isEmptyLong(Long val) {
		if (val == null)
			return 0;
		return val;
	}

}
