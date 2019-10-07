/*  * EE422C Project 2 (Mastermind) submission by
 * Replace <...> with your actual data. 
 * Vijay Srinivasan
 * vks445
 * Slip days used: <0>
 * Fall 2017
 */
package assignment2;


public class Game {
	public String secretCode;
	public int guesses;
	public boolean test;
	
	public Game(boolean test) {
		this.test = test;
		guesses = GameConfiguration.guessNumber;
		secretCode = SecretCodeGenerator.getInstance().getNewSecretCode();
		//secretCode = "YRBY";
	}
	/*
	 * Run main game loop
	 */
	public void runGame() {
		
		IO gameControl = new IO(test);
		boolean newGame = gameControl.getNewGame();
		while(newGame) {
			History history = new History();
			Code code = new Code(secretCode);
			while(guesses > 0) {
				String input = gameControl.getInput(guesses, secretCode);
				if(input.equals("HISTORY")) {
					//Display History
					history.printHistory();
				}
				else {
					String output = code.compareCode(input);
					String addHis = input + " -> " + output;
					history.add(addHis);
					System.out.println(addHis);
					if(Character.getNumericValue(output.charAt(0)) == GameConfiguration.pegNumber) {
						System.out.println("You win!\n");
						break;
					}
					guesses--;
				}
				if(guesses == 0) {
					System.out.println("You lose! The pattern was " + secretCode);
				}
				
			}
			newGame = gameControl.getNewGame();
			guesses = GameConfiguration.guessNumber;
			history.clearHistory();
			secretCode = SecretCodeGenerator.getInstance().getNewSecretCode();
		}
	}
}
