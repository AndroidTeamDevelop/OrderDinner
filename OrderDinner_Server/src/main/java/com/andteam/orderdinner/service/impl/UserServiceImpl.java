package com.andteam.orderdinner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andteam.orderdinner.dao.UserDao;
import com.andteam.orderdinner.model.User;
import com.andteam.orderdinner.service.UserService;

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
