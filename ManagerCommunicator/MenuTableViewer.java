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

public class MenuTableViewer extends JPanel{
	
	public JPanel TableHolder;
	public JTable Menu;
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
	
	public void gen_Menu()
	{
		String[] Menu_ColumnNames = {"Name", "Ingredients", "Price", "ID"};
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
		
		String[] N = new String[rowcount];
		String[] I = new String[rowcount];
		String[] P = new String[rowcount];
		String[] ID = new String[rowcount];
		
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
				double tmpp = rs.getDouble("PRICE");
				String tmpprice = ""+tmpp;
				int tmpi = rs.getInt("MENU_ID");
				String tmpid = ""+tmpi;
				
				N[rowiterator] = tmpname;
				I[rowiterator] = tmping;
				P[rowiterator] = tmpprice;
				ID[rowiterator] = tmpid;
				
				rowiterator++;
			}
			while(rs.next() == true);

		}catch(SQLException E)
		{
			E.printStackTrace();
		}
		String[][] data = new String[rowcount][4];
		
		for(int i = 0; i < rowcount; i++)
		{
			data[i][0] = N[i];
			data[i][1] = I[i];
			data[i][2] = P[i];
			data[i][3] = ID[i];
		}
		
		System.out.println(rowcount);
		
		Menu = new JTable(data, Menu_ColumnNames);
		Menu.setPreferredScrollableViewportSize(new Dimension(890, 560));
	    Menu.setFillsViewportHeight(true);
		/*DefaultTableModel model = (DefaultTableModel) Menu.getModel();
		for(int i = 0; i < rowcount; i++)
		{
			model.setValueAt(N[i], i, 0);
			model.setValueAt(I[i], i, 1);
			model.setValueAt(P[i], i, 2);
			model.setValueAt(ID[i], i, 3);
			
			model.addRow(new Object[][] {{null, null, null, null}});
		}*/
	    JScrollPane scrollPane = new JScrollPane(Menu);
	    
        //Add the scroll pane to this panel.
       
		
		TableHolder.add(scrollPane);
		
	}
}
