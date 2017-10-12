package com.trick.web.core.shiro.manager;

public interface ShiroManager {

	/**
	 * Describe: 加载过滤配置信息
	 * MrShuai 2016年12月26日
	 */
	public String loadFilterChainDefinitions();

	/**
	 * Describe: 重新构建权限过滤器 一般在修改了用户角色、用户等信息时，需要再次调用该方法
	 * MrShuai 2016年12月26日
	 */
	public void reCreateFilterChains();
}
