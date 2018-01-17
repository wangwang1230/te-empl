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
 * TeStudy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "te_training")
public class TeTraining implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private Date timestamp;
	private String name;
	private String description;
	private Integer number;
	private String startTime;
	private String endTime;
	private boolean isInsertAttend;
	private Integer state;
	private Date createTime;
	private String creator;

	// Constructors

	/** default constructor */
	public TeTraining() {
	}

	/** full constructor */
	public TeTraining(String name, String description, Integer number, String startTime, String endTime,
			boolean isInsertAttend, Integer state, Date createTime, String creator) {
		this.name = name;
		this.description = description;
		this.number = number;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isInsertAttend = isInsertAttend;
		this.state = state;
		this.createTime = createTime;
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "TeStudy [id:" + id + ", timestamp:" + timestamp + ", name:" + name + ", description:" + description
				+ ", number:" + number + ", startTime:" + startTime + ", endTime:" + endTime + ", isInsertAttend:"
				+ isInsertAttend + ", state:" + state + ", createTime:" + createTime + ", creator:" + creator + "]";
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

	@Column(name = "name", length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 128)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "number")
	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Column(name = "startTime", length = 16)
	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "endTime", length = 16)
	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "isInsertAttend")
	public boolean getIsInsertAttend() {
		return this.isInsertAttend;
	}

	public void setIsInsertAttend(boolean isInsertAttend) {
		this.isInsertAttend = isInsertAttend;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
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