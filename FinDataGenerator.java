package Manager;
//written by: Harsh Shsh
//tested by: Harsh Shah
//debugged by: Harsh Shah
import java.awt.Font;
import java.awt.GridLayout;
import java.security.acl.Group;

import javafx.*;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
//import Login.LoginWindow;
import Shared.Gradients.*;

import javax.swing.ButtonGroup;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

import com.sun.prism.paint.Color;

import javafx.application.Platform;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import javafx.scene.Scene;

import javafx.scene.text.Text;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class FinDataGenerator{

	public static FinancialHandler FinHandle = new FinancialHandler();
	public static String[] items = FinHandle.getItems();
	public static int[] amounts = FinHandle.getAmounts();
	public static String[] days = FinHandle.getDay();
	public static String[] MenuItems = FinHandle.getMenuItems();
	JFXPanel chartFxPanel = new JFXPanel();
	JFrame frame = new JFrame();
	
	//Holds day items
	public int[] Monday;
	public int[] Tuesday;
	public int[] Wednesday;
	public int[] Thursday;
	public int[] Friday;
	public int[] Saturday;
	public int[] Sunday;


	public FinDataGenerator()
	{
		organizeData();
	}

	public void organizeData()
	{
		
		
		Monday = new int[MenuItems.length];
		Tuesday = new int[MenuItems.length];
		Wednesday = new int[MenuItems.length];
		Thursday = new int[MenuItems.length];
		Friday = new int[MenuItems.length];
		Saturday = new int[MenuItems.length];
		Sunday = new int[MenuItems.length];
		
		for(int i = 0; i < Monday.length; i++)
		{
			Monday[i] = 0;
			Tuesday[i] = 0;
			Wednesday[i] = 0;
			Thursday[i] = 0;
			Friday[i] = 0;
			Saturday[i] = 0;
			Sunday[i] = 0;
		}
		
		for(int i = 0; i < days.length; i++)
		{
			if(days[i].equals("Monday"))
			{
				String it = items[i];
				int j = findItemIndex(it);
				System.out.println("J: " + j);
				Monday[j] = Monday[j] + amounts[i];
				continue;
			}
			if(days[i].equals("Tuesday"))
			{
				String it = items[i];
				int j = findItemIndex(it);
				System.out.println("J: " + j);
				Tuesday[j] = Tuesday[j] + amounts[i];
				continue;
			}
			if(days[i].equals("Wednesday"))
			{
				String it = items[i];
				int j = findItemIndex(it);
				System.out.println("J: " + j);
				Wednesday[j] = Wednesday[j] + amounts[i];
				continue;
			}
			if(days[i].equals("Thursday"))
			{
				String it = items[i];
				int j = findItemIndex(it);
				System.out.println("J: " + j);
				Thursday[j] = Thursday[j] + amounts[i];
				continue;
			}
			if(days[i].equals("Friday"))
			{
				String it = items[i];
				int j = findItemIndex(it);
				System.out.println("J: " + j);
				Friday[j] = Friday[j] + amounts[i];
				continue;
			}
			if(days[i].equals("Saturday"))
			{
				String it = items[i];
				int j = findItemIndex(it);
				System.out.println("J: " + j);
				Saturday[j] = Saturday[j] + amounts[i];
				continue;
			}
			if(days[i].equals("Sunday"))
			{
				String it = items[i];
				int j = findItemIndex(it);
				System.out.println("J: " + j);
				Sunday[j] = Sunday[j] + amounts[i];
				continue;
			}
			
		}

	}
	
	/*public BarChart Gen_ItemBarGraph()
	{
		organizeData();
		
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        BarChart<String,Number> bc =  new BarChart<String,Number>(xAxis,yAxis);
        
        bc.setTitle("Item Trends");
        xAxis.setLabel("Day");       
        yAxis.setLabel("Amount");
 
       
        
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Monday");       
        for(int i = 0; i < Monday.length; i++){
        	series1.getData().add(new XYChart.Data(MenuItems[i], Monday[i]));
        }      
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Tuesday");
        for(int i = 0; i < Tuesday.length; i++){
        	series2.getData().add(new XYChart.Data(MenuItems[i], Tuesday[i]));
        }  
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Wednesday");
        for(int i = 0; i < Wednesday.length; i++){
        	series3.getData().add(new XYChart.Data(MenuItems[i], Wednesday[i]));
        }
        
        XYChart.Series series4 = new XYChart.Series();
        series4.setName("Thursday");
        for(int i = 0; i < Thursday.length; i++){
        	series4.getData().add(new XYChart.Data(MenuItems[i], Thursday[i]));
        }
        
        XYChart.Series series5 = new XYChart.Series();
        series5.setName("Friday");
        for(int i = 0; i < Friday.length; i++){
        	series5.getData().add(new XYChart.Data(MenuItems[i], Friday[i]));
        }
        
        XYChart.Series series6 = new XYChart.Series();
        series6.setName("Saturday");
        for(int i = 0; i < Saturday.length; i++){
        	series6.getData().add(new XYChart.Data(MenuItems[i], Saturday[i]));
        }
        
        XYChart.Series series7 = new XYChart.Series();
        series7.setName("Sunday");
        for(int i = 0; i < Sunday.length; i++){
        	series7.getData().add(new XYChart.Data(MenuItems[i], Sunday[i]));
        }
        
       
        bc.getData().addAll(series1, series2, series3, series4, series5, series6, series7);
       
       
		return bc;
	}*/
	
    
	
	
	public int findItemIndex(String I)
	{
		int x = 0;
		for(int i = 0; i < MenuItems.length; i++)
		{
			if(I.equals(MenuItems[i]))
			{
				System.out.println("Accessing:" + i);
				x = i;
				break;
			}
		}
		return x;
	}

	
	
}
