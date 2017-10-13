package com.trick.biz.mvc.test.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.trick.biz.mvc.test.dao.UserMapper;
import com.trick.biz.mvc.test.model.User;
import com.trick.biz.mvc.test.service.UserService;
import com.trick.web.db.jdbc.DataSource;
import com.trick.web.db.jdbc.DynamicDataSourceHolder;


@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserMapper userDao;

	@Override
	public User getUserById(int adminId) {
		return userDao.selectByPrimaryKey(1);
	}

	@Override
//	@DataSource("dataSource_rout")
	public List<Map<String, Object>> getGirl() {
//		DynamicDataSourceHolder.setDataSource("dataSource_rout");
		return userDao.getGirl();
	}
	
	@Override
//	@DataSource("dataSource_local")
	public List<Map<String, Object>> findGirl_local() {
		return userDao.getGirl();
	}
	
}
