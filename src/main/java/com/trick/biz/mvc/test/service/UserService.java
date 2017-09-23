package com.trick.biz.mvc.test.service;

import java.util.List;
import java.util.Map;

import com.trick.biz.mvc.test.model.User;



public interface UserService{
	public User getUserById(int adminId);

	public List<Map<String, Object>> getGirl();

	List<Map<String, Object>> findGirl_local();
}
