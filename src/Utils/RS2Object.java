package Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ResultSet2Object[][]转换工具类
 */
public class RS2Object {

	public static Object[][] rs2Object(ResultSet rs) {// 书籍列表
		List<Object> list = new ArrayList<>();

		try {
			while(rs.next()) {
				List<Object> obj = new ArrayList<>();
				obj.add(rs.getString(1));
				obj.add(rs.getString(2));
				obj.add(rs.getString(3));
				obj.add(rs.getString(4));
				obj.add(rs.getInt(5));
				list.add(obj);
			}
			int len = list.size();
			Object[][] result = new Object[len][5];
			for(int i = 0; i < len; i++) {
				result[i][0] = ((List) list.get(i)).get(0);
				result[i][1] = ((List) list.get(i)).get(1);
				result[i][2] = ((List) list.get(i)).get(2);
				result[i][3] = ((List) list.get(i)).get(3);
				if((int) ((List) list.get(i)).get(4) == 1)
					result[i][4] = "在架可借";
				else
					result[i][4] = "已经借出";
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Object[][] rs2Object2(ResultSet rs) {// 已借列表
		List<Object> list = new ArrayList<>();

		try {
			while(rs.next()) {
				List<Object> obj = new ArrayList<>();
				obj.add(rs.getInt(1));
				obj.add(rs.getInt(2));
				obj.add(rs.getString(3));
				list.add(obj);
			}
			int len = list.size();
			Object[][] result = new Object[len][3];
			for(int i = 0; i < len; i++) {
				result[i][0] = ((List) list.get(i)).get(0);
				result[i][1] = ((List) list.get(i)).get(1);
				result[i][2] = ((List) list.get(i)).get(2);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static Object[][] rs2Object3(ResultSet rs) {// 所有借书记录
		List<Object> list = new ArrayList<>();

		try {
			while(rs.next()) {
				List<Object> obj = new ArrayList<>();
				obj.add(rs.getInt(1));
				obj.add(rs.getString(2));
				obj.add(rs.getInt(3));
				obj.add(rs.getString(4));
				obj.add(rs.getString(5));
				list.add(obj);
			}
			int len = list.size();
			Object[][] result = new Object[len][5];
			for(int i = 0; i < len; i++) {
				result[i][0] = ((List) list.get(i)).get(0);
				result[i][1] = ((List) list.get(i)).get(1);
				result[i][2] = ((List) list.get(i)).get(2);
				result[i][3] = ((List) list.get(i)).get(3);
				result[i][4] = ((List) list.get(i)).get(4);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
