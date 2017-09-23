package com.trick.web.core.generator.plugins;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class MybatisControllerPlugin extends BizExtendPlugin {
	
	/**
	 * Describe: 添加引用
	 * Administrator 2017年6月24日
	 */
	@Override
	public void commonImportGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Controller"));
		topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
		topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.ResponseBody"));
		topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMapping"));
		topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMethod"));
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
	public void createClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String tableName, List<GeneratedJavaFile> files) {
		//1.添加引用
		commonImportGenerated(topLevelClass, introspectedTable);
		//2.添加类注释
		commentjavaFileGenerated(topLevelClass, introspectedTable, tableName);
		//3.定义类修饰权限
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		String tableName_lower = tableName.substring(0, 1).toLowerCase() + tableName.substring(1, tableName.length());
		//4.添加注解
		if (enableAnnotation) {
			topLevelClass.addAnnotation("@Controller");
			String tableNameController = tableName_lower;
			topLevelClass.addAnnotation("@RequestMapping(value =\"/" + tableNameController + "\")");
		}
		//5.添加全局变量(Spring注入Service)
		createFieldGenerated(topLevelClass, introspectedTable, tableName);
		//6.添加方法
		createMethodGenerated(topLevelClass, introspectedTable, tableName);
		//7.添加引用
		commonImportGenerated(topLevelClass, introspectedTable);
		//8.添加引用
		GeneratedJavaFile file = new GeneratedJavaFile(topLevelClass, workurl+"\\src\\main\\java",new DefaultJavaFormatter());
        files.add(file);
	}
	
	/**
	 * Describe: 添加全局对象(Spring注入Service)
	 * MrShuai 2017年6月24日
	 */
	@Override
	public void createFieldGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String tableName) {
		Field field = new Field();
        field.setName(tableName.substring(0, 1).toLowerCase() + tableName.substring(1, tableName.length())+"Service"); // 设置变量名
        topLevelClass.addImportedType(new FullyQualifiedJavaType("com.trick.biz.mvc."+tableName+".service"));
        field.setType(new FullyQualifiedJavaType(tableName+".service")); // 类型
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
	public void createMethodGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String tableName) {
		topLevelClass.addMethod(saveModel(introspectedTable, tableName));
		topLevelClass.addMethod(removeModel(introspectedTable, tableName));
		topLevelClass.addMethod(updateMode(introspectedTable, tableName));
		topLevelClass.addMethod(listPageModel(introspectedTable, tableName));
		topLevelClass.addMethod(listDataModelByParam(introspectedTable, tableName));
	}

	/**
	 * Describe: 添加类注释
	 * MrShuai 2017年6月24日
	 */
	@Override
	public void commentjavaFileGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String tableName) {
		topLevelClass.addJavaDocLine("/**\n * Describe:" + tableName + "控制器\n * @author " + userName + "\n * @since " + javaVersion + "\n * @Date "+new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+" */");
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
	protected Method saveModel(IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method();
		method.addJavaDocLine("/**\n\t* Describe: 保存方法\n\t* "+userName+" "+new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"\n\t*/");
		method.addAnnotation("@ResponseBody");
		method.addAnnotation("@RequestMapping(value = \"save"+tableName+"\" , method = RequestMethod.POST)");
		method.setName("save"+tableName);
		method.setVisibility(JavaVisibility.PUBLIC);
		// 添加返回值
		method.setReturnType(new FullyQualifiedJavaType("String"));
		StringBuilder sb = new StringBuilder();
		sb.append("return null;");
		method.addBodyLine(sb.toString());
		return method;
	}
	
	/**
	 * Describe: 删除对象方法
	 * MrShuai 2017年8月11日
	 */
	protected Method removeModel(IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method();
		method.addJavaDocLine("/**\n\t* Describe: 删除方法\n\t* "+userName+" "+new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"\n\t*/");
		method.addAnnotation("@ResponseBody");
		method.addAnnotation("@RequestMapping(value = \"remove"+tableName+"\" , method = RequestMethod.GET)");
		method.setName("remove"+tableName);
		method.setVisibility(JavaVisibility.PUBLIC);
		// 添加返回值
		method.setReturnType(new FullyQualifiedJavaType("String"));
		StringBuilder sb = new StringBuilder();
		sb.append("return null;");
		method.addBodyLine(sb.toString());
		return method;
	}
	
	/**
	 * Describe: 更新修改对象方法
	 * MrShuai 2017年8月11日
	 */
	protected Method updateMode(IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method();
		method.addJavaDocLine("/**\n\t* Describe: 修改方法\n\t* "+userName+" "+new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"\n\t*/");
		method.addAnnotation("@ResponseBody");
		method.addAnnotation("@RequestMapping(value = \"update"+tableName+"\" , method = RequestMethod.GET)");
		method.setName("update"+tableName);
		method.setVisibility(JavaVisibility.PUBLIC);
		// 添加返回值
		method.setReturnType(new FullyQualifiedJavaType("String"));
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
		StringBuilder sb = new StringBuilder();
		sb.append("return null;");
		method.addBodyLine(sb.toString());
		return method;
	}
}
