package com.empl.mgr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TeTownship entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "te_township")
public class TeTownship implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long townshipId;
	private String townshipName;
	private String townshipCode;
	private long countyId;

	// Constructors

	/** default constructor */
	public TeTownship() {
	}

	/** full constructor */
	public TeTownship(long townshipId, String townshipName, String townshipCode, long countyId) {
		this.townshipId = townshipId;
		this.townshipName = townshipName;
		this.townshipCode = townshipCode;
		this.countyId = countyId;
	}

	@Override
	public String toString() {
		return "TeTownship [townshipId:" + townshipId + ", townshipName:" + townshipName + ", townshipCode:"
				+ townshipCode + ", countyId:" + countyId + "]";
	}

	// Property accessors
	@Id
	@Column(name = "townshipId", unique = true, nullable = false)
	public long getTownshipId() {
		return this.townshipId;
	}

	public void setTownshipId(long townshipId) {
		this.townshipId = townshipId;
	}

	@Column(name = "townshipName", nullable = false)
	public String getTownshipName() {
		return this.townshipName;
	}

	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}

	@Column(name = "townshipCode", nullable = false, length = 3)
	public String getTownshipCode() {
		return this.townshipCode;
	}

	public void setTownshipCode(String townshipCode) {
		this.townshipCode = townshipCode;
	}

	@Column(name = "countyId", nullable = false)
	public long getCountyId() {
		return this.countyId;
	}

	public void setCountyId(long countyId) {
		this.countyId = countyId;
	}

}