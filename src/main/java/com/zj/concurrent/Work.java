package com.zj.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Work {

	private static final int NUM = 4; 
	
	private static BlockingQueue<Food> queue;
	
	private static ThreadPoolExecutor producerExecutor;
	
	private static ThreadPoolExecutor consumerExecutor;
	
	public static void main(String[] args) {

		Work work = new Work();
		work.init();
		
		/*for(int i = 0; i < 50; i++){
			Producer producer = new Producer(queue);
			producerExecutor.execute(producer);
		}
		
		for(int i = 0; i < 30; i++){
			Consumer consumer = new Consumer(queue);
			consumerExecutor.execute(consumer);
		}*/
		
		for(int i = 0; i < 14; i++){
			Producer producer = new Producer(queue);
			producerExecutor.execute(producer);
			
			Consumer consumer = new Consumer(queue);
			consumerExecutor.execute(consumer);
		}
		
	}
	
	
	
	private void init(){
		
		//初始化阻塞队列
		queue = new ArrayBlockingQueue<Food>(10);
		
		//初始化线程池
		BlockingQueue<Runnable> producerWork = new ArrayBlockingQueue<>(NUM);
		producerExecutor = new ThreadPoolExecutor(2, 10, 1, TimeUnit.SECONDS, producerWork);
		
		BlockingQueue<Runnable> consumerWork = new ArrayBlockingQueue<>(NUM);
		consumerExecutor = new ThreadPoolExecutor(4, 10, 1, TimeUnit.SECONDS, consumerWork);
		
	}

}
