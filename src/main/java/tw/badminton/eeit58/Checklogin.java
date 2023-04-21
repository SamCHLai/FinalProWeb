package tw.badminton.eeit58;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import tw.badminton.api.Member;


@WebFilter(urlPatterns={"/getLogin"})
public class Checklogin extends HttpFilter implements Filter {
	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Checklogin doFilter");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse =(HttpServletResponse) response;
		
//		String loginStatus = httpRequest.getHeader("isLogin");
//		String path = httpRequest.getServletPath();
//		System.out.println(path);		
//		System.out.println(loginStatus);
		
		if(httpRequest.getSession().getAttribute("member") == null) {
			System.out.println("member ==null");
			chain.doFilter(request, response);
		}else {
			System.out.println("member !=null");
			httpResponse.sendRedirect("./index.html");
			
		}
	}
//		
//		if((memberCheck.getID() == null)) {
//			chain.doFilter(httpRequest, httpResponse);
//			try {
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1,acc);
//				ResultSet rs = pstmt.executeQuery();
//				if(rs.next()){
//					System.out.println("有找到帳號");
//					System.out.println(rs.getString("password"));
////					BCrypt.checkpw(pswd, rs.getString("password"))
////					目前測試資料的密碼是明碼，先用下面的方式確定密碼，改為暗碼後，再用上面的程式碼
//					if(pswd.equals(rs.getString("password"))){
//						System.out.println("密碼對了");
//						Member member = new Member();
//						member.setID(rs.getString("ID"));
//						member.setAccount(rs.getString("account"));
//						member.setMemberName(rs.getString("memberName"));
//						member.setPhoneNumber(rs.getString("phoneNumber"));
//						member.setGender(rs.getString("gender"));
//						member.setBirthday(rs.getString("birthday"));
//						request.getSession().setAttribute("member", member);
//						Member member1 = (Member) (request.getSession().getAttribute("member"));
//						System.out.println(member1.getID());
//						request.getSession().setMaxInactiveInterval(1*24*60*60);
//						//1天后自動失效
////						response.sendRedirect("member.html");
//						response.sendRedirect("index.html");
//					}else{
//						System.out.println("密碼錯了");
//						request.getSession().invalidate();
////						response.sendRedirect("login.html");
//						response.sendRedirect("index.html");
//						
//						
//					}
//				}else {
//					System.out.println("沒有找到帳號");
//					request.getSession().invalidate();
//					response.sendRedirect("index.html");
//				}
//				
//			}catch(Exception e) {
//				System.out.println("連線失敗");
//				response.sendError(	HttpServletResponse.SC_BAD_REQUEST);
//			}
//		}else {	
//			System.out.println("Session is old");
////			response.sendRedirect("text.html");
//			response.sendRedirect("index.html");
//		}
//		
//		
//		
//		
//		chain.doFilter(request, response);
//	}
//
//


}
