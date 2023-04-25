package tw.badminton.eeit58;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import tw.badminton.api.BCrypt;
import tw.badminton.api.Member;

//處理會員頭像上傳的功能，檔案存在FinalProWeb/memberIcon，要去server中加上
//C:/Users/USER/eclipse-workspace/Servers/Tomcat v8.5 Server at localhost-config/server.xml <host></host>之間加上
//<Context docBase="C:/Users/USER/eclipse-workspace/FinalProWeb/memberIcon" path="/FinalProWeb/memberIcon"/>
@MultipartConfig(location = "C:\\Users\\USER\\eclipse-workspace\\FinalProWeb\\memberIcon")
@WebServlet("/MemberIcon")
public class MemberIcon extends HttpServlet {
	private Connection conn;
	private Member member;
	private JSONObject json;
	
    public MemberIcon() {
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
		Part icon = request.getPart("Icon");
		String id =request.getParameter("id");
		String filename = String.format("%s.jpg",id);
		String path = "./memberIcon/";
		String sql = "UPDATE member SET Icon = ? WHERE id = ?";		
		long fileSize = icon.getSize();//得到上傳檔案的大小
		String filetype = icon.getContentType();
		
		if(filetype.startsWith("image/")) {
			if (fileSize < 2 * 1024 * 1024) {//如果檔案小於2MB
				System.out.println(filetype);
				icon.write(filename);//Part 的write()將上傳檔案以指定的檔名寫入
//				response.sendRedirect("./member.html");
				try {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,path + filename);
					pstmt.setString(2, id);
					pstmt.executeUpdate();
					member = (Member) (request.getSession().getAttribute("member"));
					member.setIcon(path + filename);
					json = new JSONObject();
					json.put("id", member.getID());
					json.put("account",member.getAccount());
					json.put("Icon", member.getIcon());
					json.put("memberName", member.getMemberName());
					json.put("phoneNumber", member.getPhoneNumber());
					json.put("gender",member.getGender());
					json.put("birthday",member.getBirthday());
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/json");
					response.getWriter().print(json);

					
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				
				
			}else {
				response.sendRedirect("./member.html");
			}
		}else {
			response.sendRedirect("./member.html");
		}

		
	}

}
