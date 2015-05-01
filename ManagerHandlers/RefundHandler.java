package Manager.ManagerHandlers;


import java.sql.ResultSet;
import java.sql.SQLException;

import Shared.Communicator.*;

public class RefundHandler extends DatabaseCommunicator{

	public RefundHandler()
	{
		this.connect("admin", "gradMay17");
		this.tell("use MAINDB");
	}
	
	public void deleteOrder(int ord)
	{
		this.update("DELETE FROM TABLE_ORDER WHERE rowid = "+ord+";");
	}
	
	public String[][] refOrders()
	{
		ResultSet rs = this.tell("select * from TABLE_ORDER where CURRENT_STATUS = 'REFUND';");
		int size = 0;
		String[][] Orders = null;
		
		try
		{
			rs.beforeFirst(); //determine size of result set
			while(rs.next() == true)
			{
				size++;
			}
			rs.beforeFirst();
			
			Orders = new String[size][4];
			int i = 0;
			while(rs.next() == true)
			{
				int tabid = rs.getInt("TABLE_ID");
				String tab = tabid+"";
				String itemname = rs.getString("ITEM_NAME");
				String currstatus = rs.getString("CURRENT_STATUS");
				String rowid = rs.getInt("rowid") + "";
				Orders[i][0] = tab;
				Orders[i][1] = itemname;
				Orders[i][2] = currstatus;
				Orders[i][3] = rowid;
				i++;
			}
			
			return Orders;
		}catch(SQLException e)
		{
			e.getMessage();
		}
		System.out.println("NOO ORDERSS!");
		return null;
		
	}
	
	public void discon()
	{
		this.disconnect();
	}
	
}
