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
	 * ��½�ӿ�
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public BaseResponse login(HttpServletRequest request) {
		// �û���ʶ��ʶ���������û���/����/�ֻ���
		String user_identity = request.getParameter("identity");
		// �û�����
		String user_pwd = request.getParameter("pwd");
		logger.debug("login��ʼ: user_identity= " + user_identity + ", user_pwd= " + user_pwd);
		
		User user = null;
		if(CommonUtils.isTelNum(user_identity)) {
			// �����ֻ��Ż�ȡ�û���Ϣ
			user = userService.getUserByTel(user_identity);
		} else if(CommonUtils.isEMail(user_identity)) {
			// ���������ȡ�û���Ϣ
			user = userService.getUserByEMail(user_identity);
		} else {
			// �����û�����ȡ�û���Ϣ
			user = userService.getUserByName(user_identity);
		}
		
		BaseResponse rsp = new BaseResponse();
		if(user != null && user_pwd.equals(user.getUser_pwd())) {
			rsp.setCode(200);
			rsp.setMessage("Success");
			rsp.setResult(user);
		} else {
			rsp.setCode(1001);
			rsp.setMessage("��½ʧ�ܣ����û������ڻ������벻��ȷ");
		}
		
		logger.debug("login������response= " + rsp.toString());
		return rsp;
	}

}
