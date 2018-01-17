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
 * TeTrainingNote entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "te_training_note")
public class TeTrainingNote implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private Date timestamp;
	private long trainingId;
	private Integer state;
	private String note;
	private Date createTime;
	private String creator;

	// Constructors

	/** default constructor */
	public TeTrainingNote() {
	}

	/** full constructor */
	public TeTrainingNote(long trainingId, Integer state, String note, Date createTime, String creator) {
		this.trainingId = trainingId;
		this.state = state;
		this.note = note;
		this.createTime = createTime;
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "TeTrainingNote [id:" + id + ", timestamp:" + timestamp + ", trainingId:" + trainingId + ", state:"
				+ state + ", note:" + note + ", createTime:" + createTime + ", creator:" + creator + "]";
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

	@Column(name = "trainingId")
	public long getTrainingId() {
		return this.trainingId;
	}

	public void setTrainingId(long trainingId) {
		this.trainingId = trainingId;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "note", length = 2048)
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