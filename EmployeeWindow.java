package Manager;

import java.awt.*; 
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ADT.Employee;
import ADT.EmployeeHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/*
 * This class is a GUI for managing the employees
 * It will have  HIRE, FIRE, EDIT buttons
 * It will display a list of employees to operate on
 */

public class EmployeeWindow extends JFrame implements ActionListener
{
	
//Components
	/*rootFrame*/
	JPanel rootPanel, empPanel, optionPanel;
	JPanel optionPanel_textPanel, optionPanel_ButtonPanel;
	JButton Edit, Fire, Hire, Done;
	JTextField Address, Position, Salary, ID;	
	JList empList;
	
	/*HireFrame*/
	JFrame HireFrame;
	JPanel Hire_rootPanel, Hire_Address, Hire_Position, Hire_Salary, Hire_Name, Hire_ID;
	JButton Hire_Add;
	JTextField H_Address, H_Position, H_Salary, H_Name, H_ID;
	
	JScrollPane EmpScroller;
	
//Layouts
	/*RootFrame Layouts*/
	GridLayout frameLayout = new GridLayout(0,2);
	GridLayout optionLayout = new GridLayout(2, 0);
	GridLayout empLayout = new GridLayout(0,1);
		
	/*HireFrame Layouts*/
	GridLayout HireLayout = new GridLayout(6,0);

//Variables
	String newAddress, newPosition, newSalary, newID;
	Employee[] EmployeeArray;
	String[] EmployeeNameArray;
	
	
	//TESTING VARIABLES
	// This string is temporary for testing purposes	
	String[] theEmployees = {"Jane Doe", "Harsh Shah", "Ryan Sanichar", "David Ark", "Sam Baysting", "Rahul Tandon", "Rob Schultz"};
	
	

	//public static void main(String[] args)
	//{
	//	new EmployeeWindow();
	//	return;
	//}


	/* 
		This function will generate the window
	*/
	public EmployeeWindow()
	{
		super();
		init();
	}

	/*
		This function will establish the window settings
	*/
	public void init()
	{
		this.setTitle("Manger--Employees");
		this.setResizable(true);
		this.setSize(700,700);
		this.frameManipulation();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(rootPanel);
		this.setVisible(true);
	}

	/*
		This function will add all sub-panels to the @rootPanel
	*/
	public void frameManipulation()
	{	
		//initialize rootPanel
		rootPanel = new JPanel();
		rootPanel.setLayout(frameLayout);
		
		//initialize employee panel
		empPanel = new JPanel();
		empPanel.setLayout(empLayout);		
		init_empPanel();
		
		//initialize the option panel
		optionPanel = new JPanel();
		optionPanel.setLayout(optionLayout);
		init_optionPanel();
	
		//Add Panels to root panel
		rootPanel.add(empPanel);
		rootPanel.add(optionPanel);
	}

