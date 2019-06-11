package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	
	public static Connection getConnection(){
		Connection conn=null;
		String url="jdbc:mysql://localhost/jbitweb2?useUnicode=true&characterEncoding=utf8";

		String id="jbitweb2";
		String passwd="9090q";
		try{
			Class.forName("com.mysql.jdbc.Driver");			
			conn=DriverManager.getConnection(url,id,passwd);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn,Statement stmt,ResultSet rs){
		try{
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn,Statement stmt){
		try{
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
