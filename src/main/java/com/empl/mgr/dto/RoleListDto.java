package com.empl.mgr.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色列表实体传输类
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class RoleListDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String description;
	private Date data;
	private String creator;
	private String time;

	public RoleListDto() {
		// TODO Auto-generated constructor stub
	}

	public RoleListDto(long roleId, String roleName, String roleDescription, Date createTime, String creator) {
		super();
		this.id = roleId;
		this.name = roleName;
		this.description = roleDescription;
		this.data = createTime;
		this.creator = creator;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RoleListDto [id:" + id + ", name:" + name + ", description:" + description + ", data:" + data
				+ ", creator:" + creator + ", time:" + time + "]";
	}

}
