package com.trick.biz.mvc.test.dao;

import java.util.List;
import java.util.Map;

import com.trick.biz.mvc.test.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	List<Map<String, Object>> getGirl();
}