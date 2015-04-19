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
		
	}
	

	public void Gen_ItemBarGraph()
	{
		
	}
	
}
