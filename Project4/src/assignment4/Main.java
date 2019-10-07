
/*  * EE422C Project 4 (Critter) submission by
 * 10/29/18 
 * Vijay Srinivasan
 * vks445
 * Slip days used: <0>
 * Fall 2018
 */package assignment4;
/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */

import java.util.Scanner;
import java.io.*;
import java.util.*
;
/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        /*
        for(int i=0; i < 100; i++) {
            try {
                Critter.makeCritter("Algae");
            }
            catch (InvalidCritterException c) {
                System.out.println("Error - Algae initialization");
            }
        }
        
        for(int j=0; j < 25; j++) {
            try{
                Critter.makeCritter("Craig");
            }
            catch (InvalidCritterException c) {
                System.out.println("Error - Craig initialization");
            }
        }
        */
        driver();
        // System.out.println("GLHF");
        
        /* Write your code above */
        System.out.flush();

    }
    
    public static void driver() {
    	while(true) {
    		System.out.print("critters> ");
    		String[] input = kb.nextLine().split(" ");
    		if(input.length <= 0) {
    			System.out.println("invalid command");
    		}
    		else {
    			
    			if(input[0].equals("quit")) {
    				if(input.length == 1) {
    					break;
    				}
    				else {
    					printInvalid(input);
    				}	
    			}
    			
    			else if(input[0].equals("step")) {
    				if(input.length == 1) {
    					Critter.worldTimeStep();
    				}
    				else {
    					int amount;
    					try {
    						amount = Integer.parseInt(input[1]);
    					}catch(NumberFormatException c) {
    						printError(input);
    						continue;
    					}
    					for(int i = 0; i < amount; i++) {
    						Critter.worldTimeStep();
    					}
    				}
    			}
    			
    			else if(input[0].equals("show")) {
    				if(input.length == 1) {
    					Critter.displayWorld();
    				}
    				else {
    					printInvalid(input);
    				}
    			}
    			
    			else if(input[0].equals("seed")) {
    				if(input.length != 2) {
    					printInvalid(input);
    				}
    				else {
    					long seedVal;
    					try {
    						seedVal = Long.parseLong(input[1]);
    					}catch(NumberFormatException c) {
    						printError(input);
    						continue;
    					}
    					Critter.setSeed(seedVal);
    				}
    			}
    			
    			else if(input[0].equals("make")) {
    				if(input.length == 1) {
    					printError(input);
    				}
    				else if(input.length == 2) {
    					String newcrit = input[1];
    					try {
    						Critter.makeCritter(newcrit);
    					}catch(InvalidCritterException c) {
    						printInvalid(input);
    					}
    				}
    				else if(input.length == 3) {
    					String newcrit = input[1];
    					int amount = 0;
    					try {
    						amount = Integer.parseInt(input[2]);
    					}catch(NumberFormatException c) {
    						printInvalid(input);
    						continue;
    					}
    					for(int i = 0; i < amount; i++) {
						
	    					try {
	    						Critter.makeCritter(newcrit);
	    					}catch(InvalidCritterException c) {
	    						printInvalid(input);
	    						break;
	    					}
    					}
    				}
    				else {
    					printInvalid(input);
    					continue;
    				}
    			}
    			else if(input[0].equals("stats")) {
    				if(input.length == 2) {
    					String newClass = input[1];
    					List<Critter> list = null;
    					try {
    						list = Critter.getInstances(newClass);
    					}catch(InvalidCritterException c) {
    						printError(input);
    						continue;
    					}
    					Critter.runStats(list);
    				}
    				else {
    					printInvalid(input);
    				}
    			}
    			else {
    				printInvalid(input);
    			}
    		}
    	}
    }
    
    public static void printInvalid(String[] command) {
    	System.out.print("invalid command: ");
    	for(int i = 0; i < command.length; i++) {
    		System.out.print(command[i] + " ");
    	}
    	System.out.println();
    }
    
    public static void printError(String[] command) {
    	System.out.print("error processing: ");
    	for(int i = 0; i < command.length; i++) {
    		System.out.print(command[i] + " ");
    	}
    	System.out.println();
    }
}
