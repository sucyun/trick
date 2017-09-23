package com.trick.biz.mvc.test.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.trick.biz.mvc.test.model.User;
import com.trick.biz.mvc.test.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/userMsg")
	public String UserMsg(HttpServletRequest request,HttpServletResponse httpServletResponse){
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		System.out.println(username);
//		System.out.println(password);
//		User template = userService.getUserById(1);
		JSONObject json = new JSONObject();
		json.put("t", "eee");
		json.put("w", "ll");
		return json.toJSONString();
	}
	@RequestMapping("/showUser")
	public ModelAndView showUser(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("show2User");
		int userId = Integer.parseInt((String)request.getParameter("id"));
        User user = this.userService.getUserById(userId);
        mv.addObject("user", user);
        mv.addObject("date", new Date());
        mv.addObject("boolean", true);
        
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 5; i++) {
        	User u = new User();
        	u.setUserName("用户"+i);
			list.add(u);
		}
        mv.addObject("userList",list);
		return mv;
	}
	@RequestMapping("/show2User")
	public ModelAndView show2User(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("test/showUser");
		int userId = Integer.parseInt((String)request.getParameter("id"));
        User user = this.userService.getUserById(userId);
        mv.addObject("user", user);
        mv.addObject("date", new Date());
        mv.addObject("boolean", true);
        
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 5; i++) {
        	User u = new User();
        	u.setUserName("用户"+i);
			list.add(u);
		}
        mv.addObject("userList",list);
		return mv;
	}
	@ResponseBody
	@RequestMapping("/getGirl")
	public String getGirl(HttpServletRequest request,HttpServletResponse httpServletResponse){
		List<Map<String,Object>> list =  this.userService.getGirl();
		String json = JSONObject.toJSONString(list);
		return json;
	}
	@ResponseBody
	@RequestMapping("/getGirl_local")
	public String _local(HttpServletRequest request,HttpServletResponse httpServletResponse){
		List<Map<String,Object>> list =  this.userService.findGirl_local();
		String json = JSONObject.toJSONString(list);
		return json;
	}
}
