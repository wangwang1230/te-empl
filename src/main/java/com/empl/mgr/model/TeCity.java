package com.empl.mgr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TeCity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "te_city")
public class TeCity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long cityId;
	private String cityName;
	private String cityCode;
	private long provinceId;

	// Constructors

	/** default constructor */
	public TeCity() {
	}

	/** full constructor */
	public TeCity(long cityId, String cityName, String cityCode, long provinceId) {
		this.cityId = cityId;
		this.cityName = cityName;
		this.cityCode = cityCode;
		this.provinceId = provinceId;
	}

	@Override
	public String toString() {
		return "TeCity [cityId:" + cityId + ", cityName:" + cityName + ", cityCode:" + cityCode + ", provinceId:"
				+ provinceId + "]";
	}

	// Property accessors
	@Id
	@Column(name = "cityId", unique = true, nullable = false)
	public long getCityId() {
		return this.cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	@Column(name = "cityName", nullable = false)
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "cityCode", nullable = false, length = 3)
	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	@Column(name = "provinceId", nullable = false)
	public long getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(long provinceId) {
		this.provinceId = provinceId;
	}

}