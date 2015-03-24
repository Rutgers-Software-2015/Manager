import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.border.*;
import javax.swing.ImageIcon;

public class ManagerRootWindow extends JFrame implements ActionListener{

	
	//This Frame
	private JFrame Win_Employees, Win_Financials, Win_Menu, Win_Inventory ;
	
	//This Frames panels
	private JPanel optionPanel;
	private JPanel rootPanel;
	
	//This Frames Buttons
	private JButton B_Employees, B_Inventory, B_Financials, B_Menu, B_temp;
	
	//The Layout of the panel
	GridLayout optionLayout = new GridLayout(2,2);
	GridLayout rootLayout = new GridLayout(1, 1);
	BorderLayout suboptLayout = new BorderLayout();
	

	public static void main(String[] args)
	{
		new ManagerRootWindow();
		return;
	}	

	public ManagerRootWindow()
	{
		super();
		init();
	}


	public void init()
	{
		this.setTitle("Manger");
		this.setResizable(true);
		this.setSize(1000,750);
		this.frameManipulation();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(rootPanel);
		this.setVisible(true);
	}

	public void frameManipulation()
	{
		rootPanel = new JPanel();
		rootPanel.setLayout(rootLayout);
		
		
		init_OptPanel();
		rootPanel.add(optionPanel);

		//B_temp = new JButton("Temp Butt");
		//rootPanel.add(B_temp);
	}
	
	public void init_OptPanel()
	{
		//Entire Left Panel
		optionPanel = new JPanel();
		optionPanel.setLayout(optionLayout);
		
		//Initialize The Buttons	
		B_Employees = new JButton("Employees");
		B_Inventory = new JButton("Inventory");
		B_Financials = new JButton("Financials");
		B_Menu = new JButton("Menu");
		
		//Styling the buttons
		B_Employees.setBorderPainted(false);
		

		//Add The ActionListener to the buttons
		B_Employees.addActionListener(this);
		B_Inventory.addActionListener(this);
		B_Financials.addActionListener(this);
		B_Menu.addActionListener(this);
		
		//Add buttons to panel
		optionPanel.add(B_Employees);
		optionPanel.add(B_Inventory);
		optionPanel.add(B_Financials);
		optionPanel.add(B_Menu);
	}

	public void actionPerformed(ActionEvent e) 
	{
		Object a = e.getSource();
		if(a == B_Employees)
			{
				gen_Employees_Win();
				//Return Employees Window
			}
		if(a == B_Inventory)
			{
				
			}
		if(a == B_Financials)
			{
				gen_Financials_Win();
			}
		if(a == B_Menu)
			{
				
			}
	}

	public void gen_Employees_Win()
	{	
		Win_Employees = new EmployeeWindow();
		Win_Employees.setVisible(true);
	}

	public void gen_Financials_Win()
	{
		Win_Financials = new FinancialWindow();
		Win_Financials.setVisible(true);
	}
	

}

