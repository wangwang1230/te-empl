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
 * TePosition entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "te_position")
public class TePosition implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long poId;
	private Date timestamp;
	private long poDepartment;
	private String poName;
	private String poDescription;
	private String creator;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TePosition() {
	}

	/** full constructor */
	public TePosition(long poDepartment, String poName, String poDescription, String creator, Date createTime) {
		this.poDepartment = poDepartment;
		this.poName = poName;
		this.poDescription = poDescription;
		this.creator = creator;
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "TePosition [poId:" + poId + ", timestamp:" + timestamp + ", poDepartment:" + poDepartment + ", poName:"
				+ poName + ", poDescription:" + poDescription + ", creator:" + creator + ", createTime:" + createTime
				+ "]";
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "poId", unique = true, nullable = false)
	public long getPoId() {
		return this.poId;
	}

	public void setPoId(long poId) {
		this.poId = poId;
	}

	@Version
	@Column(name = "timestamp", nullable = false, length = 19)
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "poDepartment")
	public long getPoDepartment() {
		return this.poDepartment;
	}

	public void setPoDepartment(long poDepartment) {
		this.poDepartment = poDepartment;
	}

	@Column(name = "poName", length = 128)
	public String getPoName() {
		return this.poName;
	}

	public void setPoName(String poName) {
		this.poName = poName;
	}

	@Column(name = "poDescription", length = 1024)
	public String getPoDescription() {
		return this.poDescription;
	}

	public void setPoDescription(String poDescription) {
		this.poDescription = poDescription;
	}

	@Column(name = "creator", length = 64)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "createTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}