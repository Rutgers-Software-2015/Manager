/**
 * InventoryHandler.java
 * Author: Ryan Sanichar
 * 
 * Handler for the InventoryWindow.java file
 * Generates ingredients to be used as the inventory. 
 */


package Manager;
import ADT.Ingredient;


public class InventoryHandler {
	
	
// The ingredient list
public Ingredient IngredientList[];
	
	// Make the ingredients, give them a name, and a quantity
	public Ingredient[] AllIngredient=
		{
			new Ingredient("Tomatoes",100),
			new Ingredient("Lettuce",100),
			new Ingredient("Celery",100),
			new Ingredient("Chicken",100),
		};
	
	// Query the ingredient
	public void QueryIngredient()
	{
		IngredientList = AllIngredient;
	}


}
