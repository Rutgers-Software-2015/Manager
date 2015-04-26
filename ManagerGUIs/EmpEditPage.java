package Manager.ManagerGUIs;
//written by: Harsh Shsh
// tested by: Harsh Shah
// debugged by: Harsh Shah
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Shared.Communicator.*;
import Shared.Gradients.*;
import Manager.ManagerHandlers.*;
import Manager.ManagerCommunicator.*;

public class EmpEditPage extends GradientPanel implements ListSelectionListener{

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
		//MASTER CHECK FOR DB CONNECTION
		boolean INTERNET;
		INTERNET = isThereInternet();
		
		if(INTERNET == true)
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
					Emp_Names[i] = temp.first_name + " " +temp.last_name;
				}
				
				EmployeeList = new JList(Emp_Names);
				EmployeeList.addListSelectionListener(this);
				//Format it, Make selection model for it
				EmployeeList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				EmployeeList.setLayoutOrientation(JList.VERTICAL);
				EmployeeList.setVisibleRowCount(20);	
				EmployeeList.setVisible(true);
				//make panel visible
				EmpListHolder.add(EmployeeList);
				EmpListHolder.setVisible(true);
		}
		else
		{
			EmpListHolder = new GradientPanel();
			EmpListHolder.setLayout(new GridLayout(3,1));
			EmpListHolder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			JLabel noInternet = new JLabel("No Internet Connection");
			JLabel noInternet2 = new JLabel("Employees Not visible");
			JLabel noInternet3 = new JLabel("NO DB CONNECTION!");
			EmpListHolder.add(noInternet);
			EmpListHolder.add(noInternet2);
			EmpListHolder.add(noInternet3);
			EmpListHolder.setVisible(true);
		}
		
		
	}
 
	public void init_FormHolder()
	{
		//Master Internet Check
		boolean INTERNET;
		INTERNET = isThereInternet();
		
		if(INTERNET == true)
		{
			FormHolder = new GradientPanel();
			FormHolder.setLayout(new GridLayout(1,1));
			FormHolder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			EmpSum = new EmpSummaryPanel(EmpListVector.elementAt(0));
			FormHolder.add(EmpSum);
			EmpSum.setVisible(true);
			FormHolder.setVisible(true);
		}else
		{
			FormHolder = new GradientPanel();
			FormHolder.setLayout(new GridLayout(1,1));
			FormHolder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			EmpSum = new EmpSummaryPanel(new EmpObj());
			FormHolder.add(EmpSum);
			EmpSum.setVisible(true);
			FormHolder.setVisible(true);
		}
	}
	

	
	public boolean isThereInternet()
	{
		try
		{
			URL yourl = new URL("http://google.com");
			HttpURLConnection yourlConnect = (HttpURLConnection)yourl.openConnection();
			yourlConnect.setConnectTimeout(5000);
			Object objData = yourlConnect.getContent();
		}catch(UnknownHostException e)
		{
			return false;
		}
		catch(IOException e)
		{
			return false;
		}
		return true;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object a = e.getSource();
		
		String tname = (String)EmployeeList.getSelectedValue();
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
		
		EmpSum.name.setText(t.first_name + " " + t.last_name);
		EmpSum.addr.setText(t.address);
		EmpSum.dob.setText(t.DOB);
		EmpSum.sch.setText(t.salary);
		EmpSum.gpa.setText(t.GPA);
		EmpSum.crimes.setText(t.crimesQuestion);
		EmpSum.q1.setText(t.Q1);
		EmpSum.q2.setText(t.Q2);
		EmpSum.q3.setText(t.Q3);
		EmpSum.q4.setText(t.Q4);
		EmpSum.pos.setText(t.position);
		EmpSum.sal.setText(t.salary);
		
		EmpSum.name.updateUI();
		EmpSum.addr.updateUI();
		EmpSum.dob.updateUI();
		EmpSum.sch.updateUI();
		EmpSum.gpa.updateUI();
		EmpSum.crimes.updateUI();
		EmpSum.q1.updateUI();
		EmpSum.q2.updateUI();
		EmpSum.q3.updateUI();
		EmpSum.q4.updateUI();
		EmpSum.pos.updateUI();
		EmpSum.sal.updateUI();
		EmpSum.updateUI();
		rootPanel.updateUI();
		this.updateUI();
	}
	
	public EmpObj answers()
	{	
		//name, addr, dob, sch, gpa, crimes, q1, q2, q3, q4,pos,sal
		EmpObj ans = new EmpObj();
		String n = EmpSum.name.getText();
		String first = null;
		String last = null;
		for(int i = 0; i < n.length(); i++)
		{
			if(n.charAt(i) == ' ')
			{
				first = n.substring(0, i+1);
				last = n.substring(i+1);
				break;
			}
		}
		
		ans.first_name = first;
		ans.last_name = last;
		ans.address = EmpSum.addr.getText();
		ans.DOB = EmpSum.dob.getText();
		ans.school = EmpSum.sch.getText();
		ans.GPA = EmpSum.gpa.getText();
		ans.crimesQuestion = EmpSum.crimes.getText();
		ans.Q1 = EmpSum.q1.getText();
		ans.Q2 = EmpSum.q2.getText();
		ans.Q3 = EmpSum.q3.getText();
		ans.Q4 = EmpSum.q4.getText();
		ans.position = EmpSum.pos.getText();
		ans.salary = EmpSum.sal.getText();
		return ans;
	}
	
}
