package Manager.ManagerGUIs;

import javax.swing.JPanel;

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
import Manager.ManagerCommunicator.*;
public class InventoryTableViewer extends JPanel {
	
	public JPanel TableHolder;
	public JTable Inventory;
	public InventoryHandler IH;
	public JScrollPane scroller;
	
	public InventoryTableViewer()
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
		
		
		gen_Inventory();
	}
	
	public void gen_Inventory()
	{
		String[] Inventory_ColumnNames = {"Ingredient", "Quantity"};
		IH = new InventoryHandler();
		ResultSet rs = IH.getAllInventory();
		
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
		
		String[] I = new String[rowcount];
		String[] Q = new String[rowcount];
		
		try{
			rs.beforeFirst();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		int rowiterator = 0;
		try{
			while(rs.next() == true)
			{
				String tmpname = rs.getString("Item_Name");
				int tmpquantity = rs.getInt("Amount");
				String tmpquantitystring = ""+tmpquantity;
				
				I[rowiterator] = tmpname;
				Q[rowiterator] = tmpquantitystring;
				
				rowiterator++;
				
			}
		}catch(SQLException E)
		{
			E.printStackTrace();
		}
		String[][] data = new String[rowcount][2];
		
		for(int i = 0; i < rowcount; i++)
		{
			data[i][0] = I[i];
			data[i][1] = Q[i];
		}
		
		System.out.println(rowcount);
		
		Inventory = new JTable(data, Inventory_ColumnNames);
		Inventory.setPreferredScrollableViewportSize(new Dimension(890, 560));
	    Inventory.setFillsViewportHeight(true);
		/*DefaultTableModel model = (DefaultTableModel) Menu.getModel();
		for(int i = 0; i < rowcount; i++)
		{
			model.setValueAt(N[i], i, 0);
			model.setValueAt(I[i], i, 1);
			model.setValueAt(P[i], i, 2);
			model.setValueAt(ID[i], i, 3);
			
			model.addRow(new Object[][] {{null, null, null, null}});
		}*/
	    JScrollPane scrollPane = new JScrollPane(Inventory);
	    
        //Add the scroll pane to this panel.
       
		
		TableHolder.add(scrollPane);
		
	}

}
