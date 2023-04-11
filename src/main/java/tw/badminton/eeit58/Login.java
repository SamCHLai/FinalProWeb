package tw.badminton.eeit58;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.badminton.api.*;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private Member member;
	//Get 方法送請求，導回首頁
	//Post方法
	/*
	 
	 設定編碼方式為UTF-8
	 做出資料庫connection 物件
	 設定變數 = 前端送來的Parameter
	 
	 if(判斷是否為新連線)
	 	try
	 		if(查詢搜尋的帳號是否存在)
	 			從資料庫要該筆資料的密碼	 			
	 			if(密碼一致)
	 				產生member 物件
	 				將會員資料(除了密碼外)，設定為member物件的field
	 				將member 設為session的Attribute
	 				設定session的有效期限為3天不互動，自動失效
 				else(密碼不一致)
 					session失效
 					轉回首頁
	 				
	 		else(資料庫中沒有該帳號)
	 			session失效
 				轉回首頁
	 			
	 	catch
	 		告知400錯誤
	 	
	 else
	 	導回首頁
	 
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./index.html");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("doPost");
		request.setCharacterEncoding("UTF-8");
		Connection conn = (Connection) this.getServletContext().getAttribute("conn");
		String sql = "SELECT * FROM member WHERE account=?";
		String acc = request.getParameter("account");
		String pswd = request.getParameter("password");
		if(request.getSession().isNew()) {
			System.out.println("Session is new");
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,acc);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
//					System.out.println("有找到帳號");
					System.out.println(rs.getString("password"));
//					BCrypt.checkpw(pswd, rs.getString("password"))
//					目前測試資料的密碼是明碼，先用下面的方式確定密碼，改為暗碼後，再用上面的程式碼
					if(pswd.equals(rs.getString("password"))){
						System.out.println("密碼對了");
						Member member = new Member();
						member.setID(rs.getString("ID"));
						member.setAccount(rs.getString("account"));
						member.setMemberName(rs.getString("memberName"));
						member.setPhoneNumber(rs.getString("phoneNumber"));
						member.setGender(rs.getString("gender"));
						member.setBirthday(rs.getString("birthday"));
						request.getSession().setAttribute("member", member);
						request.getSession().setMaxInactiveInterval(3*24*60*60);
						//3天后自動失效
//						response.sendRedirect("member.html");
						response.sendRedirect("index.html");
					}else{
//						System.out.println("密碼錯了");
						request.getSession().invalidate();
//						response.sendRedirect("login.html");
						response.sendRedirect("index.html");
						
						
					}
				}else {
//					System.out.println("沒有找到帳號");
					request.getSession().invalidate();
					response.sendRedirect("index.html");
				}
			}catch(Exception e) {
//				System.out.println("連線失敗");
				response.sendError(	HttpServletResponse.SC_BAD_REQUEST);
			}
		}else {	
//			System.out.println("Session is old");
//			response.sendRedirect("text.html");
			response.sendRedirect("index.html");
		}


		
	}

}
