package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DBConnection.ConnectJDBC;

public class actBean {
	private String username;
	private String password;
	
	public actBean(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	private String getPassword() {
		return password;
	}
	private void setPassword(String password) {
		this.password = password;
	}
	private String getUsername() {
		return username;
	}
	private void setUsername(String username) {
		this.username = username;
	}
	
	public boolean validate() {
		String sql = "select id from logins where uname = ? and passwd = ?";
		try {
			Connection conn = new ConnectJDBC().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {	
				return true;
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
