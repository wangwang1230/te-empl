package com.empl.mgr.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.dto.AddressDto;
import com.empl.mgr.model.TeCounty;

/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
@Repository
public class CountyDao extends AbstractDao<TeCounty> {

	@Override
	public Class<TeCounty> getEntityClass() {
		// TODO Auto-generated method stub
		return TeCounty.class;
	}

	@SuppressWarnings("unchecked")
	public List<AddressDto> findCountyByCityId(long cityId) {
		// TODO Auto-generated method stub
		String query = "select new com.empl.mgr.dto.AddressDto(countyId as id, countyName as name) from TeCounty where cityId = ?";
		return findSession().createQuery(query).setLong(0, cityId).list();
	}

}
