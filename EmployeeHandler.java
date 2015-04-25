package Manager;

import static org.apache.commons.codec.binary.Hex.decodeHex;
import static org.apache.commons.io.FileUtils.readFileToByteArray;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;

import Shared.Communicator.*;
import Shared.Lib.*;

public class EmployeeHandler extends DatabaseCommunicator{

	
	
	public EmployeeHandler()
	{
		super();
	}
	
	public Vector<EmpObj> getEmployees() throws SQLException
	{
		//MASTER INTERNET CHECK
		boolean INTERNET;
		INTERNET = isThereInternet();
		
		if(INTERNET == true)
			{
		
				this.connect("admin", "gradMay17");
				this.tell("use MAINDB;");
				ResultSet rs = this.tell("SELECT * FROM EmployeeList;");
				//this.consolePrintTable(rs);
				
				
				int sizeRS = 0;
				rs.beforeFirst();
				while(rs.next() == true)
				{
					sizeRS++;
				}
				
				sizeRS = sizeRS - 7;
				rs.beforeFirst();
				Vector<EmpObj> EmpListVector = new Vector(sizeRS);
				try{
					while(rs.next() == true)
					{
						boolean vis = rs.getBoolean("Visibility");
						String tf = rs.getString("firstname");
						if(vis == false || tf.equals("manager") || tf.equals("waiter") || tf.equals("kitchen") || tf.equals("busboy") || tf.equals("debug") || tf.equals("customer") || tf.equals("host") )
						{
							continue;
						}else
						{
							String fn = rs.getString("firstname");
							String ls = rs.getString("lastname");
							String addr = rs.getString("address");
							String dob = rs.getString("dob");
							String sch =  rs.getString("school");
							String gp = ""+rs.getInt("gpa");
							String c, qone, qtwo, qthree, qfour;
							boolean crm = rs.getBoolean("crimes");
							c = "yes";
							if(crm == false){
								c = "no";
							}
							boolean one = rs.getBoolean("qone");
							qone = "no";
							if(one == true)
							{
								qone = "yes";
							}
							boolean two = rs.getBoolean("qtwo");
							qtwo = "no";
							if(two == true)
							{
								qtwo = "yes";
							}
							boolean three = rs.getBoolean("qthree");
							qthree = "no";
							if(three == true)
							{
								qthree = "yes";
							}
							boolean four = rs.getBoolean("qfour");
							qfour = "no";
							if(one == true)
							{
								qfour = "yes";
							}
							String pos = rs.getString("position");
							String sal = ""+rs.getInt("salary");
							
							EmpObj temp = new EmpObj(fn, ls, addr, dob, sch, gp, c, qone, qtwo, qthree, qfour, pos, sal);
							EmpListVector.add(temp);
						}
					}
				}catch(SQLException e)
				{
					System.out.println("No Result Set");
				}
				
				System.out.println("Obtained the Employees <-- EmployeeHandler.java");
				System.out.println("There are " + EmpListVector.size() +" ready to be acted on!" );
				this.disconnect();
				return EmpListVector;
			}else
			{
				Vector<EmpObj> nothing = new Vector();
				nothing.add(new EmpObj());
				return nothing;
			}
				
	}
	
