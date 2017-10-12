package com.trick.biz.mvc.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.trick.biz.mvc.admin.dao.RoleMapper;
import com.trick.biz.mvc.admin.model.Role;
import com.trick.biz.mvc.admin.service.RoleService;
import org.springframework.stereotype.Service;


@Service("roleService")
public class RoleServiceImple implements RoleService {
	
	@Resource
	private RoleMapper roleDao;

	@Override
	public Role getRoleById(int roleId) {
		return null;
	}

	@Override
	public int insertRole(Role role) {
		return this.roleDao.insertSelective(role);
	}

	@Override
	public int updateRole(Role role) {
		return this.roleDao.updateByPrimaryKeySelective(role);
	}

	@Override
	public int deleteRole(int id) {
		return 0;
	}

	@Override
	public int countByParam(Map<String, Object> map) {
		return 0;
	}

	@Override
	public Role selectByPrimaryKey(int id) {
		return this.roleDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Role> findAll(Map<String, Object> map) {
		List<Role> list = roleDao.findAll(map);
		return list;
	}

	@Override
	public Map<String, Object> selectPritest() {
		return null;
	}

	@Override
	public List<Map<String, Object>> findRoleList(Map<String, Object> map) {
		List<Map<String, Object>> treelist = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list = roleDao.findRoleList(map);
		for (Map<String, Object> m : list) {
			Map<String, Object> tree = new HashMap<String, Object>();
			tree.put("id", m.get("id"));
			tree.put("text", m.get("name"));
			treelist.add(tree);
		}
		return treelist;
	}

	@Override
	public int removePriFromRole(Map<String, Object> map) {
		return this.roleDao.deletePriFromRole(map);
	}

	@Override
	public int addPriFroRole(List<Map<String, Object>> list) {
		return this.roleDao.insertPriFroRole(list);
	}

	@Override
	public List<Integer> findPrivilegesByRole(int roleid) {
		List<Integer> privilege = this.roleDao.getPrivilegesByRole(roleid);
		return privilege;
	}

	@Override
	public List<Integer> findParentPrivilegesByRole(int roleid) {
		List<Integer> privilege = this.roleDao.getParentPrivilegesByRole(roleid);
		return privilege;
	}


}
