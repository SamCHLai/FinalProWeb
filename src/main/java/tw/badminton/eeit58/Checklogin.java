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

//檢查是否為登入的狀況，如果不適登入狀況，回去首頁，如果是登入的狀況，往下一個servlet
@WebFilter(urlPatterns={"/getLogin","/member.html","/memberIcon/"})
public class Checklogin extends HttpFilter implements Filter {
	private Member member;
	
	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Checklogin doFilter");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse =(HttpServletResponse) response;
		String httprequestedUri = httpRequest.getRequestURI();
		System.out.println(httprequestedUri);
//		會顯示瀏覽器中的url列中的資料，如下		
//		/FinalProWeb/member.html
//		如果是超連結過去的，也是會顯示超連結的路徑
		String referer = httpRequest.getHeader("Referer");
		//會顯示為從哪一個網頁的超連結過去，例如從首頁的超連結來的話，會印出http://localhost:8080/FinalProWeb/index.html
		//直接用在瀏覽器列打url，是null
		
		member =(Member)httpRequest.getSession().getAttribute("member");
//		String loginStatus = httpRequest.getHeader("isLogin");
//		String path = httpRequest.getServletPath();
//		System.out.println(path);		
//		System.out.println(loginStatus);
		System.out.println(referer);
		
		
		
		if(referer == null) {
			
		}
		
		
		
		if(member == null) {
			System.out.println("member ==null");
			chain.doFilter(request, response);	
//			httpResponse.sendRedirect("./index.html");			

		}else {
			System.out.println("member !=null");
			httpResponse.sendRedirect("./index.html");
//			if(httprequestedUri.contains("memberIcon")) {
//				httpResponse.sendRedirect("./index.html");
//			}else {
//				chain.doFilter(request, response);	
//			}
		
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
