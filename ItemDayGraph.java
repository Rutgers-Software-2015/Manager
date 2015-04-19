package Manager;

import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.awt.*;

public class ItemDayGraph {

	public ItemDayGraph()
	{
		SwingUtilities.invokeLater(new Runnable() {
		      @Override
		      public void run() {
		        ChartFrame mainFrame = new ChartFrame();
		        mainFrame.setVisible(true);
		        }
		      });
		    }
	}

class ChartFrame extends JFrame {
	
  JFXPanel fxPanel;
  public ChartFrame(){
    initSwingComponents();

    initFxComponents();
  }

  private void initSwingComponents(){
    JPanel mainPanel = new JPanel(new BorderLayout());
    fxPanel = new JFXPanel();
    mainPanel.add(fxPanel, BorderLayout.CENTER);


    this.add(mainPanel);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setSize(800,400);
  }

  private void initFxComponents(){

    Platform.runLater(new Runnable() {
      @Override
      public void run() {
          GridPane grid = new GridPane();
          Scene scene = new Scene(grid, 800, 400);

         
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
          
         
          bc.getData().addAll(series1, series2, series3, series4, series5, series6, series7);
          
          
          grid.setVgap(20);
          grid.setHgap(20);
          grid.add(bc,2,0);
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
