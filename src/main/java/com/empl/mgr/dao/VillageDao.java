package com.empl.mgr.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.dto.AddressDto;
import com.empl.mgr.model.TeVillage;

/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
@Repository
public class VillageDao extends AbstractDao<TeVillage> {

	@Override
	public Class<TeVillage> getEntityClass() {
		// TODO Auto-generated method stub
		return TeVillage.class;
	}

	@SuppressWarnings("unchecked")
	public List<AddressDto> findVillageByTownshipId(long towhshipId) {
		// TODO Auto-generated method stub
		String query = "select new com.empl.mgr.dto.AddressDto(villageId as id, villageName as name) from TeVillage where townshipId = ?";
		return findSession().createQuery(query).setLong(0, towhshipId).list();
	}

}
