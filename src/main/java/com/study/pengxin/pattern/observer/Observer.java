package com.study.pengxin.pattern.observer;

/**
 * 观察者对象
 * @author admin
 *
 */
public abstract class Observer {
	
	protected Subject sub;
	
	
	public Observer(Subject sub) {
		this.sub = sub;
	}



	public abstract void doSomething();
}
