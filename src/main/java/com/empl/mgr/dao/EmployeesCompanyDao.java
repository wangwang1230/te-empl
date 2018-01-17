package com.empl.mgr.dao;

import org.springframework.stereotype.Repository;

import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.model.TeEmployeesCompany;

/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
@Repository
public class EmployeesCompanyDao extends AbstractDao<TeEmployeesCompany> {

	@Override
	public Class<TeEmployeesCompany> getEntityClass() {
		// TODO Auto-generated method stub
		return TeEmployeesCompany.class;
	}

}
