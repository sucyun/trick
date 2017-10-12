package com.trick.biz.mvc.admin.dao;

import com.trick.biz.mvc.admin.model.Role;

import java.util.List;
import java.util.Map;


public interface RoleMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Role record);

	int insertSelective(Role record);

	Role selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	List<Role> findAll(Map<String, Object> map);

	List<Map<String, Object>> findRoleList(Map<String, Object> map);

	int deletePriFromRole(Map<String, Object> map);

	int insertPriFroRole(List<Map<String, Object>> list);

	List<Integer> getPrivilegesByRole(int roleid);

	List<Integer> getParentPrivilegesByRole(int roleid);
}