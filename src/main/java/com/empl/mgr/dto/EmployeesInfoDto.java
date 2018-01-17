package com.empl.mgr.dto;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 员工信息传输实体类
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class EmployeesInfoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long emplId; // 员工ID
	private String fullName; // 姓名
	private String photo;
	private boolean sex; // 性别
	private String identity; //身份证号码
	private String birthday; // 出生日期
	private String participate; // 入职时间
	private String socialSecurity; // 社保卡号
	private long deparemtn; // 所属部门
	private long position; // 员工职位
	private long education; // 文化程度
	private long marriage; // 婚姻状况
	private long politics; // 政治面貌
	private long national; // 民族

	private String phone; // 手机号码
	private String contact; // 其他联系方式
	private String emergencyContact; // 紧急联系人
	private String emergencyPhone; // 紧急联系方式
	private String school; // 毕业院校
	private String professional; // 专业
	private String graduationTime; // 毕业时间
	private String schooling; // 获得学历
	private String degree; // 获得学位
	private boolean isPaySocialSecurity; // 是否缴纳社保
	private String note; // 备注

	private EmployeesAddressDto register; // 户籍地址
	private EmployeesAddressDto current; // 现在居住地址

	private EmployeesCompnayDto[] company; // 工作单位

	public EmployeesInfoDto() {
		// TODO Auto-generated constructor stub
	}

	public EmployeesInfoDto(long emplId, String fullName, String photo, boolean sex, String identity, String birthday,
			String participate, String socialSecurity, long deparemtn, long position, long education, long marriage,
			long politics, long national, String phone, String contact, String emergencyContact, String emergencyPhone,
			String school, String professional, String graduationTime, String schooling, String degree,
			boolean isPaySocialSecurity, String note, EmployeesAddressDto register, EmployeesAddressDto current,
			EmployeesCompnayDto[] company) {
		super();
		this.emplId = emplId;
		this.fullName = fullName;
		this.photo = photo;
		this.sex = sex;
		this.identity = identity;
		this.birthday = birthday;
		this.participate = participate;
		this.socialSecurity = socialSecurity;
		this.deparemtn = deparemtn;
		this.position = position;
		this.education = education;
		this.marriage = marriage;
		this.politics = politics;
		this.national = national;
		this.phone = phone;
		this.contact = contact;
		this.emergencyContact = emergencyContact;
		this.emergencyPhone = emergencyPhone;
		this.school = school;
		this.professional = professional;
		this.graduationTime = graduationTime;
		this.schooling = schooling;
		this.degree = degree;
		this.isPaySocialSecurity = isPaySocialSecurity;
		this.note = note;
		this.register = register;
		this.current = current;
		this.company = company;
	}

	public EmployeesAddressDto getRegister() {
		return register;
	}

	public void setRegister(EmployeesAddressDto register) {
		this.register = register;
	}

	public EmployeesAddressDto getCurrent() {
		return current;
	}

	public void setCurrent(EmployeesAddressDto current) {
		this.current = current;
	}

	public long getEmplId() {
		return emplId;
	}

	public void setEmplId(long emplId) {
		this.emplId = emplId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public String getParticipate() {
		return participate;
	}

	public void setParticipate(String participate) {
		this.participate = participate;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public boolean isPaySocialSecurity() {
		return isPaySocialSecurity;
	}

	public void setPaySocialSecurity(boolean isPaySocialSecurity) {
		this.isPaySocialSecurity = isPaySocialSecurity;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public EmployeesCompnayDto[] getCompany() {
		return company;
	}

	public void setCompany(EmployeesCompnayDto[] company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "EmployeesInfoDto [emplId:" + emplId + ", fullName:" + fullName + ", photo:" + photo + ", sex:" + sex
				+ ", identity:" + identity + ", birthday:" + birthday + ", participate:" + participate
				+ ", socialSecurity:" + socialSecurity + ", deparemtn:" + deparemtn + ", position:" + position
				+ ", education:" + education + ", marriage:" + marriage + ", politics:" + politics + ", national:"
				+ national + ", phone:" + phone + ", contact:" + contact + ", emergencyContact:" + emergencyContact
				+ ", emergencyPhone:" + emergencyPhone + ", school:" + school + ", professional:" + professional
				+ ", graduationTime:" + graduationTime + ", schooling:" + schooling + ", degree:" + degree
				+ ", isPaySocialSecurity:" + isPaySocialSecurity + ", note:" + note + ", register:" + register + ", current:"
				+ current + ", company:" + Arrays.toString(company) + "]";
	}

}
