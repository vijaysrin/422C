/*  * EE422C Project 4 (Critter) submission by
 * 10/29/18 
 * Vijay Srinivasan
 * vks445
 * Slip days used: <0>
 * Fall 2018
 */
package assignment4;

import java.util.List;

public class Critter2 extends Critter{
	int direction;
	int fights;
	
	public Critter2(){
		direction = Critter.getRandomInt(8);
		fights = 0;
	}
	
	@Override
	public void doTimeStep() {
		if(Critter.getRandomInt(2) / 2 == 1) {
			run(direction);
		}
		else {
			walk(direction);
		}
		
		if(Critter.getRandomInt(2) / 2 == 1 && this.getEnergy() > Params.min_reproduce_energy) {
			Critter2 child = new Critter2();
			reproduce(child, Critter.getRandomInt(8));
		}
		direction = Critter.getRandomInt(8);
	}

	@Override
	public boolean fight(String opponent) {
		if(this.getEnergy() < 10) {
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
		return "2";
	}
	
	public static void runStats(List<Critter> list) {
		int total = 0;
		for(Critter c : list) {
			Critter1 crit = (Critter1) c;
			total += crit.fights;
		}
		System.out.println("Number of Critter2: " + list.size());
		System.out.println("Number of fights: " + total);
	}
}
