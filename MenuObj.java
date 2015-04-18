package Manager;

public class MenuObj {
	
	public int MENU_ID;
	public String ITEM_NAME;
	public double PRICE;
	public double COST;
	public String INGREDIENTS; 
	public String DESCRIPTION;
	public String MENU_SECTION;
	public int VALID;
	
	public MenuObj(int MENU_ID, String ITEM_NAME, double PRICE, double COST, String INGREDIENTS,  String DESCRIPTION, String MENU_SECTION, int VALID)
	{
		this.MENU_ID = MENU_ID;
		this.ITEM_NAME = ITEM_NAME;
		this.PRICE = PRICE;
		this.COST = COST;
		this.INGREDIENTS = INGREDIENTS;
		this.DESCRIPTION = DESCRIPTION;
		this.MENU_SECTION = MENU_SECTION;
		this.VALID = VALID;
	}
	
	public MenuObj()
	{
		this.MENU_ID = 0;
		this.ITEM_NAME = "";
		this.PRICE = 0;
		this.COST = 0;
		this.INGREDIENTS = "";
		this.DESCRIPTION = "";
		this.MENU_SECTION = "";
		this.VALID = 0;
	}

}
