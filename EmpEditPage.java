package Manager;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;

import Shared.Communicator.*;
import Shared.Gradients.*;


public class EmpEditPage extends GradientPanel implements ActionListener{

	public JPanel rootPanel = new JPanel(new GridLayout(0,2));
	public JPanel EmpListHolder = new JPanel(null);
	public JPanel FormHolder = new JPanel(null);
	public JList  EmployeeList;
	public Vector EmpListVector;
	public DatabaseCommunicator DBC = new DatabaseCommunicator();
	
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
 
	public Vector<EmpObj> getEmployees() throws SQLException
	{
		
		DBC.connect("root", "gradMay17");
		DBC.tell("use MAINDB;");
		ResultSet rs = DBC.tell("SELECT * FROM EmployeeList;");
		EmpListVector = new Vector();
		
		try{
			while(rs.next())
			{
				boolean vis = rs.getBoolean("Visibility");
				if(vis == true)
				{
					continue;
				}else
				{
					String fn = rs.getString("firstname");
					String ls = rs.getString("lastname");
					String addr = rs.getString("address");
					String dob = rs.getString("dob");
					String sch =  rs.getString("school");
					String gp = ""+rs.getInt("gpa");
					String c, qone, qtwo, qthree, qfour;
					boolean crm = rs.getBoolean("crimes");
					c = "yes";
					if(crm == false){
						c = "no";
					}
					boolean one = rs.getBoolean("qone");
					qone = "no";
					if(one == true)
					{
						qone = "yes";
					}
					boolean two = rs.getBoolean("qtwo");
					qtwo = "no";
					if(two == true)
					{
						qtwo = "yes";
					}
					boolean three = rs.getBoolean("qthree");
					qthree = "no";
					if(three == true)
					{
						qthree = "yes";
					}
					boolean four = rs.getBoolean("qfour");
					qfour = "no";
					if(one == true)
					{
						qfour = "yes";
					}
					String pos = rs.getString("position");
					String sal = ""+rs.getInt("salary");
					
				}
			}
		}catch(SQLException e)
		{
			System.out.println("No Result Set");
		}
		
		return EmpListVector;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
