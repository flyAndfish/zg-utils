package com.study.pengxin.pattern;

/**
 * 内部类实现单例模式
 * @author admin
 * @date 2018-4-7
 */
public class SingletonDemo {

	private int id = (int)(Math.random()*10000);
	
	
	
	private SingletonDemo() {
		
	}

	public static SingletonDemo getInstance() {
		return nestSingleton.singleton;
	}
	
	private static class nestSingleton {
		private static SingletonDemo singleton = new SingletonDemo();
	}
	
	public void say() {
		System.out.println("id=========="+id);
	}
	
	

}
