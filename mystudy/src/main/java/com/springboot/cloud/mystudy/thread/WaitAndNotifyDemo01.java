package com.springboot.cloud.mystudy.thread;

/**
 * @program: cloud
 * @description:
 * @author: yuanmenghuai
 * @create: 2021-04-15 17:03
 **/
class MyThread extends Thread{

	@Override
	public void run(){
		synchronized (this){
			System.out.println("before notify");
			notify();
			System.out.println("after notify");
		}
	}
}

public class WaitAndNotifyDemo01 {
	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		synchronized (myThread){
			try {
				myThread.start();
				//主线程睡眠3s
				Thread.sleep(3000);
				System.out.println("before wait");
				//阻塞主线程
				myThread.wait();
				System.out.println("after wait");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
