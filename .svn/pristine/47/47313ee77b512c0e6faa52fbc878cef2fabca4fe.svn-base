package com.hbcsoft.zdy.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController<T> extends LogBaseClass<T> {
	@Autowired  
	protected transient HttpServletRequest request;
	@Autowired  
	protected transient HttpServletResponse response;
	@Autowired  
	protected transient HttpSession session;

	@ModelAttribute
	public void setReqAndRes(final HttpServletRequest request, final HttpServletResponse response)
	{
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}
}
