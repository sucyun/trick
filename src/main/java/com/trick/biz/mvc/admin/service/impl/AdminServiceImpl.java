package com.trick.biz.mvc.admin.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.trick.biz.mvc.admin.dao.AdminMapper;
import com.trick.biz.mvc.admin.model.Admin;
import com.trick.biz.mvc.admin.model.Privilege;
import com.trick.biz.mvc.admin.model.Role;
import com.trick.biz.mvc.admin.service.AdminService;
import org.springframework.stereotype.Service;


@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	@Resource
	private AdminMapper adminDao;

	@Override
	public Admin getAdminById(int adminId) {
		return null;
	}

	@Override
	public List<Map<String, Object>> findAll(Map<String, Object> map) {
		return null;
	}

	@Override
	public int insertAdmin(Admin admin) {
		return 0;
	}

	@Override
	public int updateAdmin(Admin admin) {
		return 0;
	}

	@Override
	public int deleteAdmin(int id) {
		return this.adminDao.deleteByPrimaryKey(id);
	}

	@Override
	public Admin selectPrivilegeByAdmName(String admName) {
		Admin adm = this.adminDao.selectPrivilegesByAdmName(admName);
		List<Role> role = this.adminDao.getRoleListByAdminName(admName);
		for (Role r : role) {
			List<Privilege> privilege = this.adminDao.getPrivilegeListByRole(r.getId());
			r.setPrivilege(privilege);
		}
		adm.setRole(role);
		return adm;
	}

	@Override
	public int countByParam(Map<String, Object> map) {
		return 0;
	}

	@Override
	public Admin selectByPrimaryKey(int i) {
		Admin adm = this.adminDao.selectByPrimaryKey(i);
		return adm;
	}

	@Override
	public Map<String,Object> selectPritest() {
		Map<String,Object> map = this.adminDao.pritest();
		return map;
	}

	@Override
	public Admin login(String name, char[] pwd) {
		String password = new String(pwd);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("password", password);
		Admin admin = adminDao.login(map);
		return admin;
	}

}
