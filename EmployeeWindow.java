package Manager;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
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



public class EmployeeWindow extends JFrame implements ActionListener{

	
		//Parent Panels
		private JPanel rootPanel,titlePanel,buttonPanel;
		private GradientPanel backgroundPanel,buttonPanelBackground,cardPanel;
		//Swing Objects
		private GradientButton backButton,EditButton,HireButton,FireButton,PerfomanceButton;
		private JLabel titleLabel,dateAndTime;
		//Swing Layouts
		private CardLayout c;
		//Other Variables
		private Timer timer;
		
		//DATABASE HANDLER
		private EmployeeHandler EmpHandle = new EmployeeHandler();
		
		/*Functional Stuff*/
		/* Hiring Process Stuff */
			public JPanel HP1;
			public GradientPanel HireRoot_Card;
			public HirePanel_S1  form1 = new HirePanel_S1();
			public HirePanel_S2  form2 = new HirePanel_S2();
			public HirePanel_S3  form3 = new HirePanel_S3();
			public JTabbedPane formpane;
			public GradientButton Done_Hire;
			public EmpObj H_EmpInfo;
			public JFrame Hire_Error_Window;
			public JLabel Hire_Error_Message;
			public EmpSummaryPanel SumForm;
		
		/*Editing Employee Stuff*/	
			public EmpEditPage EditPage_Card;
			public GradientPanel EmpEdit_Root;
			public GradientButton EmpEdit_Button;
		
			
		/* Firing Process Stuff */
			public GradientPanel FireRoot_Card;
			public FirePanel FirePage;
			
		public EmployeeWindow()
		{
			super();
			init();
		}


		public void init()
		{
			this.setTitle("Employee Management Console");
			this.setResizable(true);
			this.setSize(1200,700);
			this.frameManipulation();
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
			this.setResizable(false);
			getContentPane().add(rootPanel);
			
			addWindowListener(new WindowAdapter() // To open main window again if you hit the corner "X"
	        {
	            @Override
	            public void windowClosing(WindowEvent e)
	            {
	                new ManagerRootWindow();
	                dispose();
	            }
	        });
			
			c = (CardLayout)(cardPanel.getLayout());
			
			this.setVisible(true);
		}

		public void frameManipulation()
		{
			rootPanel = new JPanel();
			rootPanel.setLayout(null);
			setBackgroundPanel();
			setTitlePanel();
			setCardPanel();
			setButtonPanel();
			setRootPanel();
		}
		
		private void setRootPanel()
		{
			rootPanel.add(titlePanel);
			rootPanel.add(cardPanel);
			rootPanel.add(buttonPanel);
			rootPanel.add(buttonPanelBackground);
			rootPanel.add(backgroundPanel);
		}
		
		private void setBackgroundPanel()
		{
			// Create Button Background Panel
			buttonPanelBackground = new GradientPanel();
			buttonPanelBackground.setGradient(new Color(255,220,220), new Color(255,110,110));
			buttonPanelBackground.setBounds(0, 55, 250, 617);
			buttonPanelBackground.setBorder(new LineBorder(new Color(0, 0, 0)));
			
			// Create Background Panel
			backgroundPanel = new GradientPanel();
			backgroundPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			backgroundPanel.setGradient(new Color(255,255,255), new Color(255,110,110));
			backgroundPanel.setLayout(null);
			backgroundPanel.setBounds(0,0,1194,672);
		}
		
		//************************************************************
		//DO NOT edit the following function except for the title name
		//************************************************************
		
		private void setTitlePanel()
		{
			// Create Title Panel
			titlePanel = new JPanel();
			titlePanel.setBounds(0, 0, 1194, 56);
			titlePanel.setLayout(null);
			titlePanel.setOpaque(false);
			// Set Title
			titleLabel = new JLabel("Employee Management");
			titleLabel.setHorizontalAlignment(JLabel.CENTER);
			titleLabel.setFont(titleLabel.getFont().deriveFont(38f));
			titleLabel.setBorder(BorderFactory.createLineBorder(Color.black));
			titleLabel.setBounds(new Rectangle(0, 0, 793, 56));
			
						// Add components to Title Panel
						titlePanel.add(titleLabel);
						// Set Date and Time
						dateAndTime = new JLabel();
						dateAndTime.setBounds(792, 0, 402, 56);
						titlePanel.add(dateAndTime);
						dateAndTime.setHorizontalAlignment(JLabel.CENTER);
						dateAndTime.setFont(dateAndTime.getFont().deriveFont(28f));
						dateAndTime.setBorder(BorderFactory.createLineBorder(Color.black));
						updateClock();
						// Create a timer to update the clock
						timer = new Timer(500,this);
			            timer.setRepeats(true);
			            timer.setCoalesce(true);
			            timer.setInitialDelay(0);
			            timer.start();
		}
		
