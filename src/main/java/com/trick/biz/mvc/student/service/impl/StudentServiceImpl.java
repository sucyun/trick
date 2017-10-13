package com.trick.biz.mvc.student.service.impl;

import com.trick.biz.mvc.student.dao.StudentMapper;
import com.trick.biz.mvc.student.model.Student;
import com.trick.biz.mvc.student.service.StudentService;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Describe:Student业务处理接口
 * @author haoshuai
 * @since 1.8.0_144
 * @Date 2017年10月12日 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentDao;

    /**
	* Describe: 保存方法
	* haoshuai 2017年10月12日
	*/
    @Override
    public int saveStudent(Student student) {
        return studentDao.insert(student);
    }

    /**
	* Describe: 删除方法
	* haoshuai 2017年10月12日
	*/
    @Override
    public int removeStudent(int id) {
        return 0;
    }

    /**
	* Describe: 修改方法
	* haoshuai 2017年10月12日
	*/
    @Override
    public int updateStudent(Student student) {
        return 0;
    }

    /**
	* Describe: 列表页跳转
	* haoshuai 2017年10月12日
	*/
    @Override
    public String listPageStudent(Map<String, Object> param) {
        return null;
    }

    /**
	* Describe: 列表页数据
	* haoshuai 2017年10月12日
	*/
    @Override
    public List<Map<String, Object>> listDataStudent(Map<String, Object> param) {
        return null;
    }

    @Override
    public Student getStudent(int id) {
        return studentDao.selectByPrimaryKey(id);
    }
}