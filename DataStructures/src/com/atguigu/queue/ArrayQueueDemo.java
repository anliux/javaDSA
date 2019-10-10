package com.atguigu.queue;

import java.util.Scanner;

public class ArrayQueueDemo {

	public static void main(String[] args) {
		//测试
		ArrayQueue queue = new ArrayQueue(3);
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
class ArrayQueue{
	private int maxSize;//数组的最大容量
	private int front;//队列头
	private int rear;//队列尾
	private int[] arr;//用于存放数据
	
	//创建队列的构造器
	public  ArrayQueue(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];
		front = -1;//指向队列头部，分析出front是指向队列头的前一个位置
		rear = -1;//指向队列尾，指向队列尾的数据（即队列最后一个数据）
	}
	
	//判断队列是否为空
	public boolean isEmpty() {
		return front == rear;
	}
		
	//判断队列是否满
	public boolean isFull() {
		return rear == maxSize-1;
	}
		
	//添加数据到队列
	public void addQueue(int n) {
		if(isFull()) {
			System.out.println("队列满，不能加入数据");
			return;
		}
		rear++;//尾指针后移
		arr[rear]=n;
	}
		
	//获取队列的数据，出队列
	public int getQueue() {
		//判空
		if(isEmpty()) {
			//抛出异常
			throw new RuntimeException("队列空，不能取数据");
		}
		front++;//front后移
		return arr[front];
	}
		
	//显示队列的所有数据
	public void showQueue() {
		//判空
		if(isEmpty()) {
			System.out.println("队列空，没有可显示的数据");
			return;
		}
		//遍历
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("arr[%d]=%d\n",i,arr[i]);
		}
	}
		
	//显示队列的头数据，注意不是取出
	public int headQueue() {
		//判空
		if(isEmpty()) {
			throw new RuntimeException("队列空，没有可取的头数据");
		}
		return arr[front+1];//注意：front指向的是队列头的前一个数据。（更小的一个位置）
	}
	
	
	
}
