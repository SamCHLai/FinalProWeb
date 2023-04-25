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

//確認帳號是否已經被註冊
@WebServlet("/CheckAccount")
public class CheckAccount extends HttpServlet {

	private Connection conn;
	private JSONObject json;   

    public CheckAccount() {
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
		System.out.println("checkAccount doPost");
		String sql = "SELECT * FROM member WHERE account=?";
		String acc = request.getParameter("account");
		System.out.println(acc);
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,acc);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				System.out.println("有查到相同帳號");
				response.setContentType("application/json");
				json = new JSONObject();
				json.put("isDup", "true");				
				response.getWriter().print(json);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
