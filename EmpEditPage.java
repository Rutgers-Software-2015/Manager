package Manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Shared.Communicator.*;
import Shared.Gradients.*;


public class EmpEditPage extends GradientPanel implements ActionListener{

	public GradientPanel rootPanel = new GradientPanel();
	public GradientPanel EmpListHolder; 
	public GradientPanel FormHolder; 
	public JList  EmployeeList;
	public Vector<EmpObj> EmpListVector;
	public String[] Emp_Names;
	public EmployeeHandler Emp_H = new EmployeeHandler();
	public EmpSummaryPanel EmpSum;
	
	//TESTING ONLY
	/*public static void main(String[] args)
	{
		JFrame temp = new JFrame();
		temp.setTitle("Manager");
		temp.setVisible(false);
		temp.setResizable(true);
		temp.setSize(1200,700);
		temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		EmpEditPage t = new EmpEditPage();
		temp.add(t);
		temp.setVisible(true);
		return;
	}*/
	
	public EmpEditPage()
	{
		super();
		this.setLayout(new GridLayout(1,1));
		this.add(rootPanel);
		rootPanel.setVisible(true);
		rootPanel.setLayout(new BorderLayout());
		this.setVisible(true);
		panelManipulation();
		System.out.println("Got Here 1 <-- EmpEditPage.java");
	}
	
	public void panelManipulation()
	{
		init_EmpListHolder();
		rootPanel.add(EmpListHolder, BorderLayout.WEST);
		
		init_FormHolder();
		rootPanel.add(FormHolder, BorderLayout.CENTER);
		
		rootPanel.setVisible(true);
	}
	
	public void init_EmpListHolder()
	{
		//Create JList
		EmpListHolder = new GradientPanel();
		EmpListHolder.setLayout(new GridLayout(1,1));
		EmpListHolder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		try{
			EmpListVector = Emp_H.getEmployees();
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		
		Emp_Names = new String[EmpListVector.size()];
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
		EmployeeList.addListSelectionListener(new ListSelectionListener()
		{

			@Override
			public void valueChanged(ListSelectionEvent e) {
				/*Object a = e.getSource();
				System.out.println("Printing new emp info page!");
				String tname = String.valueOf(EmployeeList.getSelectedValue());
				String name = "";
				EmpObj t = new EmpObj();
				for(int i = 0; i < tname.length() && (tname.charAt(i) != (' ')); i++)
				{
					name = name + tname.charAt(i);
				}
				for(int i = 0 ; i < EmpListVector.size(); i++)
				{
					if(EmpListVector.elementAt(i).first_name.equals(name))
					{
						t = EmpListVector.elementAt(i);
						break;
					}
				}
				EmpSum = new EmpSummaryPanel(t);
				FormHolder.removeAll();
				rootPanel.remove(FormHolder);
				FormHolder.add(EmpSum);
				rootPanel.add(FormHolder);
				EmpSum.updateUI();
				rootPanel.updateUI();*/
				
			}
			
			
		});
		EmployeeList.setVisible(true);
		//make panel visible
		EmpListHolder.add(EmployeeList);
		EmpListHolder.setVisible(true);
	}
 
	public void init_FormHolder()
	{
		FormHolder = new GradientPanel();
		FormHolder.setLayout(new GridLayout(1,1));
		FormHolder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		EmpSum = new EmpSummaryPanel(EmpListVector.elementAt(0));
		FormHolder.add(EmpSum);
		EmpSum.setVisible(true);
		FormHolder.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object a = e.getSource();
		if(a == EmployeeList){ // If statement
		System.out.println("Printing new emp info page!");
		String tname = String.valueOf(EmployeeList.getSelectedValue());
		String name = "";
		EmpObj t = new EmpObj();
		for(int i = 0; i < tname.length() && (tname.charAt(i) != (' ')); i++)
		{
			name = name + tname.charAt(i);
		}
		for(int i = 0 ; i < EmpListVector.size(); i++)
		{
			if(EmpListVector.elementAt(i).first_name.equals(name))
			{
				t = EmpListVector.elementAt(i);
				break;
			}
		}
		EmpSum = new EmpSummaryPanel(t);
		FormHolder.removeAll();
		rootPanel.remove(FormHolder);
		FormHolder.add(EmpSum);
		rootPanel.add(FormHolder);
		EmpSum.updateUI();
		rootPanel.updateUI();
		this.updateUI();
		}
	}
	
}
