package com.empl.mgr.dto;

import java.io.Serializable;

/**
 * 为培训项目添加员工信息时, 获取员工信息列表实体传输类
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class EemplByNoneTrainingLogDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private long emplDept;
	private String name;
	private boolean sex;
	private String identity;
	private String birthday;
	private String department;

	public EemplByNoneTrainingLogDto() {
		// TODO Auto-generated constructor stub
	}

	public EemplByNoneTrainingLogDto(long emId, String emFullName, boolean emSex, String emIdentity, String emBirthday,
			long emDeparemtn) {
		super();
		this.id = emId;
		this.name = emFullName;
		this.sex = emSex;
		this.identity = emIdentity;
		this.birthday = emBirthday;
		this.emplDept = emDeparemtn;
	}

	@Override
	public String toString() {
		return "EemplByNoneTrainingLogDto [id:" + id + ", emplDept:" + emplDept + ", name:" + name + ", sex:" + sex
				+ ", identity:" + identity + ", birthday:" + birthday + ", department:" + department + "]";
	}

	public long getEmplDept() {
		return emplDept;
	}

	public void setEmplDept(long emplDept) {
		this.emplDept = emplDept;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
