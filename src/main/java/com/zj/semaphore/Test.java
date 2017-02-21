package com.zj.semaphore;

public class Test {

	public static void main(String[] args) throws InterruptedException {

		BoundedHashSet<String> set = new BoundedHashSet<>(4);
		
		System.out.println(set.add("a"));
		System.out.println(set.add("b"));
		System.out.println(set.add("c"));
		System.out.println(set.add("d"));
		
		System.out.println(set.remove("a"));
		System.out.println(set.add("e"));
		
		
		
	}

}
