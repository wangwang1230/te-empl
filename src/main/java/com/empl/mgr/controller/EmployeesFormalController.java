package com.empl.mgr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empl.mgr.annotation.SecureValid;
import com.empl.mgr.constant.EmployeesState;
import com.empl.mgr.constant.MethodType;
import com.empl.mgr.controller.support.AbstractController;
import com.empl.mgr.service.EmployeesService;
import com.empl.mgr.support.JSONReturn;

@Scope
@Controller
@RequestMapping(value = "employees/formal")
public class EmployeesFormalController extends AbstractController {

	@Autowired
	private EmployeesService employeesService;

	@ResponseBody
	@RequestMapping(value = "findEmployeesTrainingList")
	@SecureValid(code = "01002", desc = "获取正式员工列表信息", type = MethodType.FIND)
	public JSONReturn findEmployeesInternshipList(@RequestParam int serType, @RequestParam String serVal,
			@RequestParam long department, @RequestParam long position, @RequestParam int page, HttpSession httpSession) {
		return employeesService.findEmployeesList(serType, serVal, department, position, page, acctName(httpSession),
				EmployeesState.EMPL_FORMAL);
	}

	@ResponseBody
	@RequestMapping(value = "findEmployeesTrainingPage")
	@SecureValid(code = "01002", desc = "获取正式员工分页", type = MethodType.FIND)
	public JSONReturn findEmployeesInternshipPage(@RequestParam int serType, @RequestParam String serVal,
			@RequestParam long department, @RequestParam long position, @RequestParam int page, HttpSession httpSession) {
		return employeesService.findEmployeesPage(serType, serVal, department, position, page, acctName(httpSession),
				EmployeesState.EMPL_FORMAL);
	}

	@ResponseBody
	@RequestMapping(value = "departure")
	@SecureValid(code = "01002", desc = "员工离职", type = MethodType.MODIFY)
	public JSONReturn departure(@RequestParam long id, @RequestParam String note, HttpSession httpSession) {
		return employeesService.departure(id, note, acctName(httpSession));
	}

	@ResponseBody
	@RequestMapping(value = "findTrainingRecord")
	@SecureValid(code = "01002", desc = "查看某会员的培训记录", type = MethodType.FIND)
	public JSONReturn findCanTrainingList(@RequestParam long emplId, HttpSession httpSession) {
		return employeesService.findTrainingRecord(emplId, acctName(httpSession));
	}

}
