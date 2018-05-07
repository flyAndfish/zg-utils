package com.study.pengxin.proxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.study.pengxin.proxy.service.UserService;

public class MyHandler implements InvocationHandler {

	private UserService userService;
	
	
	public MyHandler(UserService userService) {
		this.userService = userService;
	}

	public Object invoke(Object proxy, Method method, Object[] args) {

		
		before();
		try {
		   method.invoke(userService, args);
		}catch (Throwable e) {
		}
		after();
		
		return "OK";
	}

	private void before() {
		System.out.println("执行before方法……");
	}
	
	private void after() {
		System.out.println("执行after方法……");
	}
}
