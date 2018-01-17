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
@RequestMapping(value = "employees/departure")
public class EmployeesDepartureController extends AbstractController {

	@Autowired
	private EmployeesService employeesService;

	@ResponseBody
	@RequestMapping(value = "findEmployeesInternshipList")
	@SecureValid(code = "01004", desc = "获取实习员工列表信息", type = MethodType.FIND)
	public JSONReturn findEmployeesInternshipList(@RequestParam int serType, @RequestParam String serVal,
			@RequestParam long department, @RequestParam long position, @RequestParam int page, HttpSession httpSession) {
		return employeesService.findEmployeesList(serType, serVal, department, position, page, acctName(httpSession),
				EmployeesState.EMPL_DEPARTURE, EmployeesState.EMPL_ELIMINATE);
	}

	@ResponseBody
	@RequestMapping(value = "findEmployeesInternshipPage")
	@SecureValid(code = "01004", desc = "获取实习员工分页", type = MethodType.FIND)
	public JSONReturn findEmployeesInternshipPage(@RequestParam int serType, @RequestParam String serVal,
			@RequestParam long department, @RequestParam long position, @RequestParam int page, HttpSession httpSession) {
		return employeesService.findEmployeesPage(serType, serVal, department, position, page, acctName(httpSession),
				EmployeesState.EMPL_DEPARTURE, EmployeesState.EMPL_ELIMINATE);
	}

	@ResponseBody
	@RequestMapping(value = "destroy")
	@SecureValid(code = "01004", desc = "销毁员工数据", type = MethodType.DELETE)
	public JSONReturn destroy(@RequestParam long emplId, HttpSession httpSession) {
		return employeesService.destroy(emplId, acctName(httpSession));
	}

	@ResponseBody
	@RequestMapping(value = "findDepartureNote")
	@SecureValid(code = "01004", desc = "获取员工离职备注", type = MethodType.FIND)
	public JSONReturn findDepartureNote(@RequestParam long emplId, HttpSession httpSession) {
		return employeesService.findDepartureNote(emplId, acctName(httpSession));
	}

	@ResponseBody
	@RequestMapping(value = "enrollEmployees")
	@SecureValid(code = "01004", desc = "重新录取员工信息", type = MethodType.MODIFY)
	public JSONReturn enrollEmployees(@RequestParam long emplId, @RequestParam String note, HttpSession httpSession) {
		return employeesService.enrollEmployees(emplId, note, acctName(httpSession));
	}

}