		//*********************************************************
		//DO NOT change the location of the following panel
		//*********************************************************
		
		private void setButtonPanel()
		{
			// Create Button Panel
			buttonPanel = new JPanel();
			buttonPanel.setBounds(7, 61, 236, 604);
			buttonPanel.setOpaque(false);
			buttonPanel.setBorder(null);
			buttonPanel.setLayout(new GridLayout(5, 0, 5, 5));
			
			// Set Request Table Status Change Button
			EditButton = new GradientButton("<html>Edit Employee Information</html>");
			EditButton.addActionListener(this);
			EditButton.setFont(EditButton.getFont().deriveFont(16.0f));
			EditButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			EditButton.setFocusPainted(false);
			
			// Set Manage Order Queue Button
			HireButton = new GradientButton("Hire Employee");
			HireButton.addActionListener(this);
			HireButton.setFont(HireButton.getFont().deriveFont(16.0f));
			HireButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			HireButton.setFocusPainted(false);
			
			// Set Accept Payment Button
			FireButton = new GradientButton("Fire Employee");
			FireButton.addActionListener(this);
			FireButton.setFont(FireButton.getFont().deriveFont(16.0f));
			FireButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			FireButton.setFocusPainted(false);
			
			// Set Request Refund Button
			PerfomanceButton = new GradientButton("Employee Performance");
			PerfomanceButton.addActionListener(this);
			PerfomanceButton.setFont(PerfomanceButton.getFont().deriveFont(16.0f));
			PerfomanceButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			PerfomanceButton.setFocusPainted(false);
			// Set Back Button
			backButton = new GradientButton("BACK");
			backButton.addActionListener(this);												
			backButton.setFont(backButton.getFont().deriveFont(16.0f));
			backButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			backButton.setFocusPainted(false);
			
			buttonPanel.add(EditButton);
			buttonPanel.add(HireButton);
			buttonPanel.add(FireButton);
			buttonPanel.add(PerfomanceButton);
			buttonPanel.add(backButton);
		}
		
		//********************************************************************************
		//DO NOT deviate from the card layout or change the size/location of the cardPanel.
		//Creating and adding cards is OK
		//********************************************************************************
		
		private void setCardPanel()
		{
			cardPanel = new GradientPanel();
			cardPanel.setLayout(new CardLayout()); // How to define a Card Layout
			cardPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			cardPanel.setGradient(new Color(255,255,255), new Color(255,110,110));
			cardPanel.setBounds(273, 79, 896, 569);
			
			HireRoot_Card = new GradientPanel();
			HireRoot_Card.setLayout(new BorderLayout());
			HireRoot_Card.setVisible(true);
			setFormPanel();
			setDoneHire();
			
			EmpEdit_Root = new GradientPanel();
			EmpEdit_Root.setLayout(new BorderLayout());
			EmpEdit_Root.setVisible(true);
			setEditPage();
			setEmpEditButton();
			
			FireRoot_Card = new GradientPanel();
			FireRoot_Card.setLayout(new BorderLayout());
			FireRoot_Card.setVisible(true);
			setFireCard();
			
			
			cardPanel.add(FireRoot_Card, "FireProcess");
			cardPanel.add(EmpEdit_Root, "EditProcess");
			cardPanel.add(HireRoot_Card, "HireProcess");
			cardPanel.setVisible(true);
		}
		public void setFireCard()
		{
			FirePage = new FirePanel();
			FirePage.setVisible(true);
			FireRoot_Card.add(FirePage, BorderLayout.CENTER);
		}
		
		private void setEditPage()
		{
			EditPage_Card = new EmpEditPage();		
			EmpEdit_Root.add(EditPage_Card, BorderLayout.CENTER);
			EditPage_Card.setVisible(true);
		}
		
		private void setEmpEditButton()
		{
			EmpEdit_Button = new GradientButton("Edit Selection");
			EmpEdit_Button.addActionListener(this);
			EmpEdit_Root.add(EmpEdit_Button, BorderLayout.SOUTH);
		}
		
		private void setFormPanel()
		{
			formpane = new JTabbedPane();
			formpane.setVisible(true);
			formpane.addTab("Page 1", form1);
			formpane.addTab("Page 2", form2);
			formpane.addTab("Page 3", form3);
			HireRoot_Card.add(formpane, BorderLayout.CENTER);
			
		}
		
