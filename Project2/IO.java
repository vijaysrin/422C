/*  * EE422C Project 2 (Mastermind) submission by
 * Replace <...> with your actual data. 
 * Vijay Srinivasan
 * vks445
 * Slip days used: <0>
 * Fall 2017
 */
package assignment2;
import java.util.*;

public class IO {
	Scanner in;
	boolean test;
	/*
	 * @param isTest decide whether or not to display secret code
	 */
	public IO(boolean isTest) {
		in = new Scanner(System.in);
		test = isTest;
		System.out.println("Welcome to Mastermind.");
	}
	/*
	 * @return true or false if the user wants to play a game
	 */
	public boolean getNewGame() {
		System.out.println("Do you want to play a new game? (Y/N):");
		String input = in.nextLine();
		if(input.equals("Y") || input.equals("y")) {
			
			return true;
		}
		else if(input.equals("N") || input.equals("n")) {
			return false;
		}
		else
			return false;
	}
	/*
	 * @param guesses amount of guesses remaining for user
	 * @param secretCode the secret code for specific game
	 * @return String user input
	 */
	public String getInput(int guesses, String secretCode) {
		boolean goodInput = false;
		List<String> colors = Arrays.asList(GameConfiguration.colors);
		while(!goodInput) {
			if(test)
				System.out.println("Secret Code: " + secretCode +  "\n\nYou have " + guesses + " guess(es) left.\nEnter guess:");
			else
				System.out.println("\nYou have " + guesses + " guess(es) left.\nEnter guess:");
			String input = in.nextLine();
			if(input.equals("HISTORY"))
				goodInput = true;
			else if(input.length() == GameConfiguration.pegNumber) {
				goodInput = true;
				for(int i = 0; i < GameConfiguration.pegNumber; i++) {
					if(!colors.contains(String.valueOf(input.charAt(i))))  {
						goodInput = false;
						System.out.println("INVALID_GUESS");
						break;
					}
				}
			}
			else {
				System.out.println("INVALID_GUESS");
			}
			if(goodInput) {
				return input;
			}
		}
		return null;
	}

}
