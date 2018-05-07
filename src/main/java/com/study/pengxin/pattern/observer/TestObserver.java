package com.study.pengxin.pattern.observer;

public class TestObserver {

	public static void main(String[] args) {
         Subject subject = new Subject();
         Observer1 ob1 = new Observer1(subject);
         Observer2 ob2 = new Observer2(subject);
         subject.setMsg("消息变化了");
	}

}
