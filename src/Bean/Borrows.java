package Bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import Utils.JDBCUtils;
import Utils.RS2Object;

/**
 * borrows表相关函数
 */
public class Borrows {

	// 完成借书操作
	public static boolean borrowBook(int book_id, String user_id) {

		// 获得当前时间
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
		int d = cal.get(Calendar.DATE);
		int h = cal.get(Calendar.HOUR_OF_DAY);
		int mi = cal.get(Calendar.MINUTE);
		int s = cal.get(Calendar.SECOND);
		String date = "" + y + "-" + m + "-" + d + "-" + h + "-" + mi + "-" + s;// 拼装时间
		String sql = "insert into borrows (borrow_id, user_id, book_id, borrow_date, return_date) VALUES (null, ?, ?, ?, '')";// 执行插入操作
		String sql2 = "update books set book_status=0 where book_id=?";// 更新书籍状态
		try (Connection conn = JDBCUtils.getConnection();
				PreparedStatement preps = conn.prepareStatement(sql);
					PreparedStatement preps2 = conn.prepareStatement(sql2);) {
			
			preps.setString(1, user_id);// 设置对应的?的值
			preps.setInt(2, book_id);
			preps.setString(3, date);
			//System.out.println(preps.toString());// 打印sql语句
			preps.execute();// 执行插入
			
			preps2.setInt(1, book_id);
			preps2.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// 完成还书操作
	public static boolean returnBook(int book_id, String user_id) {
		
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
		int d = cal.get(Calendar.DATE);
		int h = cal.get(Calendar.HOUR_OF_DAY);
		int mi = cal.get(Calendar.MINUTE);
		int s = cal.get(Calendar.SECOND);
		String date = "" + y + "-" + m + "-" + d + "-" + h + "-" + mi + "-" + s;
		String sql = "update borrows set return_date=? where book_id=? and user_id=? and return_date=''";// 执行更新操作
		String sql2 = "update books set book_status=1 where book_id=?";// 更新书籍状态
		try (Connection conn = JDBCUtils.getConnection();
				PreparedStatement preps = conn.prepareStatement(sql);
					PreparedStatement preps2 = conn.prepareStatement(sql2);){
			
			
			preps.setString(1, date);
			preps.setInt(2, book_id);
			preps.setString(3, user_id);
			//System.out.println(preps.toString());
			preps.executeUpdate();
			
			preps2.setInt(1, book_id);
			preps2.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// 查找当前用户所有未还图书
	public static Object[][] searchUserAllUnreturnBook(String user_id) {
		
		String sql = "select borrow_id, book_id, borrow_date from borrows where user_id=? and return_date=''";
		try(Connection conn = JDBCUtils.getConnection();
				PreparedStatement preps = conn.prepareStatement(sql);) {
			
			preps.setString(1, user_id);
			ResultSet rs = preps.executeQuery();
			return RS2Object.rs2Object2(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	// 查找所有借书记录
	public static Object[][] searchAllRecords() {
		
		String sql = "select * from borrows";
		try(Connection conn = JDBCUtils.getConnection();
				PreparedStatement preps = conn.prepareStatement(sql);) {
			
			ResultSet rs = preps.executeQuery();
			return RS2Object.rs2Object3(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	// 根据用户id查找指定用户的借书记录
	public static Object[][] searchRecordsByUserId(String id) {
		
		String sql = "select * from borrows where user_id like ?";
		try(Connection conn = JDBCUtils.getConnection();
				PreparedStatement preps = conn.prepareStatement(sql);) {
			
			preps.setString(1, "%" + id + "%");
			ResultSet rs = preps.executeQuery();
			return RS2Object.rs2Object3(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	// 根据借阅日期查询借书记录
	public static Object[][] searchRecordsByBorrowDate(String date) {
		
		String sql = "select * from borrows where borrow_date like ?";
		try(Connection conn = JDBCUtils.getConnection();
				PreparedStatement preps = conn.prepareStatement(sql);) {
			
			preps.setString(1, "%" + date + "%");
			ResultSet rs = preps.executeQuery();
			return RS2Object.rs2Object3(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	// 根据还书日期查询记录
	public static Object[][] searchRecordsByReturnDate(String date) {

		String sql = "select * from borrows where return_date like ?";
		try(Connection conn = JDBCUtils.getConnection();
				PreparedStatement preps = conn.prepareStatement(sql);) {

			preps.setString(1, "%" + date + "%");
			ResultSet rs = preps.executeQuery();
			return RS2Object.rs2Object3(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
