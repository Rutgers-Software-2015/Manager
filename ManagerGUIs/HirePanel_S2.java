package Manager.ManagerGUIs;
/*
@author Harsh Shah 
@tester Harsh Shah
@debugger Harsh Shah
*/
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

public class HirePanel_S2 extends JPanel
{
	public JPanel title_Panel, Q1, Q2, Q3, Q4, BN;
	public JPanel t1, t2, t3, t4;
	public JLabel w1, w2, w3, w4;
	
	public GridLayout rootLayout = new GridLayout(5,0);
	public GridLayout QLayout = new GridLayout(2,0);
	public GridLayout hscreen = new GridLayout(0,2);
	
	public JRadioButton y1, n1, y2, n2, y3, n3, y4, n4;
	
	
	
	public JLabel title;
	
	public HirePanel_S2()
	{
		super();
		this.setLayout(rootLayout);
		panelManipulation();
		this.setVisible(true);
	}
	
	public void panelManipulation()
	{
		title_Panel = new JPanel(new GridLayout(1,0));
		init_title_Panel();
		Q1 = new JPanel(QLayout);
		init_Q1();
		Q2 = new JPanel(QLayout);
		init_Q2();
		Q3 = new JPanel(QLayout);
		init_Q3();
		Q4 = new JPanel(QLayout);
		init_Q4();
		
		
		this.add(title);
		this.add(Q1);
		this.add(Q2);
		this.add(Q3);
		this.add(Q4);
		
	}
	
	public void init_title_Panel()
	{
		/* Initialize and set up title*/
		title = new JLabel("Employement Form : Page 2 of 3");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(title.getFont().deriveFont(38f));
		title_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		/* Add the title to the title panel */
		title_Panel.add(title);
		title_Panel.setVisible(true);
	}
	
	public void init_Q1()
	{
		w1 = new JLabel("I agree not to expose restaurant secrets.");
		t1 = new JPanel(hscreen);
		y1 = new JRadioButton("Yes"); 
		n1 = new JRadioButton("No");
		t1.add(y1);
		t1.add(n1);
		Q1.add(w1);
		Q1.add(t1);
		Q1.setVisible(true);
	}
	
	public void init_Q2()
	{
		w2 = new JLabel("I consent to be fired at any time without cause from my employer.");
		t2 = new JPanel(hscreen);
		y2 = new JRadioButton("Yes"); 
		n2 = new JRadioButton("No");
		t2.add(y2);
		t2.add(n2);
		Q2.add(w2);
		Q2.add(t2);
		Q2.setVisible(true);
	}
	
	public void init_Q3()
	{
		w3 = new JLabel("I agree to take random drug tested at the request of my employer.");
		t3 = new JPanel(hscreen);
		y3 = new JRadioButton("Yes"); 
		n3 = new JRadioButton("No");
		t3.add(y3);
		t3.add(n3);
		Q3.add(w3);
		Q3.add(t3);
		Q3.setVisible(true);
	}
	
	public void init_Q4()
	{
		w4 = new JLabel("I revoke my rights to have employer provided healthcare.");
		t4 = new JPanel(hscreen);
		y4 = new JRadioButton("Yes"); 
		n4 = new JRadioButton("No");
		t4.add(y4);
		t4.add(n4);
		Q4.add(w4);
		Q4.add(t4);
		Q1.setVisible(true);
	}
	
	
	public String[] answers()
	{
		String[] results = new String[4];
		
		String one = null;
		boolean oney = y1.isSelected();
		if(oney == false)
		{
			one = "false";
		}
		if(oney == true){
		one = "true";
		}
		
		String two = null;
		boolean twoy = y2.isSelected();
		if(twoy == false)
		{
			two = "false";
		}
		if(twoy == true){
		two = "true";
		}
		
		String three = null;
		boolean threey = y3.isSelected();
		if(threey == false)
		{
			three = "false";
		}
		if(threey == true){
		three = "true";
		}
		
		String four = null;
		boolean foury = y4.isSelected();
		if(foury == false)
		{
			four = "false";
		}if(foury = true){
		four = "true";
		}
		
		results[0] = one;
		results[1] = two;
		results[2] = three;
		results[3] = four;
		return results;
	}
	
	public void cleanform()
	{
		y1.setSelected(false);
		n1.setSelected(false);
		y2.setSelected(false);
		n2.setSelected(false);
		y3.setSelected(false);
		n3.setSelected(false);
		y4.setSelected(false);
		n4.setSelected(false);
	}
	
	
	
	
	
}
