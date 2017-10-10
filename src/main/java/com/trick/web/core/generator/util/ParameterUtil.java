package com.trick.web.core.generator.util;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;

import java.util.Map;

public class ParameterUtil {
    public static void setParameter(Method method,Map<String,FullyQualifiedJavaType> parameterMap) {
        for(String name : parameterMap.keySet()){
            method.addParameter(new Parameter(parameterMap.get(name),name));
        }
    }
}
