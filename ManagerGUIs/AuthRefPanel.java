package Manager.ManagerGUIs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Manager.ManagerHandlers.RefundHandler;

public class AuthRefPanel extends JPanel implements ListSelectionListener{

	public JButton AuthButton;
	public JList OrderList;
	public RefundHandler R_Handle = new RefundHandler();
	public String[][] Ords;
	public String[] listvals;
	
	
	
	
	public AuthRefPanel()
	{
		super();
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		panelManipulation();
	}
	
	public void panelManipulation()
	{
		init_OrderList();
		init_AuthButton();
	}
	
	public void init_OrderList()
	{
		Ords = R_Handle.refOrders();
		listvals = new String[Ords.length];
		for(int i = 0; i < listvals.length; i++)
		{
			String temp = "Table: ";
			for(int j = 0; j < 2; j++)
			{
				if(j == 0){
					temp = temp + Ords[i][0];
				}
				if(j == 1)
				{
					temp = temp + "\t Item Name: " + Ords[i][1];
				}
			}
			listvals[i] = temp;
			temp = "";
		}
		
		OrderList = new JList(listvals);
		OrderList.addListSelectionListener(this);
		OrderList.setVisible(true);
		this.add(OrderList, BorderLayout.CENTER);
		
		
	}
	
	public void init_AuthButton()
	{
		AuthButton = new JButton("Authorize Refund");
		AuthButton.setVisible(true);
		AuthButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				int row = OrderList.getSelectedIndex();
				int rowid = Integer.parseInt(Ords[row][3]);
				System.out.println("Row id = "+ rowid);
				System.out.println(row);
				R_Handle.deleteOrder(rowid);
				OrderList.remove(row);
				OrderList.updateUI();
				
			}
			
		});
		this.add(AuthButton, BorderLayout.SOUTH);
	}

	


	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
