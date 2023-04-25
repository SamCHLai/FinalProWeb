package tw.badminton.eeit58;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//取得login.html的網頁
@WebServlet("/getLogin")
public class getLogin extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("getLogin doGet");
			
		response.setContentType("text/html;charset=utf-8");


		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.html");
	    rd.forward(request, response);
//			try {
//				InputStream in = getServletContext().getResourceAsStream("/WEB-INF/login.html");
////				OutputStream out = response.getOutputStream();
//				PrintWriter out = response.getWriter();
//				response.setContentType("text/html");
//				out.print(in.readAllBytes());
////				out.write(in.readAllBytes());
//				out.flush();
//				out.close();
//				in.close();
//			}catch(Exception e) {
//				throw e;
//			}

	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("getLogin doPost");
		doGet(request, response);
	}

}
