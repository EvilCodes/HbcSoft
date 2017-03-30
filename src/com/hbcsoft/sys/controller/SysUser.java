package com.hbcsoft.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hbcsoft.login.controller.Login;
import com.hbcsoft.zdy.common.LogBaseClass;

@Controller
@RequestMapping(value = "/sys")
public class SysUser extends LogBaseClass<Login> {
	@RequestMapping(value = "/userList")
	public String userList() {
		return "sys/user/userList";
	}
}
