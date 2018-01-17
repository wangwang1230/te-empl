package com.empl.mgr.dto;

import java.io.Serializable;

/**
 * 通过培训项目ID查看参加过该培训项目员工记录
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class TrainingRecordDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id; // 项目ID
	private String name; // 员工信息
	private boolean sex; // 员工性别
	private String identity; // 身份证号码
	private String department; // 员工部门
	private String position; // 员工职位
	private String time; // 报名时间
	private int state; // 培训状态ID
	private String status; // 培训状态
	private String note; // 备注

	public TrainingRecordDto() {
		// TODO Auto-generated constructor stub
	}

	public TrainingRecordDto(long id, String emFullName, String emIdentity, boolean sex, String applyTime, int state,
			String note) {
		super();
		this.id = id;
		this.name = emFullName;
		this.identity = emIdentity;
		this.sex = sex;
		this.time = applyTime;
		this.state = state;
		this.note = note;
	}

	public TrainingRecordDto(long id, String name, int state, String applyTime) {
		super();
		this.id = id;
		this.name = name;
		this.time = applyTime;
		this.state = state;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "TrainingRecordDto [id:" + id + ", name:" + name + ", sex:" + sex + ", identity:" + identity
				+ ", department:" + department + ", position:" + position + ", time:" + time + ", state:" + state
				+ ", status:" + status + ", note:" + note + "]";
	}

}
