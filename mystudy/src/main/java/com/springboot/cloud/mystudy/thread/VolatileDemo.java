package com.springboot.cloud.mystudy.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: cloud
 * @description: Java 内存模型
 * @author: yuanmenghuai
 * @create: 2021-03-22 17:10
 **/
class MyData{
	volatile int number = 0;

	public void addTO60(){
		this.number = 60;
	}

	public void addPlusPlus(){
		number++;
	}

	AtomicInteger atomicInteger = new AtomicInteger();

	public void addAtomic(){
		atomicInteger.getAndIncrement();
	}

}

public class VolatileDemo {

	public static void main(String[] args) {
		MyData myData = new MyData();
		for (int i = 1; i <= 20; i++) {
			new Thread(() -> {
				for (int j = 1; j <= 1000; j++) {
					myData.addPlusPlus();
					myData.addAtomic();
				}
			},String.valueOf(i)).start();
		}
		
		//需要等待上面20个线程全部计算完，再用main线程取得最终结果值
		//try { TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) { e.printStackTrace(); }
		//默认两个线程，main线程，gc线程
		while (Thread.activeCount() > 2){
			//使当前线程从执行状态变成可执行状态（就绪状态），并让掉了自己的cpu执行时间，让其他线程运行
			Thread.yield();
		}

		//存在丢数据
		//addPlusPlus 加 synchronized 修饰时，可保证num 为 20000
		// n++ 分三步：第一步 getfield 拿到值， 第二步 add 进行加1 ，第三步 putfield 赋值， 此时可能会替换掉其他线程最新修改的值
		System.out.println(Thread.currentThread().getName() + "\t int type, finally number value: " + myData.number);
		System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type, finally number value: " + myData.atomicInteger);

		//seeOKByVolatie();
	}


	/**
	 * 验证了volatie的可见性
	 */
	private static void seeOKByVolatie() {
		MyData myData = new MyData();
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "\t come in");
			try { TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) { e.printStackTrace(); }
			myData.addTO60();
			System.out.println(Thread.currentThread().getName() + "\t update number value: " + myData.number);
		},"AAA").start();

		while (myData.number == 0){
			//main线程一直等待，zhi'dai
			//当 num 没有此volatile修饰时，一直保持在这里
		}
		System.out.println(Thread.currentThread().getName() + "\t mission is over ");
	}
}
