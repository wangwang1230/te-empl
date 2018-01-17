package com.empl.mgr.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.dto.AddressDto;
import com.empl.mgr.model.TeTownship;

/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
@Repository
public class TownshipDao extends AbstractDao<TeTownship> {

	@Override
	public Class<TeTownship> getEntityClass() {
		// TODO Auto-generated method stub
		return TeTownship.class;
	}

	@SuppressWarnings("unchecked")
	public List<AddressDto> findTownshipByCountyId(long countyId) {
		// TODO Auto-generated method stub
		String query = "select new com.empl.mgr.dto.AddressDto(townshipId as id, townshipName as name) from TeTownship where countyId = ?";
		return findSession().createQuery(query).setLong(0, countyId).list();
	}

}
