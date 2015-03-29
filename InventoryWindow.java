/**
 * InventoryWindow.java 
 * Author: Ryan Sanichar
 * 
 * The window for the Inventory. This window
 * allows the user to see the current inventory 
 * in the restaurant, and gives them the ability
 * to add, remove, and update the inventory.
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

import ADT.Ingredient;
import ADT.IngredientHandler;
import ADT.MenuItem;


public class InventoryWindow extends JFrame implements ActionListener
{	
	// Frames, panels, and buttons
	JFrame InventoryAddingFrame;
	JPanel rootPanel, buttonPanel;
	static JPanel textPanel;
	JButton addNewInventory, removeInventory, updateInventory, logOut;
	
	// Layouts
	GridLayout rootLayout = new GridLayout(0,2);
	GridLayout buttonLayout = new GridLayout(6,0);
	GridLayout textLayout = new GridLayout(1,1);
	GridLayout AddingLayout = new GridLayout(4,0);
	
	// Scroller
	JScrollPane MenuScroller;
	
	// Table that will show the data from the inventory
	JTable InventoryTable;
	
	// String that holds the inventory
	String[][] Inventory_RowData = new String[IngredientHandler.IngredientList.length][2];
	String[] Inventory_ColumnNames = {"Name of Ingredient", "Quantity"};
	
	// Text fields for users to write ingredient name and quantity added to the ingredient
	JTextField ingredientField, quantityField;
	
	// A temporary ingredient array and double array used to hold
	// to get the data and display it onto the table
	Ingredient tempIngredient[] = IngredientHandler.IngredientList;
	String[][] inglist = new String[tempIngredient.length][2];
	
	// Actions performed by the button
	public void actionPerformed(ActionEvent e) {
		Object a = e.getSource();
		
		// When the addNewInventory button is selected
		if(a == addNewInventory)
		{
			
			// Retreive data from the text fields
			String tempNewIngredient = ingredientField.getText();
			String tempQuantity = quantityField.getText();
			int quantity;

			try {
					
				// Set the quantity and the name of the ingredient to the handler
				quantity=Integer.parseInt(tempQuantity);
				IngredientHandler.AddIngredient(tempNewIngredient, quantity);
				
				// Make a new temp array of size + 1 to add the new item
				String[][] temp = new String[Inventory_RowData.length + 1][2];
				
				// For loop to make the new table
				for(int i = 0; i < Inventory_RowData.length; i++)
				{
					for(int j = 0; j < 2; j++)
						temp[i][j] = Inventory_RowData[i][j];
				}
				
				// Make the new rows
				temp[Inventory_RowData.length][0] = tempNewIngredient;
				temp[Inventory_RowData.length][1] = tempQuantity;
				
				// Set the new table
				Inventory_RowData = temp;
				InventoryTable.setModel(new DefaultTableModel(Inventory_RowData, Inventory_ColumnNames));
				
			} 
			
			// If the user inputs a value that is not a number
			catch (NumberFormatException e1)
			{
				JOptionPane.showMessageDialog(InventoryAddingFrame, "Please enter a number for quantity.");
			}

			// Return the text to asking for New Ingredient and New Quantity
			ingredientField.setText("New Ingredient");
			quantityField.setText("New Quantity");
		}
		
		// If you select UpdateInventory
		if(a == updateInventory)
		{
			// User needs to select a row
			if(InventoryTable.getSelectedRow() != -1)
			{
			// Retreive data from the text file
			String tempNewIngredient = ingredientField.getText();
			String tempQuantity = quantityField.getText();
			// Send the data to the handler
			int quantity=Integer.parseInt(tempQuantity);
			Ingredient temp=(IngredientHandler.FindInventory(tempNewIngredient));
			IngredientHandler.UpdateInventory(temp, quantity);	
			String newcount= ""+temp.count;
			
			// Update the table
			InventoryTable.getModel().setValueAt(tempNewIngredient,InventoryTable.getSelectedRow(),0);
			InventoryTable.getModel().setValueAt(newcount,InventoryTable.getSelectedRow(),1);
			}
			
			// Error message
	        else
	        {
	        	JOptionPane.showMessageDialog(InventoryAddingFrame, "Please select a menu item.");
	        }
			
			
			
		}
		
		// RemoveInventory button
		if(a == removeInventory)
		{
			
			// User need to selects a row
	        if (InventoryTable.getSelectedRow() != -1) 
	        {
	        	// Get the position of the selected row
	        	int position = InventoryTable.getSelectedRow();
	        	
	        	// Temporary array 
	        	String[][] temp = new String[Inventory_RowData.length - 1][2];
	        	
	        	// The removed item removed from the handler
	        	String removed=(String) InventoryTable.getValueAt(position, 0);
	        	IngredientHandler.Remove(removed);
	        	
	        	// Remake the table
	        	for(int i = 0; i < position; i++)
	        	{
	        		temp[i][0] = Inventory_RowData[i][0];
	        		temp[i][1] = Inventory_RowData[i][1];
	        	}
	        	
	        	for(int i = position + 1; i < Inventory_RowData.length; i++)
	        	{
	        		temp[i-1][0] = Inventory_RowData[i][0];
	        		temp[i-1][1] = Inventory_RowData[i][1];	
	        	}
	        	
	        	// Make the new table
	        	Inventory_RowData = temp;
	        	InventoryTable.setModel(new DefaultTableModel(Inventory_RowData, Inventory_ColumnNames));
	        }
	        
	        
	        // Error
	        else
	        {
	        	JOptionPane.showMessageDialog(InventoryAddingFrame, "Please select a menu item.");
	        }
			
		}
		
		// Logout
		if(a == logOut)
		{
			dispose();
		}
		
	}

	// Make the window
	public static void main(String[] args)
	{
		new InventoryWindow();
	}
	
	// Make the window
	public InventoryWindow()
	{
		super();
		init();
	}
	
	// Initialize the frames
	public void init()
	{
		this.setTitle("Manager - Edit Inventory");
		this.setResizable(true);
		this.setSize(900,600);
		this.frameManipulation();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(rootPanel);
		this.setVisible(true);	
	}
	
	// Frame manipulation 
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
	
	// Initialize the panel
	public void init_buttonPanel()
	{
	
		
		//Initialize text fields
		ingredientField = new JTextField("New Ingredient");
		quantityField = new JTextField("New Quantity");
		
		// Make text fields editable
		ingredientField.setEditable(true);
		quantityField.setEditable(true);

		// Add the text fields to the panel
		buttonPanel.add(ingredientField);
		buttonPanel.add(quantityField);
		
		//Initialize buttons
		addNewInventory = new JButton("Add New Inventory");
		updateInventory = new JButton("Update Inventory");
		removeInventory = new JButton("Delete Ingredient");
		logOut = new JButton("Back");
		
		//Add the action listeners
		addNewInventory.addActionListener(this);
		updateInventory.addActionListener(this);
		removeInventory.addActionListener(this);
		logOut.addActionListener(this);
		
		//Add to the panel
		buttonPanel.add(addNewInventory);
		buttonPanel.add(updateInventory);
		buttonPanel.add(removeInventory);
		buttonPanel.add(logOut);
		
		rootPanel.add(buttonPanel);
		
	}
	
	// Initialize the text panel
	public void init_textPanel()
	{
		
		init_inventory();
		rootPanel.add(textPanel);	
	}
	
	// Initialize the inventory 
	public void init_inventory()
	{
		//Need to populate the arrays before they are fed to the JTable
		inglist = new String[IngredientHandler.IngredientList.length][2];
		Inventory_RowData = inglist;
		getList();
		InventoryTable = new JTable(Inventory_RowData, Inventory_ColumnNames);
		MenuScroller = new JScrollPane(InventoryTable);
		InventoryTable.setFillsViewportHeight(true);
		
		textPanel.add(MenuScroller); 
	}

	// Get the list
	public void getList()
	{

		for(int i = 0; i < inglist.length; i++)
		{
			for(int j = 0; j < inglist[1].length; j++)
			{
				if(j == 0)
				{
					inglist[i][j] = tempIngredient[i].name;
				}
				if(j == 1)
				{
					inglist[i][j] = "" + tempIngredient[i].count;
				}
			}
		}
		
	}
	

	
}
