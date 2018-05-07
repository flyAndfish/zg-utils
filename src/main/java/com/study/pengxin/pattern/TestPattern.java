package com.study.pengxin.pattern;

public class TestPattern {

	public static volatile int a=0;
	
	public static void main(String[] args) {
//		for (int i = 0; i < 1000; i++) {
//			new Thread(new Runnable() {
//				public void run() {
//					SingletonDemo1.getInstance().say();;
//				}
//			}).start();
//			
//		}
		
		Thread[] threads = new Thread[50000];
		for (int i = 0; i < 50000; i++) {
		   threads[i] = new Thread(new Runnable() {
			public void run() {
				inc();
				System.out.println("a===="+a);
			}
		});
		threads[i].start();
		
	   }
		
		while(Thread.activeCount()>1)
			Thread.yield();
		System.out.println(a);
	}

	
	public  static void inc() {
		
			a++;
		
	}
}
