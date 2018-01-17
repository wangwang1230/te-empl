package com.empl.mgr.service;

import com.empl.mgr.support.JSONReturn;

public abstract interface DepartmentService {

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param page
	 * @param searchValue
	 * @param acctName
	 * @return
	 */
	public abstract JSONReturn findDepartmentList(int page, String searchValue, String acctName);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param page
	 * @param searchValue
	 * @return
	 */
	public abstract JSONReturn findDepartmentCount(int page, String searchValue);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param deptId
	 * @return
	 */
	public abstract JSONReturn findDepartmentById(long deptId);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param deptId
	 * @param name
	 * @param desc
	 * @param acctName
	 * @return
	 */
	public abstract JSONReturn modifyDepartment(long deptId, String name, String desc, String acctName);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param name
	 * @param desc
	 * @param acctName
	 * @return
	 */
	public abstract JSONReturn addDepartment(String name, String desc, String acctName);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param deptId
	 * @param acctName
	 * @return
	 */
	public abstract JSONReturn deleteDepartment(long deptId, String acctName);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @return
	 */
	public abstract JSONReturn findAllDepartment();

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param deptId
	 * @param acctName
	 * @return
	 */
	public abstract JSONReturn findDeptEmplList(long deptId, String acctName);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param deptId
	 * @param emplId
	 * @param acctName
	 * @return
	 */
	public abstract JSONReturn setPrincipal(long deptId, long emplId, String acctName);

}
