package com.atguigu.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {

	public static void main(String[] args) {
		//测试
		ArrayQueue queue = new ArrayQueue(4);//注意：有一个空的位置，实际最长为3
		char key = ' ';//接收用户输入
		java.util.Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		
		//输出一个菜单
		while(loop) {
			System.out.println("s(show)：显示队列");
			System.out.println("a(add)：添加数据到队列");
			System.out.println("g(get)：从队列中获取元素");
			System.out.println("h(head)：查看队列投的数据");
			System.out.println("e(exit)：退出程序");
			key = scanner.next().charAt(0);//接收键盘输入的字符串，并且取出它的第一个字符。
			
			switch(key) {
			case 's':
				queue.showQueue();
				break;
			case 'a':
				System.out.println("输入一个数：");
				int value = scanner.nextInt();
				queue.addQueue(value);
				break;
			case 'g': //抛异常了，要捕获
				try {
					int res = queue.getQueue();
					System.out.printf("取出的数据是%d\n",res);
				} catch (Exception e) {
					// 对所捕获异常的处理
					System.out.println(e.getMessage());
				}
				break;
			case 'h'://抛异常了，需要捕获处理
				try {
					int res = queue.headQueue();
					System.out.printf("队列头的数据是%d\n",res);
				} catch (Exception e) {
					// 对所捕获异常的处理
					System.out.println(e.getMessage());
				}
				break;
			case 'e':
				scanner.close();
				loop = false;
				break;
			default:
				break;
			}
		}
		System.out.println("程序退出");
	}
}

//使用数组模拟队列编写一个ArrayQueue类
class CircleArray{
	private int maxSize;//数组的最大容量
	private int front;
		//队列头：调整为指向队列的第一个元素，即arr[front]，初始值设为0
	private int rear;
		//队列尾：调整为指向队列最后一个元素的后一个位置，并空出一个空间作为约定，初始值设为0
	private int[] arr;//用于存放数据
	
	//创建队列的构造器
	public  CircleArray(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];
	}
	
	//判断队列是否为空
	public boolean isEmpty() {
		return front == rear;
	}
		
	//判断队列是否满
	public boolean isFull() {
		return (rear+1)%maxSize == front;
	}
		
	//添加数据到队列
	public void addQueue(int n) {
		if(isFull()) {
			System.out.println("队列满，不能加入数据");
			return;
		}
		arr[rear]=n; //rear指向最后一个元素的后一个，故直接添加
		rear=(rear+1)%maxSize;//考虑环形，取模
	}
		
	//获取队列的数据，出队列
	public int getQueue() {
		//判空
		if(isEmpty()) {
			//抛出异常
			throw new RuntimeException("队列空，不能取数据");
		}
		/*
		 * front指向队列的第一个元素
		 * 1、先把front对应的值保留到一个临时变量
		 * 2、将front后移，考虑取模
		 * 3、将临时保存的变量返回
		 * */
		int value = arr[front];
		front = (front+1)%maxSize;
		return value;
	}
		
	//显示队列的所有数据
	public void showQueue() {
		//判空
		if(isEmpty()) {
			System.out.println("队列空，没有可显示的数据");
			return;
		}
		//遍历
		//注意：从front开始遍历，遍历多少个元素：元素个数怎么求？
		for (int i = front; i < front+size(); i++) {
			System.out.printf("arr[%d]=%d\n", i%maxSize, arr[i%maxSize]);
		}
	}
	
	//求当前队列有效数据的个数
	public int size() {
		return (rear+maxSize-front)%maxSize;
	}
		
	//显示队列的头数据，注意不是取出
	public int headQueue() {
		//判空
		if(isEmpty()) {
			throw new RuntimeException("队列空，没有可取的头数据");
		}
		return arr[front];
			//注意：front指向的是队列头，计算front时已经做了取模的动作，front即为正确的位置。
	}
	
	
	
}
