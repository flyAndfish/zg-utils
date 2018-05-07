package com.study.pengxin.proxy.service;

public class UserServiceImpl implements UserService {

	public String excutor() throws Throwable{
		
		System.out.println("执行业务逻辑");
		return "执行业务逻辑";
	}

	public String say(String str) throws Throwable{
		System.out.println("holle"+str);
		return "holle"+str;
	}

}
