package com.empl.mgr.dto;

import java.io.Serializable;

/**
 * 员工基本信息类
 * 将数据库里的员工信息封闭到此类中, 可以屏蔽关键信息, 也能避免暴露数据库字段
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class EmployeesBasicInfoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id; // ID号	
	private String photo; // 头像地址
	private String name; // 名称
	private boolean sex; // 性别
	private String identity; // 身份证号
	private String birthday; // 出生日期
	private String participateTime; // 入职时间
	private String phone; // 手机号码
	private String socialSecurity; // 社保卡号
	private long deparemtn; // 部门ID
	private long position; // 职位ID
	private long education; // 学历
	private long marriage; // 婚姻状况
	private long politics; // 政治面貌 
	private long national; // 民族
	private long current; // 现居住地址
	private long register; // 户籍地址
	private int state; // 员工状态

	// 详细信息
	private String contact; // 其他联系方式
	private String emergencyContact; // 紧急联系人
	private String emergencyPhone;// 紧急联系方式
	private String school;// 毕业校院
	private String professional;// 就读专业
	private String graduationTime;// 毕业时间
	private String schooling;// 学历
	private String degree;// 学位
	private boolean isSocialSecurity;// 是否缴纳社保
	private String note;// 备注

	public EmployeesBasicInfoDto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmployeesBasicInfoDto [id:" + id + ", photo:" + photo + ", name:" + name + ", sex:" + sex
				+ ", identity:" + identity + ", birthday:" + birthday + ", participateTime:" + participateTime
				+ ", phone:" + phone + ", socialSecurity:" + socialSecurity + ", deparemtn:" + deparemtn
				+ ", position:" + position + ", education:" + education + ", marriage:" + marriage + ", politics:"
				+ politics + ", national:" + national + ", current:" + current + ", register:" + register
				+ ", contact:" + contact + ", emergencyContact:" + emergencyContact + ", emergencyPhone:"
				+ emergencyPhone + ", school:" + school + ", professional:" + professional + ", graduationTime:"
				+ graduationTime + ", schooling:" + schooling + ", degree:" + degree + ", isSocialSecurity:"
				+ isSocialSecurity + ", note:" + note + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public String getParticipateTime() {
		return participateTime;
	}

	public void setParticipateTime(String participateTime) {
		this.participateTime = participateTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public long getDeparemtn() {
		return deparemtn;
	}

	public void setDeparemtn(long deparemtn) {
		this.deparemtn = deparemtn;
	}

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	public long getEducation() {
		return education;
	}

	public void setEducation(long education) {
		this.education = education;
	}

	public long getMarriage() {
		return marriage;
	}

	public void setMarriage(long marriage) {
		this.marriage = marriage;
	}

	public long getPolitics() {
		return politics;
	}

	public void setPolitics(long politics) {
		this.politics = politics;
	}

	public long getNational() {
		return national;
	}

	public void setNational(long national) {
		this.national = national;
	}

	public long getCurrent() {
		return current;
	}

	public void setCurrent(long current) {
		this.current = current;
	}

	public long getRegister() {
		return register;
	}

	public void setRegister(long register) {
		this.register = register;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getGraduationTime() {
		return graduationTime;
	}

	public void setGraduationTime(String graduationTime) {
		this.graduationTime = graduationTime;
	}

	public String getSchooling() {
		return schooling;
	}

	public void setSchooling(String schooling) {
		this.schooling = schooling;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public boolean getIsSocialSecurity() {
		return isSocialSecurity;
	}

	public void setSocialSecurity(boolean isSocialSecurity) {
		this.isSocialSecurity = isSocialSecurity;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
