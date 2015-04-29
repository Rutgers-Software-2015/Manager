package Manager.ManagerCommunicator;
/*
 * This function helps make a graph for the items.
 @author Harsh Shsh
 @tester Harsh Shah
 @debugger Harsh Shah
*/
import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javax.swing.*;

import java.awt.*;


/*
 * This class serves to generate the graph of the financial
 * data of the restaurant. It will recieve information from the 
 * findatagenerator class and generate data points to be used in a graph
 * It makes use of the javafx libraries to format and print the grpah to the
 * screen.
 */


public class ItemDayGraph {

	public ItemDayGraph(String d)
	{
		SwingUtilities.invokeLater(new Runnable() {
		      @Override
		      public void run() {
		        ChartFrame mainFrame = new ChartFrame(d);
		        mainFrame.setVisible(true);
		        }
		      });
		    }
	}

class ChartFrame extends JFrame {
	
  JFXPanel fxPanel;
  public ChartFrame(String d){
    initSwingComponents();

    initFxComponents(d);
  }

  //Initialize Swing Components
  private void initSwingComponents(){
    JPanel mainPanel = new JPanel(new BorderLayout());
    fxPanel = new JFXPanel();
    mainPanel.add(fxPanel, BorderLayout.CENTER);


    this.add(mainPanel);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setSize(800,400);
  }

  //initialize the java fx components
  private void initFxComponents(String d){

    Platform.runLater(new Runnable() {
      @Override
      public void run() {
    	  
          GridPane grid = new GridPane();
          Scene scene = new Scene(grid,1000, 1000);
         
          
         
          /**
           * Construct and populate Bar chart.
           * It uses 2 series of data.
           */
          final CategoryAxis xAxis = new CategoryAxis();
          final NumberAxis yAxis = new NumberAxis();
          
          BarChart<String,Number> bc =  new BarChart<String,Number>(xAxis,yAxis);
          
          bc.setTitle("Item Trends");
          xAxis.setLabel("Day");       
          yAxis.setLabel("Amount");
   
          FinDataGenerator FDG = new FinDataGenerator();
          
          //generate all the points for the graph
          XYChart.Series series1 = new XYChart.Series();
          series1.setName("Monday");       
          for(int i = 0; i < FDG.Monday.length; i++){
        	  System.out.println("Food: " + FDG.Monday[i] + " " + " " + "Item: " + FDG.MenuItems[i]);
          	series1.getData().add(getData(FDG.Monday[i],FDG.MenuItems[i]));
          }      
          
          XYChart.Series series2 = new XYChart.Series();
          series2.setName("Tuesday");
          for(int i = 0; i < FDG.Tuesday.length; i++){
          	series2.getData().add(getData(FDG.Tuesday[i],FDG.MenuItems[i]));
          }  
          
          XYChart.Series series3 = new XYChart.Series();
          series3.setName("Wednesday");
          for(int i = 0; i < FDG.Wednesday.length; i++){
          	series3.getData().add(getData(FDG.Wednesday[i],FDG.MenuItems[i]));
          }
          
          XYChart.Series series4 = new XYChart.Series();
          series4.setName("Thursday");
          for(int i = 0; i < FDG.Thursday.length; i++){
          	series4.getData().add(getData(FDG.Thursday[i],FDG.MenuItems[i]));
          }
          
          XYChart.Series series5 = new XYChart.Series();
          series5.setName("Friday");
          for(int i = 0; i < FDG.Friday.length; i++){
          	series5.getData().add(getData(FDG.Friday[i],FDG.MenuItems[i]));
          }
          
          XYChart.Series series6 = new XYChart.Series();
          series6.setName("Saturday");
          for(int i = 0; i < FDG.Saturday.length; i++){
          	series6.getData().add(getData(FDG.Saturday[i],FDG.MenuItems[i]));
          }
          
          XYChart.Series series7 = new XYChart.Series();
          series7.setName("Sunday");
          for(int i = 0; i < FDG.Sunday.length; i++){
          	series7.getData().add(getData(FDG.Sunday[i],FDG.MenuItems[i]));
          }
          
          
          //add the points to a graph model
          if(d.equals("Monday"))
          {
        	  bc.getData().addAll(series1);
        	  grid.setVgap(20);
              grid.setHgap(20);
              grid.add(bc,2,0);
              fxPanel.setScene(scene);
              return;
          }
          else if(d.equals("Tuesday"))
          {
        	  bc.getData().addAll(series2);
        	  grid.setVgap(20);
              grid.setHgap(20);
              grid.add(bc,2,0);
              fxPanel.setScene(scene);
              return;
          }
          else if(d.equals("Wednesday"))
          {
        	  bc.getData().addAll(series3);
        	  grid.setVgap(20);
              grid.setHgap(20);
              grid.add(bc,2,0);
              fxPanel.setScene(scene);
              return;
          }
          else if(d.equals("Thursday"))
          {
        	  bc.getData().addAll(series4);
        	  grid.setVgap(20);
              grid.setHgap(20);
              grid.add(bc,2,0);
              fxPanel.setScene(scene);
              return;
          }
          else if(d.equals("Friday"))
          {
        	  bc.getData().addAll(series5);
        	  grid.setVgap(20);
              grid.setHgap(20);
              grid.add(bc,2,0);
              fxPanel.setScene(scene);
              return;
          }
          else if(d.equals("Saturday"))
          {
        	  bc.getData().addAll(series6);
        	  grid.setVgap(20);
              grid.setHgap(20);
              grid.add(bc,2,0);
              fxPanel.setScene(scene);
              return;
          }
          else if(d.equals("Sunday"))
          {
        	  bc.getData().addAll(series7);
        	  grid.setVgap(20);
              grid.setHgap(20);
              grid.add(bc,2,0);
              fxPanel.setScene(scene);
              return;
          }
          
          //intialize the graph
          bc.getData().addAll(series1, series2, series3, series4, series5, series6, series7);
          
         
          grid.setVgap(20);
          grid.setHgap(20);
          grid.add(bc,0,0);
          fxPanel.setScene(scene);
        }
      });

  }

  private XYChart.Data getData(double x, double y){
    XYChart.Data data = new XYChart.Data<>();
    data.setXValue(x);
    data.setYValue(y);
    return data;
  }

  private XYChart.Data getData(double x, String y){
    XYChart.Data data = new XYChart.Data<>();
    data.setYValue(x);
    data.setXValue(y);
    return data;
  }
}
