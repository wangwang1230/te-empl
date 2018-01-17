package com.empl.mgr.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.dto.AddressDto;
import com.empl.mgr.model.TeProvince;

/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
@Repository
public class ProvinceDao extends AbstractDao<TeProvince> {

	@Override
	public Class<TeProvince> getEntityClass() {
		// TODO Auto-generated method stub
		return TeProvince.class;
	}

	@SuppressWarnings("unchecked")
	public List<AddressDto> findAllProvince() {
		// TODO Auto-generated method stub
		String query = "select new com.empl.mgr.dto.AddressDto(provinceId as id, provinceName as name) from TeProvince";
		return findSession().createQuery(query).list();
	}

}
