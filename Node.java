package algoritem;

import java.util.Vector;

public class Node
{
	double y;
	
	double x;
	double value; 
	double value2;
	int pniot;
	int reverseValue;
	int count;
	int count2;
	Vector<String> allPaths;
	
	Node (double r, double u)
	{
		x = r;
		y = u;
		value2 = Double.POSITIVE_INFINITY;
		allPaths = new Vector<String>();
		
	}
}