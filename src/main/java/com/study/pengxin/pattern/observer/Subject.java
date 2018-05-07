package com.study.pengxin.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式中的主题，被观察者  管理观察者，有消息变化会通知所有观察者
 * @author admin
 *
 */
public class Subject {
    private String msg;

    List<Observer> observers = new ArrayList<Observer>();
    
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
		notifyAllObserver();
	}
    
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	public void delObserver(Observer observer) {
		observers.remove(observer);
	}
	
	private void notifyAllObserver() {
		for (Observer observer:observers) {
			observer.doSomething();
		}
	}
}
