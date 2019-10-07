package assignment3;

import java.util.ArrayList;

public class Node {
	
	private ArrayList<String> path;
	private String wordy;
	private int length;
	
	/**
	 * Constructor for Node
	 * @param p (path, ArrayList of String)
	 */
	public Node(ArrayList<String> p) {
		path = p;
	}
	
	/**
	 * Constructor for Node
	 * @param pather (path, ArrayList of String)
	 * @param word (initial word)
	 * @param len (initial length - will be updated)
	 */
	public Node(ArrayList<String> pather, String word, int len) {
		path = pather;
		wordy = word;
		length = len;
	}
	
	/**
	 * Path getter
	 * @return the path
	 */
	public ArrayList<String> getPath() {
		return path;
	}
	
	/**
	 * Word getter
	 * @return the word
	 */
	public String getWord() {
		return wordy;
	}
	
	/**
	 * Length getter
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Word setter
	 * @param temp (the word to set it to)
	 */
	public void setWord(String wordie) {
		wordy = wordie;		
	}
	
	/**
	 * Path setter
	 * @param pather (the path you need to set)
	 */
	public void setPath(ArrayList<String> pather) {
		path = pather;
	}
	
	/**
	 * Length setter
	 * @param len (the length you want to set)
	 */
	public void setLength(int len) {
		length = len;
	}
}
