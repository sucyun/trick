package com.trick.biz.mvc.admin.dao;

import com.trick.biz.mvc.admin.model.Privilege;

import java.util.List;
import java.util.Map;


public interface PrivilegeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Privilege record);

    int insertSelective(Privilege record);

    Privilege selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Privilege record);

    int updateByPrimaryKey(Privilege record);
    
    List<Map<String,Object>> findAll(Map<String, Object> map);
    
    int countByParam(Map<String, Object> map);
}