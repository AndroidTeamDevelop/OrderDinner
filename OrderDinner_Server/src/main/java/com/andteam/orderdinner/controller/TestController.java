package com.andteam.orderdinner.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andteam.orderdinner.model.BaseResponse;
import com.andteam.orderdinner.service.UserService;

@Controller
@RequestMapping("/")
public class TestController {
	
	private static Logger logger = Logger.getLogger(TestController.class);
	
	@Autowired
	private UserService userService;

	/**
	 * 通过这个接口测试项目正常显示视图
	 * @return
	 */
	@RequestMapping("testView")
	public String testView() {
		logger.debug("测试开始");
		logger.error("测试结束");
		return "test";
	}
	
	/**
	 * 通过这个接口测试项目正常返回json
	 * @return
	 */
	@RequestMapping(value="testJson", method=RequestMethod.POST)
	@ResponseBody
	public BaseResponse testJson(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String name = request.getParameter("name");
		BaseResponse rsp = new BaseResponse();
		rsp.setCode(200);
		rsp.setMessage("userId = " + userId + ", Name = " + name);
		return rsp;
	}
	
	/**
	 * 通过这个接口测试项目正常使用service
	 * @return
	 */
	@RequestMapping(value="testService", method=RequestMethod.POST)
	@ResponseBody
	public BaseResponse testService(HttpServletRequest request) {
		String id = request.getParameter("id");
		BaseResponse rsp = new BaseResponse();
		rsp.setCode(200);
		rsp.setMessage("Success");
		rsp.setResult(userService.getUserById(Integer.valueOf(id)));
		return rsp;
	}	
}
