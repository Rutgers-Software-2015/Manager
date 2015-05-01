package Manager.ManagerGUIs;
/*
@author Harsh Shah 
@tester Harsh Shah
@debugger Harsh Shah
*/
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Shared.Gradients.GradientButton;
import Shared.Gradients.GradientPanel;
import Manager.ManagerHandlers.*;
import Manager.ManagerCommunicator.*;

/*
 * This class is a frontend implementation of the managers
 * financial window. It will house the buttons and subwindows 
 * neccesary to generate the graphs
 */


public class FinancialFrame extends JFrame implements ActionListener{
	
	//Parent Panels
			private JPanel rootPanel,titlePanel,buttonPanel;
			private GradientPanel backgroundPanel,buttonPanelBackground,cardPanel;
			//Swing Objects
			private GradientButton backButton,WeekButton,DayButton;
			private JLabel titleLabel,dateAndTime;
			//Swing Layouts
			private CardLayout c;
			//Other Variables
			private Timer timer;
			
			GradientButton RefundButton;
			GradientPanel RefundsRoot_Card;
			GradientPanel WeekRoot_Card;
			GradientPanel DayRoot_Card;
			JPanel ARP = new AuthRefPanel();
			
			//My Stuff
			GradientPanel DayButtonPanel;
			JButton Mon, Tue, Wed, Thu, Fri, Sat, Sun;
				
			public FinancialFrame()
			{
				super();
				init();
			}


			public void init()
			{
				this.setTitle("Financial Console");
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
				titleLabel = new JLabel("Financial Management");
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
				WeekButton = new GradientButton("<html>Week View</html>");
				WeekButton.addActionListener(this);
				WeekButton.setFont(WeekButton.getFont().deriveFont(16.0f));
				WeekButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				WeekButton.setFocusPainted(false);
				
				// Set Manage Order Queue Button
				DayButton = new GradientButton("Day View");
				DayButton.addActionListener(this);
				DayButton.setFont(DayButton.getFont().deriveFont(16.0f));
				DayButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				DayButton.setFocusPainted(false);
				
				// Set Manage Order Queue Button
				RefundButton = new GradientButton("Refund View");
				RefundButton.addActionListener(this);
				RefundButton.setFont(DayButton.getFont().deriveFont(16.0f));
				RefundButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				RefundButton.setFocusPainted(false);
				
				
				// Set Back Button
				backButton = new GradientButton("BACK");
				backButton.addActionListener(this);												
				backButton.setFont(backButton.getFont().deriveFont(16.0f));
				backButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				backButton.setFocusPainted(false);
				
				buttonPanel.add(RefundButton);
				buttonPanel.add(WeekButton);
				buttonPanel.add(DayButton);
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
				
				WeekRoot_Card = new GradientPanel();
				WeekRoot_Card.setLayout(new BorderLayout());
				WeekRoot_Card.setVisible(true);
				
				DayRoot_Card = new GradientPanel();
				DayRoot_Card.setLayout(new BorderLayout());
				DayRoot_Card.setVisible(true);
				setDayCard();
				
				RefundsRoot_Card = new GradientPanel();
				RefundsRoot_Card.setLayout(new BorderLayout());
				RefundsRoot_Card.setVisible(true);
				setRefundsCard();
				
				
				cardPanel.add(RefundsRoot_Card, "RefundsProcess");
				cardPanel.add(DayRoot_Card, "DaySelection");
				cardPanel.add(WeekRoot_Card, "HireProcess");
				cardPanel.setVisible(true);
			}
			
			public void setRefundsCard()
			{
				RefundsRoot_Card.add(ARP);
				ARP.setVisible(true);
				
			}
			
			// Action Listener
			public void actionPerformed(ActionEvent e) 
			{
				Object a = e.getSource();
				if(a == WeekButton)
				{
					ItemDayGraph week = new ItemDayGraph("");
				}
				if(a == RefundButton)
				{
					c.show(cardPanel, "RefundsProcess");
				}
				if(a == DayButton)
				{
					c.show(cardPanel, "DaySelection");
				}
				if(a == backButton)
					{
						new ManagerRootWindow();
						dispose();
					}
				
				if(a == timer)
					{
						updateClock();
					}
				if(a == Mon)
				{
					ItemDayGraph week = new ItemDayGraph("Monday");
				}
				if(a == Tue)
				{
					ItemDayGraph week = new ItemDayGraph("Tuesday");
				}
				if(a == Wed)
				{
					ItemDayGraph week = new ItemDayGraph("Wednesday");
				}
				if(a == Thu)
				{
					ItemDayGraph week = new ItemDayGraph("Thursday");
				}
				if(a == Fri)
				{
					ItemDayGraph week = new ItemDayGraph("Friday");
				}
				if(a == Sat)
				{
					ItemDayGraph week = new ItemDayGraph("Saturday");
				}
				if(a == Sun)
				{
					ItemDayGraph week = new ItemDayGraph("Sunday");
				}
				
			}
			
			//Set up all the buttons on the panel
			public void setDayCard()
			{
				JLabel selday = new JLabel("Select Day");
				DayRoot_Card.add(selday, BorderLayout.NORTH);
				DayButtonPanel = new GradientPanel();
				DayButtonPanel.setLayout(new GridLayout(4,2));
				DayRoot_Card.add(DayButtonPanel, BorderLayout.CENTER);
				Mon = new JButton("Monday");
				Tue = new JButton("Tuesday");
				Wed = new JButton("Wednesday");
				Thu = new JButton("Thursday");
				Fri = new JButton("Friday");
				Sat = new JButton("Saturday");
				Sun = new JButton("Sunday");
				Mon.addActionListener(this);
				Tue.addActionListener(this);
				Wed.addActionListener(this);
				Thu.addActionListener(this);
				Fri.addActionListener(this);
				Sat.addActionListener(this);
				Sun.addActionListener(this);
				DayButtonPanel.add(Sun);
				DayButtonPanel.add(Mon);
				DayButtonPanel.add(Tue);
				DayButtonPanel.add(Wed);
				DayButtonPanel.add(Thu);
				DayButtonPanel.add(Fri);
				DayButtonPanel.add(Sat);
			}
			
			private void updateClock() {
	            dateAndTime.setText(DateFormat.getDateTimeInstance().format(new Date()));
	        }

}
