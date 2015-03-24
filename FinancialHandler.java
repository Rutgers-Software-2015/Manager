import java.sql.*;

//This class will handle the database for the finances

public class FinancialHandler {


	
	public static String[][] getTrend()
	{
		String[][] trendArray = 
		{
				{"Hamburger", "28"},
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
				{"Broccoli Chedder Soup","1"}
				
		};
		//Connect to DB
		
		//Query for Amount sold for each item
		
		//Format the result set into an array
		
		//Return array
		return trendArray;
	}
	
	public static String[][] getPPI()
	{
		String[][] PPIArray = 
		{
				{"Hamburger", "18"},
				{"Hot Dog", "17"},
				{"French Fries","16"},
				{"Pizza","10"},
				{"Salad","9"},
				{"Mozzarella Sticks","8"},
				{"Onion Rings","7"},
				{"Macaroni","6"},
				{"Tomata","5"},
				{"Peas and Carrots","5"},
				{"Penne Vodka","4"},
				{"Hot Pockets","3"},
				{"Chicken Sandwich","3"},
				{"Broccoli Chedder Soup","1"}
				
		};
		
		
		//Connect to DB
		
		//Query for item and amount sold
		
		//Format the result set into an array
		
		//Multiply the amount sold times the price for each item
		
		//Return the array
		return PPIArray;
	}
	
}
