package com.trick.web.core.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.NullProgressCallback;

public class SimpleMBGenerator {

    public static void main(String[] args) throws Exception {
        generate("user_t", "Student", System.getProperty("user.dir"));
    }

    /**
     * @param tableName        表名
     * @param domainObjectName 实体类名称
     * @param path             工程路径
     * @throws Exception Configuration.parseConfiguration 读取配置文件时需要
     */
    public static void generate(String tableName, String domainObjectName, String path) throws Exception {
        String domainLowerName = domainObjectName.toLowerCase();
        String domainFirstLowerName = domainObjectName.substring(0, 1).toLowerCase() + domainObjectName.substring(1, domainObjectName.length());
        List<String> warnings = new ArrayList<>();
        File configFile = new File(path + "/src/main/resources/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        //获取 generatorConfig.xml 配置文件内容
        Context context = config.getContexts().get(0);
        Calendar c = Calendar.getInstance();
        //设置ID 对应 <context id="DB2Tables" targetRuntime="MyBatis3"> 中的id
        context.setId(String.valueOf(c.getTime().getTime()) + tableName);

        String targetProject = "src/main/java";
        String targetPackage = "com.trick.biz.mvc." + domainLowerName;

        String viewPath = path+"/src/main/webapp/WEB-INF/views/" + domainLowerName;
        //获取 模型 <javaModelGenerator></javaModelGenerator>标签
        JavaModelGeneratorConfiguration javaModelGenerator = context.getJavaModelGeneratorConfiguration();
        javaModelGenerator.setTargetProject(targetProject);
        javaModelGenerator.setTargetPackage(targetPackage + ".model");

        //获取 映射xml文件 <sqlMapGenerator></sqlMapGenerator>标签
        SqlMapGeneratorConfiguration sqlMapGenerator = context.getSqlMapGeneratorConfiguration();
        sqlMapGenerator.setTargetProject(targetProject);
        sqlMapGenerator.setTargetPackage(targetPackage + ".dao.mapping");

        //获取 生成DAO <javaClientGenerator></javaClientGenerator>标签
        JavaClientGeneratorConfiguration javaClientGenerator = context.getJavaClientGeneratorConfiguration();
        javaClientGenerator.setTargetProject(targetProject);
        javaClientGenerator.setTargetPackage(targetPackage + ".dao");

        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("com.trick.web.core.generator.plugins.MybatisGeneratorPlugin");
        pluginConfiguration.addProperty("domainName", domainObjectName);
        pluginConfiguration.addProperty("domainLowerName", domainLowerName);
        pluginConfiguration.addProperty("domainFirstLowerName", domainFirstLowerName);
        pluginConfiguration.addProperty("controllerPackage", targetPackage + ".controller");
        pluginConfiguration.addProperty("servicePackage", targetPackage + ".service.impl");
        pluginConfiguration.addProperty("viewPath", viewPath);
        context.addPluginConfiguration(pluginConfiguration);

        List<TableConfiguration> table = context.getTableConfigurations();

        table.get(0).setTableName(tableName);
        table.get(0).setDomainObjectName(domainObjectName);
//        table.get(0).setGeneratedKey(new GeneratedKey("id", "MySql", true, "post"));

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(new NullProgressCallback());
    }
}
