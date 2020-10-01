

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewServ")

public class ViewServ extends HttpServlet 
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
		catch (Exception e) 
		{
				e.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		try
		{
			PrintWriter pw=response.getWriter();
			pw.println("Names Are:");
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("Select * from register");
			
			pw.println("<table>");
			pw.println("<tr>");
			
			pw.println("<th>Name</th>");
			pw.println("<th>Address</th>");
			pw.println("<th>Email</th>");
			pw.println("<th>Login</th>");
			pw.println("<th>Password</th>");
			
			pw.println("</tr>");
			
			
			while(rs.next())
			{
				pw.println("<tr>");
				
				pw.println("<td>"+rs.getString(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
				pw.println("<td>"+rs.getString(3)+"</td>");
				pw.println("<td>"+rs.getString(4)+"</td>");
				pw.println("<td>"+rs.getString(5)+"</td>");
			}
			
			pw.println("</table>");
		}
		
		catch(Exception ae)
		{
			ae.printStackTrace();
		}
	}

}
