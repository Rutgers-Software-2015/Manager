import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.border.*;
import javax.swing.ImageIcon;

public class FinancialWindow extends JFrame implements ActionListener
{
	JPanel rootPanel, buttonPanel;
	static JPanel textPanel;
	JButton Trends, ProfitPerItem;
	
	GridLayout rootLayout = new GridLayout(0,2);
	GridLayout buttonLayout = new GridLayout(2,0);
	GridLayout textLayout = new GridLayout(1,1);
	
	JScrollPane TrendScroller, PPIScroller;
	
	JTable TrendsTable, PPITable;
	
	String[][] Trends_RowData;
	String[] Trends_ColumnNames = {"Item", "Amount"};
	String[] Trends_Item = {"1", "2", "3"};
	String[] Trends_Amount = {"1", "2", "3"};
	
	String[][] PPI_RowData;
	String[] PPI_ColumnNames = {"Item", "Profit"};
	String[] PPI_Item;
	String[] PPI_Profit;
	
	
	public static void main(String[] args)
	{
		new FinancialWindow();
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object a = e.getSource();
		if(a == Trends)
		{
			if(textPanel.getComponents().length == 0)
			{
				textPanel.add(TrendScroller);
				textPanel.updateUI();
			}else{
				textPanel.removeAll();
				textPanel.add(TrendScroller);
				textPanel.updateUI();
			}
			
		}
		if(a == ProfitPerItem)
		{
			if(textPanel.getComponents().length == 0)
			{
				textPanel.add(PPIScroller);
				textPanel.updateUI();
			}else{
				textPanel.removeAll();
				textPanel.add(PPIScroller);
				textPanel.updateUI();
			}
		}
		
	}
	
	public FinancialWindow()
	{
		super();
		init();
	}
	
	public void init()
	{
		this.setTitle("Manger--Financials");
		this.setResizable(true);
		this.setSize(700,700);
		this.frameManipulation();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(rootPanel);
		this.setVisible(true);	
	}
	
	public void frameManipulation()
	{
		rootPanel = new JPanel();
		rootPanel.setLayout(rootLayout);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(buttonLayout);
		init_buttonPanel();
		
		
		textPanel = new JPanel();
		textPanel.setLayout(textLayout);
		init_textPanel();
	}
	
	public void init_buttonPanel()
	{
		//Initialize buttons
		Trends = new JButton("Trends");
		ProfitPerItem = new JButton("Profit Per Item");
		
		//Add the action listeners
		Trends.addActionListener(this);
		ProfitPerItem.addActionListener(this);
		
		//Add to the panel
		buttonPanel.add(Trends);
		buttonPanel.add(ProfitPerItem);
		
		rootPanel.add(buttonPanel);
		
	}
	
	public void init_textPanel()
	{
		init_trends();
		init_PPI();
		rootPanel.add(textPanel);
		
	}
	
	public void init_trends()
	{
		//Need to populate the arrays before they are fed to the JTable
		Trends_RowData = FinancialHandler.getTrend();
		TrendsTable = new JTable(Trends_RowData, Trends_ColumnNames);
		TrendScroller = new JScrollPane(TrendsTable);
		TrendsTable.setFillsViewportHeight(true);
	}
	
	public void init_PPI()
	{
		PPI_RowData = FinancialHandler.getPPI();
		PPITable = new JTable(PPI_RowData, PPI_ColumnNames);
		PPIScroller = new JScrollPane(PPITable);
		PPITable.setFillsViewportHeight(true);
	}
	
	
}
