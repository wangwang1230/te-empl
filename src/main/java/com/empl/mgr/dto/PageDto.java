package com.empl.mgr.dto;

import java.io.Serializable;

/**
 * 分页传输实体类
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class PageDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int count; // 总数
	private int totalPage; // 总页数
	private int nowPage; // 当前数量
	private int number; // 每页显示数据

	public PageDto() {
		// TODO Auto-generated constructor stub
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "PageDto [count:" + count + ", totalPage:" + totalPage + ", nowPage:" + nowPage + ", number:" + number
				+ "]";
	}

}
