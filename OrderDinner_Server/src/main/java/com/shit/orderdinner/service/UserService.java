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
	
	/**
	 * �����ֻ��Ż�ȡ�û�
	 * @param tel �ֻ���
	 * @return
	 */
	public User getUserByTel(String tel);
	
	/**
	 * ���������ȡ�û�
	 * @param email ����
	 * @return
	 */
	public User getUserByEMail(String email);
	
	/**
	 * �����û�����ȡ�û�
	 * @param name �û���
	 * @return
	 */
	public User getUserByName(String name);
}