		private void setDoneHire()
		{
			Done_Hire = new GradientButton("Done");
			Done_Hire.addActionListener(this);
			HireRoot_Card.add(Done_Hire, BorderLayout.SOUTH);
		}
		
		// Action Listener
		public void actionPerformed(ActionEvent e) 
		{
			Object a = e.getSource();
			if(a == backButton)
				{
					new ManagerRootWindow();
					dispose();
				}
			if(a == HireButton)
				{
					c.show(cardPanel, "HireProcess");
				
				}
			if(a == FireButton)
				{
					c.show(cardPanel, "FireProcess");
				}
			if(a == Done_Hire)
			{
				getNewHireInfo();
			}
			if(a == EditButton)
				{
					c.show(cardPanel, "EditProcess");
				
				}
			if(a == timer)
				{
					updateClock();
				}
		}
		
		
		public void getNewHireInfo()
		{
			Hire_Error_Window = new JFrame();
			Hire_Error_Window.setSize(200, 100);
			Hire_Error_Window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			
			String[] S1 = form1.answers();
			String[] S2 = form2.answers();
			String[] S3 = form3.answers();
			String S1flag;
			String S2flag;
			
			//Check form 1
			for(int i = 0; i < S1.length; i++)
			{
				//[fname, lname, address, dob, school, gpa, question]
				if((S1[i] == null) || S1[i] == "")
				{
					if(i == 0){
						S1flag = "Page 1 Error: FIRST NAME";
						Hire_Error_Message = new JLabel(S1flag);
						Hire_Error_Window.add(Hire_Error_Message);
						Hire_Error_Window.setVisible(true);
						return;
					}
					if(i == 1){
						S1flag = "Page 1 Error: LAST NAME";
						Hire_Error_Message = new JLabel(S1flag);
						Hire_Error_Window.add(Hire_Error_Message);
						Hire_Error_Window.setVisible(true);
						return;
					}
					if(i == 2){
						S1flag = "Page 1 Error: ADDRESS";
						Hire_Error_Message = new JLabel(S1flag);
						Hire_Error_Window.add(Hire_Error_Message);
						Hire_Error_Window.setVisible(true);
						return;
					}
					if(i == 3){
						S1flag = "Page 1 Error: DOB";
						Hire_Error_Message = new JLabel(S1flag);
						Hire_Error_Window.add(Hire_Error_Message);
						Hire_Error_Window.setVisible(true);
						return;
					}
					if(i == 4){
						S1flag = "Page 1 Error: SCHOOL";
						Hire_Error_Message = new JLabel(S1flag);
						Hire_Error_Window.add(Hire_Error_Message);
						Hire_Error_Window.setVisible(true);
						return;
					}
					if(i == 5){
						S1flag = "Page 1 Error: GPA";
						Hire_Error_Message = new JLabel(S1flag);
						Hire_Error_Window.add(Hire_Error_Message);
						Hire_Error_Window.setVisible(true);
						return;
					}
					if(i == 0){
						S1flag = "Page 1 Error: QUESTION ONE";
						Hire_Error_Message = new JLabel(S1flag);
						Hire_Error_Window.add(Hire_Error_Message);
						Hire_Error_Window.setVisible(true);
						return;
					}
				}
			}
			

			
			//Construct the handler object
			///[fname, lname, address, dob, school, gpa, question]
			///FNAME, LNAME, ADDRESS, DOB, SCHOOL, GPA, q1, q2, q3, q4, position, sal
			H_EmpInfo = new EmpObj(S1[0], S1[1], S1[2], S1[3], S1[4], S1[5], S1[6], S2[0], S2[1], S2[2], S2[3], S3[0], S3[1]);
			
			
			
			Hire_Error_Message = new JLabel("Worker Added!");
			Hire_Error_Window.add(Hire_Error_Message);
			Hire_Error_Window.setVisible(true);
			
			SumForm = new EmpSummaryPanel(H_EmpInfo);
			cardPanel.add(SumForm,"Summary");
			c.show(cardPanel, "Summary");
			
			//Clean up the form
			form1.cleanform();
			form2.cleanform();
			form3.cleanform();
			
			//Send the data to the database
			EmpHandle.addEmployee(H_EmpInfo);
		}
		
		
		private void updateClock() {
            dateAndTime.setText(DateFormat.getDateTimeInstance().format(new Date()));
        }
}
