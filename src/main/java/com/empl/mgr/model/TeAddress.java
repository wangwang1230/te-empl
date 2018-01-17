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
 * TeAddress entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "te_address")
public class TeAddress implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long adsId;
	private Date timestamp;
	private Integer adsType;
	private long adsProvince;
	private long adsCity;
	private long adsCounty;
	private long adsTownship;
	private long adsVillage;
	private String adsDetailed;

	// Constructors

	/** default constructor */
	public TeAddress() {
	}

	/** full constructor */
	public TeAddress(Integer adsType, long adsProvince, long adsCity, long adsCounty, long adsTownship,
			long adsVillage, String adsDetailed) {
		this.adsType = adsType;
		this.adsProvince = adsProvince;
		this.adsCity = adsCity;
		this.adsCounty = adsCounty;
		this.adsTownship = adsTownship;
		this.adsVillage = adsVillage;
		this.adsDetailed = adsDetailed;
	}

	@Override
	public String toString() {
		return "TeAddress [adsId:" + adsId + ", timestamp:" + timestamp + ", adsType:" + adsType + ", adsProvince:"
				+ adsProvince + ", adsCity:" + adsCity + ", adsCounty:" + adsCounty + ", adsTownship:" + adsTownship
				+ ", adsVillage:" + adsVillage + ", adsDetailed:" + adsDetailed + "]";
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "adsId", unique = true, nullable = false)
	public long getAdsId() {
		return this.adsId;
	}

	public void setAdsId(long adsId) {
		this.adsId = adsId;
	}

	@Version
	@Column(name = "timestamp", nullable = false, length = 19)
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "adsType")
	public Integer getAdsType() {
		return this.adsType;
	}

	public void setAdsType(Integer adsType) {
		this.adsType = adsType;
	}

	@Column(name = "adsProvince")
	public long getAdsProvince() {
		return this.adsProvince;
	}

	public void setAdsProvince(long adsProvince) {
		this.adsProvince = adsProvince;
	}

	@Column(name = "adsCity")
	public long getAdsCity() {
		return this.adsCity;
	}

	public void setAdsCity(long adsCity) {
		this.adsCity = adsCity;
	}

	@Column(name = "adsCounty")
	public long getAdsCounty() {
		return this.adsCounty;
	}

	public void setAdsCounty(long adsCounty) {
		this.adsCounty = adsCounty;
	}

	@Column(name = "adsTownship")
	public long getAdsTownship() {
		return this.adsTownship;
	}

	public void setAdsTownship(long adsTownship) {
		this.adsTownship = adsTownship;
	}

	@Column(name = "adsVillage")
	public long getAdsVillage() {
		return this.adsVillage;
	}

	public void setAdsVillage(long adsVillage) {
		this.adsVillage = adsVillage;
	}

	@Column(name = "adsDetailed", length = 512)
	public String getAdsDetailed() {
		return this.adsDetailed;
	}

	public void setAdsDetailed(String adsDetailed) {
		this.adsDetailed = adsDetailed;
	}

}