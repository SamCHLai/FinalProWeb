package tw.badminton.eeit58;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import tw.badminton.api.BCrypt;

//修改密碼用的servlet
@WebServlet("/ChangePwd")
public class ChangePwd extends HttpServlet {
	private Connection conn;
	private JSONObject json;
	
	
	//初始化時先做好connection
    public ChangePwd() {
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
		System.out.println("ChangePwd doPost");
		String sql = "SELECT password FROM member WHERE id=?";
		String sql2 = "UPDATE member SET password = ? WHERE id = ?";
		String id = request.getParameter("id");
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		System.out.println("id:" + id);
		System.out.println("oldPwd" +oldPwd);
		System.out.println("newPwd" +newPwd);
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			ResultSet rs = pstmt.executeQuery();
			response.setContentType("application/json");
			json = new JSONObject();
			json.put("PwdChangeStatus","success");
			if(rs.next()) {
				if(BCrypt.checkpw(oldPwd, rs.getString("password"))) {
					System.out.println("舊密碼正確");
					PreparedStatement pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setString(1, BCrypt.hashpw(newPwd, BCrypt.gensalt()));
					pstmt2.setString(2, id);
					pstmt2.executeUpdate();				
					json.put("PwdChangeStatus","success");
					response.getWriter().print(json);
				}else {
					json.put("PwdChangeStatus","failed");
					System.out.println("舊密碼錯誤");
					response.getWriter().print(json);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			json.put("PwdChangeStatus","failed");
			response.getWriter().print(json);
		}
	}

}
