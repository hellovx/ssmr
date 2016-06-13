package com.zh.controller.user;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zh.pojo.user.Users;
import com.zh.service.user.UsersService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UsersService usersService;
	
	private static Logger logger = Logger.getLogger(UserController.class);  

	@RequestMapping("/getall.do")
	public String test(Model model) {
		System.out.println("init test/hello.do");

		List<Users> list = this.usersService.getUsersList();

		model.addAttribute("list", list);
		
        // 记录debug级别的信息  
        logger.debug("This is debug message.");  
        // 记录info级别的信息  
        logger.info("This is info message.");  
        // 记录error级别的信息  
        logger.error("This is error message.");  
        
		return "user/list";
	}
	
	@RequestMapping("/getUserInfoById/{id}")
	public String getUserInfoById(Model model,@PathVariable("id") Integer uid){
		Users user=this.usersService.getUserByid(uid);
		model.addAttribute("user", user);
		return "user/info";
	}
	
	@RequestMapping("/reqtest.do")
	public String reqtest(HttpServletRequest request,HttpServletResponse response){
		System.out.println("进入reqtest.do");
		//项目 目录获取
		String proPath=request.getContextPath();
		System.out.println(proPath);
		proPath = request.getSession().getServletContext().getRealPath("/views");
		System.out.println(proPath);
		proPath = request.getSession().getServletContext().getRealPath("/WEB-INF");
		System.out.println(proPath);
		//属性文件获取test
		proPath = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/application.properties");
		System.out.println(proPath);
		proPath = request.getSession().getServletContext().getRealPath("/WEB-INF/spring-mvc.xml");
		System.out.println(proPath);
		InputStream in =request.getSession().getServletContext().getResourceAsStream("/WEB-INF/classes/application.properties");
		Properties pro = new Properties();  
        try {
			pro.load(in);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String url = pro.getProperty("jdbc.url");
        System.out.println(url);
      
		return "";
	}

}
