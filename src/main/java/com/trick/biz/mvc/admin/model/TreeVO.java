package com.trick.biz.mvc.admin.model;

import java.util.ArrayList;
import java.util.List;

public class TreeVO {
	private Integer id;//主键标识
	private String text;//管理模块名称
	private String url;//权限对应模块的url路径
	private Integer checked;//模块状态 1 代表启用 0 代表禁用
	private String explan;//解释描述
	private Integer statu;//模块状态 1 代表启用 0 代表禁用
	private List<TreeVO> children;//通过子父节点遍历
	private Integer parentId;//父节点的id
	
	public static TreeVO getInstance(Privilege privilege){
		TreeVO treeVO = new TreeVO();
		treeVO.setId(privilege.getId());
		treeVO.setText(privilege.getName());
		treeVO.setExplan(privilege.getExplan());
		treeVO.setParentId(privilege.getParentId());
		treeVO.setUrl(privilege.getUrl());
		treeVO.setChildren(new ArrayList<TreeVO>());
    	return treeVO;
    }
	public static TreeVO getInstance(Module privilege){
		TreeVO treeVO = new TreeVO();
		treeVO.setId(privilege.getId());
		treeVO.setText(privilege.getName());
		treeVO.setExplan(privilege.getExplan());
		treeVO.setParentId(privilege.getParentId());
		treeVO.setUrl(privilege.getUrl());
		treeVO.setChildren(new ArrayList<TreeVO>());
    	return treeVO;
    }
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getChecked() {
		return checked;
	}
	public void setChecked(Integer checked) {
		this.checked = checked;
	}
	public String getExplan() {
		return explan;
	}
	public void setExplan(String explan) {
		this.explan = explan;
	}
	public Integer getStatu() {
		return statu;
	}
	public void setStatu(Integer statu) {
		this.statu = statu;
	}
	public List<TreeVO> getChildren() {
		return children;
	}
	public void setChildren(List<TreeVO> children) {
		this.children = children;
	}
	public void setParentId(Integer parentId){
		this.parentId = parentId;
	}
	public Integer getParentId(){
		return parentId;
	}
	
	
	
}
