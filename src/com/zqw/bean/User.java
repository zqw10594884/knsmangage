package com.zqw.bean;

public class User {
	private int id;
	private String name;
	private String realName;
	private String password;
	private int authority;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return 0~9超级管理员 10~19 管理员 20~30雇员 20裁布 21售货员 22裁缝 23 安装工
	 */
	public int getAuthority() {
		return authority;
	}

	/**
	 * 
	 * @param authority
	 *            0~9超级管理员 10~19 管理员 20~30雇员 20裁布 21售货员 22裁缝 23 安装工
	 */
	public void setAuthority(int authority) {
		this.authority = authority;
	}
}
