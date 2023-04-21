package tw.badminton.eeit58;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import tw.badminton.api.BCrypt;


@WebServlet("/Register")
public class Register extends HttpServlet {
	private Connection conn;
       

    public Register() {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties prop = new Properties();
			prop.put("user", "root");
			prop.put("password", "root");    		
			conn = DriverManager.getConnection("jdbc:mysql://localhost/eeit58group3", prop);
		}catch(Exception e) {
			e.printStackTrace();
		}
    }

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Register doPost");
		String account = request.getParameter("account");
		String memberName = request.getParameter("memberName");
		String password = BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt());
		System.out.println(request.getParameter("password"));
		System.out.println(password);
		request.setCharacterEncoding("UTF-8");
		String sql = "INSERT INTO member(account,password,memberName) VALUES (?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,account);
			pstmt.setString(2,password);
			pstmt.setString(3,memberName);
			pstmt.executeUpdate();
			System.out.println("Register 完畢");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
