package com.skilldistillery.foodtruck;

public class FoodTruck {
	
	private static int nextID = 1;
	private String name;
	private String foodType;
	private double rating;
	private int ID;
	
	public FoodTruck(String name, String foodType, double rating) {
		
		this.name = name;
		this.foodType = foodType;
		this.rating = returnValidRating(rating);
		ID = nextID++;
	}
	
	private double returnValidRating(double rating) {
		
		if(rating < 0) rating = 0.0;
		else if(rating > 10) rating = 10.0;
		
		return rating;
	}
	
	public double getRating() {
		return rating;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FoodTruck [ID:");
		builder.append(ID);
		builder.append(", name: ");
		builder.append(name);
		builder.append(", type of food: ");
		builder.append(foodType);
		builder.append(", rating: ");
		builder.append(rating);
		builder.append("]");
		return builder.toString();
	}
}