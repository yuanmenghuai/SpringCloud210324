package com.springboot.cloud.mystudy.thread;

/**
 * @program: cloud
 * @description: 指令重排demo
 * @author: yuanmenghuai
 * @create: 2021-03-23 19:32
 **/
public class ReSortSeqDemo {

	int a = 0;
	boolean flag = false;

	public void method01(){
		a = 1;
		flag = true;
	}

	//多线程环境中线程交替执行，由于编译器优化重排的存在
	//两个线程中使用的变量能否保证一致性是无法确定的，结果无法预测
	public void method02(){
		if(flag){
			a = a + 5;
			System.out.println("******value: " + a);
		}
	}


}
