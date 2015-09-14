package com.shit.orderdinner.service;

import com.shit.orderdinner.model.User;

public interface UserService {
	/**
	 * 添加新用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
	
	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 */
	public User getUserById(int id);
}
