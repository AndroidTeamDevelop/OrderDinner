package com.andteam.orderdinner.dao;

import com.andteam.orderdinner.model.User;

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
