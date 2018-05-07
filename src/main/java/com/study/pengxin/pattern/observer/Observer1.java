package com.study.pengxin.pattern.observer;

public class Observer1 extends Observer {

	
	public Observer1(Subject sub) {
		super(sub);
		this.sub.addObserver(this);
	}

	@Override
	public void doSomething() {
         System.out.println("observer1 收到新消息!");
	}

}
