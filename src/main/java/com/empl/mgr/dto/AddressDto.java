package com.empl.mgr.dto;

import java.io.Serializable;

/**
 * 地址信息传输表
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class AddressDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;

	public AddressDto(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public AddressDto() {
		// TODO Auto-generated constructor stub
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
		return "AddressDto [id:" + id + ", name:" + name + "]";
	}

}
