package com.study.pengxin.pattern.observer;

public class Observer2 extends Observer {

	public Observer2(Subject sub) {
		super(sub);
		this.sub.addObserver(this);
	}

	@Override
	public void doSomething() {
		 System.out.println("observer2 收到新消息!");
	}

}
