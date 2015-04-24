package Manager;

import java.awt.*;   
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

import javax.swing.AbstractAction;
import javax.swing.Action;
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
import javax.swing.table.JTableHeader;

import Login.LoginWindow;
import Manager.MenuHandler;
import Shared.ADT.MenuItem;
import Shared.Gradients.*;
import Shared.Notifications.NotificationGUI;

import javax.swing.ButtonGroup;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class MenuWindow extends JFrame implements ActionListener{

	
		//Parent Panels
		private NotificationGUI notification;
		private JPanel rootPanel,titlePanel,buttonPanel;
		private GradientPanel backgroundPanel,buttonPanelBackground,cardPanel;
		private GradientPanel card1,card2,card3;
		//Swing Objects
		private GradientButton addItem, removeItem, updateItem, backButton;
		private JButton payWithCash, payWithCard;
		private JLabel titleLabel,dateAndTime;
		//Swing Layouts
		private CardLayout c;
		//Other Variables
		private Timer timer;
		
		private JFrame MenuAddingFrame;
		
		// Scroller
		private JScrollPane MenuScroller;
		
		// Table that will show the data from the menu
		private JTable MenuTable;
		
		private MenuHandler MenuHandle = new MenuHandler();
		
		// String that holds the menu
		private String[] Menu_RowData;
		private String[] Menu_ColumnNames = {"Name", "Ingredients", "Price", "ID"};
		
		// Text fields for users to write name, price, ingredients, and ID
		private JTextField nameField, priceField, ingredientField, IDField;
		
		static JPanel textPanel;
				
		public MenuWindow() throws SQLException
		{
			super();
			init();
		}

		public void init() throws SQLException
		{
			this.setTitle("Edit Menu");
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

		public void frameManipulation() throws SQLException
		{
			rootPanel = new JPanel();
			rootPanel.setLayout(null);
			setBackgroundPanel();
			setTitlePanel();
			setCardPanel();
			setButtonPanel();
			setRootPanel();
				
		}

		
		public static void main(String[] args) throws SQLException
		{
			new MenuWindow();
		}
		
		
		
		private void makeMenu() throws SQLException {
			// TODO Auto-generated method stub
			
			DefaultTableModel model = (DefaultTableModel) MenuTable.getModel();
			MenuHandler handler = new MenuHandler();
			String[] Menu = handler.getMenu();
			int temprow = 0;
			
			for(int i=0;i< Menu.length;i++)
			{

				model.setValueAt(Menu[i], temprow, 0);
				i++;
				model.setValueAt(Menu[i], temprow, 1);
				i++;
				model.setValueAt(Menu[i], temprow, 2);
				i++;
				model.setValueAt(Menu[i], temprow, 3);
				i++;
				

				model.addRow(new Object[][] {{null, null, null, null}});
				
			}
			
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
			titleLabel = new JLabel("Edit Menu Interface");
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
			buttonPanel.setLayout(new GridLayout(8, 0, 5, 5));
			
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
			
			// Set Request Table Status Change Button
			addItem = new GradientButton("Add Menu Item");
			addItem.addActionListener(this);
			addItem.setFont(addItem.getFont().deriveFont(16.0f));
			addItem.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			addItem.setFocusPainted(false);
			
			// Set Manage Order Queue Button
			removeItem = new GradientButton("Remove Menu Item");
			removeItem.addActionListener(this);
			removeItem.setFont(removeItem.getFont().deriveFont(16.0f));
			removeItem.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			removeItem.setFocusPainted(false);
			
			// Set Accept Payment Button
			updateItem = new GradientButton("Edit Menu Item");
			updateItem.addActionListener(this);
			updateItem.setFont(updateItem.getFont().deriveFont(16.0f));
			updateItem.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			updateItem.setFocusPainted(false);
			
			// Set Back Button
			backButton = new GradientButton("BACK");
			backButton.addActionListener(this);												
			backButton.setFont(backButton.getFont().deriveFont(16.0f));
			backButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			backButton.setFocusPainted(false);
			
			buttonPanel.add(addItem);
			buttonPanel.add(removeItem);
			buttonPanel.add(updateItem);
			buttonPanel.add(backButton);
		}
		
		public void init_menu() throws SQLException
		{
			//Need to populate the arrays before they are fed to the JTable
//			Menu_RowData = MenuHandler.getMenu();
//			MenuTable = new JTable(Menu_RowData, Menu_ColumnNames);
			
		//	makeMenu();
		//	MenuTable = new JTable();
			// MenuTable.getColumnModel().getColumn(0).setPreferredWidth(130);
			// MenuTable.getColumnModel().getColumn(1).setPreferredWidth(220);
			
			MenuTable = new JTable();
			MenuTable.setRowSelectionAllowed(false);
			MenuTable.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null},

					},				new String[] {
							"ID", "Name", "Price", "Cost", "Ingredients", "Description", "Section", "Valid"
					}
				) {
					Class[] columnTypes = new Class[] {
							Integer.class, String.class, Double.class, Double.class, String.class, String.class, String.class, Integer.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						false, false, false, false, false, false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
			});
			MenuScroller = new JScrollPane(MenuTable);
			MenuTable.setFillsViewportHeight(true);
			textPanel.add(MenuScroller); 
			
		}
		
		public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

		    ResultSetMetaData metaData = rs.getMetaData();

		    // names of columns
		    Vector<String> columnNames = new Vector<String>();
		    int columnCount = metaData.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        columnNames.add(metaData.getColumnName(column));
		    }

		    // data of the table
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		        }
		        data.add(vector);
		    }

		    return new DefaultTableModel(data, columnNames);

		}

		
		//********************************************************************************
		//DO NOT deviate from the card layout or change the size/location of the cardPanel.
		//Creating and adding cards is OK
		//********************************************************************************
	
		public void init_textPanel() throws SQLException
		{
			init_menu();
			rootPanel.add(textPanel);
			
		}
		
		private void setCardPanel() throws SQLException
		{
			cardPanel = new GradientPanel();
			cardPanel.setLayout(new CardLayout()); // How to define a Card Layout
			cardPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			cardPanel.setGradient(new Color(255,255,255), new Color(255,110,110));
			cardPanel.setBounds(273, 79, 896, 569);
			
			MenuTable = new JTable();
//			ModelOrders = (DefaultTableModel)MenuOrder.getModel();
//			Menu_RowData = MenuHandler.getMenu();
//			MenuTable = new JTable(Menu_RowData, Menu_ColumnNames);
			

//			MenuTable.getColumnModel().getColumn(0).setPreferredWidth(130);
//			MenuTable.getColumnModel().getColumn(1).setPreferredWidth(220);
			MenuScroller = new JScrollPane(MenuTable);
			MenuTable.setFillsViewportHeight(true);
			
			card1 = new GradientPanel();
			card1.add(MenuScroller); 
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
		
		private void FillMenu() throws SQLException
		{
			
			DefaultTableModel ModelInven=(DefaultTableModel) MenuTable.getModel();
			MenuHandler test = new MenuHandler();

			try{
				
			Integer[] MenuID = test.getMenuID();	
			String[] MenuName = test.getMenuName();
			Double[] MenuPrice = test.getMenuPrice();
			Double[] MenuCost = test.getMenuCost();
			String[] Ingredients = test.getMenuIngredients();
			String[] MenuDescription = test.getMenuDescription();
			String[] MenuSection = test.getMenuSection();
			Integer[] isValid = test.getMenuisValid();
			
			
			int rows= MenuName.length;
			int rowtemp=0;
			
				for(int i=0;i< MenuName.length;i++)
				{
					ModelInven.setValueAt(MenuName[i],rowtemp,0);
					ModelInven.setValueAt(MenuName[i],rowtemp,1);
					ModelInven.setValueAt(MenuName[i],rowtemp,2);
					ModelInven.setValueAt(MenuName[i],rowtemp,3);
					ModelInven.setValueAt(MenuName[i],rowtemp,4);
					ModelInven.setValueAt(MenuName[i],rowtemp,5);
					ModelInven.setValueAt(MenuName[i],rowtemp,6);
					ModelInven.setValueAt(MenuName[i],rowtemp,7);
					rowtemp++;
					
					if(ModelInven.getRowCount() < rows)
					{
						ModelInven.addRow(new Object[][]{{null, null},});
					}
				}
			
			
			}
			catch (SQLException e)
			{
				
			};

		}
		
		// Action Listener
		public void actionPerformed(ActionEvent e) 
		{
			Object a = e.getSource();
			/*
			MenuTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				
				   public void valueChanged(ListSelectionEvent event) {
				        if (MenuTable.getSelectedRow() > -1) 
				        {
							
							try{
								
								int row = MenuTable.getSelectedRow();
								
								String column_name = MenuTable.getModel().getValueAt(row, 0).toString();
								String column_ingredient = MenuTable.getModel().getValueAt(row, 1).toString();
								String column_price = MenuTable.getModel().getValueAt(row, 2).toString();
								String column_ID = MenuTable.getModel().getValueAt(row, 3).toString();
								
								nameField.setText(column_name);
								ingredientField.setText(column_ingredient);
								priceField.setText(column_price);
								IDField.setText(column_ID);
								
								nameField.updateUI();
								ingredientField.updateUI();
								priceField.updateUI();
								IDField.updateUI();
								
								buttonPanel.updateUI();
								
							}
							
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, ex);
							}
							
				        }
				    }
				
				   
			
			});	*/
			
			
			if(a == backButton)
				{
					new ManagerRootWindow();
					dispose();
				}
			if(a == addItem)
				{
			   
				
				
				// Retrieve data from the text fields
				String tempName = nameField.getText();
				String tempIngredient = ingredientField.getText();
				String tempPrice = priceField.getText();
				String tempID = IDField.getText();
				
				/*
				
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
				*/
				}
			if(a == removeItem)
				{
				
				
				
			   // c.show(cardPanel, "NO"); //Example of how to show card panel
				

				// User need to selects a row
		        if (MenuTable.getSelectedRow() != -1) 
		        {
		        	
		        	/*
		        	
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
		    	
		    		*/
		    		
		        }
		        
		        
				else 
				{
					JOptionPane.showMessageDialog(MenuAddingFrame, "Please select a menu item.");
				}
				
				
				}
			
			if(a == updateItem)
				{
				// c.show(cardPanel, "BLANK"); //Example of how to show card panel
				
				// User needs to select a row
				
		        if (MenuTable.getSelectedRow() != -1) 
		        {
				
		        // Retrieve data from the text file	
				String tempName = nameField.getText();
				String tempIngredient = ingredientField.getText();
				String tempPrice = priceField.getText();
				String tempID = IDField.getText();
				
				/*
				
				// Update the table
				MenuTable.getModel().setValueAt(tempName, MenuTable.getSelectedRow(), 0);
				MenuTable.getModel().setValueAt(tempIngredient, MenuTable.getSelectedRow(), 1);
				MenuTable.getModel().setValueAt(tempPrice, MenuTable.getSelectedRow(), 2);
				MenuTable.getModel().setValueAt(tempID, MenuTable.getSelectedRow(), 3);
				
				MenuTable.getColumnModel().getColumn(0).setPreferredWidth(130);
				MenuTable.getColumnModel().getColumn(1).setPreferredWidth(220);
				
				*/
				
		        }
		        
				// Error message
				else 
				{
					JOptionPane.showMessageDialog(MenuAddingFrame, "Please select a menu item.");
				}
				
				
				}
			if(a == timer)
				{
					updateClock();
				}
		}
		
		
		
		
		private void updateClock() {
            dateAndTime.setText(DateFormat.getDateTimeInstance().format(new Date()));
        }
}