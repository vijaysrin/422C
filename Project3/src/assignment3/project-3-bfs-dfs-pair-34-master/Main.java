/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Fall 2018
 */


package assignment3;
import java.util.*;
import java.io.*;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	// static variables and constants only here.
	
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
		// TODO methods to read in words, output ladder
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList<String> h = new ArrayList<String>();
		String word1 = keyboard.next();
		if(word1 == "/quit")
			return h;
		String word2 = keyboard.next();
		h.add(word1);
		h.add(word2);
		return h;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// If ladder is empty, return list with just start and end.
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code
		
		return null; // replace this line later with real return
	}
	
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

        	//avoid double picking, remove start in uppder case version
        	dict.remove(start.toUpperCase());
        	
        	while(!(lister.isEmpty()) && !(lister.peek().getWord().equals(end.toLowerCase()))) {
        		Node new_curr = lister.remove();
        		
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
        		return lister.peek().getPath(); //if queue is empty = no path til endword, else return path from head	
    	}
    	return null;
    }
/*    public static int getWordLadderLen(String start, String end) {	
    	LinkedList<Node> lister = new LinkedList<Node>();
		Set<String> dict = makeDictionary();
    	Node obj = new Node(start, 1);
    	lister.add(obj);

    	
    	while(!(lister.isEmpty())) {
    		Node new_curr = lister.remove();
    		String temp_word = new_curr.getWord();
    		
    		if(temp_word.equals(end))
    			return new_curr.getLength();
    		
    		//convert temp_word into all upper case
    		//String temp2_word = temp_word.toUpperCase();
    		
    		char[] check_word = temp_word.toCharArray();
    		for(int i = 0; i < check_word.length; i++) {
    			for(char x = 'A'; x <= 'Z'; x++) {
    				char tmp = check_word[i];
    				if(check_word[i] != x)
    					check_word[i] = x;
    				String check_with_dict = new String(check_word);
    				if(dict.contains(check_with_dict)) {
    					Node ladder_bar = new Node(check_with_dict, new_curr.getLength() + 1);
    					lister.add(ladder_bar);
    					dict.remove(check_with_dict);
    				}
    				check_word[i] = tmp;
    			}
    		}
    		
    	}
    	return 0;
	}
    
	*/
	public static void printLadder(ArrayList<String> ladder) {
		System.out.println("a " + (ladder.size()-2) + "-rung word ladder exists between " + ladder.get(0) + " and " + ladder.get(ladder.size()-1));
		for(int i = 0; i < ladder.size(); i++) {
			System.out.println(ladder.get(i));
		}
	}
	// TODO
	// Other private static methods here


	/* Do not modify makeDictionary */
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

/*

        		char[] check_word = temp_word.toCharArray();
        		for(int i = 0; i < check_word.length; i++) {
        			for(char x = 'A'; x <= 'Z'; x++) {
        				char tmp = check_word[i];
        				if(check_word[i] != x)
        					check_word[i] = x;
        				String check_with_dict = new String(check_word);
        				if(dict.contains(check_with_dict)) {
        					Node ladder_bar = new Node(check_with_dict, new_curr.getLength() + 1);
        					lister.add(ladder_bar);
        					dict.remove(check_with_dict);
        				}
        				check_word[i] = tmp;
        			}
        		}
        		*/

