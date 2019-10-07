/*  * EE422C Project 2 (Mastermind) submission by
 * Replace <...> with your actual data. 
 * Vijay Srinivasan
 * vks445
 * Slip days used: <0>
 * Fall 2017
 */
package assignment2;

public class Code {
	
	public String secretCode;
	
	public Code(String code) {
		secretCode = code;
	}
	/*
	 * @param input user input (either guess or HISTORY)
	 * @return output of pegs (white and black)
	 */
	public String compareCode(String input) {
		int black = 0;
		int white = 0;
		String inputArray[] = input.split("", GameConfiguration.pegNumber);
		String codeArray[] = secretCode.split("", GameConfiguration.pegNumber);
		for(int i = 0; i < GameConfiguration.pegNumber; i++) {
			if(codeArray[i].equals(inputArray[i])) {
				codeArray[i] = "X";
				inputArray[i] = "Z";
				black++;
			}
		}
		//System.out.println(codeArray[0] + codeArray[1] + codeArray[2] + codeArray[3]);
		for(int j = 0; j < GameConfiguration.pegNumber; j++) {
			boolean used = false;
			for(int z = 0; z < GameConfiguration.pegNumber; z++) {
				if(codeArray[z].equals(inputArray[j]) && z != j && !used) {
					codeArray[z] = "X";
					white++;
					used = true;
				}
			}
		}
		
		return black + "b_" + white + "w";
	}

}
