package com.andteam.orderdinner.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.andteam.orderdinner.model.User;
import com.andteam.orderdinner.service.UserService;

public class UserTest {

    private UserService userService;
	
	@Before
	public void before(){                                                                    
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
				,"classpath:conf/spring-mybatis.xml"});
		userService = (UserService) context.getBean("userServiceImpl");
	}
	
	@Test
	public void addUser(){
		User user = new User();
		user.setUser_name("zhangsan");
		user.setUser_pwd("123456");
		user.setUser_tel("15951908517");
		System.out.println(userService.insertUser(user));
	}
}
