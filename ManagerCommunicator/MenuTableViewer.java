package Manager.ManagerCommunicator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Manager.ManagerHandlers.*;
/*
 * This function gets the MENU items.
 	
 	@author Ryan Sanichar
 	@tester Ryan Sanichar
 	@debugger Ryan Sanichar
*/

public class MenuTableViewer extends JPanel{
	
	public JPanel TableHolder;
	public JTable MenuTable;
	public MenuHandler MH;
	public JScrollPane scroller;
	
	
	
	public MenuTableViewer()
	{
		super();
		this.setVisible(true);
		//this.setBounds(273, 79, 896, 569);
		this.frameManipulation();
	
	}
	
	public void frameManipulation()
	{
		TableHolder = new JPanel(new GridLayout(1,1));
		TableHolder.setVisible(true);
		
		this.add(TableHolder);
		
		
		gen_Menu();
	}
	
	public JTable gen_Menu()
	{
		String[] Menu_ColumnNames = {"ID", "Name", "Price", "Cost", "Ingredients", "Description", "Menu Section", "Valid"};
		MH = new MenuHandler();
		ResultSet rs = MH.getAllMenu();
		
		int rowcount = 0;
		try{
			rs.beforeFirst();
				while(rs.next() ==  true)
				{
					rowcount++;
				}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		String[] ID = new String[rowcount];
		String[] Name = new String[rowcount];
		String[] Price = new String[rowcount];
		String[] Cost = new String[rowcount];
		String[] Ingredients = new String[rowcount];
		String[] Description = new String[rowcount];
		String[] Section = new String[rowcount];
		String[] Valid = new String[rowcount];
		
		try
		{
			rs.first();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		int rowiterator = 0;
		try{
		
			do{
				
				String tmpname = rs.getString("ITEM_NAME");
				String tmping = rs.getString("INGREDIENTS");
				String tmpdesc = rs.getString("DESCRIPTION");
				String tmpsec = rs.getString("MENU_SECTION");
				
				double tmpp = rs.getDouble("PRICE");
				String tmpprice = ""+tmpp;
				double tmpc = rs.getDouble("COST");
				String tmpcost = ""+tmpc;
				int tmpv = rs.getInt("Valid");				
				String tmpvalid = ""+tmpv;
				int tmpi = rs.getInt("MENU_ID");				
				String tmpid = ""+tmpi;
				
				
				ID[rowiterator] = tmpid;
				Name[rowiterator] = tmpname;
				Price[rowiterator] = tmpprice;
				Cost[rowiterator] = tmpcost;
				Ingredients[rowiterator] = tmping;
				Description[rowiterator] = tmpdesc;
				Section[rowiterator] = tmpsec;
				Valid[rowiterator] = tmpvalid;
				
				rowiterator++;
			}
			while(rs.next() == true);

		}catch(SQLException E)
		{
			E.printStackTrace();
		}
		String[][] data = new String[rowcount][8];
		
		for(int i = 0; i < rowcount; i++)
		{
			data[i][0] = ID[i];
			data[i][1] = Name[i];
			data[i][2] = Price[i];
			data[i][3] = Cost[i];
			data[i][4] = Ingredients[i];
			data[i][5] = Description[i];
			data[i][6] = Section[i];
			data[i][7] = Valid[i];
		}
		
		MenuTable = new JTable(data, Menu_ColumnNames);
		MenuTable.getColumnModel().getColumn(0).setPreferredWidth(1);
		MenuTable.getColumnModel().getColumn(2).setPreferredWidth(1);
		MenuTable.getColumnModel().getColumn(3).setPreferredWidth(1);
		MenuTable.getColumnModel().getColumn(6).setPreferredWidth(10);
		MenuTable.getColumnModel().getColumn(7).setPreferredWidth(1);
		MenuTable.setPreferredScrollableViewportSize(new Dimension(890, 560));
	    MenuTable.setFillsViewportHeight(true);

	    JScrollPane scrollPane = new JScrollPane(MenuTable);
	    
        //Add the scroll pane to this panel.
       
		TableHolder.add(scrollPane);
		return MenuTable;
		
	}
}
