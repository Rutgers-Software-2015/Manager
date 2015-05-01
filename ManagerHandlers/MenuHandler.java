/**
 * MenuHandler.java
	@author Ryan Sanichar 
	@tester Ryan Sanichar
	@debugger Ryan Sanichar
 * 
 * 
 * Handler for the MenuWindow.java file
 * Generates menu items to be used as the menu. 
 */


package Manager.ManagerHandlers;

import static org.apache.commons.codec.binary.Hex.decodeHex;   
import static org.apache.commons.io.FileUtils.readFileToByteArray;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.codec.DecoderException;

import KitchenStaff.KitchenStaffCommunicator;
import Manager.ManagerGUIs.MenuObj;
import Shared.ADT.MenuItem;
import Shared.Communicator.*;
import Shared.Lib.*;
import Shared.Notifications.NotificationGUI;

import java.sql.*;  
import java.util.LinkedList;

//This class will handle the database for the finances

public class MenuHandler extends DatabaseCommunicator {
	
	private JTable MenuTable;
	
	// Constructor
	public MenuHandler()
	{
		super();
		this.connect("admin", "gradMay17");
		this.tell("use MAINDB;");
	}
	
	// Get The Menu from the Database as a ResultSet
	public ResultSet getAllMenu()
	{		
		ResultSet rs = this.tell("select * FROM MENU;");
		return rs;
	}
	
