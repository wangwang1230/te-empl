package com.empl.mgr.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empl.mgr.constant.PageConstant;
import com.empl.mgr.constant.TimeFormatConstant;
import com.empl.mgr.dao.AccountRoleDao;
import com.empl.mgr.dao.RoleDao;
import com.empl.mgr.dto.RoleListDto;
import com.empl.mgr.field.TeAccountRoleField;
import com.empl.mgr.field.TeRoleField;
import com.empl.mgr.model.TeRole;
import com.empl.mgr.service.RoleService;
import com.empl.mgr.support.JSONReturn;
import com.empl.mgr.utils.CompareUtil;
import com.empl.mgr.utils.DateTimeUtil;
import com.empl.mgr.utils.PageUtils;
import com.empl.mgr.utils.SupportUtil;

@Scope
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private AccountRoleDao accountRoleDao;

	public JSONReturn findRoleList(int page, String searchVal, String acctName) {
		// TODO Auto-generated method stub
		List<RoleListDto> dtoList = roleDao.findRoleList(page, searchVal);
		if (CollectionUtils.isEmpty(dtoList))
			return JSONReturn.buildFailure("未获取到相关数据!");
		for (RoleListDto dto : dtoList)
			dto.setTime(DateTimeUtil.conversionTime(dto.getData(), TimeFormatConstant.YYYY_MM_DD));
		return JSONReturn.buildSuccess(dtoList);
	}

	public JSONReturn findRolePage(int page, String searchVal) {
		// TODO Auto-generated method stub
		return JSONReturn.buildSuccess(PageUtils.calculatePage(page, roleDao.findRoleCount(searchVal),
				PageConstant.PAGE_LIST));
	}

	@Transactional
	public JSONReturn addRole(String roleName, String desc, String acctName) {
		// TODO Auto-generated method stub
		if (StringUtils.isEmpty(roleName))
			return JSONReturn.buildFailure("添加失败, 名称不能为空!");
		TeRole role = roleDao.findUniqueByProperty(TeRoleField.ROLE_NAME, roleName);
		if (CompareUtil.isNotEmpty(role))
			return JSONReturn.buildFailure("添加失败, 名称重复!");
		role = new TeRole();
		role.setCreateTime(DateTimeUtil.getCurrentTime());
		role.setCreator(acctName);
		role.setRoleDescription(desc);
		role.setRoleName(roleName);
		role.setRoleLabel(SupportUtil.findUUID());
		roleDao.save(role);
		return JSONReturn.buildSuccess("添加成功!");
	}

	@Transactional
	public JSONReturn modifyRole(long id, String roleName, String desc, String acctName) {
		// TODO Auto-generated method stub
		if (StringUtils.isEmpty(roleName))
			return JSONReturn.buildFailure("添加失败, 名称不能为空!");
		TeRole role = roleDao.findUniqueByProperty(TeRoleField.ROLE_ID, id);
		if (CompareUtil.isEmpty(role))
			return JSONReturn.buildFailure("操作失败, 源数据不存在!");
		if (!roleName.equals(role.getRoleName())) {
			if (CompareUtil.isNotEmpty(roleDao.findUniqueByProperty(TeRoleField.ROLE_NAME, acctName)))
				return JSONReturn.buildSuccess("修改失败, 角色名重复!");
		}
		role.setRoleName(roleName);
		role.setRoleDescription(desc);
		return JSONReturn.buildSuccess("修改成功!");
	}

	@Transactional
	public JSONReturn deleteRole(long id, String acctName) {
		// TODO Auto-generated method stub
		TeRole role = roleDao.findUniqueByProperty(TeRoleField.ROLE_ID, id);
		if (CompareUtil.isEmpty(role))
			return JSONReturn.buildFailure("删除数据失败, 源数据不存在!");
		// 删除该角色下所有帐号
		accountRoleDao.deleteByPropertyString(TeAccountRoleField.ROLE_LABEL, role.getRoleLabel());
		roleDao.delete(role);
		return JSONReturn.buildSuccess("删除成功!");
	}

	public JSONReturn findRoleById(long id) {
		// TODO Auto-generated method stub
		TeRole role = roleDao.findUniqueByProperty(TeRoleField.ROLE_ID, id);
		if (CompareUtil.isEmpty(role))
			return JSONReturn.buildFailure("操作失败, 源数据不存在");
		RoleListDto dto = new RoleListDto();
		dto.setId(role.getRoleId());
		dto.setCreator(role.getCreator());
		dto.setData(role.getCreateTime());
		dto.setName(role.getRoleName());
		dto.setDescription(role.getRoleDescription());
		return JSONReturn.buildSuccess(dto);
	}
}
