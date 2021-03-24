package com.springboot.cloud.mystudy.thread;

/**
 * @program: cloud
 * @description: 单例模式
 * @author: yuanmenghuai
 * @create: 2021-03-24 11:27
 **/
public class SingletonDemo {

	private static SingletonDemo instance = null;

	private SingletonDemo(){
		System.out.println(Thread.currentThread().getName() + "\t 我是构造方法 SingletonDemo()");
	}


}
