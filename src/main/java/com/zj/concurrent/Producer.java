package com.zj.concurrent;

import java.util.concurrent.BlockingQueue;

/**
 * 生产者
 * @author Administrator
 *
 */
public class Producer implements Runnable{

	private BlockingQueue<Food> queue;
	
	public Producer(BlockingQueue<Food> queue) {
		this.queue = queue;
	}	
	
	public void run() {
		
		//生产食物
		try {
			Food food = Food.randomFood();
			queue.put(food);
			System.out.println(Thread.currentThread().getName() + " 生产: " + food
					);
			
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
