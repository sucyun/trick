package com.trick.web.common.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

public class BaseController {
	
	protected int pageNo =1;
	protected static int pageSize = 10;
	protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
	protected JSONObject json = new JSONObject();
	private final static String PARAM_PAGE_NO = "pageNo";
	

	public <T extends Object> T handleParams(HttpServletRequest request, Class<T> cls) throws IllegalArgumentException, IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException {
		T t = cls.newInstance();
		Field[] field = cls.getDeclaredFields();
		for (Field f : field) {
			PropertyDescriptor pd = new PropertyDescriptor(f.getName(), cls);
			Method write = pd.getWriteMethod();
			String ty = f.getGenericType().toString();
			String val = request.getParameter(f.getName());
			val = StringUtils.isBlank(val) ? "" : val;
			if (ty.equals("class java.lang.String")) {
				write.invoke(t,val);
				continue;
			}
		}
		return t;
	}

	public <T extends Object> T handleParams(HttpServletRequest request, Class<T> cls, String... fields) throws InstantiationException, IllegalAccessException {
		Field[] field = cls.getDeclaredFields();
		for (Field f : field) {
			Class<?> ty = f.getType();
			if (ty.getName() instanceof String) {

			}
			String val = request.getParameter(f.getName());
		}

		return cls.newInstance();
	}

}
