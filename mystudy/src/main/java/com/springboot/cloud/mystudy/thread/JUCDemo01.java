package com.springboot.cloud.mystudy.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: cloud
 * @description:
 * @author: yuanmenghuai
 * @create: 2021-04-13 11:08
 **/
class ThreadUnsafeExample {

	private int cnt = 0;

	public void add() {
		cnt++;
	}

	public int get() {
		return cnt;
	}
}


public class JUCDemo01 {

	public static void main(String[] args) throws InterruptedException {


		final int threadSize = 1000;
		ThreadUnsafeExample example = new ThreadUnsafeExample();
		final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < threadSize; i++) {
			executorService.execute(() -> {
				example.add();
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		System.out.println(example.get());

	}

}
