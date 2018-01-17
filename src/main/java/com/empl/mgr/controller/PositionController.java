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
import com.empl.mgr.service.DepartmentService;
import com.empl.mgr.service.PositionService;
import com.empl.mgr.support.JSONReturn;

@Scope
@Controller
@RequestMapping(value = "position")
public class PositionController extends AbstractController {

	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private PositionService positionService;

	@ResponseBody
	@RequestMapping(value = "findAllDepartment")
	@SecureValid(code = "03002", desc = "获取部门下拉框", type = MethodType.FIND)
	public JSONReturn findAllDepartment() {
		return departmentService.findAllDepartment();
	}

	@ResponseBody
	@RequestMapping(value = "findPositionListInfo")
	@SecureValid(code = "03002", desc = "获取职位列表", type = MethodType.FIND)
	public JSONReturn findPositionListInfo(@RequestParam int page, @RequestParam long deptId,
			@RequestParam String searchValue, HttpSession httpSession) {
		return positionService.findPositionListInfo(page, deptId, searchValue, acctName(httpSession));
	}

	@ResponseBody
	@RequestMapping(value = "findPositionPage")
	@SecureValid(code = "03002", desc = "获取职位分页", type = MethodType.FIND)
	public JSONReturn findPositionPage(@RequestParam int page, @RequestParam long deptId,
			@RequestParam String searchValue, HttpSession httpSession) {
		return positionService.findPositionPage(page, deptId, searchValue, acctName(httpSession));
	}

	@ResponseBody
	@RequestMapping(value = "addPosition")
	@SecureValid(code = "03002", desc = "添加职位", type = MethodType.ADD)
	public JSONReturn addPosition(@RequestParam long deptId, @RequestParam String name, @RequestParam String desc,
			HttpSession httpSession) {
		return positionService.addPosition(deptId, name, desc, acctName(httpSession));
	}

	@ResponseBody
	@RequestMapping(value = "deletePosition")
	@SecureValid(code = "03002", desc = "删除职位", type = MethodType.DELETE)
	public JSONReturn deletePosition(@RequestParam long id, HttpSession httpSession) {
		return positionService.deletePosition(id, acctName(httpSession));
	}

	@ResponseBody
	@RequestMapping(value = "findPositionById")
	@SecureValid(code = "03002", desc = "根据ID获取职位信息", type = MethodType.FIND)
	public JSONReturn findPositionById(@RequestParam long id, HttpSession httpSession) {
		return positionService.findPositionById(id);
	}

	@ResponseBody
	@RequestMapping(value = "modifyPosition")
	@SecureValid(code = "03002", desc = "修改职位", type = MethodType.MODIFY)
	public JSONReturn modifyPosition(@RequestParam long id, @RequestParam long deptId, @RequestParam String name,
			@RequestParam String desc, HttpSession httpSession) {
		return positionService.modifyPosition(id, deptId, name, desc, acctName(httpSession));
	}

}
