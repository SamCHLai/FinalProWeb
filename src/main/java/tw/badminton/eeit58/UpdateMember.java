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

import org.json.JSONArray;
import org.json.JSONObject;

import tw.badminton.api.Member;

//處理會員資料的更新(除了頭像)
@WebServlet("/UpdateMember")
public class UpdateMember extends HttpServlet {
	private Connection conn;
	private JSONObject json;
	private JSONArray jsonArray;
	private Member member;
	
    public UpdateMember() {
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
		String id = request.getParameter("id");
		String memberName = request.getParameter("memberName");
		String phoneNumber =request.getParameter("phoneNumber");
		String gender =request.getParameter("gender");
		String sql = "UPDATE member SET  memberName= ?,phoneNumber = ?,gender =? WHERE id = ?";
		String sql2 = "SELECT * FROM member WHERE id=?";
		System.out.println(memberName);
		System.out.println(phoneNumber);
		System.out.println(gender);
		member = (Member) (request.getSession().getAttribute("member"));
		System.out.println(request.getSession().getAttribute("member"));
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memberName);
			pstmt.setString(2,phoneNumber);
			pstmt.setString(3,gender);
			pstmt.setString(4,id);			
			int affectRow = pstmt.executeUpdate();
			if(affectRow > 0) {
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1,id);
				ResultSet rs = pstmt2.executeQuery();
				if(rs.next()) {
					member.setMemberName(rs.getString("memberName"));
					member.setPhoneNumber(rs.getString("phoneNumber"));
					member.setGender(rs.getString("gender"));
					member.setBirthday(rs.getString("birthday"));
					System.out.println("if(rs.next()內)");
				}	
			}

			
			
			jsonArray = new JSONArray();
			json = new JSONObject();
			jsonArray.put("success");
			json.put("id", member.getID());
			json.put("account",member.getAccount());
			json.put("Icon", member.getIcon());
			json.put("memberName", member.getMemberName());
			json.put("phoneNumber", member.getPhoneNumber());
			json.put("gender",member.getGender());
			json.put("birthday",member.getBirthday());
			jsonArray.put(json);
			System.out.println(jsonArray);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
