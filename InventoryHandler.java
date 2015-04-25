/**
 * InventoryHandler.java
 * Author: Ryan Sanichar
 * 
 * Handler for the InventoryWindow.java file
 * Generates ingredients to be used as the inventory. 
 */


package Manager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import Shared.ADT.Ingredient;
import Shared.ADT.IngredientHandler;
import Shared.Communicator.DatabaseCommunicator;


public class InventoryHandler  extends DatabaseCommunicator {

	// public Ingredient IngredientList[];
	
	public ResultSet getAllInventory() throws SQLException
	{	
		
		this.connect("admin", "gradMay17");
		this.tell("use MAINDB;");
		ResultSet rsI = this.tell("select * FROM INVENTORY;");
		this.disconnect();
		return rsI;
		
	}
	

	/*
	 * Turns Inventory Result into a Array
	 * 
	 */
	public String[] getInventoryName() throws SQLException
	{	
		this.connect("admin", "gradMay17");
		this.tell("use MAINDB;");
		ResultSet I = this.tell("select * FROM INVENTORY;");
		
	//Getting rows in result set
		int rowcount=0;
		do
		{
			rowcount++;

		}while(I.next());
		
		I.beforeFirst();// Reset pointer to beginning of resultset.
		
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
			this.disconnect();
			return InventoryName;
		}
		
		catch(SQLException e)
		{
			
		}
		
		
		return null;
			
	}
	public Integer[] getInventoryQ() throws SQLException
	{	
		this.connect("admin", "gradMay17");
		this.tell("use MAINDB;");
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
			this.disconnect();
			return InventoryQ;
		}
		
		catch(SQLException e)
		{
			
		}
	
		return null;
	
	}
	
	public void updateInventoryValue(int newQuantity, String item)
	{
		this.connect("admin", "gradMay17");
		this.tell("use MAINDB;");
		ResultSet rs =  this.tell("Select Amount from INVENTORY where Item_Name = '" + item + "';");
		try
		{
			while(rs.next() == true)
			{
				int curamnt, newamnt = 0;
				curamnt = rs.getInt("Amount");
				newamnt =  newQuantity + curamnt;
				this.update("UPDATE INVENTORY SET Amount = '" + newamnt + "' WHERE Item_Name = '" + item + "';");
				this.disconnect();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			this.disconnect();
		}
	}
	
	public void AddInventoryItem(String Ingredient, int newQuantity) throws SQLException
	{

			this.connect("admin", "gradMay17");
			this.tell("use MAINDB;");
			
			try{
			this.update("INSERT INTO INVENTORY (Item_Name, Amount) VALUES "+ "('" + Ingredient + "', '" + newQuantity + "');" );
			this.disconnect();
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
				this.disconnect();
			}
			
	}
	
	public void RemoveInventoryItem(String Ingredient) throws SQLException
	{
			this.connect("admin", "gradMay17");
			this.tell("use MAINDB;");
			this.update("DELETE FROM INVENTORY WHERE Item_Name='" + Ingredient + "';");
			this.disconnect();
	}
	
	public boolean isThereInternet()
	{
		try
		{
			URL yourl = new URL("http://google.com");
			HttpURLConnection yourlConnect = (HttpURLConnection)yourl.openConnection();
			yourlConnect.setConnectTimeout(2000);
			
			Object objData = yourlConnect.getContent();
		}catch(UnknownHostException e)
		{
			return false;
		}
		catch(IOException e)
		{
			return false;
		}
		return true;
	}
	
	/*
	
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
*/

}
