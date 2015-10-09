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

	@Override
	public User getUserByTel(String tel) {
		return userDao.getUserByTel(tel);
	}

	@Override
	public User getUserByEMail(String email) {
		return userDao.getUserByEMail(email);
	}

	@Override
	public User getUserByName(String name) {
		return userDao.getUserByName(name);
	}

}
