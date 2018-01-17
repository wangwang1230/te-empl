package com.empl.mgr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.empl.mgr.dao.EmployeesLogDao;
import com.empl.mgr.model.TeEmployeesLog;
import com.empl.mgr.service.EmployeesLogService;
import com.empl.mgr.utils.DateTimeUtil;

@Scope
@Service
public class EmployeesLogServiceImpl implements EmployeesLogService {

	@Autowired
	private EmployeesLogDao employeesLogDao;

	public void save(long emplId, String acctName, int type, String note) {
		// TODO Auto-generated method stub
		employeesLogDao.save(new TeEmployeesLog(emplId, type, note, DateTimeUtil.getCurrentTime(), acctName));
	}
}
