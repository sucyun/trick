package com.trick.biz.utils.system;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;


public class InitModelPropUtil {
	private static Logger logger = Logger.getLogger(InitModelPropUtil.class);
	
	public static Object transRequestToModel(HttpServletRequest request, @SuppressWarnings("rawtypes") Class clz){
		Object obj = null;
		try {
			obj = clz.newInstance();
			Field[] field = clz.getFields();
			for (Field f : field) {
				f.set(obj, request.getParameter(f.getName()));
			}
		} catch (InstantiationException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return obj;
	}
}
