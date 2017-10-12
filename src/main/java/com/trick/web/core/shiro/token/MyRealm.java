package com.trick.web.core.shiro.token;

import com.trick.biz.mvc.admin.model.Admin;
import com.trick.biz.mvc.admin.model.Privilege;
import com.trick.biz.mvc.admin.model.Role;
import com.trick.biz.mvc.admin.service.AdminService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Describe:自定义的指定Shiro验证用户登录的类
 * @author MrShuai 2016年12月21日
 */
public class MyRealm extends AuthorizingRealm {

	@Resource
	private AdminService adminService;

	/**
	 * 为当前登录的Subject授予角色和权限
	 * @see 经测试:本例中该方法的调用时机为需授权资源被访问时
	 * @see 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
	 * @see 个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache
	 * @see 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
		String currentUsername = (String) super.getAvailablePrincipal(principals);
		List<String> roleList = new ArrayList<String>();
		List<String> permissionList = new ArrayList<String>();
		// 从数据库中获取当前登录用户的详细信息
		Admin admin = adminService.selectPrivilegeByAdmName(currentUsername);
		if (null != admin) {
			// 实体类User中包含有用户角色的实体类信息
			if (null != admin.getRole() && admin.getRole().size() > 0) {
				// 获取当前登录用户的角色
				List<Role> roles = admin.getRole();
				for (Role role : roles) {
					if (StringUtils.isNotBlank(role.getName()))
						roleList.add(role.getName());
					// 实体类Role中包含有角色权限的实体类信息
					if (null != role.getPrivilege() && role.getPrivilege().size() > 0) {
						// 获取权限
						for (Privilege pmss : role.getPrivilege()) {
							if (!StringUtils.isEmpty(pmss.getName())) {
								permissionList.add(pmss.getName());
							}
						}
					}
				}
			}
		} else {
			throw new AuthorizationException();
		}
		// 为当前用户设置角色和权限
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		simpleAuthorInfo.addRoles(roleList);
		simpleAuthorInfo.addStringPermissions(permissionList);
		return simpleAuthorInfo;
	}

	/**
	 * 验证当前登录的Subject
	 * 执行Subject.login()时触发(GlobleController.login())
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		System.out.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		Admin admin = adminService.login(token.getUsername(), token.getPassword());
		if (null != admin) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(admin, admin.getPassword(), getName());
			this.setSession("currentUser", admin);
			return authcInfo;
		} else {
			throw new AuthenticationException();
		}
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * 使用HttpSession.getAttribute(key)就可以取到
	 */
	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}
}