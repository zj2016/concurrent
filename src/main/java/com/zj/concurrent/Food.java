package com.zj.concurrent;

import java.util.Random;

public enum Food {

	APPLE(1, "苹果", "5￥"),
	PEAR(2, "梨", "6￥"),
	PEACH(3, "桃子" ,"7￥"),
	BANANA(4, "香蕉", "8￥"),
	ORANGE(5, "桔子", "9￥"),
	DEFAULT(0, "默认食物", "0￥");
	
	private int id;
	
	private String name;
	
	private String price;
	
	private Food(int id, String name, String price){
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public static Food randomFood(){
		Random random = new Random();
		int num = random.nextInt(5);
		for(Food food : Food.values()){
			if(food.id == num){
				return food;
			}
		}
		return Food.DEFAULT;
	}
	
}
