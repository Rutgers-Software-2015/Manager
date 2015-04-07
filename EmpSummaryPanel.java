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


public class EmpSummaryPanel extends JPanel{

	public GridLayout rootLayout = new GridLayout(12,0);
	public JLabel name, addr, dob, sch, gpa, crimes, q1, q2, q3, q4, pos, sal;
	public JPanel namep, addrp, dobp, schp, gpap, crimesp, q1p, q2p, q3p, q4p, posp, salp;
	
	///FNAME, LNAME, ADDRESS, DOB, SCHOOL, GPA, crimes, q1, q2, q3, q4, position, sal
	public EmpSummaryPanel(EmpObj HEMP)
	{
		super();
		this.setLayout(rootLayout);
		panelManipulation(HEMP);
		this.setVisible(true);
	}
	
	public void panelManipulation(EmpObj HEMP)
	{
		namep = new JPanel(new GridLayout(0,2));
		name = new JLabel("Name");
		String empname = HEMP.first_name +" "+HEMP.last_name;
		JLabel nname = new JLabel(empname);
		namep.add(name);
		namep.add(nname);
		namep.setVisible(true);
		
		addrp = new JPanel(new GridLayout(0,2));
		addr = new JLabel("Address");
		addrp.add(addr);
		JLabel a = new JLabel(HEMP.address);
		addrp.add(a);
		addrp.setVisible(true);
		
		dobp = new JPanel(new GridLayout(0,2));
		dob = new JLabel("Date of Birth (dd/mm/yyyy)");
		dobp.add(dob);
		JLabel d = new JLabel(HEMP.DOB);
		dobp.add(d);
		dobp.setVisible(true);
		
		schp = new JPanel(new GridLayout(0,2));
		sch = new JLabel("School");
		schp.add(sch);
		JLabel s = new JLabel(HEMP.school);
		schp.add(s);
		schp.setVisible(true);
		
		gpap = new JPanel(new GridLayout(0,2));
		gpa = new JLabel("GPA");
		gpap.add(gpa);
		JLabel g = new JLabel(HEMP.GPA);
		gpap.add(g);
		gpap.setVisible(true);
		
		crimesp = new JPanel(new GridLayout(0,2));
		crimes = new JLabel("Have you committed and crimes?");
		crimesp.add(crimes);
		JLabel c = new JLabel(HEMP.crimesQuestion);
		crimesp.add(c);
		crimesp.setVisible(true);
		
		q1p = new JPanel(new GridLayout(0,2));
		q1 = new JLabel("I agree not to expose restaurant secrets.");
		q1p.add(q1);
		JLabel q1l = new JLabel(HEMP.Q1);
		q1p.add(q1l);
		q1p.setVisible(true);
		
		q2p = new JPanel(new GridLayout(0,2));
		q2 = new JLabel("I consent to be fired at any time without cause from my employer.");
		q2p.add(q2);
		JLabel q2l = new JLabel(HEMP.Q2);
		q2p.add(q2l);
		q2p.setVisible(true);
		
		q3p = new JPanel(new GridLayout(0,2));
		q3 = new JLabel("I agree to take random drug tested at the request of my employer.");
		q3p.add(q3);
		JLabel q3l = new JLabel(HEMP.Q3);
		q3p.add(q3l);
		q3p.setVisible(true);
		
		q4p = new JPanel(new GridLayout(0,2));
		q4 = new JLabel("I revoke my rights to have employer provided healthcare.");
		q4p.add(q4);
		JLabel q4l = new JLabel(HEMP.Q4);
		q4p.add(q4l);
		q4p.setVisible(true);
			
		posp = new JPanel(new GridLayout(0,2));
		pos = new JLabel("Position");
		posp.add(pos);
		JLabel pp = new JLabel(HEMP.position);
		posp.add(pp);
		posp.setVisible(true);
		
		salp = new JPanel(new GridLayout(0,2));
		sal = new JLabel("Salary");
		salp.add(sal);
		JLabel ss = new JLabel(HEMP.salary);
		salp.add(ss);
		salp.setVisible(true);
		
		this.add(namep);
		this.add(addrp);
		this.add(dobp);
		this.add(schp);
		this.add(gpap);
		this.add(crimesp);
		this.add(q1p);
		this.add(q2p);
		this.add(q3p);
		this.add(q4p);
		this.add(posp);
		this.add(salp);
	}
	

	
}
