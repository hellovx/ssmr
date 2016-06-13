package com.zh.pojo.user;

import java.io.Serializable;

public class Users implements Serializable {

	private int uid;
	private String uname;
	private int age;
	private int sex;
	private String address;
	
	
	@Override
	public String toString() {
		return "Users [uid=" + uid + ", uname=" + uname + ", age=" + age
				+ ", sex=" + sex + ", address=" + address + "]";
	}
	public Users(int uid, String uname, int age, int sex, String address) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.age = age;
		this.sex = sex;
		this.address = address;
	}
	public Users() {
		super();
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
