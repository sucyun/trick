package com.trick.web.core.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.NullProgressCallback;

public class SimpleMBGenerator {

	public static void main(String[] args) throws Exception {
		generate("user_t", "UserT", "/run/idea_space/trick/src/main/");
		
//		generate2("F:/workspace/trick/src/main/");
	}

	public static void generate(String TableName, String DomainObjectName, String path) throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File(path + "resources/generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		//获取 generatorConfig.xml 配置文件内容
		Context context = config.getContexts().get(0);
		Calendar c = Calendar.getInstance();
		//设置ID 对应 <context id="DB2Tables" targetRuntime="MyBatis3"> 中的id
		context.setId(String.valueOf(c.getTime().getTime()));
		
		//获取 模型 <javaModelGenerator></javaModelGenerator>标签
		JavaModelGeneratorConfiguration javaModelGenerator = context.getJavaModelGeneratorConfiguration();
		javaModelGenerator.setTargetProject("src/main/java");
		javaModelGenerator.setTargetPackage("com.trick.biz.mvc.t.model");
		
		//获取 映射xml文件 <sqlMapGenerator></sqlMapGenerator>标签
		SqlMapGeneratorConfiguration sqlMapGenerator = context.getSqlMapGeneratorConfiguration();
		sqlMapGenerator.setTargetProject("src/main/java");
		sqlMapGenerator.setTargetPackage("com.trick.biz.mvc.t.dao.mapping");
		
		//获取 生成DAO <javaClientGenerator></javaClientGenerator>标签
		JavaClientGeneratorConfiguration javaClientGenerator = context.getJavaClientGeneratorConfiguration();
		javaClientGenerator.setTargetProject("src/main/java");
		javaClientGenerator.setTargetPackage("com.trick.biz.mvc.t.dao");
		
		
//		List<TableConfiguration> table = context.getTableConfigurations();
//		table.get(0).setDomainObjectName(DomainObjectName);
//		table.get(0).setTableName(TableName);
//		table.get(0).setGeneratedKey(new GeneratedKey("id","MySql",false,"pre"));
		
//		config.addContext(context);
		
		Configuration conf = new Configuration();
		conf.addContext(context);
		
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(new NullProgressCallback());
	}
	public static void generate_new(String TableName, String DomainObjectName, String path) throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		// String path = Class.class.getClass().getResource("/").getPath();
		File configFile = new File(path + "resources/generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
//		Configuration config = new Configuration();
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		//获取 generatorConfig.xml 配置文件内容
//		Context context = config.getContexts().get(0);
		Context context = new Context(ModelType.FLAT);
		Calendar c = Calendar.getInstance();
		//设置ID 对应 <context id="DB2Tables" targetRuntime="MyBatis3"> 中的id
		context.setId(String.valueOf(c.getTime().getTime()));
		
		//获取 模型 <javaModelGenerator></javaModelGenerator>标签
//		JavaModelGeneratorConfiguration javaModelGenerator = context.getJavaModelGeneratorConfiguration();
		JavaModelGeneratorConfiguration javaModelGenerator = new JavaModelGeneratorConfiguration();
		javaModelGenerator.setTargetProject("src/main/java");
		javaModelGenerator.setTargetPackage("com.trick.biz.mvc.t.model");
		
		//获取 映射xml文件 <sqlMapGenerator></sqlMapGenerator>标签
//		SqlMapGeneratorConfiguration sqlMapGenerator = context.getSqlMapGeneratorConfiguration();
		SqlMapGeneratorConfiguration sqlMapGenerator = new SqlMapGeneratorConfiguration();
		sqlMapGenerator.setTargetProject("src/main/java");
		sqlMapGenerator.setTargetPackage("com.trick.biz.mvc.t.dao.mapping");
		
		//获取 生成DAO <javaClientGenerator></javaClientGenerator>标签
//		JavaClientGeneratorConfiguration javaClientGenerator = context.getJavaClientGeneratorConfiguration();
		JavaClientGeneratorConfiguration javaClientGenerator = new JavaClientGeneratorConfiguration();
		javaClientGenerator.setTargetProject("src/main/java");
		javaClientGenerator.setTargetPackage("com.trick.biz.mvc.t.dao");
		
		
		TableConfiguration table = new TableConfiguration(context);
		table.setDomainObjectName(DomainObjectName);
		table.setTableName(TableName);
		table.setGeneratedKey(new GeneratedKey("id","MySql",false,"pre"));
		
		context.setJavaModelGeneratorConfiguration(javaModelGenerator);
		context.setSqlMapGeneratorConfiguration(sqlMapGenerator);
		context.setJavaClientGeneratorConfiguration(javaClientGenerator);
		context.addTableConfiguration(table);
//		config.addContext(context);
		
		Configuration conf = new Configuration();
		conf.addContext(context);
		
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(conf, callback, warnings);
		myBatisGenerator.generate(null);
	}
	
	public static void generate2(String path) throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File(path + "resources/generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
}
