package com.empl.mgr.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.dto.AddressDto;
import com.empl.mgr.model.TeCity;

/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
@Repository
public class CityDao extends AbstractDao<TeCity> {

	@Override
	public Class<TeCity> getEntityClass() {
		// TODO Auto-generated method stub
		return TeCity.class;
	}

	@SuppressWarnings("unchecked")
	public List<AddressDto> findCityByProvinceId(long provinceId) {
		String query = "select new com.empl.mgr.dto.AddressDto(cityId as id, cityName as name) from TeCity where provinceId = ?";
		return findSession().createQuery(query).setLong(0, provinceId).list();
	}

}
