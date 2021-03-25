package com.springboot.cloud.mystudy.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: cloud
 * @description:
 * @author: yuanmenghuai
 * @create: 2021-03-25 10:39
 * 1 CAS是什么 ？
 *      比较并交换 compareAndSet
 *
 **/
public class CASDemo {

	public static void main(String[] args) {

		AtomicInteger atomicInteger = new AtomicInteger(5);

		// mmain do thing ......

		System.out.println(atomicInteger.compareAndSet(5,2019) + "\t current data: " + atomicInteger.get());

		System.out.println(atomicInteger.compareAndSet(5,1024) + "\t current data: " + atomicInteger.get());

	}

}
