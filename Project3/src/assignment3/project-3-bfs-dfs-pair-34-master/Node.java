package assignment3;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private ArrayList<String> path;
	private String wordy;
	private int length;
	public Node(ArrayList<String> p) {
		path = p;
	}
	public Node(ArrayList<String> pather, String word, int len) {
		path = pather;
		wordy = word;
		length = len;
	}
	
	public ArrayList<String> getPath() {
		return path;
	}
	
	public String getWord() {
		return wordy;
	}
	
	public int getLength() {
		return length;
	}

	public void setWord(String temp) {
		wordy = temp;		
	}
	
	public void setPath(ArrayList<String> pather) {
		path = pather;
	}
	
	public void setLength(int len) {
		length = len;
	}
}
