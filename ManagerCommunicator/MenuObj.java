package Manager.ManagerCommunicator;
/*
 * This function  creates a MenuObject
 @author Harsh Shsh
 @tester Harsh Shah
 @debugger Harsh Shah
*/
public class MenuObj {
	
	public String MENU_ID;
	public String ITEM_NAME;
	public String PRICE;
	public String COST;
	public String INGREDIENTS; 
	public String DESCRIPTION;
	public String SECTION;
	public String VALID;
	
	public MenuObj(String MENU_ID, String ITEM_NAME, String PRICE, String COST, String INGREDIENTS,  String DESCRIPTION, String MENU_SECTION, String VALID)
	{
		this.MENU_ID = MENU_ID;
		this.ITEM_NAME = ITEM_NAME;
		this.PRICE = PRICE;
		this.COST = COST;
		this.INGREDIENTS = INGREDIENTS;
		this.DESCRIPTION = DESCRIPTION;
		this.SECTION = MENU_SECTION;
		this.VALID = VALID;
	}
	
	public MenuObj()
	{
		this.MENU_ID = "";
		this.ITEM_NAME = "";
		this.PRICE = "";
		this.COST = "";
		this.INGREDIENTS = "";
		this.DESCRIPTION = "";
		this.SECTION = "";
		this.VALID = "";
	}

}
