package com.empl.mgr.constant;

import java.io.Serializable;

/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class EmployeesLogType implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * 录入信息
	 */
	public static final int RECORDING_INFORMATION = 1;

	/*
	 * 修改信息
	 */
	public static final int MODIFY_INFORMATION = 2;

	/*
	 * 入职
	 */
	public static final int INDUCTION = 3;

	/*
	 * 淘汰
	 */
	public static final int ELIMINATE = 4;

	/*
	 * 离职
	 */
	public static final int DEPARTURE = 5;

	/*
	 * 重新入职
	 */
	public static final int AGAIN_INDUCTION = 6;

	public static String findStatus(int key) {
		switch (key) {
		case 1:
			return "录入信息";
		case 2:
			return "修改信息";
		case 3:
			return "入职";
		case 4:
			return "淘汰";
		case 5:
			return "离职";
		case 6:
			return "重新入职";
		}
		return "";
	}

}
