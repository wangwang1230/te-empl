package com.empl.mgr.controller;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

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
import com.empl.mgr.dto.EmployeesInfoDto;
import com.empl.mgr.service.EmployeesService;
import com.empl.mgr.support.JSONReturn;

@Scope
@Controller
@RequestMapping(value = "employees/internship")
public class EmployeesInternshipController extends AbstractController {

	@Autowired
	private EmployeesService employeesService;

	@ResponseBody
	@RequestMapping(value = "findChoose")
	@SecureValid(code = "01001", desc = "获取单选或多选项列表", type = MethodType.FIND)
	public JSONReturn findChoose() {
		return employeesService.findChoose();
	}

	@ResponseBody
	@RequestMapping(value = "saveEmployeesInfo")
	@SecureValid(code = "01001", desc = "保存员工信息", type = MethodType.ADD)
	public JSONReturn saveEmployeesInfo(@RequestParam String data, HttpSession httpSession) {
		EmployeesInfoDto dto = (EmployeesInfoDto) JSONObject
				.toBean(JSONObject.fromObject(data), EmployeesInfoDto.class);
		return employeesService.saveEmployeesInfo(dto, acctName(httpSession));
	}

	@ResponseBody
	@RequestMapping(value = "findEmployeesInternshipList")
	@SecureValid(code = "01001", desc = "获取实习员工列表信息", type = MethodType.FIND)
	public JSONReturn findEmployeesInternshipList(@RequestParam int serType, @RequestParam String serVal,
			@RequestParam long department, @RequestParam long position, @RequestParam int page, HttpSession httpSession) {
		return employeesService.findEmployeesList(serType, serVal, department, position, page, acctName(httpSession),
				EmployeesState.EMPL_INTERNSHIP);
	}

	@ResponseBody
	@RequestMapping(value = "findEmployeesInternshipPage")
	@SecureValid(code = "01001", desc = "获取实习员工分页", type = MethodType.FIND)
	public JSONReturn findEmployeesInternshipPage(@RequestParam int serType, @RequestParam String serVal,
			@RequestParam long department, @RequestParam long position, @RequestParam int page, HttpSession httpSession) {
		return employeesService.findEmployeesPage(serType, serVal, department, position, page, acctName(httpSession),
				EmployeesState.EMPL_INTERNSHIP);
	}

	@ResponseBody
	@RequestMapping(value = "enroll")
	@SecureValid(code = "01001", desc = "录取员工", type = MethodType.MODIFY)
	public JSONReturn enroll(@RequestParam long emId, HttpSession httpSession) {
		return employeesService.enroll(emId, acctName(httpSession));
	}

	@ResponseBody
	@RequestMapping(value = "eliminate")
	@SecureValid(code = "01001", desc = "淘汰员工", type = MethodType.MODIFY)
	public JSONReturn eliminate(@RequestParam long emId, @RequestParam String note, HttpSession httpSession) {
		return employeesService.eliminate(emId, note, acctName(httpSession));
	}

	@ResponseBody
	@RequestMapping(value = "findEmployeesInfo")
	@SecureValid(code = { "01001", "01002" }, desc = "获取员工基本信息", type = MethodType.MODIFY)
	public JSONReturn findEmployeesInfo(@RequestParam long emplId) {
		return employeesService.findEmployeesInfo(emplId);
	}

	@ResponseBody
	@RequestMapping(value = "modifyEmployeesInfo")
	@SecureValid(code = { "01001", "01002" }, desc = "修改员工信息", type = MethodType.MODIFY)
	public JSONReturn modifyEmployeesInfo(@RequestParam String data, HttpSession httpSession) {
		EmployeesInfoDto dto = (EmployeesInfoDto) JSONObject
				.toBean(JSONObject.fromObject(data), EmployeesInfoDto.class);
		return employeesService.modifyEmployeesInfo(dto, acctName(httpSession));
	}
}
