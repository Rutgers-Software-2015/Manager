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
          NumberAxis yAxis = new NumberAxis(0.0,5.0,1.0);
          NumberAxis xAxis = new NumberAxis(0.0,5.0,1.0);
          FinDataGenerator FDG = new FinDataGenerator();
          ScatterChart bc = new ScatterChart<>(xAxis,yAxis);
          XYChart.Series series =
            new XYChart.Series<>();
          series.setName("Amount");
          for(int i = 0; i < FDG.days.length; i++)
          {
        	  if(FDG.days[i].equals("Monday"))
        	  {
        		  series.getData().add(getData(FDG.Monday[i],FDG.MenuItems[i]));
        	  }
          }
          bc.getData().addAll(series);
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

