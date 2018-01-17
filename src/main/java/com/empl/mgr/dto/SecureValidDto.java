package com.empl.mgr.dto;

import java.io.Serializable;

/**
 * 权限实体传输类
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class SecureValidDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id; // ID
	private String name; // 模块名称
	private boolean find; // 查看权限
	private boolean add; // 添加权限
	private boolean delete; // 删除权限
	private boolean modify; // 修改权限

	public SecureValidDto() {
		// TODO Auto-generated constructor stub
	}

	public SecureValidDto(long id, String moduleName, boolean find, boolean add, boolean delete, boolean modify) {
		super();
		this.id = id;
		this.name = moduleName;
		this.find = find;
		this.add = add;
		this.delete = delete;
		this.modify = modify;
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

	public boolean isFind() {
		return find;
	}

	public void setFind(boolean find) {
		this.find = find;
	}

	public boolean isAdd() {
		return add;
	}

	public void setAdd(boolean add) {
		this.add = add;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public boolean isModify() {
		return modify;
	}

	public void setModify(boolean modify) {
		this.modify = modify;
	}

}
