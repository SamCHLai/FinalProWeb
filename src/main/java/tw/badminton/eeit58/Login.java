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

import tw.badminton.api.*;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private Member member;
	private Connection conn;
	private JSONObject json;
	
    public Login() {
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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login doGet");
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login doPost");
		request.setCharacterEncoding("UTF-8");
		String sql = "SELECT * FROM member WHERE account=?";
		String acc = request.getParameter("account");
		String pswd = request.getParameter("password");
		System.out.println(acc);
		System.out.println(pswd);
//		if(request.getSession().isNew()) {
//			System.out.println("Session is new");
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,acc);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					System.out.println("有找到帳號");
					System.out.println(rs.getString("password"));
//					BCrypt.checkpw(pswd, rs.getString("password"))
//					pswd.equals(rs.getString("password"))
//					目前測試資料的密碼是明碼，先用下面的方式確定密碼，改為暗碼後，再用上面的程式碼
					if(BCrypt.checkpw(pswd, rs.getString("password"))){
						System.out.println("密碼對了");
						Member member = new Member();
						member.setID(rs.getString("id"));
						member.setAccount(rs.getString("account"));
						member.setIcon(rs.getString("Icon"));
						member.setMemberName(rs.getString("memberName"));
						member.setPhoneNumber(rs.getString("phoneNumber"));
						member.setGender(rs.getString("gender"));
						member.setBirthday(rs.getString("birthday"));
						request.getSession().setAttribute("member", member);
//						Member member1 = (Member) (request.getSession().getAttribute("member"));
						request.getSession().setMaxInactiveInterval(1*24*60*60);
						//1天后自動失效
						
						response.addHeader("isLogin", "1");
//						System.out.println(response.getHeader("isLogin"));
						response.setCharacterEncoding("UTF-8");
						response.setContentType("application/json");
//						response.sendRedirect("index.html");
						json = new JSONObject();
						json.put("id", member.getID());
						json.put("account",member.getAccount());
						json.put("Icon", member.getIcon());
						json.put("memberName", member.getMemberName());
						json.put("phoneNumber", member.getPhoneNumber());
						json.put("gender",member.getGender());
						json.put("birthday",member.getBirthday());
						response.getWriter().print(json);
						System.out.println("login 登入驗證完畢，並送出json");
						//{"id":1,"Icon":"/url"}
						
					}else{
						System.out.println("密碼錯了");
						request.getSession().invalidate();
						response.addHeader("isLogin", "0");

					}
				}else {
					System.out.println("沒有找到帳號");
					request.getSession().invalidate();
					response.addHeader("isLogin", "0");
				}
				
			}catch(Exception e) {
				System.out.println("連線失敗");
				response.sendError(	HttpServletResponse.SC_BAD_REQUEST);
			}
//		}else {	
//			System.out.println("Session is old");
//			response.sendRedirect("index.html");
//		}
	}

}
