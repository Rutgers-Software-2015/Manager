/**
 * MenuHandler.java
 * Author: Ryan Sanichar
 * 
 * Handler for the MenuWindow.java file
 * Generates menu items to be used as the menu. 
 */


package Manager;

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

import org.apache.commons.codec.DecoderException;

import Shared.ADT.MenuItem;
import Shared.Communicator.*;
import Shared.Lib.*;

import java.sql.*;  
import java.util.LinkedList;

//This class will handle the database for the finances

public class MenuHandler extends DatabaseCommunicator {
	
	public MenuHandler()
	{
		super();
	}


	// public static LinkedList<LinkedList<String>> getMenu()
	public Vector<MenuObj> getMenu() throws SQLException
	{
		//MASTER INTERNET CHECK
		boolean INTERNET;
		INTERNET = isThereInternet();
		
		if(INTERNET == true)
		{
			this.connect("admin", "gradMay17");
			this.tell("use MAINDB;");
			String sqlcommand = "SELECT * FROM MENU;";
			ResultSet rs = this.tell(sqlcommand);
		
			int sizeRS = 0;
			rs.beforeFirst();
				
				while(rs.next() == true)
				{
					sizeRS++;
				}
		
				rs.beforeFirst();
				Vector<MenuObj> MenuListVector = new Vector(sizeRS);
		
				try
				{
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
							String menu_sec = ""+rs.getInt("MENU_SECTION");
							int valid = rs.getInt("VALID");
					
							MenuObj temp = new MenuObj(menu_id, item, price, cost, ingredient, description, menu_sec, valid);
							MenuListVector.add(temp);
						}
					}
				}
				
				catch(SQLException e)
				{
					System.out.println("No Result Set");
				}
				
				System.out.println("Obtained the Menu <-- MenuHandler.java");
				System.out.println("There are " + MenuListVector.size() +" ready to be acted on!" );
				this.disconnect();
				return MenuListVector;
			}
		
		else{
				Vector<MenuObj> nothing = new Vector();
				// nothing.add(new EmpObj());
				return nothing;
			}
		
	}
		
	
	
	public void addMenuItem(MenuObj E)
	{
		//MASTER INTERNET CHECK
		boolean INTERNET;
		INTERNET = isThereInternet();

		if(INTERNET == true)
			{
				this.connect("admin", "gradMay17");
				this.tell("use MAINDB;");
				
				ResultSet rs = this.tell("SELECT * FROM MENU;");
				int counter = 0;
				try{
					rs.beforeFirst();
					while(rs.next())
					{
						counter++;
					}
				}catch(SQLException e)
				{
					System.out.println(e);
				}
				
				String idstr = ""+counter;

				
				System.out.println("Attempting to add the following:");
				System.out.println("");
				System.out.println("Menu ID		: "+E.MENU_ID);
				System.out.println("Item Name	: "+E.ITEM_NAME);
				System.out.println("Price		: "+E.PRICE);
				System.out.println("Cost		: "+E.COST);
				System.out.println("Ingredients	: "+E.INGREDIENTS);
				System.out.println("Description	: "+E.DESCRIPTION);
				System.out.println("Menu Section: "+E.MENU_SECTION);
				System.out.println("Vali		: "+E.VALID);
				
				
				String params = "'"+E.MENU_ID+"'"+","+"'"+E.ITEM_NAME+"'"+","+E.PRICE+","+"'"+E.COST+"'"+","+"'"+E.INGREDIENTS+"'"+","+"'"+E.DESCRIPTION+"'"+","+E.MENU_SECTION+","+E.VALID;
				String sqlComm = "INSERT INTO MENU (MENU_ID, ITEM_NAME, PRICE, COST, INGREDIENTS,  DESCRIPTION, MENU_SECTION, VALID) VALUES (" + params + " );";
	
				this.update(sqlComm);
				
				System.out.println("Menu Item Added!");
				
				return;
			}
		else
		{
			System.out.println("NO INTERNET CONNECTION");
		}
	}

	

	
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
		
		
		// Creating the menu with the name, the ingredients, the price, and the ID
		String[][] trendArray = 
		{
				
				
				{"Chicken Sandwich", "Chicken, Bread, Lettuce", "$5", "0"},
				{"Spinach Alfredo", "Spinach, Pasta, White Sauce", "$8", "1"},
				{"Canoli", "Dough, Whipped Cream", "$3", "2"},
				{"Gulash", "Vegetables, Stock, Spices", "$5", "3"},
				{"Salad", "Vegetables, Fruit, Dressing", "$5", "5"},
				
		};
		
		return trendArray;
		
		
		// return Menu;
	}
*/
	public boolean isThereInternet()
	{
		try
		{
			URL yourl = new URL("http://google.com");
			HttpURLConnection yourlConnect = (HttpURLConnection)yourl.openConnection();
			yourlConnect.setConnectTimeout(2000);
		
			Object objData = yourlConnect.getContent();
		}	
		catch(UnknownHostException e)
		{
			return false;
		}
		catch(IOException e)
		{
			return false;
		}
	return true;
	}
	
}