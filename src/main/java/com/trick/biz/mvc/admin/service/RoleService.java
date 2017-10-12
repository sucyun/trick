package com.trick.biz.mvc.admin.service;

import com.trick.biz.mvc.admin.model.Role;

import java.util.List;
import java.util.Map;


public interface RoleService {

	public Role getRoleById(int roleId);

	public List<Role> findAll(Map<String, Object> map);

	public int insertRole(Role role);

	public int updateRole(Role role);

	public int deleteRole(int id);

	public int countByParam(Map<String, Object> map);

	public Role selectByPrimaryKey(int id);

	public Map<String, Object> selectPritest();

	public List<Map<String, Object>> findRoleList(Map<String, Object> map);
	
	public int removePriFromRole(Map<String, Object> map);
	
	public int addPriFroRole(List<Map<String, Object>> list);
	
	public List<Integer> findPrivilegesByRole(int roleid);
	
	public List<Integer> findParentPrivilegesByRole(int roleid);
}
