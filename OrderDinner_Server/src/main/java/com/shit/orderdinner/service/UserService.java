package com.shit.orderdinner.service;

import com.shit.orderdinner.model.User;

public interface UserService {
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
