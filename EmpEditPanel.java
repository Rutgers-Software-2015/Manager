package Manager;

import java.awt.*; 

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
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


public class EmpEditPanel extends JPanel {
	
	JList EmployeeList;
	JPanel ListPanel, DescPanel;
	
	/*Description Info*/
	JLabel Name, Address, DOB, Position, Salary;
	JTextField Name_Field, Address_Field, DOB_Field, Position_Field, Salary_Field;
	
	public EmpEditPanel()
	{
		super();
		this.setLayout(new GridLayout(0,2));
		panelManipulation();
		this.setVisible(true);
	}
	
	public void panelManipulation()
	{
		ListPanel = new JPanel();
		DescPanel = new JPanel(new GridLayout(5,2));
		
		ListPanel.setVisible(true);
		DescPanel.setVisible(true);
		
		this.add(ListPanel);
		this.add(DescPanel);
	}
	
	public void init_EmployeeList()
	{
		EmployeeList = new JList();
		EmployeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
	}
	
	public void init_static_DescPanel()
	{
		Name = new JLabel("Name");
		Address = new JLabel("Address");
		DOB = new JLabel("Date of Birth:");
		Position = new JLabel("Position");
		Salary = new JLabel("Salary");
		
		Name_Field = new JTextField("");
		Address_Field = new JTextField("");
		DOB_Field = new JTextField("");
		Position_Field = new JTextField("");
		Salary_Field = new JTextField("");
		
		DescPanel.add(Name);
		DescPanel.add(Name_Field);
		DescPanel.add(Address);
		DescPanel.add(Address_Field);
		DescPanel.add(DOB);
		DescPanel.add(DOB_Field);
		DescPanel.add(Position);
		DescPanel.add(Position_Field);
	}
	
}
