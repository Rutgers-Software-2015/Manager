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
import Manager.ManagerCommunicator.MenuObj;
import Shared.ADT.MenuItem;
import Shared.Communicator.*;
import Shared.Lib.*;
import Shared.Notifications.NotificationGUI;

import java.sql.*;  
import java.util.LinkedList;

//This class will handle the database for the finances

public class MenuHandler extends DatabaseCommunicator {
	
	private JTable MenuTable;
	
	public MenuHandler()
	{
		super();
		this.connect("admin", "gradMay17");
		this.tell("use MAINDB;");
	}
	
	public ResultSet getAllMenu()
	{		
		ResultSet rs = this.tell("select * FROM MENU;");
		return rs;
	}

	/*	
	public <MenuObj> getMenu() throws SQLException
	{
		
			this.connect("admin", "gradMay17");
			this.tell("use MAINDB;");
			String sqlcommand = "SELECT * FROM MENU;";
			ResultSet rs = this.tell(sqlcommand);
			this.consolePrintTable(rs);
			JTable table = new JTable(buildTableModel(rs));
			
			int sizeRS = 0;
			rs.beforeFirst();
			
			while(rs.next() == true)
			{
				sizeRS++;
			}
			
			rs.beforeFirst();
			Vector<MenuObj> MenuListVector = new Vector(sizeRS);
			
			try {
				
				while(rs.next() == true)
				{
					boolean vis = rs.getBoolean("Visibility");
					if(vis == false)
					{
						continue;
					}
			
					else
					{
						int menu_id  = rs.getInt("MENU_ID");
						String item = rs.getString("ITEM_NAME");
						double price = rs.getDouble("PRICE");
						double cost = rs.getDouble("COST");
						String ingredient =  rs.getString("INGREDIENTS");
						String description = rs.getString("DESCRIPTION");
						String menu_sec = ""+rs.getString("MENU_SECTION");
						int valid = rs.getInt("VALID");
				
						MenuObj temp = new MenuObj(menu_id, item, price, cost, ingredient, description, menu_sec, valid);
						MenuListVector.add(temp);
					}
				}

			}catch(SQLException e)
			{
				System.out.println("No Result Set");
			}
			
			System.out.println("Obtained the Menu <-- MenuHandler.java");
			System.out.println("There are " + MenuListVector.size() +" ready to be acted on!" );
			this.disconnect();
			return MenuListVector;
		
	}
	*/
	
	public String[] getMenu() throws SQLException
	{
		
			String sqlcommand = "SELECT * FROM MENU;";
			ResultSet rs = this.tell(sqlcommand);
			this.consolePrintTable(rs);
			JTable table = new JTable(buildTableModel(rs));
			
			int sizeRS = 0;
			rs.beforeFirst();
			
			while(rs.next() == true)
			{
				sizeRS++;
			}
			
			rs.beforeFirst();
			System.out.println(sizeRS);
			String[] Menu = new String[sizeRS];
			int arrayindex = 0;
			try {
				
				while(rs.next() == true)
				{
					boolean vis = rs.getBoolean("Visibility");
					if(vis == false)
					{
						continue;
					}
			
					else
					{
						Menu[arrayindex]  =  rs.getString("MENU_ID");
						arrayindex++;
						Menu[arrayindex]  =  rs.getString("ITEM_NAME");
						arrayindex++;
						Menu[arrayindex]  =  rs.getString("PRICE");
						arrayindex++;
						Menu[arrayindex]  =  rs.getString("COST");
						arrayindex++;
						Menu[arrayindex]  =  rs.getString("INGREDIENTS");
						arrayindex++;
						Menu[arrayindex]  =  rs.getString("DESCRIPTION");
						arrayindex++;
						Menu[arrayindex]  =  rs.getString("MENU_SECTION");
						arrayindex++;
						Menu[arrayindex]  =  rs.getString("VALID");
						arrayindex++;
						
							
					}
				}
				
				

			}
				catch(SQLException e)
			{
				System.out.println("No Result Set");
			}
			
			
			table = new JTable(buildTableModel(rs));
			
			return Menu;
		
	}
	
	

	
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
	
	

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
			
		}
	
		return null;
	}

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
			
		}
		
		
		return null;
	}
	
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
			
		}
	
		return null;
	}
	
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
			
		}
	
		return null;
	}
	
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
			
		}
	
	
		return null;
	}

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
			
		}
	
	
		return null;
}


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
			
		}
		
		
		return null;
	}
	
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
			
		}
	
		return null;
	}
	
	/*
	
	public void AddMenuItem(String Item_Name, double price, double cost, String Ingredients, String Description, String Menu_Section, int Valid) throws SQLException
	{
			
			String newMenuItem = "('" + Item_Name + "', '" + price + "', '" + cost + "', '" + Ingredients + "', '" + Description + "', '" + Menu_Section + "', '" + Valid + "');";
			
			ResultSet rs =  this.tell("INSERT INTO MENU (ITEM_NAME, PRICE, COST, INGREDIENTS, DESCRIPTION, MENU_SECTION, VALID) VALUES " + newMenuItem);
			
	}
	
	*/
	
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
		
		// String idstr = ""+maxid;
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

	public void RemoveMenuItem(String MenuItem) throws SQLException
	{
			this.update("DELETE FROM MENU WHERE ITEM_NAME='" + MenuItem + "';");
			
	}
	
	public void updateMenuItem(MenuObj E)
	{

		// ResultSet rs =  this.tell("Select Amount from MENU where Item_Name = '" + E.ITEM_NAME + "';");
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



