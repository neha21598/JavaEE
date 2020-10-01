

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SecondServ")

public class SecondServ extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	private Connection con;
	
	public void init()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/mydb";
			con=DriverManager.getConnection(url,"root","root");
		}
		catch(Exception ae)
		{
			ae.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			String name=request.getParameter("name");
			int age=Integer.parseInt(request.getParameter("age"));
			
			PreparedStatement pst= con.prepareStatement("insert into student values(?,?)");
			
			pst.setString(1,name);
			pst.setInt(2,age);
			
			int k=pst.executeUpdate();
			
			if(k>0)
			{
				System.out.println("Record Inserted");
			}
			else
			{
				System.out.println("There is some problem in insertion");
			}
		}
		catch(Exception ae)
		{
			ae.printStackTrace();
		}
	}

}
