package com.empl.mgr.dto;

import java.io.Serializable;

/**
 * 员工前工作单位传输实体
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class EmployeesCompnayDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name; // 单位名称
	private String state; // 入职时间
	private String end; // 离职时间
	private String jobs; // 工作岗位
	private String reason; // 离职原因

	public EmployeesCompnayDto() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getJobs() {
		return jobs;
	}

	public void setJobs(String jobs) {
		this.jobs = jobs;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "EmployeesCompnayDto [name:" + name + ", state:" + state + ", end:" + end + ", jobs:" + jobs
				+ ", reason:" + reason + "]";
	}

}