	/*
		This function established the settings of the employee panel
		The employee panel contains a list of all the employees
	*/
	public void init_empPanel()
	{
		//get all the employees for the list
		getEmployees();
		
		//Setting Up List of Employees		
		empList = new JList(EmployeeNameArray);
		
		ListSelectionModel t = empList.getSelectionModel();
		empList.addListSelectionListener( new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e){
		    	Object particularEmployee = empList.getSelectedValue();
				int employeeIndex = empList.getSelectedIndex();

				String theEmp = (String)(particularEmployee);

				String empAddress = getAddress(theEmp);
				String empPosition = getPosition(theEmp);
				String empSalary = getSalary(theEmp);
				String empID = getID(theEmp);
				
				Address.setText(empAddress);
				Position.setText(empPosition);
				Salary.setText(empSalary);
				ID.setText(empID);
				
				Address.updateUI();
				Position.updateUI();
				Salary.updateUI();
				
				optionPanel_textPanel.updateUI();
				optionPanel.updateUI();    	
			    }
			} );
		
		empList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		empList.setLayoutOrientation(JList.VERTICAL);
		empList.setVisibleRowCount(8);
		
		
		
		//Setting up the Scroll Pane
		EmpScroller = new JScrollPane(empList);
		
		
		//Add the Scroll Pane to Employee Panel
		empPanel.add(EmpScroller);
	}

	/*
		This function established the option panel
		The option panel contains informaton of the employees
		and it contains buttons to alter information
	*/	
	public void init_optionPanel()
	{
		optionPanel_textPanel = new JPanel();
		optionPanel_textPanel.setLayout(new GridLayout(4,1));
		
		//Address Text Field
		Address = new JTextField();
		Address.setEditable(false);
		JLabel AddressLabel = new JLabel("Address");
		AddressLabel.setLabelFor(Address);

		//Position Text Field
		Position = new JTextField();
		Position.setEditable(false);
		JLabel PositionLabel = new JLabel("Position");
		PositionLabel.setLabelFor(Position);


		//Salary Text Field
		Salary = new JTextField();
		Salary.setEditable(false);
		JLabel SalaryLabel = new JLabel("Salary");
		SalaryLabel.setLabelFor(Salary);

		//Id Text Field
		ID = new JTextField();
		ID.setEditable(false);
		
		
		
		optionPanel_textPanel.add(Address);
		optionPanel_textPanel.add(Position);
		optionPanel_textPanel.add(Salary);
		optionPanel_textPanel.add(ID);
		optionPanel.add(optionPanel_textPanel);
		
		Edit = new JButton("Edit");
		Fire = new JButton("Fire");
		Hire = new JButton("Hire");
		Done = new JButton("Done");
		
		Edit.addActionListener(this);
		Fire.addActionListener(this);
		Hire.addActionListener(this);
		Done.addActionListener(this);

		optionPanel_ButtonPanel = new JPanel();
		optionPanel_ButtonPanel.setLayout(new GridLayout(4,1));
		optionPanel_ButtonPanel.add(Edit);
		optionPanel_ButtonPanel.add(Fire);
		optionPanel_ButtonPanel.add(Hire);
		optionPanel_ButtonPanel.add(Done);

		optionPanel.add(optionPanel_ButtonPanel);
	}

	
	



	public void actionPerformed(ActionEvent e) 
	{
		
		Object particularEmployee = empList.getSelectedValue();
		int employeeIndex = empList.getSelectedIndex();

		String theEmp = (String)(particularEmployee);

		String empAddress = getAddress(theEmp);
		String empPosition = getPosition(theEmp);
		String empSalary = getSalary(theEmp);
		String empID = getID(theEmp);
		
		/*Address.setText("Address: " + empAddress);
		Position.setText("Position: " + empPosition);
		Salary.setText("Salary: " + empSalary);
		ID.setText("ID: " + empID);
		
		Address.updateUI();
		Position.updateUI();
		Salary.updateUI();
		ID.updateUI();
		
		optionPanel_textPanel.updateUI();
		optionPanel.updateUI();*/
	
		Object a = e.getSource();
		if(a == Edit)
			{
				Edit_Click();
				
			}
		if(a == Hire)
			{
				
				Hire_Click();
			}
		if(a == Fire)
			{
				Fire_Click(theEmp);
			}
		if(a == Done)
			{
				Done_Click(theEmp);
				dispose();
			}
		if(a == Hire_Add)
			{
				Hire_Add_Click();
			}				
	}


	/*****************************
	 * All click action functions*
	 *****************************/
	public void Edit_Click()
	{
		Address.setEditable(true);
		Position.setEditable(true);
		Salary.setEditable(true);
	}

	
	public void Hire_Click()
	{ 
		HireFrame = new JFrame();
		HireFrame.setTitle("Manger--Employees--Hire");
		HireFrame.setResizable(true);
		HireFrame.setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Hire_rootPanel = new JPanel();
		Hire_rootPanel.setLayout(HireLayout);
		
		//Hire_Name = new JPanel();
		//Hire_Address = new JPanel();
		//Hire_Position = new JPanel();
		//Hire_Salary = new JPanel();

		H_Name = new JTextField("Enter Name");
		H_Address = new JTextField("Enter Address");
		H_Position = new JTextField("Enter Position");
		H_Salary = new JTextField("Enter Salary");
		H_ID = new JTextField("ID");
		
		H_Address.setEditable(true);
		H_Position.setEditable(true);
		H_Salary.setEditable(true);		
		H_Name.setEditable(true);
		H_ID.setEditable(true);
		
		Hire_rootPanel.add(H_Name);		
		Hire_rootPanel.add(H_Address);
		Hire_rootPanel.add(H_Position);
		Hire_rootPanel.add(H_Salary);
		Hire_rootPanel.add(H_ID);
		
		Hire_Add = new JButton("Hire");
		Hire_Add.addActionListener(this);

		//Hire_rootPanel.add(Hire_Name);
		//Hire_rootPanel.add(Hire_Address);
		//Hire_rootPanel.add(Hire_Position);
		//Hire_rootPanel.add(Hire_Salary);
		Hire_rootPanel.add(Hire_Add);
		
		HireFrame.add(Hire_rootPanel);
		
		HireFrame.setVisible(true);
	}

	public void Hire_Add_Click()
	{
		String HName = H_Name.getText();
		String HAddress = H_Address.getText();
		String HPosition = H_Position.getText();
		String HSalary = H_Salary.getText();
		String HID = H_ID.getText();
		
		Employee newEmp = new Employee(HName, HAddress, HPosition, HSalary, HID);
		
		empPanel.removeAll();
		
		int NameArraySize = EmployeeNameArray.length +  1;
		String[] temp = new String[NameArraySize];
		for(int i = 0; i < temp.length - 1; i++)
		{
			temp[i] = EmployeeNameArray[i];
		}
		temp[temp.length - 1] = HName;
		EmployeeNameArray = temp;
		
		int EmployeeArraySize = EmployeeArray.length + 1;
		Employee[] temp1 = new Employee[EmployeeArraySize];
		for(int i = 0; i < temp1.length - 1; i++)
		{
			temp1[i] = EmployeeArray[i];
		}
		temp1[temp1.length - 1] = newEmp;
		EmployeeArray = temp1;
		
		empList = new JList(EmployeeNameArray);
		empList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		empList.setLayoutOrientation(JList.VERTICAL);
		empList.setVisibleRowCount(8);
		
		//Setting up the Scroll Pane
		EmpScroller = new JScrollPane(empList);
		
		empPanel.add(EmpScroller);
		
		empPanel.updateUI();
		
		
		
		
		//boolean checkEmpAdded
		//= EmployeeHandler.addEmployee(HName, HAddress, HPosition, HSalary);
		
		//if(checkEmpAdded == false)
		//{
		//	System.out.println("Unable to Add Employee!");
		//}
	
		HireFrame.setVisible(false);
	}

	public void Done_Click(String Name)
	{
		//Obtain the new filled in fields
		Object particularEmployee = empList.getSelectedValue();
		//int employeeIndex = empList.getSelectedIndex();

		String theEmp = (String)(particularEmployee);

		newAddress = Address.getText();
		newSalary = Salary.getText();
		newPosition = Position.getText();
		newID = ID.getText();
		
		System.out.println(newAddress);
		System.out.println(newSalary);
		System.out.println(newPosition);
		System.out.println(newID);
		
		for(int i = 0; i < EmployeeArray.length; i++)
		{
			if(theEmp.equals(EmployeeArray[i].Name))
			{
				EmployeeArray[i].Address = newAddress;
				EmployeeArray[i].Position = newPosition;
				EmployeeArray[i].Salary = newSalary;
				EmployeeArray[i].ID = newID;
				break;
			}
		}
		
		
		Address.setText(newAddress);
		Position.setText(newPosition);
		Salary.setText(newSalary);
		ID.setText(newID);
		
		Address.updateUI();
		Position.updateUI();
		Salary.updateUI();
		ID.updateUI();
		
		optionPanel_textPanel.updateUI();
		optionPanel.updateUI(); 
		
		
		
		//update the database
		//boolean checkedit = EmployeeHandler.editEmployee(Name, newAddress, newPosition, newSalary);
		
		//if(checkedit == false)
		//{
		//	System.out.println("Unable to edit employee!");
		//}
		
		//make fields no longer alterable
		Address.setEditable(false);
		Position.setEditable(false);
		Salary.setEditable(false);
		
	}

	public void Fire_Click(String emp)
	{
		empList.removeAll();
		empPanel.removeAll();
		int NameArraySize = EmployeeNameArray.length - 1;
		String[] temp = new String[NameArraySize];
		
		int j = 0;
		for(int i = 0; i < temp.length; i++)
		{
			if(EmployeeNameArray[j] == emp)
			{
				j++;
				//temp[i] = EmployeeNameArray[j];
				continue;
			}
			temp[i] = EmployeeNameArray[j];
			j++;
		}
		
		EmployeeNameArray = temp;
		
		int x = 0;
		Employee[] temp2 = new Employee[NameArraySize];
		for(int i = 0; i < temp2.length; i++)
		{
			if(EmployeeArray[x].Name == emp)
			{
				x++;
				temp2[i] = EmployeeArray[x];
				continue;
			}
			temp2[i] = EmployeeArray[x];
			x++;
		}
		
		EmployeeArray = temp2;
		empList = new JList(EmployeeNameArray);
		empList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		empList.setLayoutOrientation(JList.VERTICAL);
		empList.setVisibleRowCount(5);
		
		
		
		//Setting up the Scroll Pane
		EmpScroller = new JScrollPane(empList);
		
		empPanel.add(EmpScroller);
		
		
		Address.setText("");
		Position.setText("");
		Salary.setText("");
		ID.setText("");
		
		Address.updateUI();
		Position.updateUI();
		Salary.updateUI();
		ID.updateUI();
		
		empPanel.updateUI();
		
		empList.addListSelectionListener( new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e){
		    	Object particularEmployee = empList.getSelectedValue();
				int employeeIndex = empList.getSelectedIndex();

				String theEmp = (String)(particularEmployee);

				String empAddress = getAddress(theEmp);
				String empPosition = getPosition(theEmp);
				String empSalary = getSalary(theEmp);
				String empID = getID(theEmp);
				
				Address.setText("Address: " + empAddress);
				Position.setText("Position: " + empPosition);
				Salary.setText("Salary: " + empSalary);
				ID.setText("ID: " + empID);
				
				Address.updateUI();
				Position.updateUI();
				Salary.updateUI();
				ID.updateUI();
				
				optionPanel_textPanel.updateUI();
				optionPanel.updateUI();    	
			    }
			} );
		
		
	}

	//////////////////////////////////////////////////////////
	/*
		The following functions perform the async tasks including:
		1) Getting the List of all employees in an Employee[]
		2) Generating a String[] of employee names
		3) Updating the GUI to show new information
	*/
	
	public void getEmployees()
	{
		//Set the employee Array
		EmployeeArray = EmployeeHandler.getAllEmployees();
		EmployeeNameArray = new String[EmployeeArray.length];
		//Add the employee names to the employee name array
		for(int i = 0; i < EmployeeArray.length; i++)
		{
			EmployeeNameArray[i] = EmployeeArray[i].Name;
		}
		return;
	}

	/*
	 * These Functions will need to reference an employee object
	 *
	 */
	public String getAddress(String anEmp)
	{
		String retAddress;

		//search for employee in employee array
		for(int i = 0; i < EmployeeArray.length; i++)
		{
			if(EmployeeArray[i].Name == anEmp)
			{
				retAddress = EmployeeArray[i].Address;
				return retAddress;
			}
		}		
		
		return "No Listed Employee Address";
	}

	public String getPosition(String anEmp)
	{
		String retPosition;

		//search for employee in employee array
		for(int i = 0; i < EmployeeArray.length; i++)
		{
			if(EmployeeArray[i].Name == anEmp)
			{
				retPosition = EmployeeArray[i].Position;
				return retPosition;
			}
		}		
		
		
		return "No Listed Position";
	}

	public String getSalary(String anEmp)
	{
		String retSalary;

		//search for employee in employee array
		for(int i = 0; i < EmployeeArray.length; i++)
		{
			if(EmployeeArray[i].Name == anEmp)
			{
				retSalary = EmployeeArray[i].Salary;
				return retSalary;
			}
		}		
				

		//Fill in this function
		return "No Listed Salary";
	}
	
	public String getID(String anEmp)
	{
		String retID;
		
		for(int i = 0; i < EmployeeArray.length; i++)
		{
			if(EmployeeArray[i].Name == anEmp)
			{
				retID = EmployeeArray[i].ID;
				return retID;
			}
		}
		return "No ID";
		
	}

	

}
