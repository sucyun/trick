package com.trick.web.core.generator.plugins;

import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * Describe:业务层代码自动创建插件
 * 
 * @author MrShuai 2017年5月26日
 */
abstract class BizExtendPlugin {

	static String userName;
	static String javaVersion;
	static String workspace;
	boolean enableAnnotation = true;
    static String domainLowerName;
    static String domainFirstLowerName;
    static String domainName;
	
	/**
	 * Describe: 工厂方法 MrShuai 2017年5月26日
	 */
	static void initialized(Properties properties){
		workspace = System.getProperty("user.dir");
		userName = System.getProperty("user.name");
        javaVersion = System.getProperty("java.version");
        domainLowerName = properties.getProperty("domainLowerName");
        domainFirstLowerName = properties.getProperty("domainFirstLowerName");
        domainName = properties.getProperty("domainName");
	}

	/**
	 * Describe: 添加引用 MrShuai 2017年5月26日
	 */
	abstract void commonImportGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable);

	/**
	 * Describe: 名字转换 MrShuai 2017年5月26日
	 */
	abstract void commonNameFormatGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable);

	/**
	 * Describe: 创建Java文件 MrShuai 2017年5月26日
	 */
	abstract void createClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String tableName, List<GeneratedJavaFile> files);

	/**
	 * Describe: 创建全局变量  MrShuai 2017年5月26日
	 */
	abstract void createFieldGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String tableName);

	/**
	 * Describe: 创建方法 MrShuai 2017年5月26日
	 */
	abstract void createMethodGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String tableName);

	/**
	 * Describe: 添加类注释 MrShuai 2017年5月26日
	 */
	abstract void commentjavaFileGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String tableName);

	/**
	 * Describe: 添加方法注释 MrShuai 2017年5月26日
	 */
	abstract void commentMethodGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable);

}