	public void addEmployee(EmpObj E)
	{
		//MASTER INTERNET CHECK
		boolean INTERNET;
		INTERNET = isThereInternet();

		if(INTERNET == true)
			{
				this.connect("admin", "gradMay17");
				this.tell("use MAINDB;");
				
				ResultSet rs = this.tell("Select * from EmployeeList;");
				int counter = 0;
				try{
					rs.beforeFirst();
					while(rs.next())
					{
						counter++;
					}
				}catch(SQLException e)
				{
					System.out.println(e);
				}
				
				String idstr = ""+counter;
				
				String q1 = "1";
				String q2 = "1";
				String q3 = "1";
				String q4 = "1";
				String c = "0";
				if(E.Q1.equals("false")){ q1 = "0"; }
				if(E.Q2.equals("false")){ q2 = "0"; }
				if(E.Q3.equals("false")){ q3 = "0"; }
				if(E.Q4.equals("false")){ q4 = "0"; }
				if(E.crimesQuestion.equals("")){c = "1"; }
				int g = Integer.parseInt(E.GPA);
				int sally = Integer.parseInt(E.salary);
				//Check all params before using the sql
				
				System.out.println("Attempting to add the following:");
				System.out.println("");
				System.out.println("firstname	: "+E.first_name);
				System.out.println("lastname	: "+E.last_name);
				System.out.println("id			: "+counter);
				System.out.println("address		: "+E.address);
				System.out.println("dob			: "+E.DOB);
				System.out.println("school		: "+E.school);
				System.out.println("gpa			: "+E.GPA);
				System.out.println("crimes		: "+E.crimesQuestion);
				System.out.println("q1			: "+E.Q1);
				System.out.println("q2			: "+E.Q2);
				System.out.println("q3			: "+E.Q3);
				System.out.println("q4			: "+E.Q4);
				System.out.println("positon		: "+E.position);
				System.out.println("salary		: "+E.salary);
				System.out.println("q2			: "+E.Q2);
				System.out.println("username	: "+E.first_name);
				System.out.println("password	: "+E.first_name);
				System.out.println("avail		: "+1);
				
				String templl = E.first_name.toLowerCase();
				String hash =  this.SHA_256_Hash(templl);
				
				String params = "'"+E.first_name+"'"+","+"'"+E.last_name+"'"+","+counter+","+"'"+E.address+"'"+","+"'"+E.DOB+"'"+","+"'"+E.school+"'"+","+g+","+c+","+q1+","+q2+","+q3+","+q4+","+"'"+E.position+"'"+","+sally+","+1+","+"'"+ (E.first_name.toLowerCase()) +"'"+","+"'"+ hash +"'"+","+1;
				String sqlComm = "INSERT INTO EmployeeList (firstname, lastname, id, address, dob, school, gpa, crimes, qone, qtwo, qthree, qfour, position, salary, visibility, username, password, avail) VALUES (" + params + " );";
	
				this.update(sqlComm);
				
				System.out.println("EmployeeAdded!");
				
				this.disconnect();
				return;
			}
		else
		{
			System.out.println("NO INTERNET CONNECTION");
		}
	}
	
	public void fireEmployee(String[] NameAndReason)
	{
		//MASTER INTERNET CHECK
		boolean INTERNET;
		INTERNET = isThereInternet();

		if(NameAndReason[0] == null || NameAndReason[0].equals("") || NameAndReason[1] == null || NameAndReason[1].equals(""))
		{
			System.out.println("Missing Firing Parameters!");
			return;
		}
		
		if(INTERNET == true)
		{
			this.connect("admin", "gradMay17");
			this.tell("use MAINDB;");
			this.update("UPDATE EmployeeList SET visibility = false WHERE firstname = " + "'" + NameAndReason[0] + "'" + ";");
			this.update("UPDATE EmployeeList SET fire_reason = "+ "'" + NameAndReason[1] + "'" + "where firstname = " + "'" + NameAndReason[0] + "'" + ";");
			this.disconnect();
		}else
		{
			System.out.println("No DB Connection!");
		}
	}
	
	public void updateEmployee(EmpObj e)
	{
		boolean INTERNET;
		INTERNET = isThereInternet();
		
		if(INTERNET == true)
		{
			this.connect("admin", "gradMay17");
			this.tell("use MAINDB;");
			String sqlComm = "UPDATE EmployeeList SET address = " + "'" + e.address + "'" + "," +  " position = " + "'" + e.position + "'" + "," + " salary = " + e.salary + " WHERE firstname = " + "'" + e.first_name + "' " + ";";
			this.update(sqlComm);
			this.disconnect();
			
		}else
		{
			System.out.println("NO DB CONNECTION!");
		}
		
	}
	
	
	public boolean isThereInternet()
	{
		try
		{
			URL yourl = new URL("http://google.com");
			HttpURLConnection yourlConnect = (HttpURLConnection)yourl.openConnection();
			yourlConnect.setConnectTimeout(2000);
			
			Object objData = yourlConnect.getContent();
		}catch(UnknownHostException e)
		{
			return false;
		}
		catch(IOException e)
		{
			return false;
		}
		return true;
	}
	
	
}
