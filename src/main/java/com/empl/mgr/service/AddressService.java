package com.empl.mgr.service;

import com.empl.mgr.support.JSONReturn;

public abstract interface AddressService {

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @return
	 */
	public abstract JSONReturn findProvinceAll();

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param provinceId
	 * @return
	 */
	public abstract JSONReturn findCityByProvinceId(long provinceId);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param cityId
	 * @return
	 */
	public abstract JSONReturn findCountyByCityId(long cityId);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param countyId
	 * @return
	 */
	public abstract JSONReturn findTownshipByCountyId(long countyId);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param towhshipId
	 * @return
	 */
	public abstract JSONReturn findVillageByTownshipId(long towhshipId);

}
