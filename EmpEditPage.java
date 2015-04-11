package Manager;

import java.awt.BorderLayout;
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


public class EmpEditPage extends GradientPanel {

	public JPanel rootPanel = new JPanel(new GridLayout(0,2));
	public JPanel EmpListHolder = new JPanel(null);
	public JPanel FormHolder = new JPanel(null);
	public JList  EmployeeList;
	public Vector<EmpObj> EmpListVector;
	public String[] Emp_Names;
	public EmployeeHandler Emp_H = new EmployeeHandler();
	public EmpSummaryPanel EmpSum;
	
	public EmpEditPage()
	{
		super();
		this.add(rootPanel);
		rootPanel.setVisible(true);
		this.setVisible(true);
		panelManipulation();
		System.out.println("Got Here 1 <-- EmpEditPage.java");
	}
	
	public void panelManipulation()
	{
		init_EmpListHolder();
		rootPanel.add(EmpListHolder);
		
		init_FormHolder();
		rootPanel.add(FormHolder);
		
		rootPanel.setVisible(true);
	}
	
	public void init_EmpListHolder()
	{
		//Create JList
		try{
			EmpListVector = Emp_H.getEmployees();
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		
		Emp_Names = new String[EmpListVector.size()];
		for(int i = 0; i < EmpListVector.size() - 1; i++)
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
			public void valueChanged(ListSelectionEvent arg0) {
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
				EmpSum.updateUI();
				rootPanel.updateUI();
			}
			
			
		});
		EmployeeList.setVisible(true);
		//make panel visible
		EmpListHolder.add(EmployeeList);
		EmpListHolder.setVisible(true);
	}
 
	public void init_FormHolder()
	{
		EmpSum = new EmpSummaryPanel(new EmpObj());
		FormHolder.add(EmpSum);
		EmpSum.setVisible(true);
		FormHolder.setVisible(true);
	}
	
}
