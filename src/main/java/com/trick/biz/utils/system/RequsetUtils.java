package com.trick.biz.utils.system;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RequsetUtils {
	
	public static String checkParameters(HttpServletRequest request, String key){
		return StringUtils.isBlank(request.getParameter(key))?"":request.getParameter(key);
	}
	
	public static <T> T toModelValue(Class<T> cla, HttpServletRequest request){
		try {
			Object obj =  cla.newInstance();
			Field[] fields = cla.getDeclaredFields();
			for (Field field : fields) {
				String name = field.getName();//获取属性的名字
				String values = checkParameters(request,field.getName());
				name = name.substring(0, 1).toUpperCase() + name.substring(1);//将属性的首字符大写，方便构造get，set方法
				String type = field.getGenericType().toString(); // 获取属性的类型
				if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
					Method m = cla.getMethod("get" + name);
					String value = (String) m.invoke(obj);// 调用getter方法获取属性值
					if (value == null) {
						m = cla.getMethod("set" + name, String.class);
						m.invoke(obj, values);
					}
				}
				if (type.equals("class java.lang.Integer")) {
                    Method m = cla.getMethod("get" + name);
                    Integer value = (Integer) m.invoke(obj);
                    if (value == null) {
                        m = cla.getMethod("set"+name,Integer.class);
                        m.invoke(obj, Integer.parseInt(values));
                    }
                }
                if (type.equals("class java.lang.Boolean")) {
                    Method m = cla.getMethod("get" + name);
                    Boolean value = (Boolean) m.invoke(obj);
                    if (value == null) {
                        m = cla.getMethod("set"+name,Boolean.class);
                        m.invoke(obj, Boolean.parseBoolean(values));
                    }
                }
                if (type.equals("class java.util.Date")) {
                    Method m = cla.getMethod("get" + name);
                    Date value = (Date) m.invoke(obj);
                    if (value == null) {
                        m = cla.getMethod("set"+name,Date.class);
                        try {
							m.invoke(obj, new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse(values));
						} catch (ParseException e) {
							e.printStackTrace();
						}
                    }
                }
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
