import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
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


public class HirePanel_S1 extends JPanel 
{
	/* All Layouts */
		//root Layout
	public GridLayout rootLayout = new GridLayout(6,0);
		//subcontainer Layouts
	public GridLayout title_Layout = new GridLayout(1,0);
	public GridLayout name_Layout = new GridLayout(2,0);
	public GridLayout address_Layout = new GridLayout(2,2);
	public GridLayout DOB_Layout = new GridLayout(3,1);
	public GridLayout education_Layout = new GridLayout(2,1);
	public GridLayout crimes_Layout = new GridLayout(2,1);
	public GridLayout BackForward_Layout = new GridLayout(0,2);
	
	
	/* All Containers */
	public JPanel title_Panel;
	public JPanel name_Panel;
	public JPanel address_Panel;
	public JPanel DOB_Panel;
	public JPanel education_Panel;
	public JPanel crimes_Panel;
	public JPanel BackForward_Panel;
	
	/*title_Panel Attributes*/
	public JLabel title;
	
	/*name_Panel Attributes*/
	public JTextField 	FirstName;
	public JTextField 	LastName;
	public JLabel 		FName;
	public JLabel 		LName;
	public JPanel 		FNameSubPanel;
	public JPanel 		LNameSubPanel;
	
	/*address_Panel Attributes*/
	//Text Fields
	public JTextField 	Apt_Number;
	public JTextField 	Street;
	public JTextField 	Town;
	public JTextField 	State;
	// Labels
	public JLabel 		AptNumber_Label;
	public JLabel 		Street_Label;
	public JLabel 		Town_Label;
	public JLabel 		State_Label;
	//Sub Panels
	public JPanel 		Apt_SubPanel;
	public JPanel 		Street_SubPanel;
	public JPanel 		Town_SubPanel;
	public JPanel 		State_SubPanel;
	
	/*DOB_Panel Attributes*/
	public JLabel Day_Label;
	public JLabel Month_Label;
	public JLabel Year_Label;
	
	public JComboBox Day_ComboBox;
	public JComboBox Month_ComboBox;
	public JComboBox Year_ComboBox;
	
	public String[] Days;
	public String[] Months;
	public String[] Years;
	
	public JPanel Day_SubPanel;
	public JPanel Month_SubPanel;
	public JPanel Year_SubPanel;
	
	/*education_Panel Attributes*/
	public JTextField School;
	public JTextField GPA;
	
	public JLabel School_Label;
	public JLabel GPA_Label;
	
	public JPanel School_SubPanel;
	public JPanel GPA_SubPanel;
	
	/*crimes_Panel Attributes*/
	public JPanel ButtonHolder;	
	public JLabel CrimesQuestion;
	
	public JRadioButton y;
	public JRadioButton n;
	
	public String[] AnsSet;
	
	
	/* TESTING PURPOSES ONLY */
	/* Unit Testing Purposes */
	/*public static void main(String[] args)
	{
		JFrame tester = new JFrame();
		tester.setTitle("Employee Management Console");
		tester.setResizable(true);
		//tester.setLayout(new GridLayout(1,1));
		tester.setSize(1200,700);
		tester.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tester.add(new HirePanel_S1());
		tester.setVisible(true);
	}*/
	
	
	
	/* Panel Constructor*/
	public HirePanel_S1()
	{
		super();
		this.setLayout(rootLayout);
		panelManipulation();
		this.setVisible(true);
	}
	
	/* Add All subPanels to the main panel */
	public void panelManipulation()
	{
		title_Panel = new JPanel(title_Layout);
		init_title_Panel();
		
		name_Panel = new JPanel(name_Layout);
		init_name_Panel();
		
		address_Panel = new JPanel(address_Layout);
		init_address_Panel();
		
		DOB_Panel = new JPanel(DOB_Layout);
		init_DOB_Panel();
		
		education_Panel = new JPanel(education_Layout);
		init_education_Panel();
		
		crimes_Panel = new JPanel(crimes_Layout);
		init_crimes_Panel();
		
		
		
		this.add(title_Panel);
		this.add(name_Panel);
		this.add(address_Panel);
		this.add(DOB_Panel);
		this.add(education_Panel);
		this.add(crimes_Panel);
		
	}
	
