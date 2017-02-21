package com.zj.concurrent;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

	private BlockingQueue<Food> queue;
	
	public Consumer(BlockingQueue<Food> queue){
		this.queue = queue;
	}
	
	public void run() {
		
		//消费食物
		try {
			Food food = queue.take();
			System.out.println(Thread.currentThread().getName() + " 消费: " + food);
			//Thread.currentThread().sleep(2000);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
