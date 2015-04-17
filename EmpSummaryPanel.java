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
	public JLabel namel, addrl, dobl, schl, gpal, crimesl, q1l, q2l, q3l, q4l, posl, sall;
	public JPanel namep, addrp, dobp, schp, gpap, crimesp, q1p, q2p, q3p, q4p, posp, salp;
	public JTextField name, addr, dob, sch, gpa, crimes, q1, q2, q3, q4, pos, sal;
	
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
		addrp = new JPanel(new GridLayout(0,2));
		dobp = new JPanel(new GridLayout(0,2));
		schp = new JPanel(new GridLayout(0,2));
		gpap = new JPanel(new GridLayout(0,2));
		crimesp = new JPanel(new GridLayout(0,2));
		q1p = new JPanel(new GridLayout(0,2));
		q2p = new JPanel(new GridLayout(0,2));
		q3p = new JPanel(new GridLayout(0,2));
		q4p = new JPanel(new GridLayout(0,2));
		posp = new JPanel(new GridLayout(0,2));
		salp = new JPanel(new GridLayout(0,2));
		
		namel = new JLabel("Name");
		addrl = new JLabel("Address");
		dobl = new JLabel("Date of Birth");
		schl = new JLabel("School");
		gpal = new JLabel("GPA");
		crimesl = new JLabel("Have You Committed any Crimes?");
		q1l = new JLabel("I agree not to expose restaurant secrets.");
		q2l = new JLabel("I consent to be fired at any time without cause from my employer.");
		q3l = new JLabel("I agree to take random drug tested at the request of my employer.");
		q4l = new JLabel("I revoke my rights to have employer provided healthcare.");
		posl = new JLabel("Position");
		sall = new JLabel("Salary ($)");
		
		name = new JTextField(""+HEMP.first_name+" "+HEMP.last_name);
		addr = new JTextField(HEMP.address);
		dob = new JTextField(HEMP.DOB);
		sch = new JTextField(HEMP.school);
		gpa = new JTextField(HEMP.GPA);
		crimes = new JTextField(HEMP.crimesQuestion);
		q1 = new JTextField(HEMP.Q1);
		q2 = new JTextField(HEMP.Q2);
		q3 = new JTextField(HEMP.Q3);
		q4 = new JTextField(HEMP.Q4);
		pos = new JTextField(HEMP.position);
		sal = new JTextField(HEMP.salary);
		
		namep.add(namel);
		namep.add(name);
		addrp.add(addrl);
		addrp.add(addr);
		dobp.add(dobl);
		dobp.add(dob);
		schp.add(schl);
		schp.add(sch);
		gpap.add(gpal);
		gpap.add(gpa);
		crimesp.add(crimesl);
		crimesp.add(crimes);
		q1p.add(q1l);
		q1p.add(q1);
		q2p.add(q2l);
		q2p.add(q2);
		q3p.add(q3l);
		q3p.add(q3);
		q4p.add(q4l);
		q4p.add(q4);
		posp.add(posl);
		posp.add(pos);
		salp.add(sall);
		salp.add(sal);
		
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
