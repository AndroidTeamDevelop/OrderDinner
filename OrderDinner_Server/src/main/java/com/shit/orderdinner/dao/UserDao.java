package com.shit.orderdinner.dao;

import com.shit.orderdinner.model.User;

public interface UserDao {
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
	
	/**
	 * 根据手机号获取用户
	 * @param tel 手机号
	 * @return
	 */
	public User getUserByTel(String tel);
	
	/**
	 * 根据邮箱获取用户
	 * @param email 邮箱
	 * @return
	 */
	public User getUserByEMail(String email);
	
	/**
	 * 根据用户名获取用户
	 * @param name 用户名
	 * @return
	 */
	public User getUserByName(String name);
}
