/*  * EE422C Project 2 (Mastermind) submission by
 * Replace <...> with your actual data. 
 * Vijay Srinivasan
 * vks445
 * Slip days used: <0>
 * Fall 2017
 */
package assignment2;

public class History {
	String[] history;
	int count;
	public History() {
		history = new String[12];
		count = 0;
		
	}
	/*
	 * @param x line to add to history array
	 * Increments count
	 */
	public void add(String x) {
		history[count] = x;
		count++;
	}
	/*
	 * Prints out array of all turns
	 */
	public void printHistory() {
		for(int i = 0; i < count; i++) {
			System.out.println(history[i]);
		}
	}
	/*
	 * Empties out array of History
	 */
	public void clearHistory() {
		for(int i = 0; i < count; i++) {
			history[i] = null;
		}
		count = 0;
	}
	
	
}
