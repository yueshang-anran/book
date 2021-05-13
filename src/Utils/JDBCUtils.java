package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * 数据库工具
 */
public class JDBCUtils {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static ResourceBundle bundle;
	
	/**
	 * 静态代码块加载配置文件信息
	 */
	static{
		bundle = ResourceBundle.getBundle("db");
		driver = bundle.getString("jdbc.driverClass");
		url = bundle.getString("jdbc.jdbcUrl");
		username = bundle.getString("jdbc.username");
		password = bundle.getString("jdbc.password");
	}

	/**
	 * 获取数据库连接方法
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
