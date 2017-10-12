package com.trick.biz.mvc.admin.service;

import com.trick.biz.mvc.admin.model.Admin;

import java.util.List;
import java.util.Map;




public interface AdminService {

	public Admin getAdminById(int adminId);
	
	public List<Map<String,Object>> findAll(Map<String, Object> map);
	
	public int insertAdmin(Admin admin);
	
	public int updateAdmin(Admin admin);
	
	public int deleteAdmin(int id);
	
	public Admin login(String name, char[] password);
	
	public Admin selectPrivilegeByAdmName(String admName);
	
	public int countByParam(Map<String, Object> map);

	public Admin selectByPrimaryKey(int i);
	
	public Map<String,Object> selectPritest();
}
