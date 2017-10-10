package com.trick.web.core.generator.entity;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;

import java.util.HashMap;
import java.util.Map;


public class MethodEntity {
    private String javaDocLine;
    private String name;
    private String annotation;
    private JavaVisibility visibility;
    private FullyQualifiedJavaType returnType;
    private String bodyLine;
    private Map<String,FullyQualifiedJavaType> parameterMap = new HashMap<>();

    public String getJavaDocLine() {
        return javaDocLine;
    }

    public void setJavaDocLine(String javaDocLine) {
        this.javaDocLine = javaDocLine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public JavaVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(JavaVisibility visibility) {
        this.visibility = visibility;
    }

    public FullyQualifiedJavaType getReturnType() {
        return returnType;
    }

    public void setReturnType(FullyQualifiedJavaType returnType) {
        this.returnType = returnType;
    }

    public String getBodyLine() {
        return bodyLine;
    }

    public void setBodyLine(String bodyLine) {
        this.bodyLine = bodyLine;
    }

    public Map<String, FullyQualifiedJavaType> getParameterMap() {
        return parameterMap;
    }

    public void put(String name , FullyQualifiedJavaType fullyQualifiedJavaType){
        this.parameterMap.put(name,fullyQualifiedJavaType);
    }
}
