package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 *  
 * @author Holden Brown
 * 
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "mergesort";  
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		long sTime = System.nanoTime();
		mergeSortRec(points);
		long eTime = System.nanoTime();
		timeTaken = eTime - sTime;
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{	
		if (pts.length <= 1)
		{
			return;
		}
		else
		{
			int mid = pts.length/2;
			Point[] left = new Point[mid];
			Point[] right = new Point[pts.length - mid];
			
			for (int i = 0; i < left.length; i++)
			{
				left[i] = pts[i];
			}
			for (int i = 0; i < right.length; i++)
			{
				right[i] = pts[mid + i];
			}
			
			mergeSortRec(left);
			mergeSortRec(right);
			
			for (int i = 0; i < mergeArrays(left,right).length; i++)
			{
				pts[i] = mergeArrays(left,right)[i];
			}
			
		}
	}
	
	private Point[] mergeArrays(Point[] first, Point[] last)
	{
		int p = first.length;
		int q = last.length;
		Point[] result = new Point[p + q];
		
		int i = 0;
		int j = 0;
		int currIndex = 0;
		
		while (i < p && j < q)
		{
			if (pointComparator.compare(first[i], last[j]) <= 0)
			{
				result[currIndex] = first[i];
				currIndex++;
				i++;
			}
			else
			{
				result[currIndex] = last[j];
				j++;
				currIndex++;
			}
			
			if (i >= p)
			{
				for (int n = j; n < q; n++)
				{
					result[currIndex] = last[j];
					j++;
					currIndex++;
				}
			}
			else if (j >= q)
			{
				for (int n = i; n < p; n++)
				{
					result[currIndex] = first[i];
					i++;
					currIndex++;
				}
			}
		}
		
		return result;
	}
}
