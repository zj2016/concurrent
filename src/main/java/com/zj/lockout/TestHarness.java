package com.zj.lockout;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁 练习
 * @author Administrator
 * 1、await：检验此 闭锁 计数，为0说明条件满足，继续往下执行。否则阻塞等待
 * 2、countDown：将此 闭锁 计数减1，直到为0
 *
 */
public class TestHarness {

	public long timeTasks(int nThreads, final Runnable task) throws InterruptedException{

		//初始化闭锁，初始“开始闭锁”的条件计数为1
		final CountDownLatch startGate = new CountDownLatch(1);
		//初始化闭锁，初始“结束闭锁”的条件计数为工作线程的大小
		final CountDownLatch endGate = new CountDownLatch(nThreads);
		
		for(int i = 0; i < nThreads; i++){
			System.out.println("进入循环");
			Thread t = new Thread(){
				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + " in run");
						//遇到了await()，则检验此闭锁技术是否为0，为零则条件达到，继续往下执行，否则阻塞等待
						startGate.await();
						try {
							System.out.println(Thread.currentThread().getName() + " begin task run");
							task.run();
						} finally {
							System.out.println(Thread.currentThread().getName() + " finally endGate countDown");
							//countDown()方法会使计数减1，直到为0
							endGate.countDown();
						}
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			
			t.start();
			System.out.println("结束循环");
		}
		
		long start = System.currentTimeMillis();
		System.out.println("start : " + start);
		
		startGate.countDown();
		
		endGate.await();
		long end = System.currentTimeMillis();
		System.out.println("end : " + end);
		
		return end - start;
	}
	
	public static void main(String[] args) throws InterruptedException {
		TestHarness test = new TestHarness();
		long unit = test.timeTasks(6, new Work());
		System.out.println(unit);
	}
	
	
}
