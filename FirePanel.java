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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FirePanel extends GradientPanel{

	
	public GradientPanel EmpListHolder, Reason_Panel;
	public Vector<EmpObj> EmpListVector;
	public JList EmployeeList;
	public EmployeeHandler Emp_H = new EmployeeHandler();
	public JTextField Reason_Area;
	public GradientButton FireButton;
	
	public FirePanel()
	{
		super();
		this.setLayout(new GridLayout(0,2));
		panelManipulation();
		this.setVisible(true);
	}

	public void panelManipulation()
	{
		init_EmpListHolder();
		this.add(EmpListHolder);
		
		init_ReasonPanel();
		this.add(Reason_Panel);
	}
	
	public void init_EmpListHolder()
	{

			//Create JList
			EmpListHolder = new GradientPanel();
			EmpListHolder.setLayout(new GridLayout(1,1));
			try{
				EmpListVector = Emp_H.getEmployees();
			}catch(SQLException e)
			{
				System.out.println(e);
			}
			
			String [] Emp_Names = new String[EmpListVector.size()];
			for(int i = 0; i < EmpListVector.size(); i++)
			{
				EmpObj temp = EmpListVector.elementAt(i);
				Emp_Names[i] = temp.first_name + temp.last_name;
			}
			
			EmployeeList = new JList(Emp_Names);
			//Format it, Make selection model for it
			EmployeeList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			EmployeeList.setLayoutOrientation(JList.VERTICAL);
			EmployeeList.setVisibleRowCount(5);
			
			EmployeeList.setVisible(true);
			//make panel visible
			EmpListHolder.add(EmployeeList);
			EmpListHolder.setVisible(true);
		

	}
	
	public void init_ReasonPanel()
	{
		Reason_Panel = new GradientPanel();
		Reason_Panel.setLayout(new BorderLayout());
		
		Reason_Area = new JTextField();
		Reason_Area.setEditable(true);
		
		JLabel ReasonLabel = new JLabel("Reason for Firing");
		
		FireButton = new GradientButton("Fire");
		
		
		
	}
}
