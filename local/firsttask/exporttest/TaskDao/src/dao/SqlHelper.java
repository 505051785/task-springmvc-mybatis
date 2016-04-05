package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SqlHelper {

	/*
	 * private static DataSource ds;
	 * 
	 * static{ try { //1.获取命名上下文接口 Context context = new InitialContext();
	 * //2.根据名称查询服务器上的DataSource 代表前缀: java:/comp/env ds =
	 * (DataSource)context.lookup("java:/comp/env/jdbc/TestDB"); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 *//**
	 * 获取数据库连接
	 * 
	 * @return Connection
	 * */
	/*
	 * public static Connection getConnection(){ try { return
	 * ds.getConnection(); } catch (Exception e) { e.printStackTrace(); return
	 * null; } }
	 */

	public boolean excuteInsert(String strSql, List<Object> para) {
		try {
			PreparedStatement pstmt = null;
			Connection conn = getConn();
			pstmt = conn.prepareStatement(strSql);
			if (para != null) {
				int index = 1;
				for (Object item : para) {
					String type = item.getClass().getName();
					if (type.equals("java.lang.String")) {
						pstmt.setString(index, item.toString());
					} else if (type.equals("java.util.Date")) {
						java.util.Date utilDate = (java.util.Date) item;
						java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
						pstmt.setTimestamp(index, sqlDate);
					} else if (type.equals("int")) {
						pstmt.setInt(index, Integer.parseInt(item.toString()));
					}
					index++;
				}
			}
			boolean flag = pstmt.execute();
			return flag;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public ResultSet excuteSelect(Connection conn, PreparedStatement pstmt, String strSql, List<Object> para) {
		try {
			if (para != null) {
				int index = 1;
				for (Object item : para) {
					String type = item.getClass().getName();
					if (type.equals("java.lang.String")) {
						pstmt.setString(index, item.toString());
					} else if (type.equals("java.util.Date")) {
						java.util.Date utilDate = (java.util.Date) item;
						java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
						pstmt.setDate(index, sqlDate);
					} else if (type.equals("int")) {
						pstmt.setInt(index, Integer.parseInt(item.toString()));
					}
					index++;
				}
			}
			// boolean flag = pstmt.execute();
			ResultSet rs = pstmt.executeQuery();
			return rs;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public boolean excuteUpdate( String strSql, List<Object> para) {
		boolean rs = false;
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		try {
			pstmt = conn.prepareStatement(strSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (para != null) {
				int index = 1;
				for (Object item : para) {
					String type = item.getClass().getName();
					if (type.equals("java.lang.String")) {
						pstmt.setString(index, item.toString());
					} else if (type.equals("java.util.Date")) {
						java.util.Date utilDate = (java.util.Date) item;
						java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
						pstmt.setTimestamp(index, sqlDate);
					} else if (type.equals("int")) {
						pstmt.setInt(index, Integer.parseInt(item.toString()));
					}
					index++;
				}
			}
			int updateInt = pstmt.executeUpdate();
			if (updateInt > 0) {
				rs = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			rs = false;
		}
		return rs;
	}

	public static Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/task", "root", "");
			return conn;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
