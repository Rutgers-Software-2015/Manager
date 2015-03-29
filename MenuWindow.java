/**
 * MenuWindow.java
 * Author: Ryan Sanichar
 * 
 * The window for the Menu. This window
 * allows the user to see the current menu 
 * in the restaurant, and gives them the ability
 * to add, remove, and update the menu.
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
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class MenuWindow extends JFrame implements ActionListener
{
	// Frames, panels, and buttons
	JFrame MenuAddingFrame;
	JPanel rootPanel, buttonPanel;
	JPanel AddingPanel;
	static JPanel textPanel;
	JButton addItem, removeItem, updateItem, Add, logOut;
	
	// Layouts
	GridLayout rootLayout = new GridLayout(0,2);
	GridLayout buttonLayout = new GridLayout(8,0);
	GridLayout textLayout = new GridLayout(1,1);
		
	// Scroller
	JScrollPane MenuScroller;
	
	// Table that will show the data from the menu
	JTable MenuTable;
	
	// String that holds the menu
	String[][] Menu_RowData;
	String[] Menu_ColumnNames = {"Name", "Ingredients", "Price", "ID"};
	
	// Text fields for users to write name, price, ingredients, and ID
	JTextField nameField, priceField, ingredientField, IDField;
	
	// Actions performed by the button
	public void actionPerformed(ActionEvent e) {
		Object a = e.getSource();
		
		// When the addNewInventory button is selected
		if(a == addItem)
		{
			
			// Retrieve data from the text fields
			String tempName = nameField.getText();
			String tempIngredient = ingredientField.getText();
			String tempPrice = priceField.getText();
			String tempID = IDField.getText();
			
			// Make a new temp array of size + 1 to add the new item
			String[][] temp = new String[Menu_RowData.length + 1][4];
			
			// For loop to make the new table
			for(int i = 0; i < Menu_RowData.length; i++)
			{
				for(int j = 0; j < 4; j++)
					temp[i][j] = Menu_RowData[i][j];
			}
			
			// Make the new rows
			temp[Menu_RowData.length][0] = tempName;
			temp[Menu_RowData.length][1] = tempIngredient;
			temp[Menu_RowData.length][2] = tempPrice;
			temp[Menu_RowData.length][3] = tempID;
			
			// Set the new table
			Menu_RowData = temp;
			MenuTable.setModel(new DefaultTableModel(Menu_RowData, Menu_ColumnNames));
			
			// Return the text to original fields
			nameField.setText("New Name");
			ingredientField.setText("New Ingredients");
			priceField.setText("New Price");
			IDField.setText("New ID Number");
			
			MenuTable.getColumnModel().getColumn(0).setPreferredWidth(130);
			MenuTable.getColumnModel().getColumn(1).setPreferredWidth(220);
		
		}
		
		// User selects Remove Item
		if(a == removeItem)
		{
			
			// User need to selects a row
	        if (MenuTable.getSelectedRow() != -1) 
	        {
	        	
	        	// Get the position of selected row
	        	int position = MenuTable.getSelectedRow();
	        	
	        	// Temp array for table
	        	String[][] temp = new String[Menu_RowData.length - 1][4];
	        	
	        	// Loop to update temp array
	        	for(int i = 0; i < position; i++)
	        	{
	        		temp[i][0] = Menu_RowData[i][0];
	        		temp[i][1] = Menu_RowData[i][1];
	        		temp[i][2] = Menu_RowData[i][2];
	        		temp[i][3] = Menu_RowData[i][3];
	        	}
	        	
	        	for(int i = position + 1; i < Menu_RowData.length; i++)
	        	{
	        		temp[i-1][0] = Menu_RowData[i][0];
	        		temp[i-1][1] = Menu_RowData[i][1];	
	        		temp[i-1][2] = Menu_RowData[i][2];	
	        		temp[i-1][3] = Menu_RowData[i][3];	
	        	}
	        	
	        	// Update array
	        	Menu_RowData = temp;
	        	MenuTable.setModel(new DefaultTableModel(Menu_RowData, Menu_ColumnNames));
	        	
	    		MenuTable.getColumnModel().getColumn(0).setPreferredWidth(130);
	    		MenuTable.getColumnModel().getColumn(1).setPreferredWidth(220);
	        	
	            //remove selected row from the model
	            // model.removeRow(MenuTable.getSelectedRow());
	        }
	        
			else 
			{
				JOptionPane.showMessageDialog(MenuAddingFrame, "Please select a menu item.");
			}
			
		}
		// If you select UpdateItem
		if(a == updateItem)
		{
		
			// User needs to select a row
	        if (MenuTable.getSelectedRow() != -1) 
	        {
			
	      	
	        // Retrieve data from the text file	
			String tempName = nameField.getText();
			String tempIngredient = ingredientField.getText();
			String tempPrice = priceField.getText();
			String tempID = IDField.getText();
			
			// Update the table
			MenuTable.getModel().setValueAt(tempName, MenuTable.getSelectedRow(), 0);
			MenuTable.getModel().setValueAt(tempIngredient, MenuTable.getSelectedRow(), 1);
			MenuTable.getModel().setValueAt(tempPrice, MenuTable.getSelectedRow(), 2);
			MenuTable.getModel().setValueAt(tempID, MenuTable.getSelectedRow(), 3);
			
			MenuTable.getColumnModel().getColumn(0).setPreferredWidth(130);
			MenuTable.getColumnModel().getColumn(1).setPreferredWidth(220);
			
	        }
	        
			// Error message
			else 
			{
				JOptionPane.showMessageDialog(MenuAddingFrame, "Please select a menu item.");
			}
			
		}
		
		// Logout
		if(a == logOut)
		{
			dispose();
		}
		
	}
	
	
	// Constructor to initialize functions
	public MenuWindow()
	{
		super();
		init();
	}
	
	// Initializer function to initialize window
	public void init()
	{
		this.setTitle("Manager - Edit Menu");
		this.setResizable(true);
		this.setSize(900,600);
		this.frameManipulation();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(rootPanel);
		this.setVisible(true);	
	}
	
	// Function to edit the frame location and format
	public void frameManipulation()
	{
		rootPanel = new JPanel();
		rootPanel.setLayout(rootLayout);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(buttonLayout);
		init_buttonPanel();
		
		textPanel = new JPanel();
		textPanel.setLayout(textLayout);
		init_textPanel();
	}

	// Initialize the button panels
	public void init_buttonPanel()
	{
	
		
		//Initialize text fields
		nameField = new JTextField("New Name");
		ingredientField = new JTextField("New Ingredients");
		priceField = new JTextField("New Price");
		IDField = new JTextField("New ID Number");
		
		// Make text fields editable
		nameField.setEditable(true);
		ingredientField.setEditable(true);
		priceField.setEditable(true);
		IDField.setEditable(true);
		
		// Add the text fields to the panel
		buttonPanel.add(nameField);
		buttonPanel.add(ingredientField);
		buttonPanel.add(priceField);
		buttonPanel.add(IDField);
		
		//Initialize buttons
		addItem = new JButton("Add Item");
		updateItem = new JButton("Update Item");
		removeItem = new JButton("Remove Item");
		logOut = new JButton("Back");
		
		//Add the action listeners
		addItem.addActionListener(this);
		updateItem.addActionListener(this);
		removeItem.addActionListener(this);
		logOut.addActionListener(this);
		
		//Add to the panel
		buttonPanel.add(addItem);
		buttonPanel.add(updateItem);
		buttonPanel.add(removeItem);
		buttonPanel.add(logOut);
		
		rootPanel.add(buttonPanel);
		
	}
	
	// Initialize the text panel
	public void init_textPanel()
	{
		init_menu();
		rootPanel.add(textPanel);
		
	}
	
	// Initialize the menu
	public void init_menu()
	{
		//Need to populate the arrays before they are fed to the JTable
		Menu_RowData = MenuHandler.getMenu();
		MenuTable = new JTable(Menu_RowData, Menu_ColumnNames);
		MenuTable.getColumnModel().getColumn(0).setPreferredWidth(130);
		MenuTable.getColumnModel().getColumn(1).setPreferredWidth(220);
		MenuScroller = new JScrollPane(MenuTable);
		MenuTable.setFillsViewportHeight(true);
		
		textPanel.add(MenuScroller); 
	}
	
}
