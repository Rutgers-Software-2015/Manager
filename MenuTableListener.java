package Manager;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class MenuTableListener extends MouseAdapter {

	  private JTable table;
	     
	    public MenuTableListener(JTable table) 
	    {
	        this.table = table;
	    }
	     
	    public void mouseClicked(MouseEvent event) {
	        
	    	Point point = event.getPoint();
	        int column = table.columnAtPoint(point);
	         
	        JOptionPane.showMessageDialog(table, "Column header #" + column + " is clicked");
	         
	        // do your real thing here...
	    }
	
}
