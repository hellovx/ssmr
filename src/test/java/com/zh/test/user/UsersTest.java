package com.zh.test.user;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zh.pojo.user.Users;
import com.zh.service.user.UsersService;


public class UsersTest {

	@Autowired
	private UsersService usersService;

/*	@Test
	public void UsersTest() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		usersService = (UsersService) context.getBean("usersService");
		List<Users> list= usersService.getUsersList();
		
		for(Users u:list){
			System.out.println(u.getUname());
		}

	}*/
	
	@Test
	public void UsersInsertTest(){
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		usersService = (UsersService) context.getBean("usersService");
		Users user=new Users();
		user.setUname("等等");
		user.setAge(12);
		user.setAddress("北京市朝阳区酒仙桥中心小学");
		 
		System.out.println("插入前主键为:"+user.getUid());
		
		int strid=usersService.insertUsersInfo(user);
		
		System.out.println(strid);
		System.out.println("插入后主键为:"+user.getUid());
		System.out.println("插入成功！！");
		
	}
	
	
	

}
