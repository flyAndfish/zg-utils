package com.study.pengxin.proxy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

import com.study.pengxin.proxy.autor.MyProxy;
import com.study.pengxin.proxy.handler.MyHandler;
import com.study.pengxin.proxy.service.UserService;
import com.study.pengxin.proxy.service.UserServiceImpl;

import sun.misc.ProxyGenerator;

public class Test {

	public static void main(String[] args) {
		try {
			UserService service = (UserService)Proxy.newProxyInstance(Test.class.getClassLoader(), new Class<?>[] {UserService.class}, new MyHandler(new UserServiceImpl()));
		
	        createProxyClassFile();
			
		    service.say("3443");
			UserService service2 = (UserService)MyProxy.newProxyInstance(Test.class.getClassLoader(), UserService.class, new MyHandler(new UserServiceImpl()));
		
			service2.excutor();
			service2.say("ttyy");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void createProxyClassFile() {
    	byte[] generator = ProxyGenerator.generateProxyClass("$Proxy0", new Class<?>[] {UserService.class});
    	try {
			FileOutputStream output = new FileOutputStream("$Proxy0.class");
			output.write(generator);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
