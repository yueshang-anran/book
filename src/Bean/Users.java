package Bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utils.JDBCUtils;
import Utils.MD5Utils;

/**
 * users表相关函数
 */
public class Users {

	private String user_id;
	private String user_name;
	private String user_pass;
	private String user_depart;
	private String user_class;
	
	public Users() {
		
	}
	
	public Users(String user_id, String user_name, String user_pass, String user_depart, String user_class) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_pass = user_pass;
		this.user_depart = user_depart;
		this.user_class = user_class;
	}
	
	// 验证用户，0验证失败，1为学生用户，2为管理员
	public static int checkUser(String id, String password) {
		
		String sql = "select user_pass from users where user_id=?";
		try (Connection conn = JDBCUtils.getConnection();
				PreparedStatement preps = conn.prepareStatement(sql);){
			
			preps.setString(1, id);
			ResultSet rs = preps.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(password)) {// 验证通过
					if(id.equals("admin")) {// 是管理员用户
						return 2;
					} else {// 是学生用户
						return 1;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// 根据用户id查询用户信息
	public static Users getUserById(String id) {
		
		String sql = "select * from users where user_id=?";
		try (Connection conn = JDBCUtils.getConnection();
				PreparedStatement preps = conn.prepareStatement(sql);){
			
			preps.setString(1, id);
			ResultSet rs = preps.executeQuery();
			if(rs.next()) {
				return new Users(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	// 新增用户
	public static boolean addUser(String id, String name, String pass, String depart, String clazz) {
		
		String sql = "insert into users (user_id, user_name, user_pass, user_depart, user_class) "
				+ "values (?, ?, ?, ?, ?)";
		try (Connection conn = JDBCUtils.getConnection();
				PreparedStatement preps = conn.prepareStatement(sql);){
			
			preps.setString(1, id);
			preps.setString(2, name);
			preps.setString(3, MD5Utils.md5(pass));
			preps.setString(4, depart);
			preps.setString(5, clazz);
			if(!preps.execute()) {
				//JDBCUtils.release(conn, preps);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pass() {
		return user_pass;
	}

	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}

	public String getUser_depart() {
		return user_depart;
	}

	public void setUser_depart(String user_depart) {
		this.user_depart = user_depart;
	}

	public String getUser_class() {
		return user_class;
	}

	public void setUser_class(String user_class) {
		this.user_class = user_class;
	}
	
}
