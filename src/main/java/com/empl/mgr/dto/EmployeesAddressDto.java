package com.empl.mgr.dto;

import java.io.Serializable;

/**
 * 员工地址信息传输类
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class EmployeesAddressDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long province;
	private long city;
	private long county;
	private long township;
	private long village;
	private String detailed;

	public EmployeesAddressDto() {
		// TODO Auto-generated constructor stub
	}

	public EmployeesAddressDto(long province, long city, long county, long township, long village, String detailed) {
		super();
		this.province = province;
		this.city = city;
		this.county = county;
		this.township = township;
		this.village = village;
		this.detailed = detailed;
	}

	@Override
	public String toString() {
		return "EmployeesAddressDto [province:" + province + ", city:" + city + ", county:" + county + ", township:"
				+ township + ", village:" + village + ", detailed:" + detailed + "]";
	}

	public long getProvince() {
		return province;
	}

	public void setProvince(long province) {
		this.province = province;
	}

	public long getCity() {
		return city;
	}

	public void setCity(long city) {
		this.city = city;
	}

	public long getCounty() {
		return county;
	}

	public void setCounty(long county) {
		this.county = county;
	}

	public long getTownship() {
		return township;
	}

	public void setTownship(long township) {
		this.township = township;
	}

	public long getVillage() {
		return village;
	}

	public void setVillage(long village) {
		this.village = village;
	}

	public String getDetailed() {
		return detailed;
	}

	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}

}
