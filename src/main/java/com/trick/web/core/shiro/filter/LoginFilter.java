package com.trick.web.core.shiro.filter;

import com.trick.biz.mvc.admin.model.Admin;
import com.trick.web.common.utils.Log.LoggerUtils;
import com.trick.web.core.shiro.token.TokenManager;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Describe:登录拦截：当用户为登录或登录session失效时告诉用户进行登录
 * @author MrShuai 2016年12月25日
 */
public class LoginFilter extends AccessControlFilter {

	/**
	 * 访问允许
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		Admin token = TokenManager.getToken();
		if (null != token || isLoginRequest(request, response)) {
			return Boolean.TRUE;
		}
		if (ShiroFilterUtils.isAjax(request)) {// ajax请求
			Map<String,String> resultMap = new HashMap<String, String>();
			LoggerUtils.debug(getClass(), "当前用户没有登录，并且是Ajax请求！");
			resultMap.put("status", "300");
			resultMap.put("message", "当前用户没有登录！");
			ShiroFilterUtils.out(response, resultMap);
		}
		return Boolean.FALSE ;
	}

	/**
	 * 访问拒绝
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// 保存request和response到登录后的链接
		saveRequestAndRedirectToLogin(request, response);
		return Boolean.FALSE;
	}

}
