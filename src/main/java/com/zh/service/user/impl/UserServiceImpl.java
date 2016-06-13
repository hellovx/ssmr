package com.zh.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zh.mapper.user.UsersMapper;
import com.zh.pojo.user.Users;
import com.zh.service.user.UsersService;

@Service("usersService")
@Transactional
public class UserServiceImpl implements UsersService {

	@Autowired
	private UsersMapper usersMapper;
	public List<Users> getUsersList() {
		// TODO Auto-generated method stub
		return this.usersMapper.getUsersList();
	}
	public int insertUsersInfo(Users user) {
		int strid=this.usersMapper.insertUsersInfo(user);
		return strid;
		
	}
	public Users getUserByid(int uid) {
		// TODO Auto-generated method stub
		return this.usersMapper.getUserByid(uid);
	}

}
