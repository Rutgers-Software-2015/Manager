package Manager;

import java.awt.*;   
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Login.LoginWindow;
import Manager.MenuHandler;
import Shared.ADT.Ingredient;
import Shared.ADT.IngredientHandler;
import Shared.Gradients.*;
import Shared.Notifications.NotificationGUI;

import javax.swing.ButtonGroup;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class InventoryWindow extends JFrame implements ActionListener{

	
		private NotificationGUI notification;
		//Parent Panels
		private JPanel rootPanel,titlePanel,buttonPanel;
		private GradientPanel backgroundPanel,buttonPanelBackground,cardPanel;
		private GradientPanel card1,card2,card3;
		//Swing Objects
		private GradientButton addButton, removeButton, updateButton, backButton;
		private JButton payWithCash, payWithCard;
		private JLabel titleLabel,dateAndTime;
		//Swing Layouts
		private CardLayout c;
		//Other Variables
		private Timer timer, timer_checker;
		
		private JFrame InventoryAddingFrame;
		
		// Scroller
		private JScrollPane InventoryScroller;
		
		// Table that will show the data from the menu
		private JTable InventoryTable;
		
		// String that holds the menu
		private String[][] Inventory_RowData = new String[IngredientHandler.IngredientList.length][2];
		private String[] Inventory_ColumnNames = {"Name of Ingredient", "Quantity"};
		
		
		// Text fields for users to write name, price, ingredients, and ID
		private JTextField ingredientField, quantityField;
		
		static JPanel textPanel;
		
		// A temporary ingredient array and double array used to hold
		// to get the data and display it onto the table
		Ingredient tempIngredient[] = IngredientHandler.IngredientList;
		String[][] inglist = new String[tempIngredient.length][2];
		
		public InventoryWindow()
		{
			super();
			init();
		}

		public void init()
		{
			this.setTitle("Edit Inventory");
			this.setResizable(true);
			this.setSize(1200,700);
			this.frameManipulation();
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
			this.setResizable(false);
			getContentPane().add(rootPanel);
			
			addWindowListener(new WindowAdapter() // To open main window again if you hit the corner "X"
	        {
	            @Override
	            public void windowClosing(WindowEvent e)
	            {
	                new ManagerRootWindow();
	                dispose();
	            }
	        });
			
			c = (CardLayout)(cardPanel.getLayout());
			
			this.setVisible(true);
		}

		public void frameManipulation()
		{
			rootPanel = new JPanel();
			rootPanel.setLayout(null);
			setBackgroundPanel();
			setTitlePanel();
			setCardPanel();
			setButtonPanel();
			setRootPanel();
		}
		
		private void setRootPanel()
		{
			notification = new NotificationGUI(1, "Manager");
			rootPanel.add(notification);
			rootPanel.add(titlePanel);
			rootPanel.add(cardPanel);
			rootPanel.add(buttonPanel);
			rootPanel.add(buttonPanelBackground);
			rootPanel.add(backgroundPanel);
		}
		
		private void setBackgroundPanel()
		{
			// Create Button Background Panel
			buttonPanelBackground = new GradientPanel();
			buttonPanelBackground.setGradient(new Color(255,220,220), new Color(255,110,110));
			buttonPanelBackground.setBounds(0, 55, 250, 617);
			buttonPanelBackground.setBorder(new LineBorder(new Color(0, 0, 0)));
			
			// Create Background Panel
			backgroundPanel = new GradientPanel();
			backgroundPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			backgroundPanel.setGradient(new Color(255,255,255), new Color(255,110,110));
			backgroundPanel.setLayout(null);
			backgroundPanel.setBounds(0,0,1194,672);
		}
		
		//************************************************************
		//DO NOT edit the following function except for the title name
		//************************************************************
		
		private void setTitlePanel()
		{
			// Create Title Panel
			titlePanel = new JPanel();
			titlePanel.setBounds(0, 0, 1194, 56);
			titlePanel.setLayout(null);
			titlePanel.setOpaque(false);
			// Set Title
			titleLabel = new JLabel("Edit Inventory Interface");
			titleLabel.setHorizontalAlignment(JLabel.CENTER);
			titleLabel.setFont(titleLabel.getFont().deriveFont(38f));
			titleLabel.setBorder(BorderFactory.createLineBorder(Color.black));
			titleLabel.setBounds(new Rectangle(0, 0, 793, 56));
			
						// Add components to Title Panel
						titlePanel.add(titleLabel);
						// Set Date and Time
						dateAndTime = new JLabel();
						dateAndTime.setBounds(792, 0, 402, 56);
						titlePanel.add(dateAndTime);
						dateAndTime.setHorizontalAlignment(JLabel.CENTER);
						dateAndTime.setFont(dateAndTime.getFont().deriveFont(28f));
						dateAndTime.setBorder(BorderFactory.createLineBorder(Color.black));
						updateClock();
						// Create a timer to update the clock
						timer = new Timer(500,this);
			            timer.setRepeats(true);
			            timer.setCoalesce(true);
			            timer.setInitialDelay(0);
			            timer.start();
			        	timer_checker = new Timer(5000,this);
			        	timer_checker.setRepeats(true);
			        	timer_checker.setCoalesce(true);
			        	timer_checker.setInitialDelay(0);
			        	timer_checker.start();
		}
		
		//*********************************************************
		//DO NOT change the location of the following panel
		//*********************************************************
		
		private void setButtonPanel()
		{
			
			// Create Button Panel
			buttonPanel = new JPanel();
			buttonPanel.setBounds(7, 61, 236, 604);
			buttonPanel.setOpaque(false);
			buttonPanel.setBorder(null);
			buttonPanel.setLayout(new GridLayout(6, 0, 5, 5));
			
			//Initialize text fields
			ingredientField = new JTextField("New Ingredients");
			quantityField = new JTextField("New Quantity");
			
			// Make text fields editable
			ingredientField.setEditable(true);
			quantityField.setEditable(true);
			
			// Add the text fields to the panel
			buttonPanel.add(ingredientField);
			buttonPanel.add(quantityField);
			
			
			addButton = new GradientButton("Add Inventory Item");
			addButton.addActionListener(this);
			addButton.setFont(addButton.getFont().deriveFont(16.0f));
			addButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			addButton.setFocusPainted(false);
			
			
			removeButton = new GradientButton("Remove Inventory Item");
			removeButton.addActionListener(this);
			removeButton.setFont(removeButton.getFont().deriveFont(16.0f));
			removeButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			removeButton.setFocusPainted(false);
			

			updateButton = new GradientButton("Update Inventory Item");
			updateButton.addActionListener(this);
			updateButton.setFont(updateButton.getFont().deriveFont(16.0f));
			updateButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			updateButton.setFocusPainted(false);
			
			// Set Back Button
			backButton = new GradientButton("BACK");
			backButton.addActionListener(this);												
			backButton.setFont(backButton.getFont().deriveFont(16.0f));
			backButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			backButton.setFocusPainted(false);
			
			buttonPanel.add(addButton);
			buttonPanel.add(removeButton);
			buttonPanel.add(updateButton);
			buttonPanel.add(backButton);
		}
		
		public void init_inventory()
		{
//			//Need to populate the arrays before they are fed to the JTable
//			inglist = new String[IngredientHandler.IngredientList.length][2];
//			Inventory_RowData = inglist;
//			getList();
//			InventoryTable = new JTable(Inventory_RowData, Inventory_ColumnNames);
//			InventoryScroller = new JScrollPane(InventoryTable);
//			InventoryTable.setFillsViewportHeight(true);
			
			InventoryTable = new JTable();
			InventoryTable.setRowSelectionAllowed(false);
			InventoryTable.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null},

				},
				new String[] {
					"Ingredient", "Quantity"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Integer.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			InventoryTable.getColumnModel().getColumn(0).setResizable(false);
			InventoryTable.getColumnModel().getColumn(0).setPreferredWidth(120);
			InventoryTable.getColumnModel().getColumn(1).setResizable(false);
			InventoryTable.getColumnModel().getColumn(1).setMaxWidth(100);
			InventoryScroller = new JScrollPane(InventoryTable);
			InventoryTable.setFillsViewportHeight(true);
		
			
		}
		
		/*
		 * This function fills the Inventory in the top left ScrollView.The ScrollViews contains all the Ingredients.
		 *  @returns nothing. 
		 */

		private void FillInventory() throws SQLException
		{
			
			DefaultTableModel ModelInven=(DefaultTableModel) InventoryTable.getModel();
			InventoryHandler test=new InventoryHandler();

			try{

			
			String[] InventoryName=test.getInventoryName();
			Integer[] InventoryQuant=test.getInventoryQ();
			int rows=InventoryName.length;
		
				int rowtemp=0;
				for(int i=0;i<InventoryName.length;i++)
				{
				
					ModelInven.setValueAt(InventoryName[i],rowtemp,0);
					ModelInven.setValueAt(InventoryQuant[i],rowtemp,1);
					rowtemp++;
					if(ModelInven.getRowCount()<rows)
					{
						ModelInven.addRow(new Object[][]{
								{null, null},});
					}
				}
			
			
			}
			catch (SQLException e)
			{
				
			};

		}
		
		// Get the list
		
		//********************************************************************************
		//DO NOT deviate from the card layout or change the size/location of the cardPanel.
		//Creating and adding cards is OK
		//********************************************************************************
	
		public void init_textPanel()
		{
			init_inventory();
			rootPanel.add(textPanel);
			
		}
		
		private void setCardPanel()
		{
			cardPanel = new GradientPanel();
			cardPanel.setLayout(new CardLayout()); // How to define a Card Layout
			cardPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			cardPanel.setGradient(new Color(255,255,255), new Color(255,110,110));
			cardPanel.setBounds(273, 79, 896, 569);
			
			init_inventory();
			
			card1 = new GradientPanel();
			card1.add(InventoryScroller); 
			card1.setLayout(new GridLayout(1,0));
			
			cardPanel.add(card1,""); // How to add cards to a Card Layout
			
			cardPanel.setVisible(true); 
			
			/*
			
			card1 = new GradientPanel(); // Create card with a button YES
			card1.add(new JButton("YES"));
			card1.setLayout(new GridLayout(1,0));
			
			card2 = new GradientPanel(); // Create card with a button NO
			card2.setLayout(new GridLayout(1,0));
			card2.add(new JButton("NO"));
			
			card3 = new GradientPanel(); // Create blank card
			
			cardPanel.add(card1,"YES"); // How to add cards to a Card Layout
			cardPanel.add(card2,"NO"); // The string can be named anything, the string is how you call the card
			cardPanel.add(card3,"BLANK");
			
			cardPanel.setVisible(true); 
			*/
		}
		
		// Action Listener
		public void actionPerformed(ActionEvent e) 
		{
			Object a = e.getSource();
			
			InventoryTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				
				   public void valueChanged(ListSelectionEvent event) {
				        if (InventoryTable.getSelectedRow() > -1) 
				        {
							
							try{
								
								int row = InventoryTable.getSelectedRow();
								
								String column_ingredient = InventoryTable.getModel().getValueAt(row, 0).toString();
								String column_quantity = InventoryTable.getModel().getValueAt(row, 1).toString();
								
								ingredientField.setText(column_ingredient);
								quantityField.setText(column_quantity);
								
								ingredientField.updateUI();
								quantityField.updateUI();
								
								buttonPanel.updateUI();
								
							}
							
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, ex);
							}
				        }
				    }
				   
			});
			
			if(a == backButton)
				{
					new ManagerRootWindow();
					dispose();
				}
			if(a == addButton)
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
			if(a == removeButton)
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
			if(a == updateButton)
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
			if(a == timer)
				{
					updateClock();
				}
			
			if(a==timer_checker)
			{
				try {
					FillInventory();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		private void updateClock() {
            dateAndTime.setText(DateFormat.getDateTimeInstance().format(new Date()));
        }
}