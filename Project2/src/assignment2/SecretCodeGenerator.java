/*  * EE422C Project 2 (Mastermind) submission by
 * Replace <...> with your actual data. 
 * <Student Name>
 * <Student EID>
 * Slip days used: <0>
 * Fall 2017
 */
package assignment2;

import java.util.Random;

public class SecretCodeGenerator {
	private static SecretCodeGenerator instance = new SecretCodeGenerator();
	public static SecretCodeGenerator getInstance() { return instance; }
	
	
	private Random randomGenerator;
	
	//Do not create your own SecretCodeGenerator Objects
	private SecretCodeGenerator()
	{
		randomGenerator = new Random();
	}
	
	//Use this method for each game only once.
	//The correct way to call this is: SecretCodeGenerator.getInstance().getNewSecretCode()
	public String getNewSecretCode()
	{
		String result="";
		int index, numberOfPegs=GameConfiguration.pegNumber;
		String[] colors=GameConfiguration.colors;
		for(int i=0; i<numberOfPegs; i++)
		{
			index=randomGenerator.nextInt(colors.length);
			result+=colors[index];
		}
		return result;
	}
}
