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
 * TeDepartment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "te_department")
public class TeDepartment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long deptId;
	private Date timestamp;
	private String deptName;
	private Date createTime;
	private String creator;
	private String deptDescription;
	private long deptPrincipal;

	// Constructors

	/** default constructor */
	public TeDepartment() {
	}

	/** full constructor */
	public TeDepartment(String deptName, Date createTime, String creator, String deptDescription, long deptPrincipal) {
		this.deptName = deptName;
		this.createTime = createTime;
		this.creator = creator;
		this.deptDescription = deptDescription;
		this.deptPrincipal = deptPrincipal;
	}

	@Override
	public String toString() {
		return "TeDepartment [deptId:" + deptId + ", timestamp:" + timestamp + ", deptName:" + deptName
				+ ", createTime:" + createTime + ", creator:" + creator + ", deptDescription:" + deptDescription
				+ ", deptPrincipal:" + deptPrincipal + "]";
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "deptId", unique = true, nullable = false)
	public long getDeptId() {
		return this.deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	@Version
	@Column(name = "timestamp", nullable = false, length = 19)
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "deptName", length = 64)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "createTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "creator", length = 64)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "deptDescription", length = 1024)
	public String getDeptDescription() {
		return this.deptDescription;
	}

	public void setDeptDescription(String deptDescription) {
		this.deptDescription = deptDescription;
	}

	@Column(name = "deptPrincipal")
	public long getDeptPrincipal() {
		return this.deptPrincipal;
	}

	public void setDeptPrincipal(long deptPrincipal) {
		this.deptPrincipal = deptPrincipal;
	}

}