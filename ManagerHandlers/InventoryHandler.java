/**
 * InventoryHandler.java 
 * 
@author Ryan Sanichar 
@tester Ryan Sanichar
@debugger Ryan Sanichar

 * 
 * Handler for the InventoryWindow.java file
 * Gets the ingredients from the database to be used as the inventory.
 * Also connects to the database to add/edit/remove inventory items. 
 */


package Manager.ManagerHandlers;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import KitchenStaff.KitchenStaffCommunicator;
import Shared.ADT.Ingredient;
import Shared.ADT.IngredientHandler;
import Shared.Communicator.DatabaseCommunicator;
import Shared.Notifications.NotificationGUI;


public class InventoryHandler  extends DatabaseCommunicator {
	
	// Constructor that connects to the database
	public InventoryHandler()
	{
		super();
		this.connect("admin", "gradMay17");
		this.tell("use MAINDB;");
	}
	
	// Get all the inventory from the database and put it into a resultset
	public ResultSet getAllInventory()
	{	
		ResultSet rsI = this.tell("select * FROM INVENTORY;");
		return rsI;
	}
	

	/*
	 * Turns Inventory Result into a Array
	 * 
	 */
	
	// Go to the inventory and get all the names and put them into an array
	public String[] getInventoryName() throws SQLException
	{	
		ResultSet I = this.tell("select * FROM INVENTORY;");
		
		
		int rowcount=0;
		do
		{
			rowcount++;

		}while(I.next());
		
		I.beforeFirst(); 
		
		String[] InventoryName =new String[rowcount];// Initialize
		try{
			
			
			ResultSetMetaData rsd = I.getMetaData();
			int colsize=rsd.getColumnCount();
			int arrayindex=0;
			while(I.next())
			{	
		    	InventoryName[arrayindex]=I.getString(1);
				arrayindex++;
			}

			return InventoryName;
		}
		
		catch(SQLException e)
		{
			System.out.println(e);
		}
		
		
		return null;
			
	}
	
	// Go to the inventory and get all the quantities and put into an array
	public Integer[] getInventoryQ() throws SQLException
	{	

		ResultSet I = this.tell("select * FROM INVENTORY;");
		
	//Getting rows in result set
		int rowcount=0;
		do
		{
			rowcount++;

		}while(I.next());
		I.beforeFirst();// Reset pointer to beginning of resultset.
		
		Integer[] InventoryQ=new Integer[rowcount];// Initialize
		try{
			
			ResultSetMetaData rsd = I.getMetaData();
			int colsize = rsd.getColumnCount();
			int arrayindex=0;
			while(I.next())
			{	
		    	InventoryQ[arrayindex]=I.getInt(2);
				arrayindex++;
			}
			
			return InventoryQ;
		}
		
		catch(SQLException e)
		{
			System.out.println(e);
		}
	
		return null;
	
	}
	
	// Update the inventory quantity in the database
	public void updateInventoryValue(int newQuantity, String item) throws SQLException
	{

		ResultSet rs =  this.tell("Select Amount from INVENTORY where Item_Name = '" + item + "';");
		int curamnt = rs.getInt("Amount");
		int newamnt =  newQuantity + curamnt;
		this.update("UPDATE INVENTORY SET Amount = '" + newamnt + "' WHERE Item_Name = '" + item + "';");
		updateMenuItems();

	}
	
	// Add inventory item to the database
	public void AddInventoryItem(String Ingredient, int newQuantity) throws SQLException
	{

		
			this.update("INSERT INTO INVENTORY (Item_Name, Amount) VALUES "+ "('" + Ingredient + "', '" + newQuantity + "');" );
			updateMenuItems();

	}
	
	// Remove an inventory item from the database
	public void RemoveInventoryItem(String Ingredient) throws SQLException
	{

			this.update("DELETE FROM INVENTORY WHERE Item_Name='" + Ingredient + "';");
			updateMenuItems();

	}
	
	/*
	public boolean isValid() throws SQLException
	{
		
		
		ResultSet rs =  this.tell(SELECT ColumnA, case when ColumnA > 0 then 'Greater than 0' else ColumnB END AS ColumnB 
		        FROM    table1;);
		return true;
	}
	
	*/
	
	// Check if there is internet
	public boolean isThereInternet()
	{
		if(getConnectionStatus()==0){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	// Update the valid bit of the menu items depending on the existence of an inventory item
	public void updateMenuItems() throws SQLException
	{
		NotificationGUI n=new NotificationGUI(100, "Manager");
		KitchenStaffCommunicator update= new KitchenStaffCommunicator(n);
		
		ResultSet rs = this.tell("SELECT *from MENU;");
		rs.beforeFirst();
		while(rs.next())
		{
			int menuid=rs.getInt("MENU_ID");
			String ing=rs.getString("INGREDIENTS");
			String[]  temp =update.ParseIngredients(ing); // Get seperate ingredients from the menu
			
			// If the ingredient exists, valid bit is 1, else, bit is 0
			if(update.ingredientsExist(temp))
			{
				this.update("UPDATE MENU set VALID=1 where MENU_ID= "+menuid+" ;");
			}
			else
			{
				this.update("UPDATE MENU set VALID=0 where MENU_ID= "+menuid+" ;");
			}
		}
		update.disconnect();
		n.close();
	}

}
