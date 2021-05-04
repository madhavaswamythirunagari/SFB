import java.sql.*;
import java.util.*;

class DBAcc
 {
  public static void main(String args[]) throws Exception
   {
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    Connection conn=DriverManager.getConnection("jdbc:odbc:aec","Vinayaka","Vinayaka");
    System.out.println("Connection Established");

	String Sq="select *from Table1";

      Statement stm=conn.createStatement();
      ResultSet rs = stm.executeQuery(Sq);

      while(rs.next())
       {
        int sno= rs.getInt("slno");
        //String htno=rs.getString("htno");
        //float ans=rs.getInt("ans");

        System.out.println(sno);

        }
           //System.out.println(count+"row(s) inserted");

           rs.close();
           stm.close();
           conn.close();
    conn.close();
    }
  }

