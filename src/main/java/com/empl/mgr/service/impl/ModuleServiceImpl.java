package com.empl.mgr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empl.mgr.constant.LoginState;
import com.empl.mgr.constant.MethodType;
import com.empl.mgr.dao.AccountDao;
import com.empl.mgr.dao.ModuleDao;
import com.empl.mgr.dao.RoleDao;
import com.empl.mgr.dao.RoleModuleDao;
import com.empl.mgr.dto.ModuleDto;
import com.empl.mgr.field.TeAccountField;
import com.empl.mgr.field.TeModuleField;
import com.empl.mgr.field.TeRoleField;
import com.empl.mgr.model.TeAccount;
import com.empl.mgr.model.TeModule;
import com.empl.mgr.model.TeRole;
import com.empl.mgr.model.TeRoleModule;
import com.empl.mgr.service.ModuleService;
import com.empl.mgr.support.JSONReturn;
import com.empl.mgr.utils.CompareUtil;

@Scope
@Service
@Transactional(readOnly = true)
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleDao moduleDAO;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private RoleModuleDao roleModuleDao;
	@Autowired
	private RoleDao roleDao;

	public static final int TYPE_FIND = 1;
	public static final int TYPE_DELETE = 2;
	public static final int TYPE_MDOIFY = 3;
	public static final int TYPE_ADD = 4;

	public JSONReturn findMenu(String acctName) {
		// TODO Auto-generated method stub
		TeAccount teAccount = accountDao.findUniqueByProperty(TeAccountField.ACCT_NAME, acctName);
		if (CompareUtil.isEmpty(teAccount))
			return JSONReturn.buildFailure(LoginState.UNLOGIN);
		List<TeModule> moduleList = null;
		if (teAccount.getAcctSuper())
			moduleList = moduleDAO.findAll();
		else
			moduleList = moduleDAO.findMgrModule(acctName);
		if (CollectionUtils.isEmpty(moduleList))
			return JSONReturn.buildFailure("获取菜单失败!");
		return JSONReturn.buildSuccess(moduleList);
	}

	public JSONReturn findModuleParameter(String moduleCode, String acctName) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		TeAccount teAccount = accountDao.findUniqueByProperty(TeAccountField.ACCT_NAME, acctName);
		if (CompareUtil.isEmpty(teAccount))
			return JSONReturn.buildFailure(map);
		map.put("acctount", teAccount.getAcctNickname());
		if ("0".equals(moduleCode)) {
			map.put("find", true);
			map.put("add", teAccount.getAcctSuper() ? true : false);
			map.put("del", teAccount.getAcctSuper() ? true : false);
			map.put("modify", teAccount.getAcctSuper() ? true : false);
			return JSONReturn.buildSuccess(map);
		}
		TeModule teModule = moduleDAO.findUniqueByProperty(TeModuleField.MODULE_CODE, moduleCode);
		if (CompareUtil.isEmpty(teModule))
			return JSONReturn.buildFailure("非法操作!");
		map.put("moduleName", teModule.getModuleName());
		// 获取当前模块名与上级模块名, 前台做为面包绡显示
		map = findModuleName(map, moduleCode, teModule);
		boolean add = false, del = false, modify = false, find = false;
		List<TeRoleModule> secureValidList = roleModuleDao.findMySecureValid(findModuleArray(moduleCode), acctName);
		if (!teAccount.getAcctSuper() && CollectionUtils.isNotEmpty(secureValidList)) {
			for (TeRoleModule rm : secureValidList) {
				if (rm.getAdds())
					add = rm.getAdds();
				if (rm.getDeletes())
					del = rm.getDeletes();
				if (rm.getModifys())
					modify = rm.getModifys();
				if (rm.getFinds())
					find = rm.getFinds();
			}
		}
		map.put("add", teAccount.getAcctSuper() ? true : add);
		map.put("del", teAccount.getAcctSuper() ? true : del);
		map.put("modify", teAccount.getAcctSuper() ? true : modify);
		map.put("find", teAccount.getAcctSuper() ? true : find);
		return JSONReturn.buildSuccess(map);
	}

	/*
	 * 获取当前模块名称以及上级模块名称
	 */
	public Map<String, Object> findModuleName(Map<String, Object> map, String moduleCode, TeModule teModule) {
		teModule = moduleDAO.findUniqueByProperty(TeModuleField.MODULE_CODE, teModule.getModuleSuperCode());
		if (CompareUtil.isNotEmpty(teModule)) {
			map.put("superModuleName", teModule.getModuleName());
			map.put("code", teModule.getModuleCode());
		}
		return map;
	}

	public JSONReturn findBreadcrumb(String moduleCode) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		TeModule teModule = moduleDAO.findUniqueByProperty(TeModuleField.MODULE_CODE, moduleCode);
		if (CompareUtil.isEmpty(teModule))
			return JSONReturn.buildFailure("ERROR");
		TeModule superModule = moduleDAO.findUniqueByProperty(TeModuleField.MODULE_CODE, teModule.getModuleSuperCode());
		if (CompareUtil.isEmpty(superModule))
			return JSONReturn.buildFailure("ERROR");
		map.put("superName", superModule.getModuleName());
		map.put("name", teModule.getModuleName());
		map.put("code", teModule.getModuleSuperCode());
		return JSONReturn.buildSuccess(map);
	}

	public JSONReturn findAllModule(long roleId) {
		// TODO Auto-generated method stub
		TeRole teRole = roleDao.findUniqueByProperty(TeRoleField.ROLE_ID, roleId);
		if (CompareUtil.isEmpty(teRole))
			return JSONReturn.buildFailure("未获取到模块信息!");
		List<TeModule> moduleList = moduleDAO.findAll();
		if (CollectionUtils.isEmpty(moduleList))
			return JSONReturn.buildFailure("未获取到相关数据!");
		List<ModuleDto> infoList = new ArrayList<ModuleDto>();
		TeRoleModule tm = null;
		for (TeModule mo : moduleList) {
			ModuleDto dto = new ModuleDto();
			dto.setId(mo.getModuleId());
			dto.setName(mo.getModuleName());
			dto.setCode(mo.getModuleCode());
			dto.setSuperCode(mo.getModuleSuperCode());
			dto.setUrl(mo.getModulePage());
			dto.setLevel(mo.getModuleLevel());
			tm = roleModuleDao.findByRoleLabelByModuleCode(teRole.getRoleLabel(), mo.getModuleCode());
			if (CompareUtil.isNotEmpty(tm)) {
				dto.setAdd(tm.getAdds());
				dto.setDel(tm.getDeletes());
				dto.setModify(tm.getModifys());
				dto.setFind(tm.getFinds());
			}
			infoList.add(dto);
		}
		return JSONReturn.buildSuccess(infoList);
	}

	@Transactional
	public JSONReturn setRoleSecureValid(long rold, String code, int type, boolean add) {
		// TODO Auto-generated method stub
		TeModule mo = moduleDAO.findUniqueByProperty(TeModuleField.MODULE_CODE, code);
		if (CompareUtil.isEmpty(mo))
			return JSONReturn.buildFailure("操作失败, 模块不存在!");
		TeRole role = roleDao.findUniqueByProperty(TeRoleField.ROLE_ID, rold);
		if (CompareUtil.isEmpty(role))
			return JSONReturn.buildFailure("操作失败, 角色不存在!");
		TeRoleModule rm = roleModuleDao.findByRoleLabelByModuleCode(role.getRoleLabel(), mo.getModuleCode());
		if (CompareUtil.isEmpty(rm)) {
			rm = new TeRoleModule(role.getRoleLabel(), code, mo.getModuleSuperCode(), false, false, false, false);
			roleModuleDao.save(rm);
		}
		rm = setRoleSecureValid(rm, type, add);
		return JSONReturn.buildSuccessWithEmptyBody();
	}

	public TeRoleModule setRoleSecureValid(TeRoleModule rm, int type, boolean add) {
		if (type == TYPE_FIND)
			rm.setFinds(add);
		else if (type == TYPE_DELETE)
			rm.setDeletes(add);
		else if (type == TYPE_ADD)
			rm.setAdds(add);
		else
			rm.setModifys(add);
		return rm;
	}

	public boolean secureValid(String userName, String[] code, MethodType type) {
		// TODO Auto-generated method stub
		List<TeRoleModule> secureValidList = roleModuleDao.findMySecureValid(code, userName);
		if (CollectionUtils.isEmpty(secureValidList))
			return false;
		for (TeRoleModule te : secureValidList) {
			if (type == MethodType.FIND && te.getFinds())
				return true;
			else if (type == MethodType.ADD && te.getAdds())
				return true;
			else if (type == MethodType.MODIFY && te.getModifys())
				return true;
			else if (type == MethodType.DELETE && te.getDeletes())
				return true;
		}
		return false;
	}

	public String[] findModuleArray(String moduleCode) {
		return new String[] { moduleCode };
	}

}
