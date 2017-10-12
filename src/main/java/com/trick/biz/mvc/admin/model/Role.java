package com.trick.biz.mvc.admin.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 角色
 * @author MrShuai
 */
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;//主键标识
    private String name;//角色名称
    private String code;//角色编码
	private Date createTime;//创建时间
	private String createBy;//创建人
	private Integer status;//1 代表启用 0代表禁用
    private List<Privilege> privilege;//角色拥有权限

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

//	public String getCreateTime() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		return createTime==null?"":sdf.format(createTime);
//	}
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Privilege> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(List<Privilege> privilege) {
		this.privilege = privilege;
	}
    
}