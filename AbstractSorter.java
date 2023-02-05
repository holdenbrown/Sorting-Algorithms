package edu.iastate.cs228.hw2;

/**
 *  
 * @author Holden Brown
 *
 */

import java.util.Comparator;
import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later the sorted) sequence. 
 *
 */
public abstract class AbstractSorter
{	
	
	protected Point[] points;    // array of points operated on by a sorting algorithm. 
	                             // stores ordered points after a call to sort(). 
	
	protected String algorithm = null; // "selection sort", "insertion sort", "mergesort", or
	                                   // "quicksort". Initialized by a subclass constructor.
		 
	protected Comparator<Point> pointComparator = null;  
	
	protected long timeTaken; // holds the time of the scan in nano seconds
	
	
	// Add other protected or private instance variables you may need. 
	
	class c implements Comparator<Point>{
		
		@Override
		public int compare(Point p1, Point p2) {
			return p1.compareTo(p2);
		}
		
	}
	

	protected AbstractSorter()
	{
		// No implementation needed. Provides a default super constructor to subclasses. 
		// Removable after implementing SelectionSorter, InsertionSorter, MergeSorter, and QuickSorter.
	}
	
	
	/**
	 * This constructor accepts an array of points as input. Copy the points into the array points[]. 
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException
	{
		if(pts.length == 0 || pts == null) {
			System.out.println("IllegalArgumentException");
			throw new IllegalArgumentException();
		}
		else {
			points = new Point[pts.length];
			for(int i = 0; i < pts.length; i++) {
				points[i] = pts[i];
			}
		}
	}

		
	
	
	

	/**
	 * Generates a comparator on the fly that compares by x-coordinate if order == 0, by y-coordinate
	 * if order == 1. Assign the 
     * comparator to the variable pointComparator. 
     *  
	 * 
	 * @param order  0   by x-coordinate true
	 * 				 1   by y-coordinate false
	 * 			    
	 * 
	 * @throws IllegalArgumentException if order is less than 0 or greater than 1
	 *        
	 */
	public void setComparator(int order) throws IllegalArgumentException
	{
		if(order < 0 || order > 1 ) {
			System.out.println("IllegalArgumentException");
			throw new IllegalArgumentException();
		}
		else if(order == 1) {
			Point.setXorY(false);
		}
		else{
			Point.setXorY(true);
		}
		pointComparator = new c();
	}

	

	/**
	 * Use the created pointComparator to conduct sorting.  
	 * 
	 * Should be protected. Made public for testing. 
	 */
	public abstract void sort(); 
	
	
	/**
	 * Obtain the point in the array points[] that has median index 
	 * 
	 * @return	median point 
	 */
	public Point getMedian()
	{
		return points[points.length/2]; 
	}
	
	
	/**
	 * Copys the array points[] onto the array pts[]. 
	 * 
	 * @param pts
	 */
	public void getPoints(Point[] pts)
	{
		for(int i = 0; i < pts.length; i++) {
			pts[i] = points[i];
		}
	}
	

	/**
	 * Swaps the two elements indexed at i and j respectively in the array points[]. 
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j)
	{
		Point k = new Point(points[i].getX(),points[i].getY());
		points[i] = points[j];
		points[j] = k;
	}	
	
	protected long time()
	{
		return timeTaken;
	}
}
