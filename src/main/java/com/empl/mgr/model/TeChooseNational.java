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
 * TeChooseNational entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "te_choose_national")
public class TeChooseNational implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long natId;
	private Date timestamp;
	private String natName;

	// Constructors

	/** default constructor */
	public TeChooseNational() {
	}

	@Override
	public String toString() {
		return "TeChooseNational [natId:" + natId + ", timestamp:" + timestamp + ", natName:" + natName + "]";
	}

	/** full constructor */
	public TeChooseNational(String natName) {
		this.natName = natName;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "natId", unique = true, nullable = false)
	public long getNatId() {
		return this.natId;
	}

	public void setNatId(long natId) {
		this.natId = natId;
	}

	@Version
	@Column(name = "timestamp", nullable = false, length = 19)
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "natName", length = 64)
	public String getNatName() {
		return this.natName;
	}

	public void setNatName(String natName) {
		this.natName = natName;
	}

}