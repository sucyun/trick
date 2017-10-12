package com.trick.biz.mvc.admin.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.trick.biz.mvc.admin.model.Privilege;
import com.trick.biz.mvc.admin.model.Role;
import com.trick.biz.mvc.admin.service.AdminService;
import com.trick.biz.mvc.admin.service.PrivilegeService;
import com.trick.biz.mvc.admin.service.RoleService;
import com.trick.biz.utils.system.RequsetUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Describe:Controller for 管理员
 * 1、管理员CRUD
 * 2、权限CRUD
 * 3、管理员分配权限
 * @author MrShuai 2016年8月23日
 */
@Controller
@RequestMapping("/admin/")
public class AdminController {
	/*定义log4j日志*/
	private static Logger logger = Logger.getLogger(AdminController.class);
	/*Admin业务层*/
	@Resource
	private AdminService adminService;
	/*Privilege业务层*/
	@Resource
	private PrivilegeService privilegeService;
	/*Role业务层*/
	@Resource
	private RoleService roleService;
	
	
	/**
	 * Describe: 管理员新增
	 * MrShuai 2016年8月23日
	 */
	@RequestMapping("saveAdmin")
	public String saveAdmin(HttpServletRequest request){
		
		return "";
	}
	/**
	 * Describe: 管理员删除
	 * MrShuai 2016年8月23日
	 */
	@RequestMapping("removeAdmin")
	public String removeAdmin(HttpServletRequest request){
		String roleid = request.getParameter("roleid");
		int len = this.adminService.deleteAdmin(Integer.parseInt(roleid));
		return len+"";
	}
	/**
	 * Describe: 管理员修改
	 * MrShuai 2016年8月23日
	 */
	@RequestMapping("updateAdmin")
	public String updateAdmin(HttpServletRequest request){
		
		return "";
	}
	/**
	 * Describe: 管理员跳转到列表页面
	 * MrShuai 2016年8月23日
	 */
	@RequestMapping("adminList")
	public String adminList(HttpServletRequest request){
		
		return "";
	}
	/**
	 * Describe: 管理员列表查询方法
	 * MrShuai 2016年8月23日
	 */
	@RequestMapping("selectAdminList")
	public String selectAdminList(HttpServletRequest request){
		
		return "";
	}
	
	
	/**************************************************权限**********************************************************/
	
