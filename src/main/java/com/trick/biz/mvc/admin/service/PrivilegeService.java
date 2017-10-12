package com.trick.biz.mvc.admin.service;

import com.trick.biz.mvc.admin.model.Privilege;

import java.util.List;
import java.util.Map;


public interface PrivilegeService {
	
	public Privilege getPrivilegeById(int privilegeId);
	
	public List<Map<String,Object>> findAll(Map<String, Object> map);
	
	public int insertPrivilege(Privilege privilege);
	
	public int updatePrivilege(Privilege privilege);
	
	public int deletePrivilege(int id);
	
	public Privilege selectPrivilegeByAdmName(String admName);
	
	public int countByParam(Map<String, Object> map);

	public Privilege selectByPrimaryKey(int id);
	
	public Map<String,Object> selectPritest();
	
	
}
