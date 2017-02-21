package com.zj.lockout;

public class Work implements Runnable{

	
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " 执行。。。");
	}

}
