package com.andteam.orderdinner.model;

public class User {

	/** �û�Ψһ��ʶ*/
	private int user_id;
	/** �û���*/
	private String user_name;
	/** �ֻ���*/
	private String user_tel;
	/** ����*/
	private String user_pwd;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

}