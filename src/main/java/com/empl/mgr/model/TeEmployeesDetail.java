package com.empl.mgr.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * TeEmployeesDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "te_employees_detail", uniqueConstraints = @UniqueConstraint(columnNames = "emplNo"))
public class TeEmployeesDetail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long emId;
	private Date timestamp;
	private long emplNo;
	private String emContact;
	private String emEmergencyContact;
	private String emEmergencyPhone;
	private String emSchool;
	private String emProfessional;
	private String emGraduationTime;
	private String emSchooling;
	private String emDegree;
	private boolean emIsSocialSecurity;
	private String emNote;

	// Constructors

	/** default constructor */
	public TeEmployeesDetail() {
	}

	/** full constructor */
	public TeEmployeesDetail(long emplNo, String emContact, String emEmergencyContact, String emEmergencyPhone,
			String emSchool, String emProfessional, String emGraduationTime, String emSchooling, String emDegree,
			boolean emIsSocialSecurity, String emNote) {
		this.emplNo = emplNo;
		this.emContact = emContact;
		this.emEmergencyContact = emEmergencyContact;
		this.emEmergencyPhone = emEmergencyPhone;
		this.emSchool = emSchool;
		this.emProfessional = emProfessional;
		this.emGraduationTime = emGraduationTime;
		this.emSchooling = emSchooling;
		this.emDegree = emDegree;
		this.emIsSocialSecurity = emIsSocialSecurity;
		this.emNote = emNote;
	}

	@Override
	public String toString() {
		return "TeEmployeesDetail [emId:" + emId + ", timestamp:" + timestamp + ", emplNo:" + emplNo + ", emContact:"
				+ emContact + ", emEmergencyContact:" + emEmergencyContact + ", emEmergencyPhone:" + emEmergencyPhone
				+ ", emSchool:" + emSchool + ", emProfessional:" + emProfessional + ", emGraduationTime:"
				+ emGraduationTime + ", emSchooling:" + emSchooling + ", emDegree:" + emDegree
				+ ", emIsSocialSecurity:" + emIsSocialSecurity + ", emNote:" + emNote + "]";
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "emId", unique = true, nullable = false)
	public long getEmId() {
		return this.emId;
	}

	public void setEmId(long emId) {
		this.emId = emId;
	}

	@Version
	@Column(name = "timestamp", nullable = false, length = 19)
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "emplNo", unique = true)
	public long getEmplNo() {
		return this.emplNo;
	}

	public void setEmplNo(long emplNo) {
		this.emplNo = emplNo;
	}

	@Column(name = "emContact", length = 1024)
	public String getEmContact() {
		return this.emContact;
	}

	public void setEmContact(String emContact) {
		this.emContact = emContact;
	}

	@Column(name = "emEmergencyContact", length = 64)
	public String getEmEmergencyContact() {
		return this.emEmergencyContact;
	}

	public void setEmEmergencyContact(String emEmergencyContact) {
		this.emEmergencyContact = emEmergencyContact;
	}

	@Column(name = "emEmergencyPhone", length = 1024)
	public String getEmEmergencyPhone() {
		return this.emEmergencyPhone;
	}

	public void setEmEmergencyPhone(String emEmergencyPhone) {
		this.emEmergencyPhone = emEmergencyPhone;
	}

	@Column(name = "emSchool", length = 1024)
	public String getEmSchool() {
		return this.emSchool;
	}

	public void setEmSchool(String emSchool) {
		this.emSchool = emSchool;
	}

	@Column(name = "emProfessional", length = 1024)
	public String getEmProfessional() {
		return this.emProfessional;
	}

	public void setEmProfessional(String emProfessional) {
		this.emProfessional = emProfessional;
	}

	@Column(name = "emGraduationTime", length = 12)
	public String getEmGraduationTime() {
		return this.emGraduationTime;
	}

	public void setEmGraduationTime(String emGraduationTime) {
		this.emGraduationTime = emGraduationTime;
	}

	@Column(name = "emSchooling", length = 256)
	public String getEmSchooling() {
		return this.emSchooling;
	}

	public void setEmSchooling(String emSchooling) {
		this.emSchooling = emSchooling;
	}

	@Column(name = "emDegree", length = 256)
	public String getEmDegree() {
		return this.emDegree;
	}

	public void setEmDegree(String emDegree) {
		this.emDegree = emDegree;
	}

	@Column(name = "emIsSocialSecurity")
	public boolean getEmIsSocialSecurity() {
		return this.emIsSocialSecurity;
	}

	public void setEmIsSocialSecurity(boolean emIsSocialSecurity) {
		this.emIsSocialSecurity = emIsSocialSecurity;
	}

	@Column(name = "emNote", length = 65535)
	public String getEmNote() {
		return this.emNote;
	}

	public void setEmNote(String emNote) {
		this.emNote = emNote;
	}

}