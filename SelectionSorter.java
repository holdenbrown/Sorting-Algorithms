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
 * This class implements selection sort.   
 *
 */


public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		algorithm = "selection sort";
	}

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		long sTime = System.nanoTime();
        int len = points.length;
   	 
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < len - 1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < len; j++)
                if (pointComparator.compare(points[j],points[min_idx]) < 0)
                    min_idx = j;
 
            // Swap the found minimum element with the first
            // element
            swap(i, min_idx);
        }
        long eTime = System.nanoTime();
        timeTaken = eTime - sTime;
	}	
}
