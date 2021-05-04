import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/*
  
   This program displays a Frame and accepts
    Emp Details and inserts the values into the Emptable.mdb

    
*/

public class InsertIntoDB extends JFrame implements ActionListener
{
	JTextField t1,t2,t3;
	JButton b1;
	String e1,e2,e3;
 	JLabel l1,l2,l3;
	InsertIntoDB()
	{
		Container c=getContentPane();
		c.setLayout(new FlowLayout());
    		l1=new JLabel("EMP ID");
    		l2=new JLabel("EMP Name");
    		l3=new JLabel("Salary");
		t1=new JTextField("ID Number ");
    		t2=new JTextField("Enter Your Name ");
    		t3=new JTextField(" Enter Salary ");
    		c.add(l1);
		c.add(t1);
    		c.add(l2);
    		c.add(t2);
		c.add(l3);
    		c.add(t3);
		b1=new JButton(" SAVE ");
		c.add(b1);
		b1.setToolTipText(" LOAD into DATABASE ");
		b1.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{

			try
			{
				Connection conn;
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				String url="jdbc:odbc:newdsn";
				conn=DriverManager.getConnection(url);
				System.out.println("\n connection  ... ok \n");
				Statement stm=conn.createStatement();
        			e1=t1.getText();
				e2=t2.getText();
       				e3=t3.getText();
				stm.executeUpdate("insert into emplist(empid,empname,salary) values("+e1+",'"+e2+"',"+e3+")");
        			conn.commit();
				System.out.println("\nYOUR DATA IS SUCCESSFULLY ENTERED INTO THE DATABASE \n");
        			
				stm.close();
			        conn.close();
				System.exit(0);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
  }
    public static void main(String args[])
    {
           InsertIntoDB e = new InsertIntoDB();
           e.setSize(400,500);
           e.setTitle("Enter Employee Details");
           e.setVisible(true);
    }
}