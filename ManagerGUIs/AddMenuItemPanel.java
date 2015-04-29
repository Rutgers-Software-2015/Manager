/**
 * AddMenuItemPanel.java 
	@author Ryan Sanichar 
	@tester Ryan Sanichar
	@debugger Ryan Sanichar
 * 
 * 
 * Form for AddMenuItemPanel to 
 * generates menu items to be used in the menu. 
 */


package Manager.ManagerGUIs;


import java.awt.*;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
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


public class AddMenuItemPanel extends JPanel {
	
	/* All Layouts */
	//root Layout
	public GridLayout rootLayout = new GridLayout(1,0);
		//subcontainer Layouts
	public GridLayout title_Layout = new GridLayout(1,0);
	public GridLayout name_Layout = new GridLayout(6,0);
	
	/* All Containers */
	public JPanel title_Panel;
	public JPanel name_Panel;
	
	/*title_Panel Attributes*/
	public JLabel title;
	
	/*name_Panel Attributes*/
	public JTextField 	MenuItemName;
	public JTextField 	MenuItemPrice;
	public JTextField 	MenuItemCost;
	public JTextField 	MenuItemIngredients;
	public JTextField 	MenuItemDescription;
	public JTextField 	MenuItemSection;
	public JComboBox   MenuItemSectionBox;
	
	// Labels
	public JLabel 		MIName;
	public JLabel 		MIPrice;
	public JLabel 		MICost;
	public JLabel 		MIIngredients;
	public JLabel 		MIDescription;
	public JLabel 		MISection;
	
	// Panels
	public JPanel 		MINameSubPanel;
	public JPanel 		MIPriceSubPanel;
	public JPanel 		MICostSubPanel;
	public JPanel 		MIIngredientsSubPanel;
	public JPanel 		MIDescriptionSubPanel;
	public JPanel 		MISectionSubPanel;

	public String[] AnsSet;
	public String[] Sections = {"Appetizer", "Entree", "Drinks", "Dessert"};
	
	public AddMenuItemPanel()
	{
		super();
		this.setLayout(rootLayout);
		panelManipulation();
		this.setVisible(true);
	}

	/* Add All subPanels to the main panel */
	public void panelManipulation()
	{
		// title_Panel = new JPanel(title_Layout);
		// init_title_Panel();
		
		name_Panel = new JPanel(name_Layout);
		init_name_Panel();
		
		// this.add(title_Panel);
		this.add(name_Panel);
		
	}
	
	/* Initialize the title panel*/
	public void init_title_Panel()
	{
		/* Initialize and set up title*/
		title = new JLabel("Add New Menu Item");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(title.getFont().deriveFont(38f));
		title_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		/* Add the title to the title panel */
		title_Panel.add(title);
		title_Panel.setVisible(true);
	}
	
	
	// Making the form
	@SuppressWarnings("unchecked")
	public void init_name_Panel()
	{
		MIName = new JLabel("Name of Menu Item");
		MIPrice = new JLabel("Price of Menu Item");
		MICost = new JLabel("Cost of Menu Item");
		MIIngredients = new JLabel("Ingredients");
		MIDescription = new JLabel("Description of Menu Item");
		MISection = new JLabel("What Type of Item is the item?");
				
		MenuItemName = new JTextField();
		MenuItemName.setEditable(true);
		MenuItemPrice = new JTextField();
		MenuItemPrice.setEditable(true);
		MenuItemCost = new JTextField();
		MenuItemCost.setEditable(true);
		MenuItemIngredients = new JTextField();
		MenuItemIngredients.setEditable(true);
		MenuItemDescription = new JTextField();
		MenuItemDescription.setEditable(true);
		MenuItemSection = new JTextField();
		MenuItemSection.setEditable(true);
		MenuItemSectionBox = new JComboBox(Sections);
		MenuItemSectionBox.setEditable(true);
		
		MINameSubPanel = new JPanel(new BorderLayout());
		MINameSubPanel.add(MIName, BorderLayout.WEST);
		MINameSubPanel.add(MenuItemName, BorderLayout.CENTER);
		MINameSubPanel.setVisible(true);
		
		MIPriceSubPanel = new JPanel(new BorderLayout());
		MIPriceSubPanel.add(MIPrice, BorderLayout.WEST);
		MIPriceSubPanel.add(MenuItemPrice, BorderLayout.CENTER);
		MIPriceSubPanel.setVisible(true);	
		
		MICostSubPanel = new JPanel(new BorderLayout());
		MICostSubPanel.add(MICost, BorderLayout.WEST);
		MICostSubPanel.add(MenuItemCost, BorderLayout.CENTER);
		MICostSubPanel.setVisible(true);		
		
		MIIngredientsSubPanel = new JPanel(new BorderLayout());
		MIIngredientsSubPanel.add(MIIngredients, BorderLayout.WEST);
		MIIngredientsSubPanel.add(MenuItemIngredients, BorderLayout.CENTER);
		MIIngredientsSubPanel.setVisible(true);	
		
		MIDescriptionSubPanel = new JPanel(new BorderLayout());
		MIDescriptionSubPanel.add(MIDescription, BorderLayout.WEST);
		MIDescriptionSubPanel.add(MenuItemDescription, BorderLayout.CENTER);
		MIDescriptionSubPanel.setVisible(true);		
		
		MISectionSubPanel = new JPanel(new BorderLayout());
		MISectionSubPanel.add(MISection, BorderLayout.WEST);
		MISectionSubPanel.add(MenuItemSectionBox, BorderLayout.CENTER);
		MISectionSubPanel.setVisible(true);			
		
		
		name_Panel.add(MINameSubPanel);
		name_Panel.add(MIPriceSubPanel);
		name_Panel.add(MICostSubPanel);
		name_Panel.add(MIIngredientsSubPanel);
		name_Panel.add(MIDescriptionSubPanel);
		name_Panel.add(MISectionSubPanel);
		name_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
		name_Panel.setVisible(true);
	}
	
	
	// Retrieve the values from the form and put them into a String
	// array, which will be used in the MenuObj.
	public String[] answers()
	{
		String[] results = new String[6];
		
		String name, price, cost, ingredients, description, section;
		
		
		name = MenuItemName.getText();
		price = MenuItemPrice.getText();
		cost = MenuItemCost.getText();
		ingredients = MenuItemIngredients.getText();
		description = MenuItemDescription.getText();
		section = MenuItemSectionBox.getSelectedItem().toString();
		
		results[0] = name;
		results[1] = price;
		results[2] = cost;
		results[3] = ingredients;
		results[4] = description;
		results[5] = section;
		
		return results;
	}
	
	public void cleanform()
	{
		MenuItemName.setText("");
		MenuItemPrice.setText("");
		MenuItemCost.setText("");
		MenuItemIngredients.setText("");
		MenuItemDescription.setText("");
		MenuItemSection.setText("");
		
	}

}
