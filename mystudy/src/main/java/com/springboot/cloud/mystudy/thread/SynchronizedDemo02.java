package com.springboot.cloud.mystudy.thread;

/**
 * @program: cloud
 * @description:
 * @author: yuanmenghuai
 * @create: 2021-04-14 13:56
 **/
public class SynchronizedDemo02 implements Runnable {

	static SynchronizedDemo02 instence = new SynchronizedDemo02();

	@Override
	public void run() {
		// 同步代码块形式——锁为this,两个线程使用的锁是一样的,线程1必须要等到线程0释放了该锁后，才能执行
		synchronized (this){
			System.out.println("我是线程" + Thread.currentThread().getName());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "结束");
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(instence);
		Thread t2 = new Thread(instence);
		t1.start();
		t2.start();
	}


}
