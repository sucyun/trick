package com.trick.web.core.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Describe:用户令牌
 * @author MrShuai 2016年12月25日
 */
public class ShiroToken extends UsernamePasswordToken implements java.io.Serializable {

	private static final long serialVersionUID = -6451794657814516274L;

	public ShiroToken(String username, String pwd) {
		super(username, pwd);
		this.pwd = pwd;
	}
	private String pwd;

	public String getPwd() {
		return pwd;
	}
	public void setPswd(String pwd) {
		this.pwd = pwd;
	}

}
