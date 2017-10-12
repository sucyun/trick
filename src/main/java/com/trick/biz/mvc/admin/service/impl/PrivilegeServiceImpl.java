package com.trick.biz.mvc.admin.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.trick.biz.mvc.admin.dao.PrivilegeMapper;
import com.trick.biz.mvc.admin.model.Privilege;
import com.trick.biz.mvc.admin.service.PrivilegeService;
import org.springframework.stereotype.Service;


@Service("privilegeService")
public class PrivilegeServiceImpl implements PrivilegeService {
	
	@Resource
	private PrivilegeMapper privilegeDao;

	@Override
	public Privilege getPrivilegeById(int privilegeId) {
		return privilegeDao.selectByPrimaryKey(privilegeId);
	}

	@Override
	public List<Map<String, Object>> findAll(Map<String, Object> map) {
		List<Map<String, Object>> result = privilegeDao.findAll(map);
		return result;
	}

	@Override
	public int insertPrivilege(Privilege privilege) {
		return this.privilegeDao.insertSelective(privilege);
	}

	@Override
	public int updatePrivilege(Privilege privilege) {
		return this.privilegeDao.updateByPrimaryKeySelective(privilege);
	}

	@Override
	public int deletePrivilege(int id) {
		return 0;
	}

	@Override
	public Privilege selectPrivilegeByAdmName(String admName) {
		return null;
	}

	@Override
	public int countByParam(Map<String, Object> map) {
		return this.privilegeDao.countByParam(map);
	}

	@Override
	public Privilege selectByPrimaryKey(int id) {
		return this.privilegeDao.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> selectPritest() {
		return null;
	}

}
