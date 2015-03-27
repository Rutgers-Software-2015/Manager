package Manager;

import java.sql.*; 
import java.util.LinkedList;

//This class will handle the database for the finances

public class MenuHandler {


	// public static LinkedList<LinkedList<String>> getMenu()
	public static String[][] getMenu()
	{
		
		/*
		LinkedList<LinkedList<String>> Menu = new LinkedList<LinkedList<String>>();
		LinkedList<String> Stuff = new LinkedList<String>();
		
		Stuff.add("John Cena");
		Stuff.add("Hustle, Loyalty, Respect");
		Stuff.add("$28");
		Stuff.add("0");
		
		Menu.add(Stuff);
		Stuff = new LinkedList<String>(); 
		
		Stuff.add("John Cena");
		Stuff.add("Hustle, Loyalty, Respect");
		Stuff.add("$28");
		Stuff.add("1");
		
		Menu.add(Stuff);
		Stuff = new LinkedList<String>(); 
	
		Stuff.add("John Cena");
		Stuff.add("Hustle, Loyalty, Respect");
		Stuff.add("$28");
		Stuff.add("2");
		
		Menu.add(Stuff);
		Stuff = new LinkedList<String>(); 
		*/
		
		String[][] trendArray = 
		{
				{"Chicken Sandwich", "Chicken, Bread, Lettuce", "$5", "0"},
				{"Spinach Alfredo", "Spinach, Pasta, White Sauce", "$8", "1"},
				{"Canoli", "Dough, Whipped Cream", "$3", "2"},
				{"Gulash", "Vegetables, Stock, Spices", "$5", "3"},
				{"Salad", "Vegetables, Fruit, Dressing", "$5", "5"},
				/*
				{"Hot Dog", "25"},
				{"French Fries","23"},
				{"Pizza","20"},
				{"Salad","19"},
				{"Mozzarella Sticks","17"},
				{"Onion Rings","15"},
				{"Macaroni","13"},
				{"Tomata","10"},
				{"Peas and Carrots","9"},
				{"Penne Vodka","8"},
				{"Hot Pockets","5"},
				{"Chicken Sandwich","3"},
				{"Broccoli Chedder Soup","1"} */
				
		};
		//Connect to DB
		
		//Query for Amount sold for each item
		
		//Format the result set into an array
		
		//Return array
		
		return trendArray;
		// return Menu;
	}
	
}