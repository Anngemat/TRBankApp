package SQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MicrosoftSQLConnection {

	public static Connection connect() {

		try {

			String DATABASE_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String DATABASE_URL = "jdbc:sqlserver://localhost:1433";
			String DATABASE_NAME = "TRBANK";
			String USERNAME = "Admin";
			String PASSWORD = "Hasan1350005";
			String MAX_POOL = "200";
			String ENCRYPT = "false";
			String TRUST_SERVER_CERT = "false";
			Properties properties = new Properties();
			properties.setProperty("user", USERNAME);
			properties.setProperty("password", PASSWORD);
			properties.setProperty("MaxPooledStatements", MAX_POOL);
			properties.setProperty("encrypt", ENCRYPT);
			properties.setProperty("database", DATABASE_NAME);
			properties.setProperty("trustServerCertificate", TRUST_SERVER_CERT);
			Class.forName(DATABASE_DRIVER);
			Connection conn = DriverManager.getConnection(DATABASE_URL, properties);
			System.out.println("Connected MSSQL!");
			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("EXCEPTION: " + e.getMessage());
			return null;
		}

	}
	public static void main(String[] args) {
		MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
		sql.connect();
	}
}
