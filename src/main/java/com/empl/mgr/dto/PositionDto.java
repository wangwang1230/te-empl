package com.empl.mgr.dto;

import java.io.Serializable;

/**
 * 职位实体传输类
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class PositionDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;

	public PositionDto() {
		// TODO Auto-generated constructor stub
	}

	public PositionDto(long poId, String poName) {
		super();
		this.id = poId;
		this.name = poName;
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
		return "PositionDto [id:" + id + ", name:" + name + "]";
	}

}
