

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServ")

public class LoginServ extends HttpServlet 
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
		String Login=request.getParameter("login");
		String Password=request.getParameter("password");
		
		PreparedStatement pst;
		try 
		{
			pst = con.prepareStatement("Select * from register where Login=? AND Password=?");
		
		pst.setString(1, Login);
		pst.setString(2, Password);
		
		ResultSet rs=pst.executeQuery();
		
		if(rs.next()) 
		{
			RequestDispatcher rd=request.getRequestDispatcher("Success.html");
			rd.forward(request, response);
		}
		
		else 
		{
			RequestDispatcher rd=request.getRequestDispatcher("Fail.html");
			rd.forward(request, response);
		}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
	}
}
