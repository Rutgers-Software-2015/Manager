/**
 * ManagerRootWindow.java
 * Author: Ryan Sanichar and Harsh Shah
 * 
 * The root window for the manager class. This window 
 * shows the four buttons that lead to the four classes
 * that the manager can do:
 * MenuWindow, Employee Window, FinancialWindow, and InventoryWindoiw
 * 
 */


package Manager;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.border.*;
import javax.swing.ImageIcon;

import Login.LoginWindow;

public class ManagerRootWindow extends JFrame implements ActionListener{

	
	//This Frame
	private JFrame Win_Employees, Win_Financials, Win_Menu, Win_Inventory ;
	
	//This Frames panels
	private JPanel optionPanel;
	private JPanel rootPanel;
	
	//This Frames Buttons
	private JButton B_Employees, B_Inventory, B_Financials, B_Menu, B_temp;
	
	//The Layout of the panel
	GridLayout optionLayout = new GridLayout(2,2);
	GridLayout rootLayout = new GridLayout(1, 1);
	BorderLayout suboptLayout = new BorderLayout();
	
	/* Main
	public static void main(String[] args)
	{
		new ManagerRootWindow();
		return;
	}	
	*/

	// Constructor
	public ManagerRootWindow()
	{
		super();
		init();
		// Logout when closing window
		addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                new LoginWindow();
                dispose();
            }
        });
		
	}

	// Initialize manager window
	public void init()
	{
		this.setTitle("Manager");
		this.setResizable(true);
		this.setSize(1000,750);
		this.frameManipulation();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(rootPanel);
		this.setVisible(true);
	}

	// Set the frame manipulation
	public void frameManipulation()
	{
		rootPanel = new JPanel();
		rootPanel.setLayout(rootLayout);
		
		
		init_OptPanel();
		rootPanel.add(optionPanel);

	}
	
	// Initialize all the panels with the buttons
	public void init_OptPanel()
	{
		//Entire Left Panel
		optionPanel = new JPanel();
		optionPanel.setLayout(optionLayout);
		
		//Initialize The Buttons	
		B_Employees = new JButton("Employees");
		B_Inventory = new JButton("Inventory");
		B_Financials = new JButton("Financials");
		B_Menu = new JButton("Menu");
		
		//Styling the buttons
		B_Employees.setBorderPainted(false);
		

		//Add The ActionListener to the buttons
		B_Employees.addActionListener(this);
		B_Inventory.addActionListener(this);
		B_Financials.addActionListener(this);
		B_Menu.addActionListener(this);
		
		//Add buttons to panel
		optionPanel.add(B_Employees);
		optionPanel.add(B_Inventory);
		optionPanel.add(B_Financials);
		optionPanel.add(B_Menu);
	}

	public void actionPerformed(ActionEvent e) 
	{
		Object a = e.getSource();
		if(a == B_Employees)
			{
				gen_Employees_Win();
				//Return Employees Window
			}
		if(a == B_Inventory)
			{
				gen_Inventory();
				//Return Inventory Window
			}
		if(a == B_Financials)
			{
				gen_Financials_Win();
				//Return Financial Window
			}
		if(a == B_Menu)
			{
                gen_Menu();
                //Return Menu Window
			}
	}

	// Generate window function
	public void gen_Employees_Win()
	{	
		Win_Employees = new EmployeeWindow();
		Win_Employees.setVisible(true);
	}

	// Generate window function
	public void gen_Financials_Win()
	{
		Win_Financials = new FinancialWindow();
		Win_Financials.setVisible(true);
	}
    
	// Generate window function
    public void gen_Menu()
    {
        Win_Menu = new MenuWindow();
        Win_Menu.setVisible(true);
    }
    
    // Generate window function
    public void gen_Inventory()
    {
    	Win_Inventory = new InventoryWindow();
    	Win_Inventory.setVisible(true);
    }
	

}

