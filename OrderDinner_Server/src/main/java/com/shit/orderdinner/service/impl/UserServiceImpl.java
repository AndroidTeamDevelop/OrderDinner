package com.shit.orderdinner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shit.orderdinner.dao.UserDao;
import com.shit.orderdinner.model.User;
import com.shit.orderdinner.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	public int insertUser(User user) {
		return userDao.insertUser(user);
	}

	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

}
