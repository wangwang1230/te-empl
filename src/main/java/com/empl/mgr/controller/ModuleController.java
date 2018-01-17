package com.empl.mgr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empl.mgr.controller.support.AbstractController;
import com.empl.mgr.service.ModuleService;
import com.empl.mgr.support.JSONReturn;

@Scope
@Controller
public class ModuleController extends AbstractController {

	@Autowired
	private ModuleService moduleService;

	@ResponseBody
	@RequestMapping(value = "findMenu")
	public JSONReturn findMenu(HttpSession httpSession) {
		return moduleService.findMenu(acctName(httpSession));
	}

	@ResponseBody
	@RequestMapping(value = "findModuleParameter")
	public JSONReturn findModuleParameter(@RequestParam String moduleCode, HttpSession httpSession) {
		return moduleService.findModuleParameter(moduleCode, acctName(httpSession));
	}

}
