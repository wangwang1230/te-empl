package com.empl.mgr.constant.db;

/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public enum TeAccountState {

	delete(-1, "删除"), normal(0, "正常");

	private int state;
	private String name;

	private TeAccountState() {
		// TODO Auto-generated constructor stub
	}

	private TeAccountState(int state, String name) {
		this.state = state;
		this.name = name;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
