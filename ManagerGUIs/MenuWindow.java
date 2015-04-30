/**
 * MenuWindow.java
 * 
 * This class facilitates interaction between the Manager
 * and the Menu features of the Manager Restaurant System
 * 
 * @author Ryan Sanichar
 * @tester Ryan Sanichar
 * @debugger Ryan Sanichar
 * 
 */

package Manager.ManagerGUIs;

import java.awt.*;    

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
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

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
		private GradientPanel backgroundPanel,buttonPanelBackground,cardPanel;
		//Swing Objects
		private GradientButton addItem, removeItem, updateItem, backButton, viewButton;
		private JLabel titleLabel,dateAndTime;
		//Swing Layouts
		private CardLayout c;
		//Other Variables
		private Timer timer;
		public GradientPanel AddMenu_Card, EditMenu_Card;
		private AddMenuItemPanel form_add = new AddMenuItemPanel();
		private EditMenuItemPanel form_edit = new EditMenuItemPanel();
		private JTabbedPane formpane_add, formpane_edit;
		private GradientButton Done_Add, Done_Edit;
		
		private JFrame Add_Error_Window;
		private JLabel Add_Error_Message;
		private MenuObj MenuItem;
		private EmpSummaryPanel SummaryForm;
		
		// Instance of the table viewer
		MenuTableViewer MTV = new MenuTableViewer();
		
		private JFrame MenuAddingFrame;
		
		// Scroller
		private JScrollPane MenuScroller;		
		
		// Instance of the Menu Handler
		private MenuHandler MenuHandle = new MenuHandler();
		
		// Text panel
		private static JPanel textPanel;
		
		
		// Constructor
		public MenuWindow() throws SQLException
		{
			super();
			init();			
			c = (CardLayout)(cardPanel.getLayout());
			this.setVisible(true);
			 
		}
		
		// Panel for the form to add menu items
		private void setFormAddPanel()
		{
			formpane_add = new JTabbedPane();
			formpane_add.setVisible(true);
			formpane_add.addTab("Add Menu Item", form_add);
			AddMenu_Card.add(formpane_add, BorderLayout.CENTER);

		}

		// Panel for the form to edit menu items
		private void setFormEditPanel()
		{			
			formpane_edit = new JTabbedPane();
			formpane_edit.setVisible(true);
			formpane_edit.addTab("Edit Menu Item", form_edit);
			EditMenu_Card.add(formpane_edit, BorderLayout.CENTER);
		}
		
		// Initialize the GUI and the frames
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
					notification.close();
					MenuHandle.disconnect();
	            	new ManagerRootWindow();
	                dispose();
	            }
	        });
			
		//	c = (CardLayout)(cardPanel.getLayout());

			this.setVisible(true);
		}

		// Initialize the panels
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
		
		// Set the Main Panel
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
		
		// Set the Panel for the Background 
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
		
		
		// Set the title panel
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
		
		// Button Panel
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
		
		
		// Card Panel, which contains the JTable, and the 2 forms. 
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
			setFormAddPanel();
			setDoneAdd();
			
			EditMenu_Card = new GradientPanel();
			EditMenu_Card.setLayout(new BorderLayout());
			EditMenu_Card.setVisible(true);
			setFormEditPanel();
			setDoneEdit();
			
			
			MTV = new MenuTableViewer();
			cardPanel.add(MTV, "Menu");
			cardPanel.add(AddMenu_Card, "AddMenuItem");
			cardPanel.add(EditMenu_Card, "EditMenuItem");
			cardPanel.setVisible(true);
		
		}
		
		// Inititalize Done Button for Add 
		private void setDoneAdd()
		{
			Done_Add = new GradientButton("Done");
			Done_Add.addActionListener(this);
			AddMenu_Card.add(Done_Add, BorderLayout.SOUTH);
		}
		
		// Inititalize Done Button for Edit
		private void setDoneEdit()
		{
			Done_Edit = new GradientButton("Done");
			Done_Edit.addActionListener(this);
			EditMenu_Card.add(Done_Edit, BorderLayout.SOUTH);
		}
		
		// Check for Internet
		public boolean isThereInternet()
		{
			if(MenuHandle.getConnectionStatus()==0){
				return true;
			}
			else{
				return false;
			}
			
		}
			

		// Fill the JTable with the values from the database
		private void FillMenu()
		{
			
			TableModel ModelMenu;
			DefaultTableModel model = new DefaultTableModel();
			TableColumnModel columns = MTV.MenuTable.getColumnModel();
			ModelMenu = MTV.MenuTable.getModel();
			MenuHandler test = new MenuHandler();

			// Retreive each column individually 
			try{

			Integer[] MenuID = test.getMenuID();
			String[] MenuName = test.getMenuName();
			Double[] MenuPrice = test.getMenuPrice();
			Double[] MenuCost = test.getMenuCost();
			String[] MenuIngredients = test.getMenuIngredients();
			String[] MenuDescription = test.getMenuDescription();
			String[] MenuSection = test.getMenuSection();
			Integer[] MenuValid = test.getMenuisValid();
			
			int rows = MenuName.length;
				Vector<Vector> data = new Vector<Vector>();
				Vector row = new Vector();
				Vector<String> columnvector = new Vector<String>();
				int rowtemp=0;
				
				//Populate columns
				for(int i = 0; i < columns.getColumnCount(); i++){
					columnvector.add((String)columns.getColumn(i).getHeaderValue());
				}
				
				// Add the values into the vector
				for(int i = 0; i < MenuName.length; i++)
				{
					
					row.add(MenuID[i]);
					row.add(MenuName[i]);
					row.add(MenuPrice[i]);
					row.add(MenuCost[i]);
					row.add(MenuIngredients[i]);
					row.add(MenuDescription[i]);
					row.add(MenuSection[i]);
					row.add(MenuValid[i]);
					data.add(row);
					row = new Vector();
								
				}
				
				
				model.setDataVector(data, columnvector);
				MTV.MenuTable.setModel(model);
				model.fireTableDataChanged();
				// Disconnect functions!
				test.disconnect();
			
			
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
				addItem.setVisible(true);
				
			}
			

			if(a == Done_Add)
			{
				getNewMenuItem();  
				FillMenu();
				c.show(cardPanel, "Menu");
				removeItem.setVisible(true);
				updateItem.setVisible(true);
				addItem.setVisible(true);
				backButton.setVisible(true);
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
				addItem.setVisible(false);
				backButton.setVisible(false);
				form_add.cleanform();
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
							FillMenu();
							
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

				
			        if (MTV.MenuTable.getSelectedRow()  != -1) 
			        {
			        
			        	addItem.setVisible(false);
						removeItem.setVisible(false);
						updateItem.setVisible(false);	
						backButton.setVisible(false);
			        	
			       
				        	c.show(cardPanel, "EditMenuItem");
				        	int position = MTV.MenuTable.getSelectedRow();
				        	String menu_id = MTV.MenuTable.getModel().getValueAt(position, 0).toString();
				        	String menu_name = MTV.MenuTable.getModel().getValueAt(position, 1).toString();
				        	String menu_price = MTV.MenuTable.getModel().getValueAt(position, 2).toString();
				        	String menu_cost = MTV.MenuTable.getModel().getValueAt(position, 3).toString();
				        	String menu_ing = MTV.MenuTable.getModel().getValueAt(position, 4).toString();
				        	String menu_desc = MTV.MenuTable.getModel().getValueAt(position, 5).toString();
				        	String menu_section = MTV.MenuTable.getModel().getValueAt(position, 6).toString();
				        	
				        	form_edit.MenuItemID.setText(menu_id);
				        	form_edit.MenuItemName.setText(menu_name);
				        	form_edit.MenuItemPrice.setText(menu_price);
				        	form_edit.MenuItemCost.setText(menu_cost);
				        	form_edit.MenuItemIngredients.setText(menu_ing);
				        	form_edit.MenuItemDescription.setText(menu_desc);
				        	form_edit.MenuItemSectionBox.setSelectedItem(menu_section);
				
			        }
			        
					// Error message
					else 
						JOptionPane.showMessageDialog(MenuAddingFrame, "Please select a menu item.");
				
				}
			
			if(a == Done_Edit)
			{
				updateMenuItem();  
				FillMenu();
				c.show(cardPanel, "Menu");
				removeItem.setVisible(true);
				updateItem.setVisible(true);
				addItem.setVisible(true);
				backButton.setVisible(true);
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
			
			
			String[] S = form_add.answers();
			String S1flag;
			
			//Check form 1
			for(int i = 0; i < S.length; i++)
			{
				
				System.out.println(S[i]);
				
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
			MenuItem = new MenuObj("", S[0], S[1], S[2], S[3], S[4], S[5], "1");
//			 SummaryForm = new EmpSummaryPanel(MenuItem);
//			cardPanel.add(SumForm,"Summary");
		 	c.show(cardPanel, "Summary");
	
			//Clean up the form
			form_add.cleanform();
			
			//Send the data to the database
			Boolean INTERNET;
			INTERNET = isThereInternet();
			if(INTERNET == true){
				System.out.println("Adding the New Menu Item!");
				MenuHandle.AddMenuItem(MenuItem);
			//	Add_Error_Message = new JLabel("Item Added!");
			//	Add_Error_Window.add(Add_Error_Message);
			//	Add_Error_Window.setVisible(true);
				
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
		
		public void updateMenuItem()
		{
			Add_Error_Window = new JFrame();
			Add_Error_Window.setSize(200, 100);
			Add_Error_Window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			
			String[] S = form_edit.answers();
			String S1flag;
			
			//Check form 1
			for(int i = 0; i < S.length; i++)
			{
				
				System.out.println(S[i]);
				
				if((S[i] == null) || S[i] == "")
				{
					
					if(i == 1){
						S1flag = "Error: Enter Menu Item Name";
						Add_Error_Message = new JLabel(S1flag);
						Add_Error_Window.add(Add_Error_Message);
						Add_Error_Window.setVisible(true);
						return;
					}
					if(i == 2){
						S1flag = "Error: Enter Price";
						Add_Error_Message = new JLabel(S1flag);
						Add_Error_Window.add(Add_Error_Message);
						Add_Error_Window.setVisible(true);
						return;
					}
					if(i == 3){
						S1flag = "Error: Enter Cost";
						Add_Error_Message = new JLabel(S1flag);
						Add_Error_Window.add(Add_Error_Message);
						Add_Error_Window.setVisible(true);
						return;
					}
					if(i == 4){
						S1flag = "Error: Enter Ingredients";
						Add_Error_Message = new JLabel(S1flag);
						Add_Error_Window.add(Add_Error_Message);
						Add_Error_Window.setVisible(true);
						return;
					}
					if(i == 5){
						S1flag = "Error: Enter Description";
						Add_Error_Message = new JLabel(S1flag);
						Add_Error_Window.add(Add_Error_Message);
						Add_Error_Window.setVisible(true);
						return;
					}
					if(i == 6){
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
			MenuItem = new MenuObj(S[0], S[1], S[2], S[3], S[4], S[5], S[6], "1");
//			 SummaryForm = new EmpSummaryPanel(MenuItem);
//			cardPanel.add(SumForm,"Summary");
		 	c.show(cardPanel, "Summary");
			
			//Send the data to the database
			Boolean INTERNET;
			INTERNET = isThereInternet();
			if(INTERNET == true){
				System.out.println("Updating the Menu Item!");
				MenuHandle.updateMenuItem(MenuItem);
			//	Add_Error_Message = new JLabel("Item Updating!");
			//	Add_Error_Window.add(Add_Error_Message);
			//	Add_Error_Window.setVisible(true);
				
			}
			else
			{
				Add_Error_Message = new JLabel("NO INTERNET CONNECTION!");
				Add_Error_Window.add(Add_Error_Message);
				Add_Error_Window.setVisible(true);
			}
		
		}
		
}