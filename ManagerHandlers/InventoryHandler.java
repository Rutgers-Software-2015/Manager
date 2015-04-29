/**
 * InventoryHandler.java
 * Author: Ryan Sanichar
 * 
 * Handler for the InventoryWindow.java file
 * Generates ingredients to be used as the inventory. 
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


public class InventoryHandler  extends DatabaseCommunicator {
	
	public InventoryHandler()
	{
		super();
		this.connect("admin", "gradMay17");
		this.tell("use MAINDB;");
	}
	
	public ResultSet getAllInventory()
	{	
		ResultSet rsI = this.tell("select * FROM INVENTORY;");
		return rsI;
	}
	

	/*
	 * Turns Inventory Result into a Array
	 * 
	 */
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
			
		}
		
		
		return null;
			
	}
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
			
		}
	
		return null;
	
	}
	
	public void updateInventoryValue(int newQuantity, String item) throws SQLException
	{

		ResultSet rs =  this.tell("Select Amount from INVENTORY where Item_Name = '" + item + "';");
		int curamnt = rs.getInt("Amount");
		int newamnt =  newQuantity + curamnt;
		this.update("UPDATE INVENTORY SET Amount = '" + newamnt + "' WHERE Item_Name = '" + item + "';");
		updateMenuItems();

	}
	
	public void AddInventoryItem(String Ingredient, int newQuantity) throws SQLException
	{

		
			this.update("INSERT INTO INVENTORY (Item_Name, Amount) VALUES "+ "('" + Ingredient + "', '" + newQuantity + "');" );
			updateMenuItems();

	}
	
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
	
	public boolean isThereInternet()
	{
		if(getConnectionStatus()==0){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public void updateMenuItems() throws SQLException
	{
		KitchenStaffCommunicator update= new KitchenStaffCommunicator();
		
		ResultSet rs = this.tell("SELECT *from MENU;");
		rs.beforeFirst();
		while(rs.next())
		{
			int menuid=rs.getInt("MENU_ID");
			String ing=rs.getString("INGREDIENTS");
			String[]  temp =update.ParseIngredients(ing);
			
			if(update.ingredientsExist(temp))
			{
				this.update("UPDATE MENU set VALID=1 where MENU_ID= "+menuid+" ;");
			}
			else
			{
				this.update("UPDATE MENU set VALID=0 where MENU_ID= "+menuid+" ;");
			}
		}
		update.dis();
	}

}
