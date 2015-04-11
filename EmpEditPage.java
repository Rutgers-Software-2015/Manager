

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Shared.Gradients.*;


public class EmpEditPage extends GradientPanel implements ActionListener{

	public JPanel rootPanel = new JPanel(new GridLayout(0,2));
	public JPanel EmpListHolder = new JPanel(null);
	public JPanel FormHolder = new JPanel(null);
	public JList  EmployeeList;
	public EmpObj[] EmpListArray;
	
	public EmpEditPage()
	{
		super();
		this.add(rootPanel);
		this.setVisible(true);
		panelManipulation();
	}
	
	public void panelManipulation()
	{
		rootPanel.add(EmpListHolder);
		rootPanel.add(FormHolder);
		rootPanel.setVisible(true);
	}
	
	public void init_EmpListHolder()
	{
		//Create JList
		//Format it
		//Make selection model for it
		//add listener to it
		//add it to the panel
		//make panel visible
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
