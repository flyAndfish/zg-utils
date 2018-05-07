package com.study.pengxin.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 循环屏障  所有线程分组进来，满足一组后再往下执行
 * @author admin
 *
 */
public class CyclicBarrierDemo {
     public static void main(String[] args) {
		
    	 final CyclicBarrier cb = new CyclicBarrier(3,new Runnable() {
			
			public void run() {
                System.out.println("人员已经到齐，各自拍照留恋！");
                try {
					Thread.sleep((long)(Math.random()*10000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	 
    	 ExecutorService pool = Executors.newCachedThreadPool();
    	 
    	 for(int i=0;i<3;i++) {
    		 final int n=i+1;
    		 Runnable runnable = new Runnable() {
				public void run() {
					try {
						Thread.sleep((long)Math.random()*10000);
						System.out.println(n+"--到达--");
						//添加屏障，阻塞线程
						cb.await();
						System.out.println(n+"--吃完饭了--");
						cb.await();
						System.out.println("------yiqizou------");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			};
			
			pool.execute(runnable);
    	 }
	}
}
