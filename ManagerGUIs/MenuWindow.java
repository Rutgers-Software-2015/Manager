package Manager.ManagerGUIs;

import java.awt.*;   
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
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
import javax.swing.JTabbedPane;
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
//import Manager.MenuHandler;
import Shared.ADT.MenuItem;
import Shared.Gradients.*;
import Shared.Notifications.NotificationGUI;

import javax.swing.ButtonGroup;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Manager.ManagerHandlers.*;
import Manager.ManagerCommunicator.*;




public class MenuWindow extends JFrame implements ActionListener{

	
		//Parent Panels
		private NotificationGUI notification;
		private JPanel rootPanel,titlePanel,buttonPanel;
		private GradientPanel backgroundPanel,buttonPanelBackground,cardPanel, Menu_Card;
		private GradientPanel card1,card2,card3;
		//Swing Objects
		private GradientButton addItem, removeItem, updateItem, backButton, viewButton;
		private JButton payWithCash, payWithCard;
		private JLabel titleLabel,dateAndTime;
		//Swing Layouts
		private CardLayout c;
		//Other Variables
		private Timer timer;
		public GradientPanel AddMenu_Card;
		private AddMenuItemPanel form = new AddMenuItemPanel();
		private JTabbedPane formpane;
		private GradientButton Done_Add, Done_Edit, Cancel;
		
		private JFrame Add_Error_Window;
		private JLabel Add_Error_Message;
		private MenuObj MenuItem;
		private EmpSummaryPanel SummaryForm;
		
		MenuTableViewer MTV = new MenuTableViewer();
		
		
		private JFrame MenuAddingFrame;
		
		// Scroller
		private JScrollPane MenuScroller;
		
