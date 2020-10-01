

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RegisterServ")

public class RegisterServ extends HttpServlet 
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
		
		String Name=request.getParameter("name");
		String Address=request.getParameter("address");
		String Email=request.getParameter("email");
		String Login=request.getParameter("login");
		String Password=request.getParameter("password");
		
		try 
		{
			PreparedStatement pst = con.prepareStatement("insert into register values(?,?,?,?,?)");
			
			pst.setString(1, Name);
			pst.setString(2, Address);
			pst.setString(3,Email);
			pst.setString(4, Login);
			pst.setString(5, Password);
			
			int k= pst.executeUpdate();
			
			if(k>0)
			{
				System.out.println("Record Inserted successfully");
			}
			else
			{
				System.out.println("Failed to insert the Records");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
	}

}
