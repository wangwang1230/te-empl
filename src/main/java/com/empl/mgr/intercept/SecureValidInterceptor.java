package com.empl.mgr.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.empl.mgr.annotation.SecureValid;
import com.empl.mgr.constant.LoginState;
import com.empl.mgr.constant.SessionKey;
import com.empl.mgr.model.TeAccount;
import com.empl.mgr.service.AccountService;
import com.empl.mgr.service.ModuleService;
import com.empl.mgr.support.JSONReturn;
import com.empl.mgr.utils.CompareUtil;

public class SecureValidInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private ModuleService moduleService;
	@Autowired
	private AccountService accountService;

	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handle) throws Exception {

		if (req.getRequestURI().contains("mgr/0"))
			return true;
		String userName = (String) req.getSession().getAttribute(SessionKey.MODULEACCTNAME);
		if (StringUtils.isEmpty(userName)) {
			resp.getOutputStream().print(JSONObject.fromObject(JSONReturn.buildFailure(LoginState.UNLOGIN)).toString());
			return false;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handle;
		SecureValid secureValid = handlerMethod.getMethod().getAnnotation(SecureValid.class);
		if (CompareUtil.isEmpty(secureValid))
			return true;
		// 从数据库中获取当前账户是否存在, 如果不存在, 提示未登录
		TeAccount teAccount = accountService.findAccountByName(userName);
		if (CompareUtil.isEmpty(teAccount)) {
			resp.getOutputStream().print(JSONObject.fromObject(JSONReturn.buildFailure(LoginState.UNLOGIN)).toString());
			return false;
		}
		// 如果是超管, 直接通过拦截器
		if (teAccount.getAcctSuper())
			return true;
		if (!moduleService.secureValid(userName, secureValid.code(), secureValid.type())) {
			resp.getOutputStream().print(
					JSONObject.fromObject(JSONReturn.buildFailure(LoginState.PERMISSION_DENIED)).toString());
			return false;
		}
		return true;
	}
}
