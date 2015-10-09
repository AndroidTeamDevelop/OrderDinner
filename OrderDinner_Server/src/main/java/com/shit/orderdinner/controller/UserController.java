package com.shit.orderdinner.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shit.orderdinner.common.CommonUtils;
import com.shit.orderdinner.model.BaseResponse;
import com.shit.orderdinner.model.User;
import com.shit.orderdinner.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 登陆接口
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public BaseResponse login(HttpServletRequest request) {
		// 用户辨识标识，可能是用户名/邮箱/手机号
		String user_identity = request.getParameter("identity");
		// 用户密码
		String user_pwd = request.getParameter("pwd");
		logger.debug("login开始: user_identity= " + user_identity + ", user_pwd= " + user_pwd);
		
		User user = null;
		if(CommonUtils.isTelNum(user_identity)) {
			// 根据手机号获取用户信息
			user = userService.getUserByTel(user_identity);
		} else if(CommonUtils.isEMail(user_identity)) {
			// 根据邮箱获取用户信息
			user = userService.getUserByEMail(user_identity);
		} else {
			// 根据用户名获取用户信息
			user = userService.getUserByName(user_identity);
		}
		
		BaseResponse rsp = new BaseResponse();
		if(user != null && user_pwd.equals(user.getUser_pwd())) {
			rsp.setCode(200);
			rsp.setMessage("Success");
			rsp.setResult(user);
		} else {
			rsp.setCode(1001);
			rsp.setMessage("登陆失败，该用户不存在或者密码不正确");
		}
		
		logger.debug("login结束：response= " + rsp.toString());
		return rsp;
	}

}
