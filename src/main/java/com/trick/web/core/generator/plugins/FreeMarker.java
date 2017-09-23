package com.trick.web.core.generator.plugins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.config.Context;

import com.trick.web.core.entity.MBGeneratorModel;


public class FreeMarker {

	private FreeMarkerUtil util;
	protected Context context;

	public void printTest(String parameter,IntrospectedTable introspectedTable,String viewPath){
		List<IntrospectedColumn> introspectedColumns = getColumnsInThisClass(introspectedTable);
		// 表注释
		String remark = introspectedTable.getRemarks();
		// 表字段
		List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
		//字段名
		List<String> remarks = new ArrayList<String>();
		List<Comlumn> columnMap = new ArrayList<Comlumn>();

		// 遍历表，将字段与对应的注释放入到bean中
		for(IntrospectedColumn introspectedColumn:introspectedColumns){
			if(StringUtils.isEmpty(introspectedColumn.getRemarks())){
				remarks.add(introspectedColumn.getJavaProperty());
				columnMap.add(new Comlumn(introspectedColumn.getJavaProperty(), introspectedColumn.getJavaProperty()));
			}else{
				columnMap.add(new Comlumn(introspectedColumn.getJavaProperty(), introspectedColumn.getRemarks()));
				remarks.add(introspectedColumn.getRemarks());
			}
		}
		//字段
		List<String> column = new ArrayList<String>();
		for(IntrospectedColumn ic:columns){
			column.add(ic.getJavaProperty());
		}

		util = new FreeMarkerUtil();
		viewPath = viewPath.replace(".", "/");
		Map<String, Object> root = new HashMap<String, Object>();
//		Map<String,List<MBGeneratorModel>> map = MBGeneratorModelServiceImpl.integration();
		String s1 = parameter.replaceAll("[A-Z]", " $0");
		String[] s2 = s1.split(" ");
		String tableName = "";
		for(int i=0;i<s2.length;i++){
			if(!StringUtils.equals(s2[i], "")){
				tableName += s2[i].substring(0,1).toLowerCase()+s2[i].substring(1,s2[i].length())+"_";
			}
		}
//		if(map.containsKey(tableName.substring(0,tableName.length()-1))){
//			List<MBGeneratorModel> list = new ArrayList<MBGeneratorModel>();
//			list = map.get(tableName.substring(0,tableName.length()-1));
//			if(list==null){
//				root.put("columnTypeName", null);
//			}else{
//				for(int i=0; i<list.size();i++){
//					if(!StringUtils.isEmpty(introspectedColumns.get(i).getRemarks())){
//						list.get(i).setDictName(introspectedColumns.get(i).getRemarks());
//					}
//					else{
//						list.get(i).setDictName(introspectedColumns.get(i).getJavaProperty());
//					}
//				}
//				root.put("columnTypeName", list);
//			}
//		}
//		else{
//			root.put("columnTypeName", null);
//		}
		StringBuilder stringBuilder = new StringBuilder();
		for(String str:column){
			stringBuilder.append(str);
			stringBuilder.append("\",\"");
		}
		// 取得注释列表的第一项
		String selectName = remarks.get(0);
		// 取得字段列表的第一项
		String readonly = column.get(0);
		if(StringUtils.isEmpty(selectName)){
			selectName = column.get(0);
		}
		stringBuilder.setLength(stringBuilder.length()-3);
		// 注释
		root.put("comments", remarks);
		// 名称与字段名的列表
		root.put("columnMap", columnMap);
		// 表名
		String parameterName = parameter.substring(0, 1).toLowerCase()+parameter.substring(1,parameter.length());
		root.put("table", parameterName);
		// 字段名
		root.put("parameter", column);
		// 表头
		root.put("Keycomments", remark);
		// 检索条件
		root.put("selectValue", column.get(0));
		// 检索名称
		root.put("selectName", selectName);
		// 只读
		root.put("readonly",readonly);
		util.fPrint("grid.ftl", viewPath,root, (new StringBuilder(String.valueOf(parameter))).append("Grid.jsp").toString());
		util.fPrint("edit.ftl", viewPath,root, (new StringBuilder(String.valueOf(parameter))).append("Edit.jsp").toString());
	}
	private List<IntrospectedColumn> getColumnsInThisClass(IntrospectedTable introspectedTable) {
		List<IntrospectedColumn> introspectedColumns;
		if (includePrimaryKeyColumns(introspectedTable)) {
			if (includeBLOBColumns(introspectedTable)) {
				introspectedColumns = introspectedTable.getAllColumns();
			} else {
				introspectedColumns = introspectedTable.getNonBLOBColumns();
			}
		} else {
			if (includeBLOBColumns(introspectedTable)) {
				introspectedColumns = introspectedTable.getNonPrimaryKeyColumns();
			} else {
				introspectedColumns = introspectedTable.getBaseColumns();
			}
		}

		return introspectedColumns;
	}
	private boolean includeBLOBColumns(IntrospectedTable introspectedTable) {
		return !introspectedTable.getRules().generateRecordWithBLOBsClass() && introspectedTable.hasBLOBColumns();
	}
	private boolean includePrimaryKeyColumns(IntrospectedTable introspectedTable) {
		return !introspectedTable.getRules().generatePrimaryKeyClass() && introspectedTable.hasPrimaryKeyColumns();
	}
}
