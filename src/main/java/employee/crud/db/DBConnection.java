package employee.crud.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	
	public static final String dbURL ="jdbc:mysql://localhost:3307/employee_db";
	public static final String dbUserName="root";
	public static final String dbPassword="root";
		


	public static Connection getConnection() {	
		
		System.out.println("Start getConnection");//Logger:Log4j
		
		try {
			//load mysql jdbc driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		 connection=DriverManager.getConnection(dbURL,dbUserName,dbPassword);
		
		 if(connection != null) {
			 System.out.println("You are connected :) ");
			 return connection; 
		 }else {
			 System.out.println(" Connection Issue :( ");
			 return null;
		 }
		 
		}catch(Exception e) {
			 System.out.println(" Exception in DB Connection  :( ==>"+e.getMessage());
			e.printStackTrace();
			return null;
		}
		
	}
	public static Connection connection=getConnection();
	
	public static void main(String[] args) {
		
		// Connection connection = getConnection();
		 System.out.println(DBConnection.connection);
	}
}
