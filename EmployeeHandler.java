package Manager;

import static org.apache.commons.codec.binary.Hex.decodeHex;
import static org.apache.commons.io.FileUtils.readFileToByteArray;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
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
	
		this.connect("admin", "gradMay17");
		this.tell("use MAINDB;");
		ResultSet rs = this.tell("SELECT * FROM EmployeeList;");
		this.consolePrintTable(rs);
		
		
		int sizeRS = 0;
		rs.beforeFirst();
		while(rs.next() == true)
		{
			sizeRS++;
		}
		
		rs.beforeFirst();
		Vector<EmpObj> EmpListVector = new Vector(sizeRS);
		try{
			while(rs.next() == true)
			{
				boolean vis = rs.getBoolean("Visibility");
				if(vis == false)
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
		return EmpListVector;
	}
}
