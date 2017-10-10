package com.trick.biz.mvc.student.controller;

import com.trick.biz.mvc.student.model.Student;
import com.trick.biz.mvc.student.service.StudentService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Describe:Student控制器
 * @author haoshuai
 * @since 1.8.0_144
 * @Date 2017年10月07日 */
@Controller
@RequestMapping(value ="/student/")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
	* Describe: 保存方法
	* haoshuai 2017年10月07日
	*/
    @ResponseBody
    @RequestMapping(value = "saveStudent" , method = RequestMethod.POST)
    public String saveStudent(HttpServletRequest request, Student student) {
        String name = request.getParameter("name");
        int l = studentService.saveStudent(student);
		return l+"";
    }

    /**
	* Describe: 删除方法
	* haoshuai 2017年10月07日
	*/
    @ResponseBody
    @RequestMapping(value = "removeStudent" , method = RequestMethod.GET)
    public String removeStudent(HttpServletRequest request) {
        return null;
    }

    /**
	* Describe: 修改方法
	* haoshuai 2017年10月07日
	*/
    @ResponseBody
    @RequestMapping(value = "updateStudent" , method = RequestMethod.GET)
    public String updateStudent(HttpServletRequest request) {
        return null;
    }

    /**
	* Describe: 列表页跳转
	* haoshuai 2017年10月07日
	*/
    @RequestMapping(value = "listPageStudent" , method = RequestMethod.GET)
    public String listPageStudent(HttpServletRequest request) {
        return null;
    }

    /**
	* Describe: 列表页数据
	* haoshuai 2017年10月07日
	*/
    @ResponseBody
    @RequestMapping(value = "listDataStudentByParam" , method = RequestMethod.GET)
    public String listDataStudentByParam(HttpServletRequest request) {
        return null;
    }
}