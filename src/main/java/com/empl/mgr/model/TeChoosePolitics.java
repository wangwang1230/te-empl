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
 * TeChoosePolitics entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "te_choose_politics")
public class TeChoosePolitics implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long polId;
	private Date timestamp;
	private String polName;

	// Constructors

	/** default constructor */
	public TeChoosePolitics() {
	}

	@Override
	public String toString() {
		return "TeChoosePolitics [polId:" + polId + ", timestamp:" + timestamp + ", polName:" + polName + "]";
	}

	/** full constructor */
	public TeChoosePolitics(String polName) {
		this.polName = polName;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "polId", unique = true, nullable = false)
	public long getPolId() {
		return this.polId;
	}

	public void setPolId(long polId) {
		this.polId = polId;
	}

	@Version
	@Column(name = "timestamp", nullable = false, length = 19)
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "polName", length = 64)
	public String getPolName() {
		return this.polName;
	}

	public void setPolName(String polName) {
		this.polName = polName;
	}

}