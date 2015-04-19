package Manager;

import javafx.*;


import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


//import Login.LoginWindow;
import Shared.Gradients.*;

import javax.swing.ButtonGroup;
import javax.swing.border.LineBorder;


public class GraphGenerator{

	JPanel ItemBarGraphPanel, ItemVsTimePanel;
	FinancialHandler FinHandle = new FinancialHandler();
	String[] items = FinHandle.getItems();
	int[] amounts = FinHandle.getAmounts();
	String[] days = FinHandle.getDay();
	String[] MenuItems = FinHandle.getMenuItems();
	
	//Holds day items
	int[] Monday;
	int[] Tuesday;
	int[] Wednesday;
	int[] Thursday;
	int[] Friday;
	int[] Saturday;
	int[] Sunday;

	
	public void GraphGenerator()
	{
		
	}

	public void organizeData()
	{
		Monday = new int[MenuItems.length];
		Tuesday = new int[MenuItems.length];
		Wednesday = new int[MenuItems.length];
		Thursday = new int[MenuItems.length];
		Friday = new int[MenuItems.length];
		Saturday = new int[MenuItems.length];
		Sunday = new int[MenuItems.length];
		
		for(int i = 0; i < Monday.length; i++)
		{
			Monday[i] = 0;
			Tuesday[i] = 0;
			Wednesday[i] = 0;
			Thursday[i] = 0;
			Friday[i] = 0;
			Saturday[i] = 0;
			Sunday[i] = 0;
		}
		
		for(int i = 0; i < days.length; i++)
		{
			if(days[i].equals("Monday"))
			{
				String it = items[i];
				int j = findItemIndex(it);
				Monday[j] = Monday[j] + amounts[j];
				continue;
			}
			if(days[i].equals("Tuesday"))
			{
				String it = items[i];
				int j = findItemIndex(it);
				Tuesday[j] = Tuesday[j] + Tuesday[j];
				continue;
			}
			if(days[i].equals("Wednesday"))
			{
				String it = items[i];
				int j = findItemIndex(it);
				Wednesday[j] = Wednesday[j] + Wednesday[j];
				continue;
			}
			if(days[i].equals("Thursday"))
			{
				String it = items[i];
				int j = findItemIndex(it);
				Thursday[j] = Thursday[j] + Thursday[j];
				continue;
			}
			if(days[i].equals("Friday"))
			{
				String it = items[i];
				int j = findItemIndex(it);
				Friday[j] = Friday[j] + Friday[j];
				continue;
			}
			if(days[i].equals("Saturday"))
			{
				String it = items[i];
				int j = findItemIndex(it);
				Saturday[j] = Saturday[j] + Saturday[j];
				continue;
			}
			if(days[i].equals("Sunday"))
			{
				String it = items[i];
				int j = findItemIndex(it);
				Sunday[j] = Sunday[j] + Sunday[j];
				continue;
			}
			
		}
		
		
	}
	
	public int findItemIndex(String I)
	{
		for(int i = 0; i < MenuItems.length; i++)
		{
			if(I.equals(MenuItems[i]))
			{
				return i;
			}
		}
		return -1;
	}

	public void Gen_ItemBarGraph()
	{
		
	}
	
}
