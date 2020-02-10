package algoritem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
/**
 * 
 * @author Meir Fuchs
 *
 */


public class BestPath {
	Node[][]Mymat;
	int teta;
	double min2;
	Vector<String> allPaths;
	ArrayList<String> min2Path;
	
	/*
	 * constructor Best Path
	 */
	 
	 
public BestPath (Node[][]mat, int teta){
	this.Mymat = new Node [mat.length][mat.length];
	this.teta = teta;
	allPaths = new Vector<String>();
	min2Path = new ArrayList<String>();
	for (int i = 0; i < Mymat.length; i++) {
		for (int j = 0; j < Mymat[i].length; j++) {
			this.Mymat[i][j] = new Node(mat[i][j].x,mat[i][j].y );
		}
	}
	this.Mymat[0][0].value = 0;
	this.Mymat[0][0].count = 1;
	init();
}
//******PART A*****

/*
 * function to build Matrix. init row and column and then check from [1,1] to [n,n] what is the minimum right
 * and up, or right and then up.
 */
public void  init() {
	
	// init the first column 
	for (int i = 1; i < this.Mymat.length; i++) {
		this.Mymat[i][0].value = Mymat[i-1][0].value + Mymat[i-1][0].y;
		this.Mymat[i][0].count = Mymat[i-1][0].count;
	}
	// init the first row
	for (int j = 1; j < Mymat[0].length; j++) {
		Mymat[0][j].value = Mymat[0][j-1].value + Mymat[0][j-1].x;
		Mymat[0][j].count = Mymat[0][j-1].count;
	}
// check Minimum - up and right or right and then up
	for (int i = 1; i < Mymat.length; i++) {
		for (int j = 1; j < Mymat[0].length; j++) {
			if (Mymat[i][j-1].value + Mymat[i][j-1].x < Mymat[i-1][j].value + Mymat[i-1][j].y) //  up and then right is minimum
			{
				Mymat[i][j].value = Mymat[i][j-1].value + Mymat[i][j-1].x;
				Mymat[i][j].count = Mymat[i][j-1].count; 
			}
			else
				if (Mymat[i][j-1].value + Mymat[i][j-1].x > Mymat[i-1][j].value + Mymat[i-1][j].y) //// right and then up
				{
					Mymat[i][j].value = Mymat[i-1][j].value + Mymat[i-1][j].y;
					Mymat[i][j].count = Mymat[i-1][j].count;
				}
				else // equal
				{
					Mymat[i][j].value = Mymat[i-1][j].value + Mymat[i-1][j].y;
					Mymat[i][j].count = Mymat[i-1][j].count + Mymat[i][j-1].count;
				}
		}
	}
	
	
	
	
	
}

 //this Methood need to return Cheepest price from src to dest 

public double getCheapestPrice() {
		return Mymat[Mymat.length-1][Mymat[0].length-1].value;

}
			//*****getNumOfOptimalPaths()***
// this Methood need to return how many path has with Shortest path

public int getNumOfOptimalPaths() {
	int size = getAllOptimalPaths().size();
	return size;
}

// return Min turns need to do in the Shortest path
public int printNumOfTurns() {
	ArrayList<String> AllPathList = InitAllPath(this.Mymat, this.Mymat.length-1, this.Mymat[0].length-1, "", new ArrayList<String>());
	int min=Integer.MAX_VALUE;
	for (int i=0; i<AllPathList.size(); i++) {
		int count=1;
		String A= AllPathList.get(i);
		for (int j=0; j<A.length()-1; j++) {
			if (A.charAt(j)!=A.charAt(j+1))
					count++;
		}
		if (count<min)
			min =count;
	}
	return min;
}

// return List String with all the Shortest path and MinPniot

public ArrayList<String> getAllOptimalPaths()
{
	ArrayList<String> OPT = new ArrayList<String>();
	ArrayList<String> AllPathList = InitAllPath(this.Mymat, this.Mymat.length-1, this.Mymat[0].length-1, "", new ArrayList<String>()); 
	int MinPniot = printNumOfTurns();
	for (int i=0; i<AllPathList.size(); i++) {
		int count=1;
		String A= AllPathList.get(i);
		for (int j=0; j<A.length()-1; j++) {
			if (A.charAt(j)!=A.charAt(j+1))
					count++;
		}
		if (count ==MinPniot)
			OPT.add(A);
	}
	return OPT;
}
//return List String with all the Shortest path

public ArrayList<String> InitAllPath(Node[][] mat, int i, int j, String path, ArrayList<String> paths) {
	if (i==0 && j==0) //0 is right
		paths.add(path);
	else
		if (i==0 && j>0)
		{
			InitAllPath(mat,  i,  j-1, "0" + path, paths);
		}
		else
			if (i>0 && j==0)
			{
			
				InitAllPath(mat,  i-1,  j, "1" + path, paths);
			}
			else 
				if (mat[i][j-1].value + mat[i][j-1].x < mat[i-1][j].value + mat[i-1][j].y)
				{
					InitAllPath(mat,  i,  j-1, "0" + path, paths);
				}
				else 
					if (mat[i][j-1].value + mat[i][j-1].x > mat[i-1][j].value + mat[i-1][j].y)
					{
						InitAllPath(mat,  i-1,  j, "1" + path, paths);
					} 
					else if (mat[i][j-1].value + mat[i][j-1].x == mat[i-1][j].value + mat[i-1][j].y) // they are equals
					{
						
						InitAllPath(mat,  i-1,  j, "1" + path, paths);
						
						InitAllPath(mat,  i,  j-1, "0" + path, paths);
						
					}
	return paths;
}

public ArrayList<String> getAllCheapestPaths(){
return InitAllPath(this.Mymat, this.Mymat.length-1, this.Mymat[0].length-1, "", new ArrayList<String>()); 	
}

