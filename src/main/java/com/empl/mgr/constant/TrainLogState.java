package com.empl.mgr.constant;

/**
 * 员工培训记录状态
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class TrainLogState {

	/*
	 * 已报名
	 */
	public static final int APPLY = 1;

	/*
	 * 培训中
	 */
	public static final int START = 2;

	/*
	 * 完成培训
	 */
	public static final int FINISH = 3;

	/*
	 * 停止培训
	 */
	public static final int EXIT = 4;

	/*
	 * 已评分
	 */
	public static final int EVALUATION = 5;

	public static String findStatus(int key) {
		switch (key) {
		case 1:
			return "已报名";
		case 2:
			return "培训中";
		case 3:
			return "培训完成";
		case 4:
			return "停止培训";
		case 5:
			return "已评分";
		}
		return "";
	}
}
