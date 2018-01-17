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
 * TeChooseMarriage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "te_choose_marriage")
public class TeChooseMarriage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long marId;
	private Date timestamp;
	private String marName;

	// Constructors

	/** default constructor */
	public TeChooseMarriage() {
	}

	@Override
	public String toString() {
		return "TeChooseMarriage [marId:" + marId + ", timestamp:" + timestamp + ", marName:" + marName + "]";
	}

	/** full constructor */
	public TeChooseMarriage(String marName) {
		this.marName = marName;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "marId", unique = true, nullable = false)
	public long getMarId() {
		return this.marId;
	}

	public void setMarId(long marId) {
		this.marId = marId;
	}

	@Version
	@Column(name = "timestamp", nullable = false, length = 19)
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "marName", length = 64)
	public String getMarName() {
		return this.marName;
	}

	public void setMarName(String marName) {
		this.marName = marName;
	}

}