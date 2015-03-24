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
	JFrame MenuAddingFrame;
	JPanel rootPanel, buttonPanel;
	JPanel AddingPanel;
	static JPanel textPanel;
	JButton addItem, removeItem, Add, logOut;
	
	
	GridLayout rootLayout = new GridLayout(0,2);
	GridLayout buttonLayout = new GridLayout(7,0);
	GridLayout textLayout = new GridLayout(1,1);
		
	JScrollPane MenuScroller;
	
	JTable MenuTable;
	
	String[][] Menu_RowData;
	String[] Menu_ColumnNames = {"Name", "Ingredients", "Price", "ID"};
	
	JTextField nameField, priceField, ingredientField, IDField;
	
	public static void main(String[] args)
	{
		new MenuWindow();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		Object a = e.getSource();
		
		if(a == addItem)
		{
			
			String tempName = nameField.getText();
			String tempIngredient = ingredientField.getText();
			String tempPrice = priceField.getText();
			String tempID = IDField.getText();
			
			String[][] temp = new String[Menu_RowData.length + 1][4];
			
			for(int i = 0; i < Menu_RowData.length; i++)
			{
				for(int j = 0; j < 4; j++)
					temp[i][j] = Menu_RowData[i][j];
			}
			
			temp[Menu_RowData.length][0] = tempName;
			temp[Menu_RowData.length][1] = tempIngredient;
			temp[Menu_RowData.length][2] = tempPrice;
			temp[Menu_RowData.length][3] = tempID;
			
			Menu_RowData = temp;
			
			MenuTable.setModel(new DefaultTableModel(Menu_RowData, Menu_ColumnNames));
		}
		
		if(a == removeItem)
		{
	        if (MenuTable.getSelectedRow() != -1) 
	        {
	        	int position = MenuTable.getSelectedRow();
	        	String[][] temp = new String[Menu_RowData.length - 1][4];
	        	
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
	        	
	        	Menu_RowData = temp;
	        	MenuTable.setModel(new DefaultTableModel(Menu_RowData, Menu_ColumnNames));
	        	
	            //remove selected row from the model
	            // model.removeRow(MenuTable.getSelectedRow());
	        }
			
		}
		
		if(a == logOut)
		{
			
		}
		
	}
	
	
	
	public MenuWindow()
	{
		super();
		init();
	}
	
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
		removeItem = new JButton("Remove Item");
		logOut = new JButton("Logout");
		
		//Add the action listeners
		addItem.addActionListener(this);
		removeItem.addActionListener(this);
		logOut.addActionListener(this);
		
		//Add to the panel
		buttonPanel.add(addItem);
		buttonPanel.add(removeItem);
		buttonPanel.add(logOut);
		
		rootPanel.add(buttonPanel);
		
	}
	
	public void init_textPanel()
	{
		init_menu();
		rootPanel.add(textPanel);
		
	}
	
	public void init_menu()
	{
		//Need to populate the arrays before they are fed to the JTable
		Menu_RowData = MenuHandler.getMenu();
		MenuTable = new JTable(Menu_RowData, Menu_ColumnNames);
		MenuScroller = new JScrollPane(MenuTable);
		MenuTable.setFillsViewportHeight(true);
		
		textPanel.add(MenuScroller); 
	}
	
}
