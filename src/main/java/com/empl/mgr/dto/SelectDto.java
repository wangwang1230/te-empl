package com.empl.mgr.dto;

import java.io.Serializable;

/**
 * 下拉框通用传输类
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class SelectDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;

	public SelectDto() {
		// TODO Auto-generated constructor stub
	}

	public SelectDto(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SelectDto [id:" + id + ", name:" + name + "]";
	}

}
