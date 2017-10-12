package com.trick.biz.mvc.admin.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Admin implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;//主键标识
    private String code;//角色编码
    private String name;//管理员名称
    private String password;//管理员密码
    private Date createtime;//创建时间
    private String createby;//创建人
    private Integer status;//1 代表启用 0代表禁用
    private List<Role> role;//管理员拥用的角色

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}
    
}