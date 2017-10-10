package com.trick.web.core.generator.plugins;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.*;

public class MybatisControllerPlugin extends BizExtendPlugin {
	
	/**
	 * Describe: 添加引用
	 * Administrator 2017年6月24日
	 */
	@Override
	public void commonImportGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		Set<FullyQualifiedJavaType> importTypeSet= new HashSet<FullyQualifiedJavaType>();
		importTypeSet.add(new FullyQualifiedJavaType("org.springframework.stereotype.Controller"));
		importTypeSet.add(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
		importTypeSet.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.ResponseBody"));
		importTypeSet.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMapping"));
		importTypeSet.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMethod"));
		importTypeSet.add(new FullyQualifiedJavaType("javax.servlet.http.HttpServletRequest"));
        importTypeSet.add(new FullyQualifiedJavaType("com.trick.biz.mvc."+domainLowerName+".service."+ domainName +"Service"));
        importTypeSet.add(new FullyQualifiedJavaType("com.trick.biz.mvc."+domainLowerName+".model."+ domainName));
		topLevelClass.addImportedTypes(importTypeSet);
	}

	/**
	 * Describe: 名字转换
	 * MrShuai 2017年6月24日
	 */
	@Override
	public void commonNameFormatGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		
	}

	/**
	 * Describe: 创建Java文件 - 控制器
	 * MrShuai 2017年6月24日
	 */
	@Override
	public void createClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String domainObjectName, List<GeneratedJavaFile> files) {
		//1.添加引用
		commonImportGenerated(topLevelClass, introspectedTable);
		//2.添加类注释
		commentjavaFileGenerated(topLevelClass, introspectedTable, domainObjectName);
		//3.定义类修饰权限
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		String tableName_lower = domainObjectName.substring(0, 1).toLowerCase() + domainObjectName.substring(1, domainObjectName.length());
		//4.添加注解
		if (enableAnnotation) {
			topLevelClass.addAnnotation("@Controller");
			String tableNameController = tableName_lower;
			topLevelClass.addAnnotation("@RequestMapping(value =\"/" + tableNameController + "/\")");
		}
		//5.添加全局变量(Spring注入Service)
		createFieldGenerated(topLevelClass, introspectedTable, domainObjectName);
		//6.添加方法
		createMethodGenerated(topLevelClass, introspectedTable, domainObjectName);
		//7.添加引用
		commonImportGenerated(topLevelClass, introspectedTable);

		GeneratedJavaFile file = new GeneratedJavaFile(topLevelClass, workspace +"/src/main/java",new DefaultJavaFormatter());
        files.add(file);
	}
	
	/**
	 * Describe: 添加全局对象(Spring注入Service)
	 * MrShuai 2017年6月24日
	 */
	@Override
	public void createFieldGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String domainObjectName) {
		Field field = new Field();
        field.setName( domainLowerName +"Service"); // 设置变量名
        field.setType(new FullyQualifiedJavaType(domainObjectName +"Service")); // 类型
        field.setVisibility(JavaVisibility.PRIVATE);
        if (enableAnnotation) {
            field.addAnnotation("@Autowired");
        }
        topLevelClass.addField(field);
	}

	/**
	 * Describe: 创建方法
	 * MrShuai 2017年6月24日
	 */
	@Override
	public void createMethodGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String domainObjectName) {
		topLevelClass.addMethod(saveModel(introspectedTable, domainObjectName));
		topLevelClass.addMethod(removeModel(introspectedTable, domainObjectName));
		topLevelClass.addMethod(updateMode(introspectedTable, domainObjectName));
		topLevelClass.addMethod(listPageModel(introspectedTable, domainObjectName));
		topLevelClass.addMethod(listDataModelByParam(introspectedTable, domainObjectName));
	}

	/**
	 * Describe: 添加类注释
	 * MrShuai 2017年6月24日
	 */
	@Override
	public void commentjavaFileGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String domainObjectName) {
		topLevelClass.addJavaDocLine("/**\n * Describe:" + domainObjectName + "控制器\n * @author " + userName + "\n * @since " + javaVersion + "\n * @Date "+new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+" */");
	}

	/**
	 * Describe: 添加方法注释
	 * MrShuai 2017年6月24日
	 */
	@Override
	public void commentMethodGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		
	}

	/**
	 * Describe: 保存对象方法
	 * MrShuai 2017年8月11日
	 */
	protected Method saveModel(IntrospectedTable introspectedTable, String domainObjectName) {
		Method method = new Method();
		String t = "\n\t\t";
		method.addJavaDocLine("/**\n\t* Describe: 保存方法\n\t* "+userName+" "+new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"\n\t*/");
		method.addAnnotation("@ResponseBody");
		method.addAnnotation("@RequestMapping(value = \"save"+ domainObjectName +"\" , method = RequestMethod.POST)");
		method.setName("save"+ domainObjectName);
		method.setVisibility(JavaVisibility.PUBLIC);
		// 添加返回值
		method.setReturnType(new FullyQualifiedJavaType("String"));
		method.addParameter(new Parameter(new FullyQualifiedJavaType("HttpServletRequest"),"request"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType(domainObjectName),domainFirstLowerName));
		StringBuilder sb = new StringBuilder();
		sb.append("int l = "+domainLowerName+"Service.save"+domainObjectName+"("+domainLowerName+");");
		sb.append(t+"return l+\"\";");
		method.addBodyLine(sb.toString());
		return method;
	}
	
	/**
	 * Describe: 删除对象方法
	 * MrShuai 2017年8月11日
	 */
	protected Method removeModel(IntrospectedTable introspectedTable, String domainObjectName) {
	    Method method = new Method();
	    method.addJavaDocLine("/**\n\t* Describe: 删除方法\n\t* "+userName+" "+new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"\n\t*/");
		method.addAnnotation("@ResponseBody");
		method.addAnnotation("@RequestMapping(value = \"remove"+ domainObjectName +"\" , method = RequestMethod.GET)");
		method.setName("remove"+ domainObjectName);
		method.setVisibility(JavaVisibility.PUBLIC);
		// 添加返回值
		method.setReturnType(new FullyQualifiedJavaType("String"));
		method.addParameter(new Parameter(new FullyQualifiedJavaType("HttpServletRequest"),"request"));
		StringBuilder sb = new StringBuilder();
		sb.append("return null;");
		method.addBodyLine(sb.toString());
		return method;
	}
	
	/**
	 * Describe: 更新修改对象方法
	 * MrShuai 2017年8月11日
	 */
	protected Method updateMode(IntrospectedTable introspectedTable, String domainObjectName) {
		Method method = new Method();
		method.addJavaDocLine("/**\n\t* Describe: 修改方法\n\t* "+userName+" "+new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"\n\t*/");
		method.addAnnotation("@ResponseBody");
		method.addAnnotation("@RequestMapping(value = \"update"+ domainObjectName +"\" , method = RequestMethod.GET)");
		method.setName("update"+ domainObjectName);
		method.setVisibility(JavaVisibility.PUBLIC);
		// 添加返回值
		method.setReturnType(new FullyQualifiedJavaType("String"));
		method.addParameter(new Parameter(new FullyQualifiedJavaType("HttpServletRequest"),"request"));
		StringBuilder sb = new StringBuilder();
		sb.append("return null;");
		method.addBodyLine(sb.toString());
		return method;
	}
	
	/**
	 * Describe: 对象列表跳转
	 * MrShuai 2017年8月11日
	 */
	protected Method listPageModel(IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method();
		method.addJavaDocLine("/**\n\t* Describe: 列表页跳转\n\t* "+userName+" "+new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"\n\t*/");
		method.addAnnotation("@RequestMapping(value = \"listPage"+tableName+"\" , method = RequestMethod.GET)");
		method.setName("listPage"+tableName);
		method.setVisibility(JavaVisibility.PUBLIC);
		// 添加返回值
		method.setReturnType(new FullyQualifiedJavaType("String"));
		method.addParameter(new Parameter(new FullyQualifiedJavaType("HttpServletRequest"),"request"));
		StringBuilder sb = new StringBuilder();
		sb.append("return null;");
		method.addBodyLine(sb.toString());
		return method;
	}
	
	/**
	 * Describe: 根据条件查询对象列表方法
	 * MrShuai 2017年8月11日
	 */
	protected Method listDataModelByParam(IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method();
		method.addJavaDocLine("/**\n\t* Describe: 列表页数据\n\t* "+userName+" "+new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"\n\t*/");
		method.addAnnotation("@ResponseBody");
		method.addAnnotation("@RequestMapping(value = \"listData"+tableName+"ByParam\" , method = RequestMethod.GET)");
		method.setName("listData"+tableName+"ByParam");
		method.setVisibility(JavaVisibility.PUBLIC);
		// 添加返回值
		method.setReturnType(new FullyQualifiedJavaType("String"));
		method.addParameter(new Parameter(new FullyQualifiedJavaType("HttpServletRequest"),"request"));
		StringBuilder sb = new StringBuilder();
		sb.append("return null;");
		method.addBodyLine(sb.toString());
		return method;
	}
}