		// Table that will show the data from the menu
		JTable MenuTable;
		
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
				
				  
			
			});	
			
			*/
			
			c = (CardLayout)(cardPanel.getLayout());
			this.setVisible(true);
			 
		}
		
		private void setFormPanel()
		{
			formpane = new JTabbedPane();
			formpane.setVisible(true);
			formpane.addTab("Page 1", form);
			AddMenu_Card.add(formpane, BorderLayout.CENTER);
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
			
		//	c = (CardLayout)(cardPanel.getLayout());

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
			buttonPanel.setLayout(new GridLayout(5, 0, 5, 5));
			
			
			viewButton = new GradientButton("View Menu");
			viewButton.addActionListener(this);
			viewButton.setFont(viewButton.getFont().deriveFont(16.0f));
			viewButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			viewButton.setFocusPainted(false);
			
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
			
			buttonPanel.add(viewButton);
			buttonPanel.add(addItem);
			buttonPanel.add(removeItem);
			buttonPanel.add(updateItem);
			buttonPanel.add(backButton);
		}
		
		//********************************************************************************
		//DO NOT deviate from the card layout or change the size/location of the cardPanel.
		//Creating and adding cards is OK
		//********************************************************************************
	
		public void init_textPanel() throws SQLException
		{
			rootPanel.add(textPanel);	
		}
		
		private void setCardPanel() throws SQLException
		{
			cardPanel = new GradientPanel();
			cardPanel.setLayout(new CardLayout()); // How to define a Card Layout
			cardPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			cardPanel.setGradient(new Color(255,255,255), new Color(255,110,110));
			cardPanel.setBounds(273, 79, 896, 569);
			
			AddMenu_Card = new GradientPanel();
			AddMenu_Card.setLayout(new BorderLayout());
			AddMenu_Card.setVisible(true);
			setFormPanel();
			setDoneAdd();
			
			// init_menu();
			
			// MenuTable = MTV.gen_Menu();
			
			MTV = new MenuTableViewer();
			cardPanel.add(MTV, "Menu");
			cardPanel.add(AddMenu_Card, "AddMenuItem");
			cardPanel.setVisible(true);
			
			
			// MenuHandle.getAllMenu();
			
			
			/*
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
			
			Menu_Card = new GradientPanel();
			Menu_Card.setLayout(new GridLayout(1,1));
			Menu_Card.add(MenuTable);
			cardPanel.add(Menu_Card, "MenuProcess");
			// c.show(cardPanel, "MenuProcess");
			*/
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
				
		private void setDoneAdd()
		{
			Done_Add = new GradientButton("Done");
			Done_Add.addActionListener(this);
			AddMenu_Card.add(Done_Add, BorderLayout.SOUTH);
		}
		
		public boolean isThereInternet()
		{
			try
			{
				URL yourl = new URL("http://google.com");
				HttpURLConnection yourlConnect = (HttpURLConnection)yourl.openConnection();
				yourlConnect.setConnectTimeout(2000);
				
				Object objData = yourlConnect.getContent();
			}catch(UnknownHostException e)
			{
				return false;
			}
			catch(IOException e)
			{
				return false;
			}
			return true;
		}
		
		/*
		
		private void setEditPage()
		{
			EditPage_Card = new EmpEditPage();		
			EmpEdit_Root.add(EditPage_Card, BorderLayout.CENTER);
			EditPage_Card.setVisible(true);
		}
		
		*/
		
		public JTable remove(JTable table, int row)
		{
			
			table.remove(row);
			return table;
		}
		
		private void FillMenu()
		{
			
			DefaultTableModel ModelMenu;
			ModelMenu = (DefaultTableModel) MTV.MenuTable.getModel();
			MenuHandler test = new MenuHandler();

			try{

			Integer[] MenuID = test.getMenuID();
			String[] MenuName = test.getMenuName();
			Double[] MenuPrice = test.getMenuPrice();
			Double[] MenuCost = test.getMenuCost();
			String[] MenuIngredients = test.getMenuIngredients();
			String[] MenuDescription = test.getMenuDescription();
			String[] MenuSection = test.getMenuDescription();
			
			int rows = MenuName.length;
		
				int rowtemp=0;
				for(int i = 0; i < MenuName.length; i++)
				{
				
					ModelMenu.setValueAt(MenuID[i],rowtemp,0);
					ModelMenu.setValueAt(MenuName[i],rowtemp,1);
					ModelMenu.setValueAt(MenuPrice[i],rowtemp,2);
					ModelMenu.setValueAt(MenuCost[i],rowtemp,3);
					ModelMenu.setValueAt(MenuIngredients[i],rowtemp,4);
					ModelMenu.setValueAt(MenuDescription[i],rowtemp, 5);
					ModelMenu.setValueAt(MenuSection[i],rowtemp, 6);
					
				
					rowtemp++;
					if(ModelMenu.getRowCount()<rows)
					{
						ModelMenu.addRow(new Object[][]{{null, null, null, null, null, null, null},});
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
			
			if(a == viewButton)
			{
				c.show(cardPanel, "Menu");
				removeItem.setVisible(true);
				updateItem.setVisible(true);
				backButton.setVisible(true);
				
			}
			

			if(a == Done_Add)
			{
				getNewMenuItem();  
				MTV.gen_Menu();
			}
			
			if(a == Done_Edit)
			{
				// getEditItem();
			}

						
			//c.show(cardPanel, "MenuProcess");
			if(a == backButton)
				{
					notification.close();
					MenuHandle.disconnect();
					new ManagerRootWindow();
					dispose();
				}
			
			if(a == addItem)
				{

				removeItem.setVisible(false);
				updateItem.setVisible(false);
				backButton.setVisible(false);
				c.show(cardPanel, "AddMenuItem");
			
				
				}
			if(a == removeItem)
				{
				
				   // c.show(cardPanel, "NO"); //Example of how to show card panel
					// User need to selects a row
			        if (MTV.MenuTable.getSelectedRow() != -1) 
			        {

						try 
						{
				        	int position = MTV.MenuTable.getSelectedRow();
				        	String[] names = MenuHandle.getMenuName();
				        	String name = names[position];
							MenuHandle.RemoveMenuItem(name);
							remove(MTV.MenuTable, position);
							MTV.gen_Menu();
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        } 
		        
			        else 
			        	JOptionPane.showMessageDialog(MenuAddingFrame, "Please select a menu item.");
				
				}
			
			if(a == updateItem)
				{
				// c.show(cardPanel, "BLANK"); //Example of how to show card panel
				
				// User needs to select a row
				
			        if (MTV.MenuTable.getSelectedRow()  != -1) 
			        {
					
						removeItem.setVisible(false);
						updateItem.setVisible(false);
						backButton.setVisible(false);	
			        	
			        // Retrieve data from the text file	
					String tempName = nameField.getText();
					String tempIngredient = ingredientField.getText();
					String tempPrice = priceField.getText();
					String tempID = IDField.getText();
					
			        }
			        
					// Error message
					else 
						JOptionPane.showMessageDialog(MenuAddingFrame, "Please select a menu item.");
				
				}
			
			if(a == timer)
					updateClock();

		}	
		
		private void updateClock() {
            dateAndTime.setText(DateFormat.getDateTimeInstance().format(new Date()));
        }
		
		
		
		public void getNewMenuItem()
		{
			Add_Error_Window = new JFrame();
			Add_Error_Window.setSize(200, 100);
			Add_Error_Window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			
			String[] S = form.answers();
			String S1flag;
			
			//Check form 1
			for(int i = 0; i < S.length; i++)
			{
				//[fname, lname, address, dob, school, gpa, question]
				if((S[i] == null) || S[i] == "")
				{
					
					if(i == 0){
						S1flag = "Error: Enter Menu Item Name";
						Add_Error_Message = new JLabel(S1flag);
						Add_Error_Window.add(Add_Error_Message);
						Add_Error_Window.setVisible(true);
						return;
					}
					if(i == 1){
						S1flag = "Error: Enter Price";
						Add_Error_Message = new JLabel(S1flag);
						Add_Error_Window.add(Add_Error_Message);
						Add_Error_Window.setVisible(true);
						return;
					}
					if(i == 2){
						S1flag = "Error: Enter Cost";
						Add_Error_Message = new JLabel(S1flag);
						Add_Error_Window.add(Add_Error_Message);
						Add_Error_Window.setVisible(true);
						return;
					}
					if(i == 3){
						S1flag = "Error: Enter Ingredients";
						Add_Error_Message = new JLabel(S1flag);
						Add_Error_Window.add(Add_Error_Message);
						Add_Error_Window.setVisible(true);
						return;
					}
					if(i == 4){
						S1flag = "Error: Enter Description";
						Add_Error_Message = new JLabel(S1flag);
						Add_Error_Window.add(Add_Error_Message);
						Add_Error_Window.setVisible(true);
						return;
					}
					if(i == 5){
						S1flag = "Error: Enter Section";
						Add_Error_Message = new JLabel(S1flag);
						Add_Error_Window.add(Add_Error_Message);
						Add_Error_Window.setVisible(true);
						return;
					}
				}
			}
			

			//Construct the handler object
			///[fname, lname, address, dob, school, gpa, question]
			///FNAME, LNAME, ADDRESS, DOB, SCHOOL, GPA, q1, q2, q3, q4, position, sal
		//	MenuItem = new MenuObj("", S[0], S[1], S[2], S[3], S[4], S[5], "1");
			// SummaryForm = new EmpSummaryPanel(MenuItem);
		//	cardPanel.add(SumForm,"Summary");
		// 	c.show(cardPanel, "Summary");
	
			//Clean up the form
			form.cleanform();
			
			//Send the data to the database
			Boolean INTERNET;
			INTERNET = isThereInternet();
			if(INTERNET == true){
				System.out.println("Adding the New Menu Item!");
				MenuHandle.AddMenuItem(MenuItem);
				Add_Error_Message = new JLabel("Item Added!");
				Add_Error_Window.add(Add_Error_Message);
				Add_Error_Window.setVisible(true);
				
				/*
				
				//HERE
				EmpEdit_Root = new GradientPanel();
				EmpEdit_Root.setLayout(new BorderLayout());
				EmpEdit_Root.setVisible(true);
				setEditPage();
				setEmpEditButton();
				EmpEdit_Root.updateUI();
				cardPanel.add(EmpEdit_Root, "EditProcess");
				//Here
				FireRoot_Card = new GradientPanel();
				FireRoot_Card.setLayout(new BorderLayout());
				FireRoot_Card.setVisible(true);
				setFireCard();
				cardPanel.add(FireRoot_Card, "FireProcess");
				
				*/
				
			}
			else
			{
				Add_Error_Message = new JLabel("NO INTERNET CONNECTION!");
				Add_Error_Window.add(Add_Error_Message);
				Add_Error_Window.setVisible(true);
			}
		
		}
		
}