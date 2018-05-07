package com.study.pengxin.thread;

import java.util.concurrent.Exchanger;

/**
 * 俩个线程交换资源
 * @author admin
 *
 */
public class ExchangerDemo {

	public static void main(String[] args) {
         final Exchanger<String> exchanger = new Exchanger<String>();
         
         //A线程 拥有资源1000万
         new Thread(new Runnable() {
			public void run() {
				String resource="1000万";
				try {
					String result = exchanger.exchange(resource);
					System.out.println("A用"+resource+"交换回了"+result);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
         
       //B线程 拥有资源别墅
       new Thread(new Runnable() {
			public void run() {
				String resource="别墅";
				try {
					String result = exchanger.exchange(resource);
					System.out.println("B用"+resource+"交换回了"+result);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
         
	}

}
