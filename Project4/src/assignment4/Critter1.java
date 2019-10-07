/*  * EE422C Project 4 (Critter) submission by
 * 10/29/18 
 * Vijay Srinivasan
 * vks445
 * Slip days used: <0>
 * Fall 2018
 */
package assignment4;

import java.util.List;

public class Critter1 extends Critter{
	int direction;
	int fights;
	
	public Critter1(){
		direction = Critter.getRandomInt(8);
		fights = 0;
	}
	
	@Override
	public void doTimeStep() {
		if(this.getEnergy() > 50 && this.getEnergy() < 100) {
			run(direction);
		}
		else if(this.getEnergy() > 25) {
			walk(direction);
		}
		else if(this.getEnergy() >= 100) {
			Critter1 child = new Critter1();
			reproduce(child, Critter.getRandomInt(8));
		}
		direction = Critter.getRandomInt(8);
	}

	@Override
	public boolean fight(String opponent) {
		if(this.getEnergy() < 50) {
			run(direction);
			return false;
		}
		else{
			fights++;
			return true;
		}

	}

	@Override
	public String toString () {
		return "1";
	}
	
	public static void runStats(List<Critter> list) {
		int total = 0;
		for(Critter c : list) {
			Critter1 crit = (Critter1) c;
			total += crit.fights;
		}
		System.out.println("Number of Critter1: " + list.size());
		System.out.println("Number of fights: " + total);
	}
}
