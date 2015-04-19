package Manager;

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
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class FinancialFrame extends JFrame implements ActionListener{
	
	public JButton Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, All;
	
	FinancialFrame()
	{
		this.setTitle("Manager");
		this.setVisible(true);
		this.setResizable(true);
		this.setSize(1200,700);
		this.frameManipulation();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	public void frameManipulation()
	{
		JPanel rootPanel = new JPanel(new GridLayout(2,0));
		rootPanel.setVisible(true);
		
		this.add(rootPanel);
		
		JPanel DaySelectionPanel = new JPanel(new GridLayout(0,7));
		DaySelectionPanel.setVisible(true);
		rootPanel.add(DaySelectionPanel);
		
		JPanel SundayPanel = new JPanel(new BorderLayout());
		Sunday = new JButton("Sunday");
		Sunday.addActionListener(this);
		SundayPanel.add(Sunday);
		SundayPanel.setVisible(true);
		SundayPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sunday", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		DaySelectionPanel.add(SundayPanel);
		
		JPanel MondayPanel = new JPanel(new BorderLayout());
		Monday = new JButton("Monday");
		Monday.addActionListener(this);
		MondayPanel.add(Monday);
		MondayPanel.setVisible(true);
		MondayPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Monday", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		DaySelectionPanel.add(MondayPanel);
		
		JPanel TuesdayPanel = new JPanel(new BorderLayout());
		Tuesday = new JButton("Tuesday");
		Tuesday.addActionListener(this);
		TuesdayPanel.add(Tuesday);
		TuesdayPanel.setVisible(true);
		TuesdayPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tuesday", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		DaySelectionPanel.add(TuesdayPanel);
		
		JPanel WednesdayPanel = new JPanel(new BorderLayout());
		Wednesday = new JButton("Wednesday");
		Wednesday.addActionListener(this);
		WednesdayPanel.add(Wednesday);
		WednesdayPanel.setVisible(true);
		WednesdayPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Wednesday", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		DaySelectionPanel.add(WednesdayPanel);
		
		JPanel ThursdayPanel = new JPanel(new BorderLayout());
		Thursday = new JButton("Thursday");
		Thursday.addActionListener(this);
		ThursdayPanel.add(Thursday);
		ThursdayPanel.setVisible(true);
		ThursdayPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thursday", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		DaySelectionPanel.add(ThursdayPanel);
		
		JPanel FridayPanel = new JPanel(new BorderLayout());
		Friday = new JButton("Friday");
		Friday.addActionListener(this);
		FridayPanel.add(Friday);
		FridayPanel.setVisible(true);
		FridayPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Friday", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		DaySelectionPanel.add(FridayPanel);
		
		JPanel SaturdayPanel = new JPanel(new BorderLayout());
		Saturday = new JButton("Saturday");
		Saturday.addActionListener(this);
		SaturdayPanel.add(Saturday);
		SaturdayPanel.setVisible(true);
		SaturdayPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Saturday", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		DaySelectionPanel.add(SaturdayPanel);
		
		JPanel AllPanel =  new JPanel(new BorderLayout());
		All = new JButton("All Days");
		AllPanel.add(All);
		rootPanel.add(AllPanel);
		AllPanel.setVisible(true);
		All.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		Object e = a.getSource();
		if(e == Sunday)
		{
			new ItemDayGraph("Sunday");
		}
		if(e == Monday)
		{
			new ItemDayGraph("Monday");
		}
		if(e == Tuesday)
		{
			new ItemDayGraph("Tuesday");
		}
		if(e == Wednesday)
		{
			new ItemDayGraph("Wednesday");
		}
		if(e == Thursday)
		{
			new ItemDayGraph("Thursday");
		}
		if(e == Friday)
		{
			new ItemDayGraph("Friday");
		}
		if(e == Saturday)
		{
			new ItemDayGraph("Saturday");
		}
		if(e == All)
		{
			new ItemDayGraph("");
		}
		
	}
}
