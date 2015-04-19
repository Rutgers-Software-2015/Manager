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

import Shared.Communicator.*;
import Shared.Lib.*;


public class FinancialHandler extends DatabaseCommunicator{

	public FinancialHandler()
	{
		
	}
	
	public String[] getItems()
	{
		this.connect("admin", "gradMay17");
		this.tell("use MAINDB;");
		ResultSet rs = this.tell("Select Item_Name from OrderHistory;");
		
		int size = 0;
		
		try{
			rs.beforeFirst();
		}catch(SQLException e)
		{
			System.out.println("Not sure why this failed.....1");
		}
		
		try{
			while(rs.next() == true)
			{
				size++;
			}
		}catch(SQLException e)
		{
			System.out.println("Not sure why this failed.....2");
		}
		
		String[] items = new String[size];
		int i = 0;
		try{
			while(rs.next() == true)
			{
				String t = rs.getString("Item_Name");
				items[i] = t;
				i++;
			}
		}catch(SQLException e)
		{
			System.out.println("Not sure why this failed.....2");
		}
		
		return items;
	}
	
	public int[] getAmounts()
	{
		this.connect("admin", "gradMay17");
		this.tell("use MAINDB;");
		ResultSet rs = this.tell("Select Date from OrderHistory;");
		
		int size = 0;
		
		try{
			rs.beforeFirst();
		}catch(SQLException e)
		{
			System.out.println("Not sure why this failed.....1");
		}
		
		try{
			while(rs.next() == true)
			{
				size++;
			}
		}catch(SQLException e)
		{
			System.out.println("Not sure why this failed.....2");
		}
		
		int[] amounts = new int[size];
		int i = 0;
		try{
			while(rs.next() == true)
			{
				int t = rs.getInt("Amount");
				amounts[i] = t;
				i++;
			}
		}catch(SQLException e)
		{
			System.out.println("Not sure why this failed.....2");
		}
		
		return amounts;
	}
	
	public String[] getDay()
	{
		this.connect("admin", "gradMay17");
		this.tell("use MAINDB;");
		ResultSet rs = this.tell("Select Date from OrderHistory;");
		
		int size = 0;
		
		try{
			rs.beforeFirst();
		}catch(SQLException e)
		{
			System.out.println("Not sure why this failed.....1");
		}
		
		try{
			while(rs.next() == true)
			{
				size++;
			}
		}catch(SQLException e)
		{
			System.out.println("Not sure why this failed.....2");
		}
		
		String[] days = new String[size];
		int i = 0;
		try{
			while(rs.next() == true)
			{
				String t = rs.getString("Date");
				days[i] = t;
				i++;
			}
		}catch(SQLException e)
		{
			System.out.println("Not sure why this failed.....2");
		}
		this.disconnect();
		
		return days;
	}
	
	public String[] getMenuItems()
	{
		String[] items = null;
		this.connect("admin", "gradMay17");
		this.tell("use MAINDB;");
		int size = 0;
		String sqlcomm = "Select ITEM_NAME from MENU";
		
		try{
			ResultSet rs = this.tell(sqlcomm);
			rs.beforeFirst();
			while(rs.next() == true)
			{
				size++;
			}
			items = new String[size];
			rs.beforeFirst();
			int i = 0;
			while(rs.next() == true)
			{
				items[i] = rs.getString("ITEM_NAME");
			}
		}catch(SQLException e)
		{
			System.out.println(e);
		}		
		
		return items;
	}
	
	
}
