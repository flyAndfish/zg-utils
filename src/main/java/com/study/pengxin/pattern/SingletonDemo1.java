package com.study.pengxin.pattern;

/**
 * 双检锁/双重校验锁实现单例
 * @author admin
 *
 */
public class SingletonDemo1 {

	private static SingletonDemo1 singleton;
	
	private int id = (int)(Math.random()*10000);
	
	private SingletonDemo1() {
	}

	public static SingletonDemo1 getInstance(){
		
		if(singleton==null) {
		    synchronized (SingletonDemo1.class) {
				if(singleton==null) {
					singleton = new SingletonDemo1();
					System.out.println("3333");
				}
		    }  
		}
		return singleton;
	}
	
	
	
	public static void main(String[] args) {
		for (int i = 0; i < 10000; i++) {
			new Thread(new Runnable() {
				public void run() {
					SingletonDemo1.getInstance();
				}
			}).start();
			
		}
	}
}
