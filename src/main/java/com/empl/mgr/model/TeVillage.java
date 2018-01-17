package com.empl.mgr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TeVillage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "te_village")
public class TeVillage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long villageId;
	private String villageName;
	private String villageCode;
	private long townshipId;

	// Constructors

	/** default constructor */
	public TeVillage() {
	}

	/** full constructor */
	public TeVillage(long villageId, String villageName, String villageCode, long townshipId) {
		this.villageId = villageId;
		this.villageName = villageName;
		this.villageCode = villageCode;
		this.townshipId = townshipId;
	}

	@Override
	public String toString() {
		return "TeVillage [villageId:" + villageId + ", villageName:" + villageName + ", villageCode:" + villageCode
				+ ", townshipId:" + townshipId + "]";
	}

	// Property accessors
	@Id
	@Column(name = "villageId", unique = true, nullable = false)
	public long getVillageId() {
		return this.villageId;
	}

	public void setVillageId(long villageId) {
		this.villageId = villageId;
	}

	@Column(name = "villageName", nullable = false)
	public String getVillageName() {
		return this.villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	@Column(name = "villageCode", nullable = false, length = 3)
	public String getVillageCode() {
		return this.villageCode;
	}

	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}

	@Column(name = "townshipId", nullable = false)
	public long getTownshipId() {
		return this.townshipId;
	}

	public void setTownshipId(long townshipId) {
		this.townshipId = townshipId;
	}

}