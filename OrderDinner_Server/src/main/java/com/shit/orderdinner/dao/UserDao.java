package com.shit.orderdinner.dao;

import com.shit.orderdinner.model.User;

public interface UserDao {
	/**
	 * ������û�
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
	
	/**
	 * ����id��ȡ�û�
	 * @param id
	 * @return
	 */
	public User getUserById(int id);
}
