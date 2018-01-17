package com.empl.mgr.dao;

import org.springframework.stereotype.Repository;

import com.empl.mgr.constant.EmployeesLogType;
import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.model.TeEmployeesLog;

/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
@Repository
public class EmployeesLogDao extends AbstractDao<TeEmployeesLog> {

	@Override
	public Class<TeEmployeesLog> getEntityClass() {
		// TODO Auto-generated method stub
		return TeEmployeesLog.class;
	}

	public TeEmployeesLog findDepartureNote(long emplId) {
		// TODO Auto-generated method stub
		String query = "from TeEmployeesLog where emplId = ? and type = ? order by id desc";
		return (TeEmployeesLog) findSession().createQuery(query).setLong(0, emplId)
				.setInteger(1, EmployeesLogType.DEPARTURE).setMaxResults(1).uniqueResult();
	}

}
