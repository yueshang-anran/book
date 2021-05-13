package Bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utils.JDBCUtils;
import Utils.RS2Object;

/**
 * books表相关函数
 */
public class Books {
	
	// 添加书籍
	public static boolean addBook(String book_name, String book_publisher, String book_classify) {
		
		String sql = "insert into books (book_name, book_publisher, book_classify, book_status) "
				+ "values (?, ?, ?, 1)";
		try(Connection conn = JDBCUtils.getConnection();
				PreparedStatement preps = conn.prepareStatement(sql);) {
			
			preps.setString(1, book_name);
			preps.setString(2, book_publisher);
			preps.setString(3, book_classify);
			if(!preps.execute()) {
				//JDBCUtils.release(conn, preps);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 删除书籍
	public static boolean deleteBook(int id) {
		
		String sql = "delete from books where book_id=?";
		try(Connection conn = JDBCUtils.getConnection();
				PreparedStatement preps = conn.prepareStatement(sql);) {
			
			preps.setInt(1, id);
			if(!preps.execute()) {
				//JDBCUtils.release(conn, preps);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	// 搜索所有书籍
	public static Object[][] searchAllBook() {
		
		String sql = "select * from books";
		try(Connection conn = JDBCUtils.getConnection();
				PreparedStatement preps = conn.prepareStatement(sql);) {
			
			ResultSet rs = preps.executeQuery();
			return RS2Object.rs2Object(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// 按书号搜索书籍
	public static Object[][] searchBookById(String id) {
		
		int book_id = Integer.parseInt(id);
		String sql = "select * from books where book_id=?";
		try (Connection conn = JDBCUtils.getConnection();
				PreparedStatement preps = conn.prepareStatement(sql);){
			
			preps.setInt(1, book_id);
			ResultSet rs = preps.executeQuery();
			return RS2Object.rs2Object(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// 按书名搜索书籍
	public static Object[][] searchBookByName(String name) {
		
		String sql = "select * from books where book_name like ?";
		try (Connection conn = JDBCUtils.getConnection();
				PreparedStatement preps = conn.prepareStatement(sql);){
			
			preps.setString(1, "%" + name + "%");		//模糊查询带有name的结果
			ResultSet rs = preps.executeQuery();
			return RS2Object.rs2Object(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	// 按出版社搜索书籍
	public static Object[][] searchBookByPublisher(String publisher) {
		
		String sql = "select * from books where book_publisher like ?";
		try(Connection conn = JDBCUtils.getConnection();
				PreparedStatement preps = conn.prepareStatement(sql);) {
			
			preps.setString(1, "%" + publisher + "%");		//模糊查询带有publisher的结果
			ResultSet rs = preps.executeQuery();
			return RS2Object.rs2Object(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	// 按分类搜索书籍
	public static Object[][] searchBookByClassify(String classify) {
		
		String sql = "select * from books where book_classify like ?";
		try(Connection conn = JDBCUtils.getConnection();
				PreparedStatement preps = conn.prepareStatement(sql);) {
			
			preps.setString(1, "%" + classify + "%");		//模糊查询带有classify的结果
			ResultSet rs = preps.executeQuery();
			return RS2Object.rs2Object(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
