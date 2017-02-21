package com.zj.semaphore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 信号量 练习
 * @author Administrator
 * 信号量Semaphore会初始化一个大小，然后调用acquire()方法，将从信号量中获取一个许可，然后进行操作。
 * 使用完成之后，需要调用release()方法释放许可到Semaphore中
 * 
 * @param <T>
 */
public class BoundedHashSet<T> {

	private final Set<T> set;
	
	private final Semaphore sema; 
	
	public BoundedHashSet(int bound){
		this.set = Collections.synchronizedSet(new HashSet<T>());
		this.sema = new Semaphore(bound);
	}
	
	public boolean add(T t) throws InterruptedException{
		sema.acquire();
		
		boolean b = false;
		
		try {
			b = set.add(t);
			return b;
		} finally {
			if(!b){
				sema.release();
			}
		}
	}
	
	public boolean remove(T t){
		boolean b = set.remove(t);
		if(b){
			sema.release();
		}
		return b;
	}
	
}
