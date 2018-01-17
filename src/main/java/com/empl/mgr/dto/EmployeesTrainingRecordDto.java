package com.empl.mgr.dto;

import java.io.Serializable;

/**
 * 培训记录实体传输类
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class EmployeesTrainingRecordDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id; // 记录ID
	private String name; // 项目名称
	private int state; // 培训状态ID
	private String status; // 培训状态
	private String time; // 报名时间
	private String note; // 备注信息

	public EmployeesTrainingRecordDto() {
		// TODO Auto-generated constructor stub
	}

	public EmployeesTrainingRecordDto(long logId, String name, int state, String applyTime, String note) {
		super();
		this.id = logId;
		this.name = name;
		this.state = state;
		this.time = applyTime;
		this.note = note;
	}

	@Override
	public String toString() {
		return "EmployeesTrainingRecordDto [id:" + id + ", name:" + name + ", state:" + state + ", status:" + status
				+ ", time:" + time + ", note:" + note + "]";
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
