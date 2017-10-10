package com.trick.web.core.generator.plugins;

import java.text.SimpleDateFormat;
import java.util.*;
import com.trick.web.core.generator.entity.MethodEntity;
import com.trick.web.core.generator.util.ParameterUtil;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.*;

public class MybatisServicePlugin extends BizExtendPlugin {

	/**
	 * Describe: 添加引用
	 * Administrator 2017年6月24日
	 */
	@Override
	public void commonImportGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		Set<FullyQualifiedJavaType> importTypeSet= new HashSet<FullyQualifiedJavaType>();
		importTypeSet.add(new FullyQualifiedJavaType("javax.annotation.Resource"));
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
	 * Describe: 创建Java文件 - 业务接口
	 * MrShuai 2017年6月24日
	 */
	public void createInterfaceGenerated(IntrospectedTable introspectedTable, String domainName, List<GeneratedJavaFile> files) {

	    Interface iface = new Interface(new FullyQualifiedJavaType("com.trick.biz.mvc."+domainLowerName+".service."+ domainName +"Service"));
        iface.setVisibility(JavaVisibility.PUBLIC);

	    Method save = new Method();
        save.setVisibility(JavaVisibility.PUBLIC);
        save.setName("save"+ domainName);
        save.setReturnType(new FullyQualifiedJavaType("int"));
        save.addParameter(new Parameter(new FullyQualifiedJavaType(domainName),domainLowerName));

        Method remove = new Method();
        remove.setVisibility(JavaVisibility.PUBLIC);
        remove.setName("remove"+ domainName);
        remove.setReturnType(new FullyQualifiedJavaType("int"));
        remove.addParameter(new Parameter(new FullyQualifiedJavaType("int"),"id"));

        Method update = new Method();
        update.setVisibility(JavaVisibility.PUBLIC);
        update.setName("update"+ domainName);
        update.setReturnType(new FullyQualifiedJavaType("int"));
        update.addParameter(new Parameter(new FullyQualifiedJavaType(domainName),domainLowerName));

        Method listPage = new Method();
        listPage.setVisibility(JavaVisibility.PUBLIC);
        listPage.setName("listPage"+ domainName);
        listPage.setReturnType(new FullyQualifiedJavaType("String"));
        listPage.addParameter(new Parameter(new FullyQualifiedJavaType("Map<String,Object>"),"param"));

        Method listData = new Method();
        listData.setVisibility(JavaVisibility.PUBLIC);
        listData.setName("listData"+ domainName);
        listData.setReturnType(new FullyQualifiedJavaType("List<Map<String,Object>>"));
        listData.addParameter(new Parameter(new FullyQualifiedJavaType("Map<String,Object>"),"param"));

        iface.addMethod(save);
        iface.addMethod(remove);
        iface.addMethod(update);
        iface.addMethod(listPage);
        iface.addMethod(listData);

        iface.addImportedType(new FullyQualifiedJavaType("java.util.List"));
        iface.addImportedType(new FullyQualifiedJavaType("java.util.Map"));
        iface.addImportedType(new FullyQualifiedJavaType("com.trick.biz.mvc."+domainLowerName+".model."+ domainName));

        GeneratedJavaFile file = new GeneratedJavaFile(iface, workspace +"/src/main/java",new DefaultJavaFormatter());
        files.add(file);

	}

