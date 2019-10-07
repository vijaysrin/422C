/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Jacob Stokes
 * jts3867
 * 16345
 * Vijay Srinivasan
 * vks445
 * 16345
 * Slip days used: <0>
 * Git URL: https://github.com/EE422C-Fall-2018/project-3-bfs-dfs-pair-34
 * Fall 2018
 */


package assignment3;
import java.util.*;
import java.io.*;
import java.util.Queue;
import java.util.ArrayList;

public class Main {
	
	//static variables and constants only here.
	public static HashMap<String, ArrayList<String>> map;
	/**
	 * Main driver function, initializes keyboard and sets the heap (for grading only)		
	 * @param args (command-line arguments)
	 * @throws Exception (may throw an exception)
	 */
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file, for student testing and grading only
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default input from Stdin
			ps = System.out;			// default output to Stdout
		}
		initialize();
		
		ArrayList<String> parsed = parse(kb);
		if(parsed.size() == 0)
			return;
		ArrayList<String> bfs_ladder = getWordLadderBFS(parsed.get(0), parsed.get(1));
		printLadder(bfs_ladder);
		ArrayList<String> dfs_ladder = getWordLadderDFS(parsed.get(0), parsed.get(1));
		printLadder(dfs_ladder);

	}
	
	/**
	 * This function initializes any static variables or constants
	 * This will be called before running any JUNIT tests
	 * Hence, call it only once at the start of main
	 */
	public static void initialize() {
		map = createMap(makeDictionary());
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		//make an empty ArrayList to return if /quit is given as first input
		ArrayList<String> ret = new ArrayList<String>();
		
		//collect first word
		String word1 = keyboard.next();
		
		//check if user wants to quit
		if(word1.equals("/quit"))
			return ret;
		
		//collect second word for destination
		String word2 = keyboard.next();
		
		//add words to the ArrayList
		ret.add(word1);
		ret.add(word2);
		
		//return parsed input
		return ret;
	}
	
	/**
	 * This function is the helper function for DFS search
	 * @param start (first input for word ladder search)
	 * @param end (the destination word)
	 * @param visited (set of visited words)
	 * @param map (HashMap of all words and their adjacent words)
	 * @return ArrayList<String> of the word ladder or null if doesn't exist
	 */
	public static ArrayList<String> getWordLadderDFSHelper(String start, String end, HashSet<String> visited, HashMap<String, ArrayList<String>> map) {
		//Check if we have arrived at the final word and return arraylist if true
		if(start.equals(end)) {
			ArrayList<String> ended = new ArrayList<String>();
			ended.add(end.toLowerCase());
			return ended;
		}
		
		//get arraylist of all adjacent words to start
		ArrayList<String> adj = map.get(start);
		
		//remove all words that have already been visited
		for(int i = adj.size() - 1; i >= 0; i--) {
			if(visited.contains(adj.get(i))) {
				adj.remove(i);
			}
		}
		
		//loop through all adjacent words
		while(!adj.isEmpty()) {
			
			//Find adjacent word that is closest to end word
			int ix = 0;
			int diff = 0;
			int closest = end.length();
			for(int i  = 0; i < adj.size(); i++) {
				diff = 0;
				for(int j = 0; j < adj.get(i).length(); j++) {
					if(end.charAt(j) != adj.get(i).charAt(j)){
						diff++;
					}
					if(diff < closest) {
						closest = diff;
						ix = i;
					}
				}
			}
			
			//remove word from list, add it to visited list, and run recursion with new word
			String nextWord = adj.remove(ix);
			visited.add(nextWord);
			ArrayList<String> result = getWordLadderDFSHelper(nextWord, end, visited, map);
			
			//if end has been found
			if(!result.isEmpty()) {
				result.add(0, start.toLowerCase());
				return result;
			}

		}
		//return empty list if no path was found
		return adj;
		
 	
	}
	/**
	 * This function returns a word ladder using DFS recursion given a start and end
	 * @param start (first input for word ladder search)
	 * @param end (the destination word)
	 * @return ArrayList<String> of the word ladder
	 */
	public static ArrayList<String> getWordLadderDFS(String start, String end) {

		Set<String> dict = makeDictionary();
		//Set both words to upper case
		start = start.toUpperCase();
		
		ArrayList<String> result = new ArrayList<String>();
		if(end.equals("/quit")) {
			result.add(start.toLowerCase());
			result.add(end.toLowerCase());
			return result;
		}
			
	
		end = end.toUpperCase();
		//Create HashSet to store visited words
		HashSet<String> visited = new HashSet<String>();
		visited.add(start);
		
		//Run helper function
		result = getWordLadderDFSHelper(start, end, visited, map);
		
		//If the return is empty, add start and end
		if(result.isEmpty()) {
			result.add(start.toLowerCase());
			result.add(end.toLowerCase());
		}
		return result;
		
	}
	
	/**
	 * This function is a boolean checker if the words are adjacent (like cat and hat)
	 * @param one (word to be tested for adjacency)
	 * @param two (word to be tested against for adjacency
	 * @return
	 */
	public static boolean isadj(String one, String two) {
		String oner = one.toUpperCase();
		String twor = two.toUpperCase();
		int cnt = 0;
		int dif = one.length();
		for(int i = 0; i < dif; i++) {
			if(oner.charAt(i) != (twor.charAt(i)))
				cnt++;
			if(cnt > 1)
				return false;
		}
		if(cnt == 1)
			return true;
		else
			return false;
	}
	
	/**
	 * This function returns an N-2 word ladder where N is the number of words from start to finish including the start and finish
	 * @param start (the start word)
	 * @param end (the end word)
	 * @return ArrayList<String> (Full word ladder)
	 */
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
    	//create dictionary
		Set<String> dict = makeDictionary();
		
		//only if the upper case version of the words are inside the dict, do something
    	if(dict.contains(end.toUpperCase()) && dict.contains(start.toUpperCase())) {
        	
    		//create a path to follow for real entries
    		ArrayList<String> real_path = new ArrayList<String>(); //create arraylist for real path
    		real_path.add(start.toLowerCase());  //add the first element inside path
    		
    		//Non final paths go to queue
    		Queue<Node> lister = new LinkedList<Node>(); //create a fifo queue
        	Node obj = new Node(real_path, start.toLowerCase(), 1); //nodes have a word, path, and length
        	lister.add(obj); //add first in queue

        	//avoid double picking, remove start in upper case version
        	dict.remove(start.toUpperCase());
        	
        	while(!(lister.isEmpty()) && !(lister.peek().getWord().equals(end.toLowerCase()))) {
        		Node new_curr = lister.remove(); //set current removed from the queue
        		
        		String temp_word = new_curr.getWord();
        		
        		if(temp_word.equals(end.toLowerCase()))
        			return new_curr.getPath();
        		
        		//convert temp_word into all upper case
        		//String temp2_word = temp_word.toUpperCase();
        		Iterator<String> itty = dict.iterator();
        		while(itty.hasNext()) {
        			String temp = itty.next();
        			if(isadj(temp, new_curr.getWord())) {
        				ArrayList<String> list = new ArrayList<String>(new_curr.getPath());
        				list.add(temp.toLowerCase());
        				
        				//words differ by one
        				Node dump = new Node(list, temp.toLowerCase(), new_curr.getLength()+1);
        				lister.add(dump);
        				
        				//done with word
        				itty.remove();
        			}
        		}

        	}
        	if(!(lister.isEmpty()))
        		return lister.peek().getPath(); //if queue is not empty, there is a word ladder that does indeed exist
    	}
    	//if queue is empty, that means the input is invalid or we could not find a ladder
    	ArrayList<String> ret = new ArrayList<String>(); //create empty ArrayList of String
    	ret.add(start.toLowerCase()); //fill it with start
    	ret.add(end.toLowerCase()); //and with finish
    	return ret; //return that variable
    }
    
    /**
     * This function prints out the ladder or the no-print message if non applicable
     * @param ladder (the ladder that needs to be printed)
     */
	public static void printLadder(ArrayList<String> ladder) {
		
		//test if size is 2 because it can only get here if no ladder exists
		if(ladder.size() == 2)
			System.out.println("no word ladder can be found between " + ladder.get(0) + " and " + ladder.get(ladder.size()-1) + ".");
		
		//print out the message for the proper ladder
		else {
			System.out.println("a " + (ladder.size()-2) + "-rung word ladder exists between " + ladder.get(0) + " and " + ladder.get(ladder.size()-1) + ".");
			for(int i = 0; i < ladder.size(); i++) {
				System.out.println(ladder.get(i));
			}
		}
	}

	/**
	 * This function makes a HashMap of all words and their adjacent words
	 * @param dict (set of all words to reference)
	 * @return HashMap that contains all words in dict
	 */
	public static HashMap<String, ArrayList<String>> createMap(Set<String> dict){
		
		//declare HashMap to return
		HashMap<String, ArrayList<String>> result = new HashMap<>();
		
		//Iterate through dict and add to HashMap
		for(String str : dict) {
			ArrayList<String> list = new ArrayList<String>();
			result.put(str, list);
			for(String str2 : dict) {
				
				//Use isadj() function to check if a word is adjacent to the dict word
				if(isadj(str, str2)) {
					list.add(str2);
				}
			}
		}
		return result;
	}
	


	/**
	 * This function makes a Dictionary set
	 * @return Set <String> (a set String for the new made Dictionary)
	 */
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
}
