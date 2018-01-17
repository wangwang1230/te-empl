package com.empl.mgr.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * TeEmployeesCompany entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "te_employees_company")
public class TeEmployeesCompany implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long comId;
	private Date timestamp;
	private long emplNo;
	private String comName;
	private String comParticipateTime;
	private String comLeaveTime;
	private String comPosition;
	private String comReason;

	// Constructors

	@Override
	public String toString() {
		return "TeEmployeesCompany [comId:" + comId + ", timestamp:" + timestamp + ", emplNo:" + emplNo + ", comName:"
				+ comName + ", comParticipateTime:" + comParticipateTime + ", comLeaveTime:" + comLeaveTime
				+ ", comPosition:" + comPosition + ", comReason:" + comReason + "]";
	}

	/** default constructor */
	public TeEmployeesCompany() {
	}

	/** full constructor */
	public TeEmployeesCompany(long emplNo, String comName, String comParticipateTime, String comLeaveTime,
			String comPosition, String comReason) {
		this.emplNo = emplNo;
		this.comName = comName;
		this.comParticipateTime = comParticipateTime;
		this.comLeaveTime = comLeaveTime;
		this.comPosition = comPosition;
		this.comReason = comReason;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "comId", unique = true, nullable = false)
	public long getComId() {
		return this.comId;
	}

	public void setComId(long comId) {
		this.comId = comId;
	}

	@Version
	@Column(name = "timestamp", nullable = false, length = 19)
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "emplNo")
	public long getEmplNo() {
		return this.emplNo;
	}

	public void setEmplNo(long emplNo) {
		this.emplNo = emplNo;
	}

	@Column(name = "comName", length = 256)
	public String getComName() {
		return this.comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	@Column(name = "comParticipateTime", length = 12)
	public String getComParticipateTime() {
		return this.comParticipateTime;
	}

	public void setComParticipateTime(String comParticipateTime) {
		this.comParticipateTime = comParticipateTime;
	}

	@Column(name = "comLeaveTime", length = 12)
	public String getComLeaveTime() {
		return this.comLeaveTime;
	}

	public void setComLeaveTime(String comLeaveTime) {
		this.comLeaveTime = comLeaveTime;
	}

	@Column(name = "comPosition", length = 128)
	public String getComPosition() {
		return this.comPosition;
	}

	public void setComPosition(String comPosition) {
		this.comPosition = comPosition;
	}

	@Column(name = "comReason", length = 1024)
	public String getComReason() {
		return this.comReason;
	}

	public void setComReason(String comReason) {
		this.comReason = comReason;
	}

}