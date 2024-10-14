package daos;

import java.sql.*;

public class MyConnection {
	Connection conn = null;
	String path = null;
	
	public MyConnection(String path) {
		this.path = path;
	}
	
	public void open() {
		if (conn != null) {
			System.out.printf("Ya está abierto!.\n");
		} else {
			try {
				conn = DriverManager.getConnection(path);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close() {
		if (conn == null) {
			System.out.printf("Ya está cerrado!.\n");
		} else {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	public String getPath() {
		return this.path;
	}
	
	public Connection getConnection() {
		return this.conn;
	}
}