package Manager;
//written by: Harsh Shsh
//tested by: Harsh Shah
//debugged by: Harsh Shah
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



//import Login.LoginWindow;
import Shared.Gradients.*;
import Shared.Notifications.NotificationGUI;
import Login.LoginWindow;

public class ManagerRootWindow extends JFrame implements ActionListener{

		//Swing Variables
		private JPanel rootPanel,titlePanel;
		private GradientPanel backgroundPanel;
		private GradientButton logoutButton;
		//private GradientButton logoutButton;
		private JLabel titleLabel,dateAndTime;
		//Other Variables
		private Timer timer;
		private JPanel buttonPanel;
		
		//Manager Sub Buttons
		private GradientButton EmployeeButton;
		private GradientButton FinancialsButton;
		private GradientButton InventoryButton;
		private GradientButton MenuButton;
		
		private NotificationGUI notifications;
		
		public ManagerRootWindow()
		{
			super();
			init();
		}


		public void init()
		{
			this.setTitle("Manager");
			this.setVisible(false);
			this.setResizable(true);
			this.setSize(1200,700);
			this.frameManipulation();
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
			this.setResizable(false);
			getContentPane().add(rootPanel);
			
			/* addWindowListener(new WindowAdapter() // To open login again if you hit the corner "X"
		        {
		            @Override
		            public void windowClosing(WindowEvent e)
		            {
		                new LoginWindow();
		                dispose();
		            }
		        });
			*/
			this.setVisible(true);
		} 

		public void frameManipulation()
		{
			setBackgroundPanel();
			setTitlePanel();
			setButtonPanel();
			setRootPanel();
		}
		
		private void setRootPanel()
		{
			notifications = new NotificationGUI(5, "Manager");
			rootPanel = new JPanel();
			rootPanel.setLayout(null);
			rootPanel.add(notifications);
			rootPanel.add(titlePanel);
			rootPanel.add(buttonPanel);
			rootPanel.add(backgroundPanel);
			rootPanel.setVisible(true);
		}
		
		private void setBackgroundPanel()
		{
			// Create Background Panel
			backgroundPanel = new GradientPanel();
			backgroundPanel.setGradient(Color.white,new Color(153,230,255));
			//backgroundPanel.setBrightness(backgroundPanel.getColor2(),1);
			backgroundPanel.setLayout(null);
			backgroundPanel.setBounds(0,0,1200,700);
			
			logoutButton = new GradientButton("LOGOUT");
			logoutButton.setBounds(187, 550, 825, 66);
			logoutButton.setFocusPainted(false);
			logoutButton.setFont(logoutButton.getFont().deriveFont(16.0f));
			backgroundPanel.add(logoutButton);
			logoutButton.addActionListener(this);
			
			backgroundPanel.setVisible(true);
		}
		
		
		//*********************************************************
		//DO NOT edit the following function except for title name
		//*********************************************************
		
		private void setTitlePanel()
		{
			// Create Title Panel
			titlePanel = new JPanel();
			titlePanel.setLayout(null);
			titlePanel.setOpaque(false);
			titlePanel.setBounds(new Rectangle(0, 0, 1200, 56));
			// Set Title
			titleLabel = new JLabel("Manager Directory");
			titleLabel.setHorizontalAlignment(JLabel.CENTER);
			titleLabel.setFont(titleLabel.getFont().deriveFont(38f));
			titleLabel.setBorder(BorderFactory.createLineBorder(Color.black));
			titleLabel.setBounds(new Rectangle(0, 0, 793, 56));
			// Create a timer to update the clock
			timer = new Timer(500,this);
            timer.setRepeats(true);
            timer.setCoalesce(true);
            timer.setInitialDelay(0);
            timer.addActionListener(this);
            timer.start();

			// Add components to Title Panel
			titlePanel.add(titleLabel);
			// Set Date and Time
			dateAndTime = new JLabel();
			dateAndTime.setBounds(792, 0, 402, 56);
			titlePanel.add(dateAndTime);
			dateAndTime.setHorizontalAlignment(JLabel.CENTER);
			dateAndTime.setFont(dateAndTime.getFont().deriveFont(28f));
			dateAndTime.setBorder(BorderFactory.createLineBorder(Color.black));
			
			titlePanel.setVisible(true);
		}
		
		private void setButtonPanel()
		{
			// Only adjust horizontal and vertical gaps for this layout
			// DO NOT adjust panel size or location!!
			
			buttonPanel = new JPanel();
			buttonPanel.setBounds(200, 105, 800, 388); // setBounds(DONT FUCK WITH)
			buttonPanel.setLayout(new GridLayout(2, 2, 150, 100)); // GridLayout(DONT FUCK WITH, DONT FUCK WITH, h gap, v gap)
			
			EmployeeButton = new GradientButton("Employees");
			EmployeeButton.setFont(EmployeeButton.getFont().deriveFont(16.0f));
			EmployeeButton.addActionListener(this);
			buttonPanel.add(EmployeeButton);
			
			FinancialsButton = new GradientButton("Financials");
			FinancialsButton.setFont(FinancialsButton.getFont().deriveFont(16.0f));
			FinancialsButton.addActionListener(this);
			buttonPanel.add(FinancialsButton);
			
			InventoryButton = new GradientButton("Inventory");
			InventoryButton.setFont(InventoryButton.getFont().deriveFont(16.0f));
			InventoryButton.addActionListener(this);
			buttonPanel.add(InventoryButton);
			
			MenuButton = new GradientButton("Menu");
			MenuButton.setFont(MenuButton.getFont().deriveFont(16.0f));
			MenuButton.addActionListener(this);
			buttonPanel.add(MenuButton);
			
			buttonPanel.setOpaque(false);
			buttonPanel.setVisible(true);
			
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			Object a = e.getSource();
			if(a == EmployeeButton)
			{
				new  EmployeeWindow();
				dispose();
			}
			if(a == FinancialsButton)
				{
				 new FinancialFrame();
				 dispose();
				}
			if(a == MenuButton)
				{
					try{
						new MenuWindow();
						dispose();
					}catch(SQLException l)
					{
						l.printStackTrace();
					}
				}
			if(a == InventoryButton)
				{
				new InventoryWindow();
				dispose();
				}
			if(a == logoutButton)
				{
					new LoginWindow();
					dispose();
				}
			if(a == timer)
				{
					updateClock();
				}
		}
		
		private void updateClock() {
            dateAndTime.setText(DateFormat.getDateTimeInstance().format(new Date()));
        }

}

