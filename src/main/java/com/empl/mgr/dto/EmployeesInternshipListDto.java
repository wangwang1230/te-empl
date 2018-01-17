package com.empl.mgr.dto;

import java.io.Serializable;

/**
 * 员工列表传输实体类
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class EmployeesInternshipListDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id; // 员工ID
	private String name; // 员工姓名
	private boolean sex; // 员工性别
	private String date; // 入职时间
	private String department; // 所属部门
	private String position; // 所属职位
	private String identity; // 身份证号
	private long deptId;
	private long positionId;

	public EmployeesInternshipListDto() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 实习员工列表信息
	 */
	public EmployeesInternshipListDto(long emId, String emFullName, String emIdentity, boolean emSex,
			String emParticipateTime, long emDeparemtn, long emPosition) {
		super();
		this.id = emId;
		this.name = emFullName;
		this.identity = emIdentity;
		this.sex = emSex;
		this.date = emParticipateTime;
		this.deptId = emDeparemtn;
		this.positionId = emPosition;
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

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public long getPositionId() {
		return positionId;
	}

	public void setPositionId(long positionId) {
		this.positionId = positionId;
	}

	@Override
	public String toString() {
		return "EmployeesInternshipListDto [id:" + id + ", name:" + name + ", sex:" + sex + ", date:" + date
				+ ", department:" + department + ", position:" + position + ", identity:" + identity + "]";
	}

}
