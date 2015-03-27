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
	
	JFrame InventoryAddingFrame;
	JPanel rootPanel, buttonPanel;
	static JPanel textPanel;
	JButton addNewInventory, removeInventory, updateInventory, logOut;
	
	GridLayout rootLayout = new GridLayout(0,2);
	GridLayout buttonLayout = new GridLayout(6,0);
	GridLayout textLayout = new GridLayout(1,1);
	GridLayout AddingLayout = new GridLayout(4,0);
	
	JScrollPane MenuScroller;
	
	JTable MenuTable;
	
	String[][] Menu_RowData = new String[IngredientHandler.IngredientList.length][2];
	String[] Menu_ColumnNames = {"Name of Ingredient", "Quantity"};
	
	JTextField ingredientField, quantityField;
	
	Ingredient tempIngredient[] = IngredientHandler.IngredientList;
	String[][] inglist = new String[tempIngredient.length][2];
	
	//InventoryList 
	
	public void actionPerformed(ActionEvent e) {
		Object a = e.getSource();
		
		if(a == addNewInventory)
		{
			
			String tempNewIngredient = ingredientField.getText();
			String tempQuantity = quantityField.getText();
			int quantity;

			try {
				
				
				quantity=Integer.parseInt(tempQuantity);
				
				IngredientHandler.AddIngredient(tempNewIngredient, quantity);
				
				String[][] temp = new String[Menu_RowData.length + 1][2];
				
				for(int i = 0; i < Menu_RowData.length; i++)
				{
					for(int j = 0; j < 2; j++)
						temp[i][j] = Menu_RowData[i][j];
				}
				
				temp[Menu_RowData.length][0] = tempNewIngredient;
				temp[Menu_RowData.length][1] = tempQuantity;
				
				Menu_RowData = temp;
				
				MenuTable.setModel(new DefaultTableModel(Menu_RowData, Menu_ColumnNames));
				
			} 
			catch (NumberFormatException e1)
			{
				JOptionPane.showMessageDialog(InventoryAddingFrame, "Please enter a number for quantity.");
			}

			ingredientField.setText("New Ingredient");
			quantityField.setText("New Quantity");
		}
		
		if(a == updateInventory)
		{
			
			if(MenuTable.getSelectedRow() != -1)
			{
			String tempNewIngredient = ingredientField.getText();
			String tempQuantity = quantityField.getText();
			int quantity=Integer.parseInt(tempQuantity);
			Ingredient temp=(IngredientHandler.FindInventory(tempNewIngredient));
			IngredientHandler.UpdateInventory(temp, quantity);	
			String newcount= ""+temp.count;
			MenuTable.getModel().setValueAt(tempNewIngredient,MenuTable.getSelectedRow(),0);
			MenuTable.getModel().setValueAt(newcount,MenuTable.getSelectedRow(),1);
			}
	        else
	        {
	        	JOptionPane.showMessageDialog(InventoryAddingFrame, "Please select a menu item.");
	        }
			
			
			
		}
		
		if(a == removeInventory)
		{
			
	        if (MenuTable.getSelectedRow() != -1) 
	        {
	        	
	        	int position = MenuTable.getSelectedRow();
	        	String[][] temp = new String[Menu_RowData.length - 1][2];
	        	String removed=(String) MenuTable.getValueAt(position, 0);
	        	IngredientHandler.Remove(removed);
	        	for(int i = 0; i < position; i++)
	        	{
	        		temp[i][0] = Menu_RowData[i][0];
	        		temp[i][1] = Menu_RowData[i][1];
	        	}
	        	
	        	for(int i = position + 1; i < Menu_RowData.length; i++)
	        	{
	        		temp[i-1][0] = Menu_RowData[i][0];
	        		temp[i-1][1] = Menu_RowData[i][1];	
	        	}
	        	
	        	Menu_RowData = temp;
	        	MenuTable.setModel(new DefaultTableModel(Menu_RowData, Menu_ColumnNames));
	        }
	        
	        else
	        {
	        	JOptionPane.showMessageDialog(InventoryAddingFrame, "Please select a menu item.");
	        }
			
		}
		
		if(a == logOut)
		{
			dispose();
		}
		
	}

	public static void main(String[] args)
	{
		new InventoryWindow();
	}
	

	public InventoryWindow()
	{
		super();
		init();
	}
	
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
	
	public void init_textPanel()
	{
		
		init_inventory();
		rootPanel.add(textPanel);	
	}
	
	public void init_inventory()
	{
		//Need to populate the arrays before they are fed to the JTable
		inglist = new String[IngredientHandler.IngredientList.length][2];
		Menu_RowData = inglist;
		getList();
		MenuTable = new JTable(Menu_RowData, Menu_ColumnNames);
		MenuScroller = new JScrollPane(MenuTable);
		MenuTable.setFillsViewportHeight(true);
		
		textPanel.add(MenuScroller); 
	}

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
