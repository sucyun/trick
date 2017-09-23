package com.trick.web.common.code;

public enum ResultCode {
	SERVER_ERROR("200", "success"), USER_NOT_EXIST("-10", "用户不存在"), PASSWORD_ERROR("2", "密码错误");

	private String code;
	private String msg;

	private ResultCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public static String getResponseMsg(String code) {
		for (ResultCode resultCode : ResultCode.values()) {
			if (code.equals(resultCode.getCode())) {
				return resultCode.getMsg();
			}
		}
		return SERVER_ERROR.getMsg();
	}
}