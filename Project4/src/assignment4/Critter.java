/*  * EE422C Project 4 (Critter) submission by
 * 10/29/18 
 * Vijay Srinivasan
 * vks445
 * Slip days used: <0>
 * Fall 2018
 */
package assignment4;
/* CRITTERS Critter.java
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



import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	//private boolean inFight;
	private boolean moved;
	
	protected final void walk(int direction) {
		switch(direction) {
			case 0:{
				x_coord = (x_coord + 1) % Params.world_width;
			}
			case 1:{
				x_coord = (x_coord + 1) % Params.world_width;
				y_coord = (y_coord + 1) % Params.world_height;
			}
			case 2:{
				y_coord = (y_coord + 1) % Params.world_height;
			}
			case 3:{
				y_coord = (y_coord + 1) % Params.world_height;
				if(x_coord == 0) {
					x_coord = Params.world_width - 1;
				}
				else {
					x_coord--;
				}
			}
			case 4:{
				if(x_coord == 0) {
					x_coord = Params.world_width - 1;
				}
				else {
					x_coord--;
				}
			}
			case 5:{
				if(x_coord == 0) {
					x_coord = Params.world_width - 1;
				}
				else {
					x_coord--;
				}
				if(y_coord == 0) {
					y_coord = Params.world_height - 1;
				}
				else {
					y_coord--;
				}
			}
			case 6:{
				if(y_coord == 0) {
					y_coord = Params.world_height - 1;
				}
				else {
					y_coord--;
				}
			}
			case 7:{
				if(y_coord == 0) {
					y_coord = Params.world_height - 1;
				}
				else {
					y_coord--;
				}
				x_coord = (x_coord + 1) % Params.world_width;
			}
		}
		energy -= Params.walk_energy_cost;
		moved = true;
	}
	
	protected final void run(int direction) {
		switch(direction) {
			case 0:{
				x_coord = (x_coord + 2) % Params.world_width;
			}
			case 1:{
				x_coord = (x_coord + 2) % Params.world_width;
				y_coord = (y_coord + 2) % Params.world_height;
			}
			case 2:{
				y_coord = (y_coord + 2) % Params.world_height;
			}
			case 3:{
				y_coord = (y_coord + 2) % Params.world_height;
				if(x_coord == 0) {
					x_coord = Params.world_width - 2;
				}
				else if(x_coord == 1) {
					x_coord = Params.world_width - 1;
				}
				else {
					x_coord -= 2;
				}
			}
			case 4:{
				if(x_coord == 0) {
					x_coord = Params.world_width - 2;
				}
				else if(x_coord == 1) {
					x_coord = Params.world_width - 1;
				}
				else {
					x_coord -= 2;
				}
			}
			case 5:{
				if(x_coord == 0) {
					x_coord = Params.world_width - 2;
				}
				else if(x_coord == 1) {
					x_coord = Params.world_width - 1;
				}
				else {
					x_coord -= 2;
				}
				if(y_coord == 0) {
					y_coord = Params.world_height - 2;
				}
				else if(y_coord == 1) {
					y_coord = Params.world_height - 1;
				}
				else {
					y_coord -= 2;
				}
			}
			case 6:{
				if(y_coord == 0) {
					y_coord = Params.world_height - 2;
				}
				else if(y_coord == 1) {
					y_coord = Params.world_height - 1;
				}
				else {
					y_coord -= 2;
				}
			}
			case 7:{
				if(y_coord == 0) {
					y_coord = Params.world_height - 2;
				}
				else if(y_coord == 1) {
					y_coord = Params.world_height - 1;
				}
				else {
					y_coord -= 2;
				}
				x_coord = (x_coord + 2) % Params.world_width;
			}
		}
		energy -= Params.run_energy_cost;
		moved = true;
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		if(energy < Params.min_reproduce_energy) {
			return;
		}
		offspring.energy = energy / 2;
		energy = (int)Math.ceil(energy / 2);
		switch(direction) {
			case 0:{
				x_coord = (x_coord + 2) % Params.world_width;
			}
			case 1:{
				x_coord = (x_coord + 2) % Params.world_width;
				y_coord = (y_coord + 2) % Params.world_height;
			}
			case 2:{
				y_coord = (y_coord + 2) % Params.world_height;
			}
			case 3:{
				y_coord = (y_coord + 2) % Params.world_height;
				if(x_coord == 0) {
					x_coord = Params.world_width - 2;
				}
				else if(x_coord == 1) {
					x_coord = Params.world_width - 1;
				}
				else {
					x_coord -= 2;
				}
			}
			case 4:{
				if(x_coord == 0) {
					x_coord = Params.world_width - 2;
				}
				else if(x_coord == 1) {
					x_coord = Params.world_width - 1;
				}
				else {
					x_coord -= 2;
				}
			}
			case 5:{
				if(x_coord == 0) {
					x_coord = Params.world_width - 2;
				}
				else if(x_coord == 1) {
					x_coord = Params.world_width - 1;
				}
				else {
					x_coord -= 2;
				}
				if(y_coord == 0) {
					y_coord = Params.world_height - 2;
				}
				else if(y_coord == 1) {
					y_coord = Params.world_height - 1;
				}
				else {
					y_coord -= 2;
				}
			}
			case 6:{
				if(y_coord == 0) {
					y_coord = Params.world_height - 2;
				}
				else if(y_coord == 1) {
					y_coord = Params.world_height - 1;
				}
				else {
					y_coord -= 2;
				}
			}
			case 7:{
				if(y_coord == 0) {
					y_coord = Params.world_height - 2;
				}
				else if(y_coord == 1) {
					y_coord = Params.world_height - 1;
				}
				else {
					y_coord -= 2;
				}
				x_coord = (x_coord + 2) % Params.world_width;
			}
		}
		babies.add(offspring);
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try {
			Critter newClass = (Critter) Class.forName(myPackage + "." + critter_class_name).newInstance();
			newClass.x_coord = getRandomInt(Params.world_width);
			newClass.y_coord = getRandomInt(Params.world_height);
			newClass.energy = Params.start_energy;
			//newClass.inFight = false;
			newClass.moved = false;
			Critter.population.add(newClass);
			
		}
		catch(ClassNotFoundException ex){
			throw new InvalidCritterException(critter_class_name);
		} catch (InstantiationException e) {
			throw new InvalidCritterException(critter_class_name);
		} catch (IllegalAccessException e) {
			throw new InvalidCritterException(critter_class_name);
		}
		
		
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		Class<?> critClass;
		try {
			critClass = Class.forName(myPackage + "." + critter_class_name);
		}
		catch (ClassNotFoundException c) {
			throw new InvalidCritterException(critter_class_name);
		}
		for(Critter c : population) {
			if(critClass.isInstance(c)) {
				result.add(c);
			}
		}
		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static String runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		String result= "";
		for (String s : critter_count.keySet()) {
			result += prefix + s + ":" + critter_count.get(s);
			prefix = ", ";
		}
		return result;		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		// Complete this method.
		babies.clear();
		population.clear();
	}
	
	public static void worldTimeStep() {
		// Complete this method.
		for(int i = 0; i < population.size(); i++) {
			population.get(i).doTimeStep();
		}
		for(int i = 0; i < population.size(); i++) {
			if(population.get(i).getEnergy() <= 0) {
				continue;
			}
			for(int j = i + 1; j < population.size(); j++) {
				if(population.get(j).getEnergy() <= 0) {
					continue;
				}
				Critter critter1 = population.get(i);
				Critter critter2 = population.get(j);
				if(critter1.x_coord == critter2.x_coord && critter1.y_coord == critter2.y_coord) {
					//critter1.inFight = true;
					//critter2.inFight = true;
					boolean fight1 = critter1.fight(critter2.toString());
					boolean fight2 = critter2.fight(critter1.toString());
					if(critter1.energy >= 0 && critter2.energy >= 0) {
						if(critter1.x_coord == critter2.x_coord && critter1.y_coord == critter2.y_coord) {
							int random1 = 0;
							int random2 = 0;
							if(fight1) {
								random1 = Critter.getRandomInt(critter1.energy);
							}
							if(fight2) {
								random2 = Critter.getRandomInt(critter2.energy);
							}
							if(random1 > random2) {
								critter1.energy += critter2.energy / 2;
								critter2.energy = 0;
							}
							else if(random2 > random1){
								critter2.energy += critter1.energy / 2;
								critter1.energy = 0;
							}
							else {
								int equal = Critter.getRandomInt(2);
								if(equal == 0) {
									critter1.energy += critter2.energy / 2;
									critter2.energy = 0;
								}
								else {
									critter2.energy += critter1.energy / 2;
									critter1.energy = 0;
								}
							}	
						}
					}	
				}
			}
		}
		for(int i = 0; i < Params.refresh_algae_count; i++) {
			try {
				makeCritter("Algae");
			}catch(InvalidCritterException c) {}
		}
		for(Critter c : babies) {
			population.add(c);
		}
		babies.clear();
		int popSize = population.size();
		int index = 0;
		for(int k=0; k < popSize; k++){
			population.get(index).energy -= Params.rest_energy_cost;
			if(population.get(index).energy <= 0)
				population.remove(population.get(index));
			else
				index++;
		}
		for(Critter c : population) {
			c.moved = false;
		}
	}
	
	public static void displayWorld() {
		// Complete this method.
		
		String[][] world = new String[Params.world_height + 2][Params.world_width + 2];
		
		for(int i = 0; i < Params.world_height + 2; i++) {
			for(int j = 0; j < Params.world_width + 2; j++) {
				if(i == 0 || i == Params.world_height + 1) {
					if(j == 0 || j == Params.world_width + 1) {
						world[i][j] = "+";
					}
					else {
						world[i][j] = "-";
					}
				}
				else if(j == 0 || j == Params.world_width + 1 ) {						
						world[i][j] = "|";
				}
				else {
					world[i][j] = " ";
				}
			}
		}
		
		for(Critter c : population) {
			
			world[c.y_coord + 1][c.x_coord + 1] = c.toString();
		}
		for(int i = 0; i < Params.world_height + 2; i++) {
			for(int j = 0; j < Params.world_width + 2; j++) {
				System.out.print(world[i][j]);
			}
			System.out.println();
		}
		
		
	}
}