	/* Initialize the title panel*/
	public void init_title_Panel()
	{
		/* Initialize and set up title*/
		title = new JLabel("Employement Form : Page 1 of 3");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(title.getFont().deriveFont(38f));
		title_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		/* Add the title to the title panel */
		title_Panel.add(title);
		title_Panel.setVisible(true);
	}
	
	public void init_name_Panel()
	{
		FName = new JLabel("First Name");
		LName = new JLabel("Last Name");
		
		FirstName = new JTextField();
		FirstName.setEditable(true);
		
		LastName = new JTextField();
		LastName.setEditable(true);
		
		FNameSubPanel = new JPanel(new BorderLayout());
		FNameSubPanel.add(FName, BorderLayout.WEST);
		FNameSubPanel.add(FirstName, BorderLayout.CENTER);
		FNameSubPanel.setVisible(true);
		
		LNameSubPanel = new JPanel(new BorderLayout());
		LNameSubPanel.add(LName, BorderLayout.WEST);
		LNameSubPanel.add(LastName, BorderLayout.CENTER);
		LNameSubPanel.setVisible(true);
		
		name_Panel.add(FNameSubPanel);
		name_Panel.add(LNameSubPanel);
		name_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
		name_Panel.setVisible(true);
	}
	
	public void init_address_Panel()
	{
		//Initialize all TextFields
		Apt_Number = new JTextField();
		Street = new JTextField();
		Town = new JTextField();
		State = new JTextField();
		
		//Initialize all the Labels
		AptNumber_Label = new JLabel("Apt/Number");
		Street_Label = new JLabel("Street");
		Town_Label = new JLabel("Town");
		State_Label = new JLabel("State");
		
		//Generate the SubPanels
		Apt_SubPanel = new JPanel(new BorderLayout());
		Apt_SubPanel.add(AptNumber_Label, BorderLayout.WEST);
		Apt_SubPanel.add(Apt_Number, BorderLayout.CENTER);
		Apt_SubPanel.setVisible(true);
		
		Street_SubPanel = new JPanel(new BorderLayout());
		Street_SubPanel.add(Street_Label, BorderLayout.WEST);
		Street_SubPanel.add(Street, BorderLayout.CENTER);
		Street_SubPanel.setVisible(true);
		
		Town_SubPanel = new JPanel(new BorderLayout());
		Town_SubPanel.add(Town_Label, BorderLayout.WEST);
		Town_SubPanel.add(Town, BorderLayout.CENTER);
		Town_SubPanel.setVisible(true);
		
		State_SubPanel = new JPanel(new BorderLayout());
		State_SubPanel.add(State_Label, BorderLayout.WEST);
		State_SubPanel.add(State, BorderLayout.CENTER);
		State_SubPanel.setVisible(true);
		
		//Add All the subpanels to main panel
		address_Panel.add(Apt_SubPanel);
		address_Panel.add(Street_SubPanel);
		address_Panel.add(Town_SubPanel);
		address_Panel.add(State_SubPanel);
		
		//finalize the panel
		address_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
		address_Panel.setVisible(true);

	}
	
	public void init_DOB_Panel()
	{
		Days = new String[31];
		for(int i = 0; i < 30; i++)
		{
			int temp = i + 1;
			Days[i] = "" + temp;
		}
		
		Months = new String[12];
		for(int i = 0; i < 12; i++)
		{
			int temp = i + 1;
			Months[i] = "" + temp;
		}
		
		int base = 1950;
		Years = new String[65];
		for(int i = 0; i < 65; i++)
		{
			base++;
			Years[i] = "" + base;
		}    
		
		
		Day_ComboBox = new JComboBox(Days);
		
		Month_ComboBox = new JComboBox(Months);
		
		Year_ComboBox = new JComboBox(Years);
		
		Year_Label = new JLabel("Year");
		Month_Label = new JLabel("Month");
		Day_Label = new JLabel("Day");
		
		Day_SubPanel = new JPanel(new BorderLayout());
		Day_SubPanel.add(Day_Label, BorderLayout.WEST);
		Day_SubPanel.add(Day_ComboBox, BorderLayout.CENTER);
		Day_SubPanel.setVisible(true);
		
		Month_SubPanel = new JPanel(new BorderLayout());
		Month_SubPanel.add(Month_Label, BorderLayout.WEST);
		Month_SubPanel.add(Month_ComboBox, BorderLayout.CENTER);
		Month_SubPanel.setVisible(true);
		
		Year_SubPanel = new JPanel(new BorderLayout());
		Year_SubPanel.add(Year_Label, BorderLayout.WEST);
		Year_SubPanel.add(Year_ComboBox, BorderLayout.CENTER);
		Year_SubPanel.setVisible(true);
		
		DOB_Panel.add(Day_SubPanel);
		DOB_Panel.add(Month_SubPanel);
		DOB_Panel.add(Year_SubPanel);
		
		DOB_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
		DOB_Panel.setVisible(true);
	}
	
