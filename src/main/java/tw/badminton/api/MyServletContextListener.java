package tw.badminton.api;

import java.sql.*;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext sc = arg0.getServletContext();
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/eeit58group3", prop);
			System.out.println("OK");
			sc.setAttribute("conn", conn);
		}catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
