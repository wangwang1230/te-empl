package com.empl.mgr.constant;

/**
 * 培训项目状态
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class TrainingState {

	/*
	 * 未开始
	 */
	public static final int NOT_STARTED = 0;

	/*
	 * 已开始
	 */
	public static final int HAVE_BEGUN = 1;

	/*
	 * 已经完成
	 */
	public static final int FINISH = 2;

	/*
	 * 强制结束
	 */
	public static final int FORCED_END = 3;

	public static String findVal(int key) {
		switch (key) {
		case 0:
			return "未开始";
		case 1:
			return "已经开始";
		case 2:
			return "完成培训";
		case 3:
			return "强制停止";
		}
		return "";
	}

}
