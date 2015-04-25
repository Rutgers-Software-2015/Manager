package Manager;
//written by: Harsh Shsh
//tested by: Harsh Shah
//debugged by: Harsh Shah
public class EmpObj {
	public String first_name, last_name;
	public String address;
	public String DOB;
	public String school;
	public String GPA;
	public String crimesQuestion;
	public String Q1, Q2, Q3, Q4;
	public String position, salary;
	
	public EmpObj(String FNAME, String LNAME, String ADDRESS, String DOB, String SCHOOL, String GPA, String crime, String q1, String q2, String q3, String q4, String position, String sal)
	{
		this.first_name = FNAME;
		this.last_name = LNAME;
		this.address = ADDRESS;
		this.DOB = DOB;
		this.school = SCHOOL;
		this.GPA = GPA;
		this.crimesQuestion = crime;
		this.Q1 = q1;
		this.Q2 = q2;
		this.Q3 = q3;
		this.Q4 = q4;
		this.position = position;
		this.salary = sal;
	}
	
	public EmpObj()
	{
		this.first_name = "";
		this.last_name = "";
		this.address = "";
		this.DOB = "";
		this.school = "";
		this.GPA = "";
		this.crimesQuestion = "";
		this.Q1 = "";
		this.Q2 = "";
		this.Q3 = "";
		this.Q4 = "";
		this.position = "";
		this.salary = "";
	}
	
}
