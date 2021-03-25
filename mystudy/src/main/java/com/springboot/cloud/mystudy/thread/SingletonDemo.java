package com.springboot.cloud.mystudy.thread;

/**
 * @program: cloud
 * @description: 常规的单例模式，在单线程下是不安全 , 需要加 volatile 和 DCL
 * @author: yuanmenghuai
 * @create: 2021-03-24 11:27
 **/
public class SingletonDemo {

	private static volatile SingletonDemo instance = null;

	private SingletonDemo(){
		System.out.println(Thread.currentThread().getName() + "\t 我是构造方法 SingletonDemo()");
	}


	private static SingletonDemo getInstance(){
		if(instance == null){

			//DCL (Double Check Lock 双端检索机制)
			//DCL机制不一定线程安全，原因是有指令重排序的存在，加入volatile可以禁止指令重排
			//原因在于某一个线程执行到第一次检测，读取到的instance不为null时，instance的引用对象可能没有完成初始化。
			// instance = new SingletonDemo() 可以分为以下三步完成（伪代码）
			// memory = allocate(); 1 分配对象内存空间
			// instance(memory);    2 初始化对象
			// instance = memory;   3 设置instance指向刚分配的内存地址，此时instance != null
			//步骤2和步骤3不存在数据依赖关系，而且无论重排前还是重排后程序的执行结果在单线程中并没有改变，因此这种重排优化是允许的

			synchronized (SingletonDemo.class){
				if(instance == null){
					instance = new SingletonDemo();
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) {
		//单线程（main线程的操作动作。。。。。。）
//		System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//		System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//		System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

		//并发多线程后，情况发生了很大的变化
		for (int i = 1; i <= 10; i++) {
			new Thread(() -> {
				// 打印了多次 我是构造方法 SingletonDemo()
				SingletonDemo.getInstance();
			},String.valueOf(i)).start();
		}
	}

}
