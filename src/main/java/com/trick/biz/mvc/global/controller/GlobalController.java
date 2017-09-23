package com.trick.biz.mvc.global.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Scope(value="prototype")
@Controller
@RequestMapping("/g/")
public class GlobalController {
	
	/**
	 * Describe: 登录
	 * MrShuai 2017年4月24日
	 */
	@RequestMapping(value="login",method=RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView mv = new ModelAndView("user/login");
		mv.addObject("username","admin");
		return mv;
	}
	
	@RequestMapping(value="sys",method=RequestMethod.GET)
	public ModelAndView sys(){
		return new ModelAndView("common/global/main");
	}
	
	
	@RequestMapping(value="viewtest",method=RequestMethod.GET)
	public ModelAndView viewtest(){
		return new ModelAndView("map/map01");
	}
}
