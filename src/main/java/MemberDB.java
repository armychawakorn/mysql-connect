import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDB {
	private Connection con;
	private List<List<String>> DatabaseData = null;
	private String dbURL = "jdbc:mysql://localhost/blueshop?characterEncoding=utf-8";
	public MemberDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.err.println("Error loading driver: " + e);
		}
	}
	public MemberDB FindAll() {
		List<List<String>> sql = new ArrayList<List<String>>();
		try {
			con = DriverManager.getConnection(dbURL, "root", "");
			PreparedStatement pStatement = con.prepareStatement("SELECT * FROM member");
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				List<String> sqlData = new ArrayList<String>();
				for(int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
					sqlData.add(resultSet.getString(i));
				}
				sql.add(sqlData);
			}
		}catch (SQLException e) {
			System.err.println("Error database connection: " + e);
		}
		DatabaseData = sql;
		return this;
	}
	public MemberDB FindOne(String username) {
		List<List<String>> sql = new ArrayList<List<String>>();
		try {
			con = DriverManager.getConnection(dbURL, "root", "");
			PreparedStatement pStatement = con.prepareStatement("SELECT * FROM member WHERE username = ?");
			pStatement.setString(1, username);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				List<String> sqlData = new ArrayList<String>();
				for(int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
					sqlData.add(resultSet.getString(i));
				}
				sql.add(sqlData);
			}
		}catch (SQLException e) {
			System.err.println("Error database connection: " + e);
		}
		DatabaseData = sql;
		return this;
	}
	public void DisplayData() {
		if(this.DatabaseData.toArray().length <= 0) {
			System.out.println("Empty");
			return;
		}
		for(List<String> data : this.DatabaseData) {
			System.out.println(data);
		}
		try {
			this.con.close();
		}catch(SQLException e) {
			System.err.println("Error database connection: " + e);
		}
	}
}
