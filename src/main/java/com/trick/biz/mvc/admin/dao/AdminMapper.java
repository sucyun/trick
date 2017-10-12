package com.trick.biz.mvc.admin.dao;

import com.trick.biz.mvc.admin.model.Admin;
import com.trick.biz.mvc.admin.model.Privilege;
import com.trick.biz.mvc.admin.model.Role;

import java.util.List;
import java.util.Map;



public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    Admin selectPrivilegesByAdmName(String admName);
    
    Map<String,Object> pritest();
    
    List<Role> getRoleListByAdminName(String admName);
    
    List<Privilege> getPrivilegeListByRole(int roleid);
    
    List<Map<String,Object>> findAll(Map<String, Object> map);

	Admin login(Map<String, Object> map);
}