package com.trick.web.common.utils.Log;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Describe:Log日志输出封装
 * @author MrShuai 2016年12月25日
 */
public class LoggerUtils {
	/**
	 * 是否开启Debug
	 */
	public static boolean isDebug =  Logger.getLogger(LoggerUtils.class).isDebugEnabled();
	/**
	 * Describe: Debug 输出
	 * @param clazz   目标.Class
	 * @param message 输出信息
	 * MrShuai 2016年12月25日
	 */
	public static void debug(Class<? extends Object> clazz , String message){
		if(!isDebug)return ;
		Logger logger = Logger.getLogger(clazz);
		logger.debug(message);
	}
	/**
	 * Describe: Debug 输出
	 * @param clazz  	目标.Class
	 * @param fmtString 输出信息key
	 * @param value		输出信息value
	 * MrShuai 2016年12月25日
	 */
	public static void fmtDebug(Class<? extends Object> clazz, String fmtString, Object...value){
		if(!isDebug)return ;
		if (StringUtils.isBlank(fmtString)) {
			return;
		}
		if (null != value && value.length != 0) {
			fmtString = String.format(fmtString, value);
		}
		debug(clazz, fmtString);
	}
	/**
	 * Describe: Error 输出
	 * @param clazz  	目标.Class
	 * @param message	输出信息
	 * @param e			异常类
	 * MrShuai 2016年12月25日
	 */
	public static void error(Class<? extends Object> clazz, String message, Exception e) {
		Logger logger = Logger.getLogger(clazz);
		if (null == e) {
			logger.error(message);
			return;
		}
		logger.error(message, e);
	}
	/**
	 * Describe: Error 输出
	 * @param clazz  	目标.Class
	 * @param message	输出信息
	 * MrShuai 2016年12月25日
	 */
	public static void error(Class<? extends Object> clazz, String message) {
		error(clazz, message, null);
	}
	/**
	 * Describe: 异常填充值输出
	 * @param clazz 	目标.Class
	 * @param fmtString	输出信息key
	 * @param e			异常类
	 * @param value		输出信息value
	 * MrShuai 2016年12月25日
	 */
	public static void fmtError(Class<? extends Object> clazz, Exception e, String fmtString, Object... value) {
		if (StringUtils.isBlank(fmtString)) {
			return;
		}
		if (null != value && value.length != 0) {
			fmtString = String.format(fmtString, value);
		}
		error(clazz, fmtString, e);
	}
	/**
	 * Describe: 异常填充值输出
	 * @param clazz		目标.Class
	 * @param fmtString 输出信息key
	 * @param value		输出信息value
	 * MrShuai 2016年12月25日
	 */
	public static void fmtError(Class<? extends Object> clazz, String fmtString, Object... value) {
		if (StringUtils.isBlank(fmtString)) {
			return;
		}
		if (null != value && value.length != 0) {
			fmtString = String.format(fmtString, value);
		}
		error(clazz, fmtString);
	}
}
