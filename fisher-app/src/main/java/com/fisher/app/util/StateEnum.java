package com.fisher.app.util;

public enum StateEnum {
	SUC("成功"), FAL("失败"), EXIST("已存在");
	// 成员变量
	private String msg;

	// 构造方法
	private StateEnum(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

}
