package com.study.pengxin.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 倒计时器  所有子线程执行完毕，才执行主线程
 * @author admin
 *
 */
public class CountDownLatchDemo {

	public static void main(String[] args) {

		final CountDownLatch cdl = new CountDownLatch(2);
		
		new Thread(new Runnable() {
			public void run() {
				System.out.println("A线程进入");
				
				try {
					Thread.sleep((long)(Math.random()*10000));
					cdl.countDown();
					System.out.println("A线程执行完毕");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				System.out.println("B线程进入");
				
				try {
					Thread.sleep((long)(Math.random()*10000));
					cdl.countDown();
					System.out.println("B线程执行完毕");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		try {
			cdl.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("两个子线程执行完成，执行主线程");
	}

}