	/**
	 * Describe: 创建Java文件 - 实现类
	 * MrShuai 2017年6月24日
	 */
	@Override
	public void createClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String domainName, List<GeneratedJavaFile> files) {
		//创建接口业务层接口
        createInterfaceGenerated(introspectedTable, domainName, files);

	    //添加类注释
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		topLevelClass.addJavaDocLine("/**\n * Describe:" + domainName + "业务处理接口\n * @author " + userName + "\n * @since " + javaVersion + "\n * @Date "+new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+" */");
		String tableLowerName = domainName.substring(0, 1).toLowerCase() + domainName.substring(1, domainName.length());
		topLevelClass.addAnnotation("@Service(\""+ tableLowerName + "Service\")");
		topLevelClass.addSuperInterface(new FullyQualifiedJavaType(domainName +"Service"));
		//添加方法
		Field field = new Field();
		field.setName(tableLowerName+"Dao"); // 设置变量名

		field.setType(new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType())); // 类型
		field.setVisibility(JavaVisibility.PRIVATE);
		if (enableAnnotation) {
			field.addAnnotation("@Resource");
		}
		topLevelClass.addField(field);
		topLevelClass.addMethod(saveModel(introspectedTable, domainName));
		topLevelClass.addMethod(removeModel(introspectedTable, domainName));
		topLevelClass.addMethod(updateMode(introspectedTable, domainName));
		topLevelClass.addMethod(listPageModel(introspectedTable, domainName));
		topLevelClass.addMethod(listDataModelByParam(introspectedTable, domainName));
		topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Service"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("javax.annotation.Resource"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("java.util.List"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("java.util.Map"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("com.trick.biz.mvc."+domainLowerName+".service."+ domainName +"Service"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("com.trick.biz.mvc."+domainLowerName+".dao."+ domainName +"Mapper"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("com.trick.biz.mvc."+domainLowerName+".model."+ domainName));

		GeneratedJavaFile file = new GeneratedJavaFile(topLevelClass, workspace +"/src/main/java",new DefaultJavaFormatter());
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
    private Method saveModel(IntrospectedTable introspectedTable, String domainName) {
        MethodEntity entity = new MethodEntity();
        entity.setJavaDocLine("/**\n\t* Describe: 保存方法\n\t* "+userName+" "+new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"\n\t*/");
        entity.setName("save"+domainName);
        entity.setReturnType(new FullyQualifiedJavaType("int"));
        entity.setBodyLine("return 0;");
        entity.put(domainLowerName,new FullyQualifiedJavaType(domainName));
		return generatorMethod(entity);
	}

	/**
	 * Describe: 删除对象方法
	 * MrShuai 2017年8月11日
	 */
    private Method removeModel(IntrospectedTable introspectedTable, String domainName) {
        MethodEntity entity = new MethodEntity();
        entity.setJavaDocLine("/**\n\t* Describe: 删除方法\n\t* "+userName+" "+new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"\n\t*/");
        entity.setName("remove"+domainName);
        entity.setReturnType(new FullyQualifiedJavaType("int"));
        entity.setBodyLine("return 0;");
        entity.put("id",new FullyQualifiedJavaType("int"));
        return generatorMethod(entity);
	}

	/**
	 * Describe: 更新修改对象方法
	 * MrShuai 2017年8月11日
	 */
    private Method updateMode(IntrospectedTable introspectedTable, String domainName) {
        MethodEntity entity = new MethodEntity();
        entity.setJavaDocLine("/**\n\t* Describe: 修改方法\n\t* "+userName+" "+new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"\n\t*/");
        entity.setName("update"+domainName);
        entity.setReturnType(new FullyQualifiedJavaType("int"));
        entity.setBodyLine("return 0;");
        entity.put(domainLowerName,new FullyQualifiedJavaType(domainName));
        return generatorMethod(entity);
	}

	/**
	 * Describe: 对象列表跳转
	 * MrShuai 2017年8月11日
	 */
    private Method listPageModel(IntrospectedTable introspectedTable, String domainName) {
        MethodEntity entity = new MethodEntity();
        entity.setJavaDocLine("/**\n\t* Describe: 列表页跳转\n\t* "+userName+" "+new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"\n\t*/");
        entity.setName("listPage"+domainName);
        entity.setReturnType(new FullyQualifiedJavaType("String"));
        entity.setBodyLine("return null;");
        entity.put("param",new FullyQualifiedJavaType("Map<String,Object>"));
        return generatorMethod(entity);
	}

	/**
	 * Describe: 根据条件查询对象列表方法
	 * MrShuai 2017年8月11日
	 */
    private Method listDataModelByParam(IntrospectedTable introspectedTable, String domainName) {
        MethodEntity entity = new MethodEntity();
        entity.setJavaDocLine("/**\n\t* Describe: 列表页数据\n\t* "+userName+" "+new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"\n\t*/");
        entity.setName("listData"+domainName);
        entity.setReturnType(new FullyQualifiedJavaType("List<Map<String,Object>>"));
        entity.setBodyLine("return null;");
        entity.put("param",new FullyQualifiedJavaType("Map<String,Object>"));
        return generatorMethod(entity);
	}

    /**
     * 创建 method 模版方法
     * @param methodEntity method属性实体类
     * @return method 对象
     */
	private Method generatorMethod(MethodEntity methodEntity){
        Method method = new Method();
        method.addJavaDocLine(methodEntity.getJavaDocLine());
        method.setName(methodEntity.getName());
        method.addAnnotation("@Override");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(methodEntity.getReturnType());
        method.addBodyLine(methodEntity.getBodyLine());
        ParameterUtil.setParameter(method,methodEntity.getParameterMap());
        return method;
    }
}
