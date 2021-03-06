package com.trick.web.core.generator.plugins;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class MybatisGeneratorPlugin extends PluginAdapter {
    private String servicePack;
    private String controllerPack;
    private String serviceImplPack;
    private FullyQualifiedJavaType controllerType;
    private FullyQualifiedJavaType serviceType;
    private String viewPath;

	@Override
	public boolean validate(List<String> arg0) {
		BizExtendPlugin.initialized(properties);

		controllerPack = properties.getProperty("controllerPackage");
		controllerType = new FullyQualifiedJavaType(controllerPack);
		servicePack = properties.getProperty("servicePackage");
		serviceType = new FullyQualifiedJavaType(servicePack);
		viewPath = properties.getProperty("viewPath");
		return true;
	}
	@Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		List<GeneratedJavaFile> files = new ArrayList<GeneratedJavaFile>();
		String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();
		TopLevelClass controllerClass = new TopLevelClass(controllerType + "."+domainObjectName+ "Controller");
		TopLevelClass serviceClass = new TopLevelClass(serviceType + "."+domainObjectName+ "ServiceImpl");
		
		//创建 Controller
		BizExtendPlugin controllerPlugin = new MybatisControllerPlugin();
		controllerPlugin.createClassGenerated(controllerClass, introspectedTable, domainObjectName, files);
		//创建 Service
		BizExtendPlugin servicePlugin = new MybatisServicePlugin();
		servicePlugin.createClassGenerated(serviceClass, introspectedTable, domainObjectName, files);
		FreeMarker freemarker = new FreeMarker();
		freemarker.printTest(domainObjectName, introspectedTable, viewPath);
		return files;
	}

}
