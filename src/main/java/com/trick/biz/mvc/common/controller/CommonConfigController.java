package com.trick.biz.mvc.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("conf")
public class CommonConfigController {
	@RequestMapping(value="home",method=RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView mv = new ModelAndView("common/conf/home");
		mv.addObject("name", "admin");
		return mv;
	}
	
	/**
	 * Describe: 表单模板
	 * MrShuai 2017年5月4日
	 */
	@RequestMapping(value="form",method=RequestMethod.GET)
	public ModelAndView form(){
		ModelAndView mv = new ModelAndView("common/conf/form");
		return mv;
	}
	/**
	 * Describe: 数据网格
	 * MrShuai 2017年5月4日
	 */
	@RequestMapping(value="datagrid",method=RequestMethod.GET)
	public ModelAndView datagrid(){
		ModelAndView mv = new ModelAndView("common/conf/datagrid");
		return mv;
	}
	/**
	 * Describe: Echart折线图
	 * MrShuai 2017年5月4日
	 */
	@RequestMapping(value="echart",method=RequestMethod.GET)
	public ModelAndView echart(){
		ModelAndView mv = new ModelAndView("common/conf/echart");
		return mv;
	}
	/**
	 * Describe: 通用小零部件
	 * MrShuai 2017年5月4日
	 */
	@RequestMapping(value="small",method=RequestMethod.GET)
	public ModelAndView small(){
		ModelAndView mv = new ModelAndView("common/conf/small");
		return mv;
	}
}
