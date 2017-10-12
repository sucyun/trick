package com.trick.biz.mvc.admin.model;

import java.util.Date;
import java.util.List;

public class Module {
    private Integer id;//主键标识
    private String code;//权限编码
    private String name;//管理模块名称
    private Integer parentId;//模块的子父节点
    private String url;//权限对应模块的url路径
    private String explan;//解释描述
    private Date createtime;//创建时间
	private String createby;//创建人
    private Integer status;//模块状态 1 代表启用 0 代表禁用
    private List<Module> module;//通过子父节点遍历
    
    public static Module getInstance(Privilege privilege){
    	Module mo = new Module();
    	mo.setId(privilege.getId());
    	mo.setCode(privilege.getCode());
    	mo.setName(privilege.getName());
    	mo.setExplan(privilege.getExplan());
    	mo.setParentId(privilege.getParentId());
    	return mo;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public String getName() {
        return name;
    }
	
	public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getExplan() {
        return explan;
    }

    public void setExplan(String explan) {
        this.explan = explan == null ? null : explan.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public List<Module> getModule() {
		return module;
	}

	public void setModule(List<Module> module) {
		this.module = module;
	}


    
}