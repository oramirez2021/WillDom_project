package com.hulkStore_ms.model;

public class User {
	private int user_id;
	private String user_name;
	private String user_last_name;
	private String password;


	public User	(int user_id, String user_name, String user_last_name, String password) {
		super();
		this.setUser_id(user_id);
		this.setUser_name(user_name);
		this.setUser_last_name(user_last_name);
		this.setPassword(password);
	}


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


	public String getUser_last_name() {
		return user_last_name;
	}


	public void setUser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

}
