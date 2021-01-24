package com.skilldistillery.foodtruck;

import java.util.*;

public class FoodTruckApp {
	
	private FoodTruck[] foodTrucks = new FoodTruck[5];
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		FoodTruckApp eatOut = new FoodTruckApp();
		
		eatOut.run(in);
		
		in.close();
	}
	
	private void run(Scanner in) {
		
		welcome();
		
		foodTrucksPrompt(in);
		
		menuCycle(in);
	}
	
	private void welcome() {
		System.out.println("__________________________________________________________________________\n");
		System.out.println("Welcome to Food Truck App!");
		System.out.println("Enter data about your food trucks and then query the results!");
		System.out.println("__________________________________________________________________________\n");
	}
	
	public void foodTrucksPrompt(Scanner in) {
		
		System.out.println("Enter info about your food trucks: ");
		
		// the user enters data about 0 to 5 food trucks.  These trucks are added to the foodTrucks array.
		int index;
		for(index = 0 ; index < 5 ; index++) {
			
			System.out.println("Food Truck " + (index+1) + "'s name: (Enter \"quit\" to end this prompt.)");
			String name = in.nextLine();
			
			if(name.equals("quit")) break;
			
			System.out.println(findPossessiveString(name) + " food type:");
			String foodType = in.nextLine();
			
			System.out.println(findPossessiveString(name) + " rating on a scale of zero to ten, ten being the best possible rating:");
			double rating = in.nextDouble();
			in.nextLine();
			
			foodTrucks[index] = new FoodTruck(name, foodType, rating);
		}
		// now that all of the user's food trucks have been entered, 
		// the following code ensures that the array of food trucks is the right size.
		foodTrucks = Arrays.copyOfRange(foodTrucks, 0, index);
		
		
		System.out.println("\nThank you for the input!");
	}
	
	private void menuCycle(Scanner in) {
		
		boolean keepRunning = true;
		
		while(keepRunning) {
			showMenu();
			keepRunning = userMenuSelection(in);
		}
	}
	
	private void showMenu() {
		System.out.println(	  "_________________________________________________\n\n"
						    + "          -{ Main Menu }-                  \n"     
				            + "_________________________________________________\n\n"
							+ "1 : List all existing food trucks.\n"
							+ "2 : See the average rating of food trucks.\n"
							+ "3 : Display the highest-rated food truck.\n"
							+ "4 : Quit the program.\n"
							+ "_________________________________________________\n\n"
							+ "    Enter the number of your selection.            \n"
							+ "_________________________________________________\n\n");
	}
	
	/*
	 * @return boolean - 
	 * 					returns false if the user inputs 4, "Quit the program".
	 * 					otherwise returns true.
	 * 					(userMenuSelection returns a boolean because it was designed to be 
	 * 								used in tandem with the keepRunning boolean in menuCycle().)
	 */
	private boolean userMenuSelection(Scanner in) {
		
		System.out.print(" : ");
		int userChoice = in.nextInt();
		
		switch ( userChoice ) {
			
			case 1:
				displayFoodTrucks();
				return true;
			case 2: 
				displayAverageRating();
				return true;
			case 3: 
				displayHighestRatedFoodTruck();
				return true;
			case 4:
				auRevoir();
				return false;
			default: 
				System.out.println("\nThat is not a recognized option.\n");
				return true;
		}	
	}
	
	/*
	 * User ended program - goodbye message.
	 */
	private void auRevoir() {
		System.out.println("\n__________________________________________________________________________\n\n");
		System.out.println("Thank you for choosing Food Truck App.  Have a nice day!");
		System.out.println("__________________________________________________________________________\n\n");
	}
	
	public void displayAverageRating() {
		
		double average = getAverageRating();
		
		if(average == -1) {
			System.out.println("\nThere are no food trucks to average.\n");
			return;
		}
		
		System.out.println("\nThe average rating for all of the food trucks is: " + getAverageRating()+ "\n");
	}
	
	/*
	 * @return the average rating of the FoodTruck(s) within foodTrucks[], in the form of a double.
	 * 			If there are no food trucks, then negative one is returned.
	 */
	private double getAverageRating() {
		
		if(foodTrucks.length == 0) return -1;
		
		double toReturn = 0.0;
		
		for(int i = 0 ; i < foodTrucks.length ; i++) {
			toReturn += foodTrucks[i].getRating();
		}
		
		return toReturn / foodTrucks.length;
	}
	
	public void displayHighestRatedFoodTruck() {
		
		FoodTruck highestRated = getHighestRatedFoodTruck();
		
		if(highestRated == null) {
			System.out.println("\nThere are no food trucks.\n");
			return;
		}
		System.out.println("\nThe highest rated food truck is: " + getHighestRatedFoodTruck() + "\n");
	}
	
	public FoodTruck getHighestRatedFoodTruck() {
		
		if(foodTrucks.length == 0 ) return null;
		
		FoodTruck highestRated = foodTrucks[0];
		
		for(int i = 1 ; i < foodTrucks.length ; i++) {
			
			if(foodTrucks[i].getRating() > highestRated.getRating()) {
				highestRated = foodTrucks[i];
			}
		}
		
		return highestRated;
	}
	
//	public FoodTruck[] getFoodTrucks() {
//		
//		LinkedList<FoodTruck> trucks = new LinkedList<>();
//		
//		for(int i = 0; i < foodTrucks.length ; i++) {
//			if( foodTrucks[i] == null ) break;
//			trucks.add(foodTrucks[i]);
//		}
//		
//		FoodTruck[] trucksToReturn = new FoodTruck[trucks.size()];
//		
//		for( int i = 0 ; i < trucksToReturn.length ; i++ ) {
//			trucksToReturn[i] = trucks.get(i);
//		}
//		
//		return trucksToReturn;
//	}
	
	/* I wasn't sure how to handle these "double possessive" grammatical cases.  
	 *  https://abovethelaw.com/career-files/lawprose-lesson-113-how-do-you-form-a-possessive-with-a-name-that-itself-ends-with-a-possessive-s-as-with-mcdonalds/
	 *   
	 */
	private String findPossessiveString(String foodTruckName) {
		
		if( foodTruckName.endsWith("'s") ) return foodTruckName;
		
		return foodTruckName +"'s";
	}
	
	public void displayFoodTrucks() {
		
		if(foodTrucks.length == 0 ) {
			System.out.println("\nNo food trucks to display.\n");
			return;
		}
		
		System.out.println();
		
		for(int i = 0; i < foodTrucks.length ; i++) {
			System.out.println(foodTrucks[i]);
		}
	}
}