	// Get the MenuID from the database and put into array
	public Integer[] getMenuID() throws SQLException {
		
		ResultSet I = this.tell("select * FROM MENU;");
		
		//Getting rows in result set
		int rowcount=0;
		do
		{
			rowcount++;

		}while(I.next());
		I.beforeFirst();// Reset pointer to beginning of resultset.
		
		Integer[] MenuID = new Integer[rowcount]; // Initialize
		try{
			
			ResultSetMetaData rsd = I.getMetaData();
			int colsize = rsd.getColumnCount();
			int arrayindex=0;
			while(I.next())
			{	
				MenuID[arrayindex]=I.getInt(1);
				arrayindex++;
			}
			return MenuID;
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	
		return null;
	}

	// Get the Menu Name from the database and put into array
	public String[] getMenuName() throws SQLException {

		ResultSet I = this.tell("select * FROM MENU;");
		
	//Getting rows in result set
		int rowcount=0;
		do
		{
			rowcount++;
		}
		
		while(I.next());
		
		I.beforeFirst();// Reset pointer to beginning of resultset.
		
		String[] MenuName=new String[rowcount];// Initialize
		try{
			
			
			ResultSetMetaData rsd = I.getMetaData();
			int colsize=rsd.getColumnCount();
			int arrayindex=0;
			while(I.next())
			{	
		    	MenuName[arrayindex]=I.getString(2);
				arrayindex++;
			}
			
			return MenuName;
		}
		
		catch(SQLException e)
		{
			System.out.println(e);
		}
		
		
		return null;
	}
	
	// Get the Menu Price from the database and put into array
	public Double[] getMenuPrice() throws SQLException {
		
		ResultSet I = this.tell("select * FROM MENU;");
		
		//Getting rows in result set
		int rowcount=0;
		do
		{
			rowcount++;

		}while(I.next());
		I.beforeFirst();// Reset pointer to beginning of resultset.
		
		Double[] MenuPrice = new Double[rowcount]; // Initialize
		try{
			
			ResultSetMetaData rsd = I.getMetaData();
			int colsize = rsd.getColumnCount();
			int arrayindex=0;
			while(I.next())
			{	
				MenuPrice[arrayindex]=I.getDouble(3);
				arrayindex++;
			}
			
			return MenuPrice;
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	
		return null;
	}
	
	// Get the Menu Cost from the database and put into array
	public Double[] getMenuCost() throws SQLException {
		

		ResultSet I = this.tell("select * FROM MENU;");
		
		//Getting rows in result set
		int rowcount=0;
		do
		{
			rowcount++;

		}while(I.next());
		I.beforeFirst();// Reset pointer to beginning of resultset.
		
		Double[] MenuCost = new Double[rowcount]; // Initialize
		try{
			
			ResultSetMetaData rsd = I.getMetaData();
			int colsize = rsd.getColumnCount();
			int arrayindex=0;
			while(I.next())
			{	
				MenuCost[arrayindex]=I.getDouble(4);
				arrayindex++;
			}
			
			return MenuCost;
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	
		return null;
	}
	
	// Get the Menu Ingredients from the database and put into array
	public String[] getMenuIngredients() throws SQLException {


		ResultSet I = this.tell("select * FROM MENU;");
	
		//Getting rows in result set
		int rowcount=0;
		do
		{
		rowcount++;
		}
	
		while(I.next());
	
		I.beforeFirst();// Reset pointer to beginning of resultset.
	
		String[] MenuIngredients = new String[rowcount];// Initialize
		try{
		
		
			ResultSetMetaData rsd = I.getMetaData();
			int colsize=rsd.getColumnCount();
			int arrayindex=0;
			while(I.next())
			{	
				MenuIngredients[arrayindex]=I.getString(5);
				arrayindex++;
			}

			return MenuIngredients;
		}
	
		catch(SQLException e)
		{
			System.out.println(e);
		}
	
	
		return null;
	}

	// Get the Menu Description from the database and put into array
	public String[] getMenuDescription() throws SQLException {

		ResultSet I = this.tell("select * FROM MENU;");
	
//Getting rows in result set
		int rowcount=0;
		do
		{
			rowcount++;
		}
	
		while(I.next());
	
		I.beforeFirst();// Reset pointer to beginning of resultset.
	
		String[] MenuDescription = new String[rowcount];// Initialize
		try{
		
		
			ResultSetMetaData rsd = I.getMetaData();
			int colsize=rsd.getColumnCount();
			int arrayindex=0;
			while(I.next())
			{	
				MenuDescription[arrayindex]=I.getString(6);
				arrayindex++;
			}

			return MenuDescription;
		}
	
		catch(SQLException e)
		{
			System.out.println(e);
		}
	
	
		return null;
}

	// Get the Menu Section from the database and put into array
	public String[] getMenuSection() throws SQLException {
	
		ResultSet I = this.tell("select * FROM MENU;");
		
	//Getting rows in result set
		int rowcount=0;
		do
		{
			rowcount++;
		}
		
		while(I.next());
		
		I.beforeFirst();// Reset pointer to beginning of resultset.
		
		String[] MenuDescription = new String[rowcount];// Initialize
		try{
			
			
			ResultSetMetaData rsd = I.getMetaData();
			int colsize=rsd.getColumnCount();
			int arrayindex=0;
			while(I.next())
			{	
				MenuDescription[arrayindex]=I.getString(7);
				arrayindex++;
			}
			
			return MenuDescription;
		}
		
		catch(SQLException e)
		{
			System.out.println(e);
		}
		
		
		return null;
	}
	
	// Get the Menu Valid Bit from the database and put into array
	public Integer[] getMenuisValid() throws SQLException {
		
		ResultSet I = this.tell("select * FROM MENU;");
		
		//Getting rows in result set
		int rowcount=0;
		do
		{
			rowcount++;

		}while(I.next());
		I.beforeFirst();// Reset pointer to beginning of resultset.
		
		Integer[] MenuID = new Integer[rowcount]; // Initialize
		try{
			
			ResultSetMetaData rsd = I.getMetaData();
			int colsize = rsd.getColumnCount();
			int arrayindex=0;
			while(I.next())
			{	
				MenuID[arrayindex]=I.getInt(8);
				arrayindex++;
			}

			return MenuID;
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	
		return null;
	}
	
	// Add a menu object to the menu/database
	public void AddMenuItem(MenuObj E)
	{
		
		ResultSet rs = this.tell("Select * from MENU;");
		int maxid=0;
		int counter = 0;
		try{
			rs.beforeFirst();
			while(rs.next())
			{
				int menuid=rs.getInt("MENU_ID");
				if(menuid>maxid)
				{
					maxid=menuid;
				}
			}
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		
		String newMenuItem = "('" + (maxid + 1) + "', '" + E.ITEM_NAME + "', '" + E.PRICE + "', '" + E.COST + "', '" + E.INGREDIENTS + "', '" + E.DESCRIPTION + "', '" + E.SECTION + "', '" + E.VALID + "');";
		String command = "INSERT INTO MENU (MENU_ID, ITEM_NAME, PRICE, COST, INGREDIENTS, DESCRIPTION, MENU_SECTION, VALID) VALUES " + newMenuItem;
		this.update(command);
		try {
			updateMenuItemValidBit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Menu Item Added!");
	
	}
	// Remove a menu object from the menu/database
	public void RemoveMenuItem(String MenuItem) throws SQLException
	{
			this.update("DELETE FROM MENU WHERE ITEM_NAME='" + MenuItem + "';");
			
	}
	
	// Update a menu object to the menu/database
	public void updateMenuItem(MenuObj E)
	{

		
		this.update("DELETE FROM MENU WHERE MENU_ID='" + E.MENU_ID + "';");
		String MenuItem = "('" + E.MENU_ID + "', '" + E.ITEM_NAME + "', '" + E.PRICE + "', '" + E.COST + "', '" + E.INGREDIENTS + "', '" + E.DESCRIPTION + "', '" + E.SECTION + "', '" + E.VALID + "');";
		String command = "INSERT INTO MENU (MENU_ID, ITEM_NAME, PRICE, COST, INGREDIENTS, DESCRIPTION, MENU_SECTION, VALID) VALUES " + MenuItem;
		this.update(command);
		try {
			updateMenuItemValidBit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Check to see if the menu item has enough ingredients in stock
	public void updateMenuItemValidBit() throws SQLException
	{
		NotificationGUI n=new NotificationGUI(200, "Manager");
		KitchenStaffCommunicator update= new KitchenStaffCommunicator(n);
		
		ResultSet rs = this.tell("SELECT *from MENU;");
		rs.beforeFirst();
		while(rs.next())
		{
			int menuid=rs.getInt("MENU_ID");
			String ing=rs.getString("INGREDIENTS");
			String[]  temp =update.ParseIngredients(ing);
			
			// If the ingredient exists, the valid bit is 1, else, the bit is 0
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
		n.close();
	}

	

}



