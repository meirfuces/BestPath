package algoritem;

import java.util.ArrayList;

public class mainBestPath {
	public static void main(String[] args) {
		Node[][] mat = new Node[4][4];
		// 3+5+4+1+3+5=21	
		
		
			//--2----3----5--
			//|    |    |    |
			//10    1    4    8
			//|    |    |    | 
			//--4----3----1--
			//|    |    |    |
			//5    11    1    2
			//|    |    |    |
			//--2----5----3--
			//|    |    |    |
			//3    4    8    4
			//|    |    |    |
			//--1----8----3--
			
			mat[0][0] = new Node(1,3);  //right and up
			mat[0][1] = new Node(8,4);
			mat[0][2] = new Node(3,8);
			mat[0][3] = new Node(0,4);
			
			mat[1][0] = new Node(2,5);
			mat[1][1] = new Node(5,11);
			mat[1][2] = new Node(3,1);
			mat[1][3] = new Node(0,2);
			
			mat[2][0] = new Node(4,10);
			mat[2][1] = new Node(3,1);
			mat[2][2] = new Node(1,4);
			mat[2][3] = new Node(0,8);
			
			mat[3][0] = new Node(2,0);
			mat[3][1] = new Node(3,0);
			mat[3][2] = new Node(5,0);
			mat[3][3] = new Node(0,0);
			
			
			BestPath Test = new BestPath(mat, 2);

			System.out.println("Num of Path is " +Test.getNumOfCheapestPaths());
			System.out.println("The short path Length is: "+Test.getCheapestPrice());
			System.out.println("All paths = " + Test.getAllCheapestPaths());
			
			
			System.out.println("Num of Turns is: " +Test.printNumOfTurns());
			ArrayList<String> s = Test.getAllOptimalPaths();
			for (int i = 0; i < s.size(); i++) {
				System.out.println(s.get(i));
			}
			System.out.println("Num Optinmy is: " + Test.getNumOfOptimalPaths());
			System.out.println();
			
			
			//Part B
					System.out.println("*****PART B******");
	System.out.println();
	//2.1
	System.out.println("min 2 is: " + Test.getNumOfCheapestPaths2());
	//2.2
	System.out.println("number path with min pniot and  min 2 is: " + Test.getNumOfOptimalPaths2());
	
			//2.3
			System.out.println("get num optimi min2 is " + Test.getCheapestPrice2()); //		// 3+5+4+1+3+5=21
			//2.4
			System.out.println("the min pnit is : " + Test.getNumOfTurns2());
			//2.5
			System.out.println("the path with min2 and until Teta path is: " + Test.getAllCheapestPaths2());
			
			//2.6
			System.out.println("" + Test.getAllOptimalPaths2());
		}
}
