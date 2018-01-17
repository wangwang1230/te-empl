package com.empl.mgr.service;

import com.empl.mgr.constant.MethodType;
import com.empl.mgr.support.JSONReturn;

public abstract interface ModuleService {

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param acctName
	 * @return
	 */
	public abstract JSONReturn findMenu(String acctName);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param moduleCode
	 * @param acctName
	 * @return
	 */
	public abstract JSONReturn findModuleParameter(String moduleCode, String acctName);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param moduleCode
	 * @return
	 */
	public abstract JSONReturn findBreadcrumb(String moduleCode);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param roleId
	 * @return
	 */
	public abstract JSONReturn findAllModule(long roleId);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param rold
	 * @param code
	 * @param type
	 * @param add
	 * @return
	 */
	public abstract JSONReturn setRoleSecureValid(long rold, String code, int type, boolean add);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param userName
	 * @param code
	 * @param type
	 * @return
	 */
	public abstract boolean secureValid(String userName, String[] code, MethodType type);

}
