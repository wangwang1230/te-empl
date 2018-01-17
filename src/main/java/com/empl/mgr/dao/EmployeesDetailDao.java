package com.empl.mgr.dao;

import org.springframework.stereotype.Repository;

import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.model.TeEmployeesDetail;

/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
@Repository
public class EmployeesDetailDao extends AbstractDao<TeEmployeesDetail> {

	@Override
	public Class<TeEmployeesDetail> getEntityClass() {
		// TODO Auto-generated method stub
		return TeEmployeesDetail.class;
	}

}
