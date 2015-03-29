/**
 * MenuHandler.java
 * Author: Ryan Sanichar
 * 
 * Handler for the MenuWindow.java file
 * Generates menu items to be used as the menu. 
 */


package Manager;

import java.sql.*; 
import java.util.LinkedList;

//This class will handle the database for the finances

public class MenuHandler {


	// public static LinkedList<LinkedList<String>> getMenu()
	public static String[][] getMenu()
	{
		
		// The following commented code will be used in Demo 2
		// Implementing a linked list
		
		/*
		LinkedList<LinkedList<String>> Menu = new LinkedList<LinkedList<String>>();
		LinkedList<String> Stuff = new LinkedList<String>();
		
		Stuff.add("");
		Stuff.add("");
		Stuff.add("$28");
		Stuff.add("0");
		
		Menu.add(Stuff);
		Stuff = new LinkedList<String>(); 
		
		Stuff.add("");
		Stuff.add("");
		Stuff.add("$28");
		Stuff.add("0");
		
		Menu.add(Stuff);
		Stuff = new LinkedList<String>(); 
		
		*/
		// Creating the menu with the name, the ingredients, the price, and the ID
		String[][] trendArray = 
		{
				
				
				{"Chicken Sandwich", "Chicken, Bread, Lettuce", "$5", "0"},
				{"Spinach Alfredo", "Spinach, Pasta, White Sauce", "$8", "1"},
				{"Canoli", "Dough, Whipped Cream", "$3", "2"},
				{"Gulash", "Vegetables, Stock, Spices", "$5", "3"},
				{"Salad", "Vegetables, Fruit, Dressing", "$5", "5"},
				
		};
		
		/* For Demo 2
		
		//Connect to DB
		
		//Query for Amount sold for each item
		
		//Format the result set into an array
		
		//Return array
		  
		 
		 */
		
		return trendArray;
		// return Menu;
	}
	
}