	/**
	 * Describe: 权限新增
	 * MrShuai 2016年8月23日
	 */
	@ResponseBody
	@RequestMapping("savePrivilege")
	public String savePrivilege(HttpServletRequest request){
		String userName = (String) request.getSession().getAttribute("userName");
		String name = RequsetUtils.checkParameters(request, "name");
		String url = RequsetUtils.checkParameters(request, "url");
		String parentid = RequsetUtils.checkParameters(request, "priParent");
		String explan = RequsetUtils.checkParameters(request, "explan");
		
		String id = RequsetUtils.checkParameters(request, "priid");
		String type = RequsetUtils.checkParameters(request, "type");
		Privilege privilege = new Privilege();
		
		if("update".equals(type)){
			privilege = privilegeService.selectByPrimaryKey(Integer.parseInt(id));
			privilege.setName(name);
			if(!"".equals(parentid))//如果父类不为空插入父类id
				privilege.setParentId(Integer.parseInt(parentid));
			privilege.setUrl(url);
			privilege.setStatus(1);
			privilege.setExplan(explan);
			return ""+privilegeService.updatePrivilege(privilege);
		}
		
		privilege.setCreateBy(userName);
		privilege.setCreateTime(new Date());
		privilege.setName(name);
		if(!"".equals(parentid))//如果父类不为空插入父类id
			privilege.setParentId(Integer.parseInt(parentid));
		privilege.setUrl(url);
		privilege.setStatus(1);
		privilege.setExplan(explan);
		
		return "";
	}
	/**
	 * Describe: 权限删除
	 * MrShuai 2016年8月23日
	 */
	@RequestMapping("removePrivilege")
	public String removePrivilege(HttpServletRequest request){
		
		return "";
	}
	/**
	 * Describe: 权限修改根据id查询方法
	 * MrShuai 2016年8月23日
	 */
	@RequestMapping("selectPrivilegeById")
	public String selectPrivilegeById(HttpServletRequest request,String id){
		Privilege privilege = privilegeService.selectByPrimaryKey(Integer.parseInt(id));
		request.setAttribute("privilege", privilege);
		request.setAttribute("type", "update");
		return "admin/privilege/privilege_add";
	}
	/**
	 * Describe: 权限修改保存方法
	 * MrShuai 2016年8月23日
	 */
	@RequestMapping("updatePrivilege")
	public String updatePrivilege(HttpServletRequest request){
		
		return "";
	}
	/**
	 * Describe: 权限跳转到列表页面
	 * MrShuai 2016年8月23日
	 */
	@RequestMapping("privilegeList")
	public String privilegeList(HttpServletRequest request){
		return "admin/privilege/privilege_list";
	}
	/**
	 * Describe: 权限列表查询方法
	 * MrShuai 2016年8月23日
	 */
	@ResponseBody
	@RequestMapping("selectPrivilegeList")
	public String selectPrivilegeList(HttpServletRequest request){
		String name = StringUtils.isNotBlank(request.getParameter("name"))?request.getParameter("name"):"";
		String currentPage = StringUtils.isNotBlank(request.getParameter("currentPage"))?request.getParameter("currentPage"):"1";
		String pagesize = StringUtils.isNotBlank(request.getParameter("pagesize"))?request.getParameter("pagesize"):"5";
		int begin = (Integer.parseInt(currentPage)-1)*Integer.parseInt(pagesize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", begin);
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("name", name);
		List<Map<String, Object>> list = privilegeService.findAll(map);
		return JSON.toJSONString(list);
	}
	
	
	/**************************************************角色**********************************************************/
	
	/**
	 * Describe: 角色删除
	 * MrShuai 2016年8月23日
	 */
	@RequestMapping("removeRole")
	public String removeRole(HttpServletRequest request){
		
		return "";
	}
	
	/**
	 * Describe: 角色权限删除
	 * MrShuai 2016年8月23日
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping("removePriFromRole")
	public String removePriFromRole(HttpServletRequest request) throws UnsupportedEncodingException{
		String privilegesList = new String(request.getParameter("privilegesList").getBytes("iso-8859-1"), "utf-8");
		String roleid = request.getParameter("roleid");
		JSONArray js = JSONArray.parseArray(privilegesList);
		JSONObject j = new JSONObject();
		StringBuffer sb = new StringBuffer();
		for (Object obj : js) {
			j = (JSONObject) obj;
			String s = j.getString("id");
			sb.append(","+s);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleid", Integer.valueOf(roleid));
		map.put("privilegeid", sb.toString().substring(1));
		
		return ""+roleService.removePriFromRole(map);
	}
	
	/**
	 * Describe: 角色权限添加
	 * MrShuai 2016年8月23日
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping("addPriFroRole")
	public String addPriFroRole(HttpServletRequest request){
		String priIds = StringUtils.isNotBlank(request.getParameter("priIds"))?request.getParameter("priIds"):"";
		String roleid = StringUtils.isNotBlank(request.getParameter("roleid"))?request.getParameter("roleid"):"";
		String[] pri = priIds.split(",");
		List<Integer> role_privileges = this.roleService.findPrivilegesByRole(Integer.parseInt(roleid));
		List<Integer> cur_parent_privileges = this.roleService.findPrivilegesByRole(Integer.parseInt(roleid));
		List<Map<String, Object>> role_pri = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < pri.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleid", Integer.valueOf(roleid));
			map.put("priid", Integer.valueOf(pri[i]));
			role_pri.add(map);
		}
		int l = roleService.addPriFroRole(role_pri);
		return ""+l;
	}
	
	/**
	 * Describe: 角色修改根据id查询方法
	 * MrShuai 2016年8月23日
	 */
	@ResponseBody
	@RequestMapping("selectRoleById")
	public String selectRoleById(HttpServletRequest request){
		String roleid = request.getParameter("id");
		Role role = roleService.selectByPrimaryKey(Integer.parseInt(roleid));
		return JSON.toJSONString(role);
	}
	/**
	 * Describe: 根据条件判断角色修改或角色新增
	 * MrShuai 2016年8月23日
	 */
	@ResponseBody
	@RequestMapping("updateOrInsertRole")
	public String updateRole(HttpServletRequest request){
		String userName = (String) request.getSession().getAttribute("userName");
		String param = StringUtils.isNotBlank(request.getParameter("param"))?request.getParameter("param"):"";
		JSONObject json = JSONObject.parseObject(param);
		int len = 0;
		Role role = new Role();
		role.setName(json.getString("name"));
		if(StringUtils.isNotBlank(json.getString("id"))){
			role.setId(json.getInteger("id"));
			len = roleService.updateRole(role);
		}else{
			role.setCode("");
			role.setCreateBy(userName);
			role.setCreateTime(new Date());
			len = roleService.insertRole(role);
		}
		
		return ""+len;
	}
	/**
	 * Describe: 角色跳转到列表页面
	 * MrShuai 2016年8月23日
	 */
	@RequestMapping("roleList")
	public String roleList(HttpServletRequest request){
		return "admin/role/role_list";
	}
	/**
	 * Describe: 角色树形列表查询方法
	 * MrShuai 2016年8月23日
	 */
	@ResponseBody
	@RequestMapping("selectRoleList")
	public String selectRoleList(HttpServletRequest request){
		List<Map<String, Object>> list = roleService.findRoleList(null);
		System.out.println(list);
		return JSON.toJSONString(list);
	}
	
	/**
	 * Describe: 角色拥有权限列表查询方法
	 * MrShuai 2016年8月23日
	 */
	@ResponseBody
	@RequestMapping("selectPriListForRole")
	public String selectPriListForRole(HttpServletRequest request){
		String isHave = StringUtils.isNotBlank(request.getParameter("isHave"))?request.getParameter("isHave"):"";
		String roleid = StringUtils.isNotBlank(request.getParameter("roleid"))?request.getParameter("roleid"):"";
		String currentPage = StringUtils.isNotBlank(request.getParameter("currentPage"))?request.getParameter("currentPage"):"1";
		String pagesize = StringUtils.isNotBlank(request.getParameter("pagesize"))?request.getParameter("pagesize"):"10";
		int begin = (Integer.parseInt(currentPage)-1)*Integer.parseInt(pagesize);
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		int total = 0;
		switch (isHave) {
		case "have":
			map.put("roleid", roleid);
			total = privilegeService.countByParam(map);
			list = privilegeService.findAll(map);
			break;
		case "nothave":
			map.put("roleid", roleid);
			map.put("position", "out");
			total = privilegeService.countByParam(map);
			map.put("begin", begin);
			map.put("pagesize", Integer.parseInt(pagesize));
			list = privilegeService.findAll(map);
			break;
		case "":
			total = privilegeService.countByParam(map);
			map.put("begin", begin);
			map.put("pagesize", Integer.parseInt(pagesize));
			list = privilegeService.findAll(map);
		}
		result.put("rows", list);
		result.put("total", total);
		return JSON.toJSONString(result);
	}

}
