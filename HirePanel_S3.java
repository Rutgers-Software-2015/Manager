package Manager;
//written by: Harsh Shsh
//tested by: Harsh Shah
//debugged by: Harsh Shah
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

public class HirePanel_S3 extends JPanel 
{
	public GridLayout rootLayout = new GridLayout(3,0);

	public JPanel title_Panel, position_Panel, salary_Panel;
	
	public JButton Back, Next;
	
	public JLabel title, position_Label, salary_Label;
	
	public String[] Salary = {"7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
	public String[] Position = {"Chef", "Waiter", "Busboy", "Host"};
	
	public JComboBox salary_box;
	public JComboBox position_box;
	
	public HirePanel_S3()
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
		position_Panel = new JPanel(new GridLayout(0,2));
		init_position_Panel();
		salary_Panel = new JPanel(new GridLayout(0,2));
		init_salary_Panel();
		
		this.add(title_Panel);
		this.add(position_Panel);
		this.add(salary_Panel);
	}
	
	
	public void init_title_Panel()
	{
		/* Initialize and set up title*/
		title = new JLabel("Employement Form : Page 3 of 3");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(title.getFont().deriveFont(38f));
		title_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		/* Add the title to the title panel */
		title_Panel.add(title);
		title_Panel.setVisible(true);
	}
	
	public void init_position_Panel()
	{
		position_box = new JComboBox(Position);
		position_Label = new JLabel("Position");
		
		position_Panel.add(position_Label);
		position_Panel.add(position_box);
		position_Panel.setVisible(true);
		position_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public void init_salary_Panel()
	{
		salary_box = new JComboBox(Salary);
		salary_Label = new JLabel("Salary");
		
		salary_Panel.add(salary_Label);
		salary_Panel.add(salary_box);
		salary_Panel.setVisible(true);
		salary_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public String[] answers(){
		String[] results = new String[2];
		
		String p = String.valueOf(position_box.getSelectedItem());
		String s = String.valueOf(salary_box.getSelectedItem());
		
		results[0] = p;
		results[1] = s;
		
		return results;
	}
	
	public void cleanform()
	{
		position_box.setSelectedIndex(1);
		salary_box.setSelectedIndex(1);
	}
}
