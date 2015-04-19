package Manager;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.GridPane;

import javax.swing.*;

import java.awt.*;

public class MondayScatter {

	public MondayScatter()
	{
		SwingUtilities.invokeLater(new Runnable() {
		      @Override
		      public void run() {
		        ChartFrame1 mainFrame = new ChartFrame1();
		        mainFrame.setVisible(true);
		        }
		      });
		    }
	}

class ChartFrame1 extends JFrame {
	
  JFXPanel fxPanel;
  public ChartFrame1(){
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
          
          bc.getData().addAll(series1);
          
          grid.add(bc,0,0);
          
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

