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
 * TeEmployeesLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "te_employees_log")
public class TeEmployeesLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private Date timestamp;
	private long emplId;
	private Integer type;
	private String note;
	private Date createTime;
	private String creator;

	// Constructors

	/** default constructor */
	public TeEmployeesLog() {
	}

	/** full constructor */
	public TeEmployeesLog(long emplId, Integer type, String note, Date createTime, String creator) {
		this.emplId = emplId;
		this.type = type;
		this.note = note;
		this.createTime = createTime;
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "TeEmployeesLog [id=" + id + ", timestamp=" + timestamp + ", emplId=" + emplId + ", type=" + type
				+ ", note=" + note + ", createTime=" + createTime + ", creator=" + creator + "]";
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Version
	@Column(name = "timestamp", nullable = false, length = 19)
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "emplId")
	public long getEmplId() {
		return this.emplId;
	}

	public void setEmplId(long emplId) {
		this.emplId = emplId;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "note", length = 1024)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "createTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "creator", length = 128)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

}