	public int getNumOfCheapestPaths() {
		return Mymat[Mymat.length-1][Mymat[0].length-1].count;
	}
	
	
	/// Part B
	

	
	//2.1
	public int getNumOfCheapestPaths2()
 {
		this.min2= wholeSearch(Mymat);
		//ArrayList<String> CheapestPaths2 = new ArrayList<String>();
		this.min2Path = new ArrayList<String>();
		for(String pathCurr : allPaths) 
		{

			double sum = 0;
			int i=0,j=0;
			for (int k = 0; k < pathCurr.length(); k++) 
			{
				if(pathCurr.charAt(k) == '0') 
				{ 
					sum += this.Mymat[i][j].x;
					j++;
				}
				else 
				{
					sum += this.Mymat[i][j].y;
					i++;
				}
			}

				if(sum==min2) {
					min2Path.add(pathCurr);
				
				}
			}
		
		
		
		//ArrayList<String> ch = getAllCheapestPaths2();
		return min2Path.size();
	}
	//2.2
	public int getNumOfOptimalPaths2() {
	ArrayList<String> optiPath = getAllOptimalPaths2();
	return optiPath.size();
	}
	
	//2.3
	public double getCheapestPrice2()

	{
		//this.min2= wholeSearch(Mymat);
		return this.min2;	
	}
	
	//2.4
	public int getNumOfTurns2() {		
		int min=Integer.MAX_VALUE;
		for (int i=0; i<min2Path.size(); i++) {
			int count=1;
			String A= min2Path.get(i);
			for (int j=0; j<A.length()-1; j++) {
				if (A.charAt(j)!=A.charAt(j+1))
						count++;
			}
			if (count<min)
				min =count;
		}
		return min;
		
	}
	//2.5
	
	public ArrayList<String> getAllCheapestPaths2()
	{
		if (this.min2Path.size()<=teta) {
		
			return this.min2Path;
		}
		else {
			ArrayList<String> listTeta = new ArrayList<String>();
			for (int i = 0; i<teta; i++) {
				System.out.println("heeloo");
				listTeta.add(min2Path.get(i));
			}
			return listTeta;
		}
	
	}
	
	//2.6
	public ArrayList<String> getAllOptimalPaths2(){
		ArrayList<String> OPT = new ArrayList<String>();
//		ArrayList<String> AllPathList = getAllCheapestPaths2();

		int MinPniot = getNumOfTurns2();
		for (int i=0; i<this.min2Path.size(); i++) {
			int count=1;
			String A= this.min2Path.get(i);
			for (int j=0; j<A.length()-1; j++) {
				if (A.charAt(j)!=A.charAt(j+1))
						count++;
			}
			if (count ==MinPniot)
				OPT.add(A);
		}
		return OPT;
	
	}
	
	
	//**Help function
	
	public double wholeSearch(Node[][] mat) {
		this.allPaths = getAllPermotstions(mat);
		double min1 = getCheapestPrice();
		double min2 = Double.MAX_VALUE; 
		for(String pathCurr : allPaths) 
		{
			double sum = 0;
			int i=0,j=0;
			for (int k = 0; k < pathCurr.length(); k++) 
			{
				if(pathCurr.charAt(k) == '0') 
				{ 
					sum += mat[i][j].x;
					j++;
				}
				else 
				{
					sum += mat[i][j].y;
					i++;
				}
			}

				if(sum > min1 && sum<min2) min2 =sum;
			}
			return min2;

		}
	
	
	public static Vector<String> getAllPermotstions(Node[][] mat) {
		Vector<String> ans = new Vector<String>();
		getAllPaths(ans,mat,mat.length-1,mat[0].length-1,"");
		return ans;
	}

	private static void getAllPaths(Vector<String> ans, Node[][] mat,int i, int j, String temp) {
		if(i == 0 && j == 0) {
			ans.add(temp);
			return;
		}
		else if(i == 0) {
			getAllPaths(ans,mat,0,j-1,temp + "0");
		}
		else if(j == 0) {
			getAllPaths(ans,mat,i-1,0,temp + "1");
		}
		else {
			getAllPaths(ans,mat,i,j-1,temp + "0");
			getAllPaths(ans,mat,i-1,j,temp + "1");
		}
	}
	
	
}

