package com.springboot.cloud.mystudy.thread;
import java.util.concurrent.locks.LockSupport;

/**
 * @program: cloud
 * @description:
 * @author: yuanmenghuai
 * @create: 2021-04-15 17:16
 **/
class MyThread01 extends Thread {
	private Object object;

	public MyThread01(Object object) {
		this.object = object;
	}

	@Override
	public void run() {
		System.out.println("before unpark");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 获取blocker
		System.out.println("Blocker info " + LockSupport.getBlocker((Thread) object));
		// 释放许可
		LockSupport.unpark((Thread) object);
		// 休眠500ms，保证先执行park中的setBlocker(t, null);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 再次获取blocker
		System.out.println("Blocker info " + LockSupport.getBlocker((Thread) object));

		System.out.println("after unpark");
	}
}

public class LockSupportDemo01 {
	public static void main(String[] args) {
		MyThread01 myThread = new MyThread01(Thread.currentThread());
		myThread.start();
		System.out.println("before park");
		// 获取许可
		LockSupport.park("ParkAndUnparkDemo");
		System.out.println("after park");
	}
}

