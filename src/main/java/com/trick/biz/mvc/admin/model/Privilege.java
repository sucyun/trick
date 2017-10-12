package com.trick.biz.mvc.admin.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限(模块权限粒度)
 * @author MrShuai
 */
public class Privilege implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;//主键标识
    private String code;//权限编码
    private String explan;//解释描述
    private String url;//权限对应模块的url路径
    private Integer parentId;//模块的子父节点
    private String name;//管理模块名称
    private Date createTime;//创建时间
	private String createBy;//创建人
    private Integer status;//模块状态 1 代表启用 0 代表禁用
//    private List<Menu> menu;//模块按钮权限
    
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
		this.code = code;
	}

	public String getExplan() {
        return explan;
    }

    public void setExplan(String explan) {
        this.explan = explan == null ? null : explan.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

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
	
//	public List<Menu> getMenu() {
//		return menu;
//	}
//
//	public void setMenu(List<Menu> menu) {
//		this.menu = menu;
//	}
	
    
}