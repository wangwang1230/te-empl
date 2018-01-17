package com.empl.mgr.exception;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.empl.mgr.support.JSONReturn;

@ControllerAdvice
public class ExceptionIntercept implements Serializable {

	private static final long serialVersionUID = 1L;

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler({ Exception.class })
	public JSONReturn exception(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		ex.printStackTrace();
		return JSONReturn.buildFailure("服务器错误!");
	}

}
