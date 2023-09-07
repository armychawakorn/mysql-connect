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
	public MemberDB() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	public MemberDB FindAll() throws SQLException {
		List<List<String>> returnData = new ArrayList<List<String>>();
		con = DriverManager.getConnection(dbURL, "root", "");
		PreparedStatement pStatement = con.prepareStatement("SELECT * FROM member");
		ResultSet resultSet = pStatement.executeQuery();
		while (resultSet.next()) {
			List<String> sqlData = new ArrayList<String>();
			for(int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
				sqlData.add(resultSet.getString(i));
			}
			returnData.add(sqlData);
		}
		DatabaseData = returnData;
		return this;
	}
	public MemberDB FindOne(String username) throws SQLException {
		List<List<String>> returnData = new ArrayList<List<String>>();
		con = DriverManager.getConnection(dbURL, "root", "");
		PreparedStatement pStatement = con.prepareStatement("SELECT * FROM member WHERE username = ?");
		pStatement.setString(1, username);
		ResultSet resultSet = pStatement.executeQuery();
		while (resultSet.next()) {
			List<String> sqlData = new ArrayList<String>();
			for(int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
				sqlData.add(resultSet.getString(i));
			}
			returnData.add(sqlData);
		}
		DatabaseData = returnData;
		return this;
	}
	public void DisplayData() throws SQLException {
		if(this.DatabaseData.toArray().length <= 0) {
			System.out.println("Empty");
			return;
		}
		for(List<String> data : this.DatabaseData) {
			System.out.println(data);
		}
		this.con.close();
	}
}
