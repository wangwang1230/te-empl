package com.empl.mgr.service;

public abstract interface EmployeesLogService {

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param emplId
	 * @param acctName
	 * @param type
	 * @param note
	 */
	public abstract void save(long emplId, String acctName, int type, String note);

}
