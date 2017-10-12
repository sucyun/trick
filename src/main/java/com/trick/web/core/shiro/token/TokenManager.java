package com.trick.web.core.shiro.token;

import com.trick.biz.mvc.admin.model.Admin;
import com.trick.web.common.utils.SpringContextUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * Describe:用户令牌管理者，判断用户是否登录或去用户信息等
 * 
 * @author MrShuai 2016年12月25日
 */
public class TokenManager {
	// 用户登录管理
	public static final MyRealm realm = SpringContextUtil.getBean("myRealm", MyRealm.class);
	// 用户session管理,后期拓展,暂时不用
//	public static final CustomSessionManager customSessionManager = SpringContextUtil.getBean("customSessionManager", CustomSessionManager.class);
	/**
	 * Describe:获取当前登录的Admin用户
	 * MrShuai 2016年12月25日
	 */
	public static Admin getToken(){
		Admin token = (Admin) SecurityUtils.getSubject().getPrincipal();
		return token ;
	}
	/**
	 * Describe:获取当前用户的Session
	 * MrShuai 2016年12月25日
	 */
	public static Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}
	/**
	 * Describe: 获取当前用户名NAME
	 * MrShuai 2016年12月25日
	 */
	public static String getName(){
		return getToken().getName();
	}
	/**
	 * Describe:获取当前用户ID 
	 * MrShuai 2016年12月25日
	 */
	public static Integer getUserId(){
		return getToken()==null?null:getToken().getId();
	}
	/**
	 * Describe:把值放入到当前登录用户的Session里
	 * MrShuai 2016年12月25日
	 */
	public static void setVal2Session(Object key , Object value){
		getSession().setAttribute(key, value);
	}
	/**
	 * Describe:从当前登录用户的Session里取值
	 * MrShuai 2016年12月25日
	 */
	public static Object getVal2Session(Object key){
		return getSession().getAttribute(key);
	}
	/**
	 * Describe:获取验证码，获取一次后删除 
	 * MrShuai 2016年12月25日
	 */
	public static String getYZM(){
		String code = (String) getSession().getAttribute("CODE");
		getSession().removeAttribute("CODE");
		return code ;
	}
	/**
	 * Describe:判断是否登录 
	 * MrShuai 2016年12月25日
	 */
	public static boolean isLogin() {
		return null != SecurityUtils.getSubject().getPrincipal();
	}
	/**
	 * 登录
	 * @param rememberMe
	 * @return
	 */
	public static Admin login(Admin admin,Boolean rememberMe){
		ShiroToken token = new ShiroToken(admin.getName(), admin.getPassword());
		token.setRememberMe(rememberMe);
		SecurityUtils.getSubject().login(token);
		return getToken();
	}
	/**
	 * Describe:shiro退出登录 
	 * MrShuai 2016年12月25日
	 */
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
	
}
