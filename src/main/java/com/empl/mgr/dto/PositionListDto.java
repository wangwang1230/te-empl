package com.empl.mgr.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 职位信息传输实体类
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class PositionListDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String positionName;
	private long department;
	private String description;
	private String creator;
	private Date date;
	private String departmentName;
	private String time;

	public PositionListDto() {
		// TODO Auto-generated constructor stub
	}

	public PositionListDto(long poId, String poName, long poDepartment, String poDescription, String creator,
			Date createTime, String deptName) {
		super();
		this.id = poId;
		this.positionName = poName;
		this.department = poDepartment;
		this.description = poDescription;
		this.creator = creator;
		this.date = createTime;
		this.departmentName = deptName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public long getDepartment() {
		return department;
	}

	public void setDepartment(long department) {
		this.department = department;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "PositionListDto [id:" + id + ", positionName:" + positionName + ", department:" + department
				+ ", description:" + description + ", creator:" + creator + ", date:" + date + ", departmentName:"
				+ departmentName + "]";
	}

}
