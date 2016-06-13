package com.zh.service.user;

import java.util.List;

import com.zh.pojo.user.Users;

public interface UsersService {
	
	public Users getUserByid(int uid);
	public List<Users> getUsersList();
	public int insertUsersInfo(Users user);
}
