/*  * EE422C Project 2 (Mastermind) submission by
 * Replace <...> with your actual data. 
 * Vijay Srinivasan
 * vks445
 * Slip days used: <0>
 * Fall 2017
 */

package assignment2;

public class Driver {

	public static void main(String[] args) {
		if(args.length == 1) {
			Game game = new Game(true);
			game.runGame();
		}
		else {
			Game game = new Game(false);
			game.runGame();
		}	
	}
}
