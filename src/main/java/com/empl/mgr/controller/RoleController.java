package com.empl.mgr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empl.mgr.annotation.SecureValid;
import com.empl.mgr.constant.MethodType;
import com.empl.mgr.controller.support.AbstractController;
import com.empl.mgr.service.ModuleService;
import com.empl.mgr.service.RoleService;
import com.empl.mgr.support.JSONReturn;

@Scope
@Controller
@RequestMapping(value = "role")
public class RoleController extends AbstractController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private ModuleService moduleService;

	@ResponseBody
	@RequestMapping(value = "findRoleList")
	@SecureValid(code = "04002", desc = "获取角色列表", type = MethodType.FIND)
	public JSONReturn findRoleList(@RequestParam int page, @RequestParam String searchVal, HttpSession httpSession) {
		return roleService.findRoleList(page, searchVal, acctName(httpSession));
	}

	@ResponseBody
	@RequestMapping(value = "findRolePage")
	@SecureValid(code = "04002", desc = "获取角色列表分页", type = MethodType.FIND)
	public JSONReturn findRolePage(@RequestParam int page, @RequestParam String searchVal, HttpSession httpSession) {
		return roleService.findRolePage(page, searchVal);
	}

	@ResponseBody
	@RequestMapping(value = "addRole")
	@SecureValid(code = "04002", desc = "新增角色", type = MethodType.ADD)
	public JSONReturn addRole(@RequestParam String name, @RequestParam String desc, HttpSession httpSession) {
		return roleService.addRole(name, desc, acctName(httpSession));
	}

	@ResponseBody
	@RequestMapping(value = "modifyRole")
	@SecureValid(code = "04002", desc = "修改角色信息", type = MethodType.MODIFY)
	public JSONReturn modifyRole(@RequestParam long id, @RequestParam String name, @RequestParam String desc,
			HttpSession httpSession) {
		return roleService.modifyRole(id, name, desc, acctName(httpSession));
	}

	@ResponseBody
	@RequestMapping(value = "deleteRole")
	@SecureValid(code = "04002", desc = "删除角色", type = MethodType.DELETE)
	public JSONReturn deleteRole(@RequestParam long id, HttpSession httpSession) {
		return roleService.deleteRole(id, acctName(httpSession));
	}

	@ResponseBody
	@RequestMapping(value = "findRoleById")
	@SecureValid(code = "04002", desc = "根据ID号获取角色信息", type = MethodType.FIND)
	public JSONReturn findRoleById(@RequestParam long id) {
		return roleService.findRoleById(id);
	}

	@ResponseBody
	@RequestMapping(value = "findAllModule")
	@SecureValid(code = "04002", desc = "获取所有模块", type = MethodType.FIND)
	public JSONReturn findAllModule(@RequestParam long roleId) {
		return moduleService.findAllModule(roleId);
	}

	@ResponseBody
	@RequestMapping(value = "setRoleSecureValid")
	@SecureValid(code = "04002", desc = "为角色设置模块信息", type = MethodType.MODIFY)
	public JSONReturn setRoleSecureValid(@RequestParam long rold, @RequestParam String code, @RequestParam int type,
			@RequestParam boolean add) {
		return moduleService.setRoleSecureValid(rold, code, type, add);
	}

}
