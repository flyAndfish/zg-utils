package com.study.pengxin.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

	public static void main(String[] args) {

		Semaphore semaphore = new Semaphore(2);
		ExecutorService se = Executors.newCachedThreadPool();
		
		for(int i=1;i<=20;i++) {
			se.execute(new MyTask(semaphore, i));
		}
	}
	
}

class MyTask implements Runnable{
	//定义信号量
	private Semaphore s;
	//当前线程编号
	private int threadNum;
	
	public MyTask(Semaphore s,int threadNum) {
		this.s = s;
		this.threadNum = threadNum;
	}
	
	public void run() {
		try {
			//获取信号量
			s.acquire();
			System.out.println("当前用户"+threadNum+"进入窗口，准备买票");
			Thread.sleep((long)(Math.random()*10000));
			System.out.println("当前用户"+threadNum+"买完票，准备离开");
			Thread.sleep((long)(Math.random()*10000));
			System.out.println("当前用户"+threadNum+"离开窗口");
			s.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
