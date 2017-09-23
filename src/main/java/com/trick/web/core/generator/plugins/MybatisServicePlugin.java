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
import org.springframework.stereotype.Service;

public class MybatisServicePlugin extends BizExtendPlugin {
	
	/**
	 * Describe: 添加引用
	 * Administrator 2017年6月24日
	 */
	@Override
	public void commonImportGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		
	}
	
	/**
	 * Describe: 名字转换
	 * MrShuai 2017年6月24日
	 */
	@Override
	public void commonNameFormatGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		
	}
	
	/**
	 * Describe: 创建Java文件 - 业务接口
	 * MrShuai 2017年6月24日
	 */
	public void createInterfaceGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		
	}

	/**
	 * Describe: 创建Java文件 - 实现类
	 * MrShuai 2017年6月24日
	 */
	@Override
	public void createClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String tableName, List<GeneratedJavaFile> files) {
		//添加类注释
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		topLevelClass.addJavaDocLine("/**\n * Describe:" + tableName + "业务处理接口\n * @author " + userName + "\n * @since " + javaVersion + "\n * @Date "+new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+" */");
		String tableNameService= null;
		//添加类名称及注解
		if (enableAnnotation) {
			tableNameService = tableName.substring(0, 1).toLowerCase() + tableName.substring(1, tableName.length());
			topLevelClass.addAnnotation("@Service(name=\""+ tableNameService + "Service\")");
		}
		topLevelClass.addSuperInterface(new FullyQualifiedJavaType(tableNameService+"Service"));
		//添加方法
		Field field = new Field();
        field.setName("userTDao"); // 设置变量名
        topLevelClass.addImportedType("org.springframework.stereotype.userTService");
        field.setType(new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType())); // 类型
        field.setVisibility(JavaVisibility.PRIVATE);
        if (enableAnnotation) {
            field.addAnnotation("@Resource");
        }
        topLevelClass.addField(field);
		topLevelClass.addMethod(saveModel(introspectedTable, tableName));
		topLevelClass.addMethod(removeModel(introspectedTable, tableName));
		topLevelClass.addMethod(updateMode(introspectedTable, tableName));
		topLevelClass.addMethod(listPageModel(introspectedTable, tableName));
		topLevelClass.addMethod(listDataModelByParam(introspectedTable, tableName));
		topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Controller"));
		topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMapping"));
		topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMethod"));
		topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.ResponseBody"));
		
		GeneratedJavaFile file = new GeneratedJavaFile(topLevelClass, "F://workspace//trick//src//main/java",new DefaultJavaFormatter());
        files.add(file);
	}

	/**
	 * Describe: 添加全局对象
	 * MrShuai 2017年6月24日
	 */
	@Override
	public void createFieldGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String tableName) {
		
	}
	
	/**
	 * Describe: 创建方法
	 * MrShuai 2017年6月24日
	 */
	@Override
	public void createMethodGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String tableName) {
		
	}

	/**
	 * Describe: 添加类注释
	 * MrShuai 2017年6月24日
	 */
	@Override
	public void commentjavaFileGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String tableName) {
		
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
		method.setName("save"+tableName);
		method.addAnnotation("@Override");
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
		method.setName("remove"+tableName);
		method.addAnnotation("@Override");
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
		method.setName("listPage"+tableName);
		method.addAnnotation("@Override");
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
		method.setName("listData"+tableName+"ByParam");
		method.addAnnotation("@Override");
		method.setVisibility(JavaVisibility.PUBLIC);
		// 添加返回值
		method.setReturnType(new FullyQualifiedJavaType("String"));
		StringBuilder sb = new StringBuilder();
		sb.append("return null;");
		method.addBodyLine(sb.toString());
		return method;
	}

}