	public void init_education_Panel()
	{
		School = new JTextField();
		GPA = new JTextField();
		
		School_Label = new JLabel("School/University");
		GPA_Label = new JLabel("GPA");
		
		School_SubPanel = new JPanel(new BorderLayout());
		GPA_SubPanel = new JPanel(new BorderLayout());
		
		School_SubPanel.add(School_Label, BorderLayout.WEST);
		School_SubPanel.add(School, BorderLayout.CENTER);
		
		GPA_SubPanel.add(GPA_Label, BorderLayout.WEST);
		GPA_SubPanel.add(GPA, BorderLayout.CENTER);
		
		education_Panel.add(School_SubPanel);
		education_Panel.add(GPA_SubPanel);
		
		education_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
		education_Panel.setVisible(true);
	}
	
	public void init_crimes_Panel()
	{
		CrimesQuestion = new JLabel("Have you committed and crimes?");
		CrimesQuestion.setHorizontalAlignment(JLabel.CENTER);
		
		crimes_Panel.add(CrimesQuestion);
		
		y = new JRadioButton("Yes");
		n = new JRadioButton("No");
		
		ButtonHolder = new JPanel(new BorderLayout());
		ButtonHolder.add(y, BorderLayout.WEST);
		ButtonHolder.add(n, BorderLayout.EAST);
		
		crimes_Panel.add(ButtonHolder);
		
		crimes_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
		crimes_Panel.setVisible(true);
	}
	
	//[fname, lname, address, dob, school, gpa, question]
	public String[] answers()
	{
		String[] results = new String[7];
		
		String fnametemp;
		try{
			fnametemp = FirstName.getText();
		}catch(Exception e)
		{
			fnametemp = null;
		}
		results[0] = fnametemp;
		
		String lnametemp;
		try
		{
			lnametemp = LastName.getText();	
		}catch(Exception e)
		{
			lnametemp = null;
		}
		results[1] = lnametemp;
		
		String address1, address2, address3, address4;
		try{
		address1 = Apt_Number.getText();
		address2 = Street.getText();
		address3 = Town.getText();
		address4 = State.getText();
		}catch(Exception e)
		{
			address1 = null;
			address2 = null;
			address3 = null;
			address4 = null;
		}
		String addr = address1+" "+address2 +" "+address3+" "+address4;
		results[2] = addr;
		
		String day, month, year;
		try{
		day = String.valueOf(Day_ComboBox.getSelectedItem());
		month  = String.valueOf(Month_ComboBox.getSelectedItem());
		year = String.valueOf(Year_ComboBox.getSelectedItem());
		}catch(Exception e)
		{
			day = null;
			month = null;
			year = null;
		}
		String dobby = day + "/"+ month + "/" + year;
		results[3] = dobby;
		
		String sch;
		try{
			sch = School.getText();
		}catch(Exception e)
		{
			sch = null;
		}
		results[4] = sch;
		
		String g;
		try{
			g = GPA.getText();
		}catch(Exception e)
		{
			g = null;
		}
		results[5] = g;
		
		String cq;
		boolean isy =  y.isSelected();
		boolean isn = n.isSelected();
		if(isy || (!isn))
		{
			cq = "yes";
		}
		cq = "no";
		results[6] = cq;
		
		return results;
	}
	
	public void cleanform()
	{
		FirstName.setText("");
		LastName.setText("");
		Apt_Number.setText("");
		Street.setText("");
		Town.setText("");
		State.setText("");
		School.setText("");
		GPA.setText("");
		y.setSelected(false);
		n.setSelected(false);
	}
	
}
