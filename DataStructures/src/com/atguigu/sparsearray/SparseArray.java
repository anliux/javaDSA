package com.atguigu.sparsearray;

public class SparseArray {

	public static void main(String[] args) {
		//创建一个原始的二维数组11*11
		//0：没有棋子，1表示黑子，2表示蓝子
		int chessArr1[][] = new int [11][11];
		chessArr1[1][2] = 1;
		chessArr1[2][3] = 2;
		chessArr1[4][5] = 2;
		
		//输出原始的二维数组
		System.out.println("原始的二维数组：");
		for (int [] row : chessArr1) {
			for(int data: row) {
				System.out.printf("%d\t", data);
			}	
			System.out.println();
		}
	
		//将二维数组 转 稀疏数组
		//1、先遍历二维数组，得到非0数据的个数
		int sum = 0;
		for(int i=0; i<chessArr1.length; i++) {
			for(int j=0; j<chessArr1[0].length; j++) {
				if(chessArr1[i][j] != 0)
					sum++;
			}
		}
		System.out.println("sum="+sum);
		System.out.println("chessArr1.length="+chessArr1.length);
		System.out.println("chessArr1[0].length="+chessArr1[0].length);
		

		//2、创建对应的稀疏数组
		int sparseArr[][] = new int[sum+1][3];
		//给稀疏数组赋值
		sparseArr[0][0] = chessArr1.length;
		System.out.println("sparseArr[0][0]="+sparseArr[0][0]);
		sparseArr[0][1] = chessArr1[0].length;
		sparseArr[0][2] = sum;
		//遍历二维数组，将非0的值存放到稀疏数组中。


		int count = 0; //用于记录是第几个非0数据
		for(int i=0; i<chessArr1.length; i++) {
			for(int j=0; j<chessArr1[0].length; j++) {
				if(chessArr1[i][j] != 0) {
					count++;
					sparseArr[count][0] = i;
					sparseArr[count][1] = j;
					sparseArr[count][2] = chessArr1[i][j];
				}
				
			}
		}
		
		
		//输出稀疏数组的形式
		System.out.println();
		System.out.println("得到的稀疏数组为：");
		for(int i=0; i<sparseArr.length;i++) {
			System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
		}
		System.out.println();
		
		//将稀疏数组 转 二维数组
		//1、先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
		int chessArr2[][] =new int[sparseArr[0][0]][ sparseArr[0][1] ];
		
		//2、再读取稀疏数组后几行的数组，并赋给原始的二维数组即可。
		for (int i = 1; i < sparseArr.length; i++) {
			chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
		}
		//输出还原后的二维数组

		for(int[] row : chessArr2) {
			for(int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}
	}

}
