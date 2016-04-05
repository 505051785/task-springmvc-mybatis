package service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.SqlHelper;
import model.AddTaskVO;
import model.EditTaskVO;
import model.Task;
import model.TaskVO;
import model.TasksVO;
import model.User;

public class TaskService {

	public void AddTaks(Task task) {
		// String strSql =
		// "insert into task (title,description,sponsor,executor,starttime,endtime) values (?,?,?,?,?);";
		StringBuilder sbSql = new StringBuilder();
		sbSql.append("insert into task (");
		List<Object> para = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder();
		Field[] field = task.getClass().getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			String name = field[i].getName();
			String type = field[i].getGenericType().toString(); // 锟斤拷取锟斤拷锟皆碉拷锟斤拷锟斤拷

			if (type.equals("class java.lang.String")) { // 锟斤拷锟絫ype锟斤拷锟斤拷锟斤拷锟酵ｏ拷锟斤拷前锟斤拷锟�class "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
				Method m;
				try {
					name = name.substring(0, 1).toUpperCase() + name.substring(1);
					m = task.getClass().getMethod("get" + name);
					String value = (String) m.invoke(task); // 锟斤拷锟斤拷getter锟斤拷锟斤拷锟斤拷取锟斤拷锟斤拷值
					if (value == null) {
						continue;
					}
					sb.append(name);
					sb.append(",");
					para.add(value);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (type.equals("class java.util.Date")) {
				Method m;
				try {
					name = name.substring(0, 1).toUpperCase() + name.substring(1);
					m = task.getClass().getMethod("get" + name);
					Date value = (Date) m.invoke(task);
					if (value == null) {
						continue;
					}
					sb.append(name);
					sb.append(",");
					para.add(value);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		sb.setLength(sb.length() - 1);
		sbSql.append(sb.toString());
		sbSql.append(") values (");
		for (int i = 0; i < para.size(); i++) {
			sbSql.append("?");
			sbSql.append(",");
		}
		sbSql.setLength(sbSql.length() - 1);
		sbSql.append(");");
		/*
		 * para.add(task.getTitle()); para.add(task.getDescription());
		 * para.add(task.getSponsor()); para.add(task.getExecutor());
		 * para.add(task.getStarttime()); para.add(task.getEndtime());
		 */
		SqlHelper sqlHelper = new SqlHelper();
		sqlHelper.excuteInsert(sbSql.toString(), para);
	}
	
	public void ModifyTask(Task task) {
		// String strSql =
		// "insert into task (title,description,sponsor,executor,starttime,endtime) values (?,?,?,?,?);";
		StringBuilder sbSql = new StringBuilder();
		sbSql.append("update task set ");
		List<Object> para = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder();
		Field[] field = task.getClass().getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			String name = field[i].getName();
			String type = field[i].getGenericType().toString(); 

			if (type.equals("class java.lang.String")) { 
				Method m;
				try {
					name = name.substring(0, 1).toUpperCase() + name.substring(1);
					m = task.getClass().getMethod("get" + name);
					String value = (String) m.invoke(task);
					if (value == null) {
						continue;
					}
					sb.append(name);
					sb.append("=?");
					para.add(value);
					sb.append(",");
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (type.equals("class java.util.Date")) {
				Method m;
				try {
					name = name.substring(0, 1).toUpperCase() + name.substring(1);
					m = task.getClass().getMethod("get" + name);
					Date value = (Date) m.invoke(task);
					if (value == null) {
						continue;
					}
					sb.append(name);
					sb.append("=?");
					para.add(value);
					sb.append(",");
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		sb.setLength(sb.length() - 1);
		sbSql.append(sb.toString());
		sbSql.append(" where id="+task.getId());
		sbSql.append(";");
		SqlHelper sqlHelper = new SqlHelper();
		sqlHelper.excuteUpdate(sbSql.toString(),para);
	}

	public List<TaskVO> GetTasks(Task task) throws SQLException {
		List<TaskVO> result = new ArrayList<TaskVO>();
		List<Task> tasks = new ArrayList<Task>();
		String strsql = "select * from task;";
		SqlHelper sqlHelper = new SqlHelper();
		List<Object> para = null;
		Connection conn = SqlHelper.getConn();
		PreparedStatement pstmt = conn.prepareStatement(strsql);
		ResultSet rs = sqlHelper.excuteSelect(conn, pstmt, strsql, para);
		while (rs.next()) {
			Task item = new Task();
			item.setTitle(rs.getString("title"));
			item.setStarttime(rs.getTimestamp("starttime"));
			item.setDescription(rs.getString("description"));
			item.setEndtime(rs.getTimestamp("endtime"));
			item.setExecutor(rs.getString("executor"));
			item.setId(rs.getInt("id"));
			item.setSponsor(rs.getString("sponsor"));
			item.setExecutendtime(rs.getTimestamp("executendtime"));
			item.setExecutestatus(rs.getString("executestatus"));
			item.setType(rs.getString("type"));
			tasks.add(item);
		}
		strsql = "select * from user;";
		pstmt = conn.prepareStatement(strsql);
		rs = sqlHelper.excuteSelect(conn, pstmt, strsql, para);
		Map<String, User> usersMap = new HashMap<String, User>();
		while (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUserCode(rs.getString("usercode"));
			user.setUserName(rs.getString("username"));
			user.setAddTime(rs.getTimestamp("addtime"));
			user.setUpdateTime(rs.getTimestamp("updatetime"));
			usersMap.put(user.getUserCode(), user);
		}
		for (Task item : tasks) {
			TaskVO taskVO = new TaskVO();
			taskVO.setTask(item);
			taskVO.setUser(usersMap.get(item.getExecutor()));
			result.add(taskVO);
		}
		conn.close();
		rs.close();
		pstmt.close();
		return result;
	}
	
	public List<TaskVO> GetTasks(int perPageNum ,int intPageNo) throws SQLException {
		List<TaskVO> result = new ArrayList<TaskVO>();
		List<Task> tasks = new ArrayList<Task>();
		int startIndex=(intPageNo-1)*perPageNum;
		String strsql = "select * from task ORDER BY starttime limit "+startIndex+" ,"+perPageNum+" ;";
		SqlHelper sqlHelper = new SqlHelper();
		List<Object> para = null;
		Connection conn = SqlHelper.getConn();
		PreparedStatement pstmt = conn.prepareStatement(strsql);
		ResultSet rs = sqlHelper.excuteSelect(conn, pstmt, strsql, para);
		while (rs.next()) {
			Task item = new Task();
			item.setTitle(rs.getString("title"));
			item.setStarttime(rs.getTimestamp("starttime"));
			item.setDescription(rs.getString("description"));
			item.setEndtime(rs.getTimestamp("endtime"));
			item.setExecutor(rs.getString("executor"));
			item.setId(rs.getInt("id"));
			item.setSponsor(rs.getString("sponsor"));
			item.setExecutendtime(rs.getTimestamp("executendtime"));
			item.setExecutestatus(rs.getString("executestatus"));
			item.setType(rs.getString("type"));
			tasks.add(item);
		}
		strsql = "select * from user;";
		pstmt = conn.prepareStatement(strsql);
		rs = sqlHelper.excuteSelect(conn, pstmt, strsql, para);
		Map<String, User> usersMap = new HashMap<String, User>();
		while (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUserCode(rs.getString("usercode"));
			user.setUserName(rs.getString("username"));
			user.setAddTime(rs.getTimestamp("addtime"));
			user.setUpdateTime(rs.getTimestamp("updatetime"));
			usersMap.put(user.getUserCode(), user);
		}
		for (Task item : tasks) {
			TaskVO taskVO = new TaskVO();
			taskVO.setTask(item);
			taskVO.setUser(usersMap.get(item.getExecutor()));
			result.add(taskVO);
		}
		conn.close();
		rs.close();
		pstmt.close();
		return result;
	}
	
	public int GetTasksCount() throws SQLException {
		int result =0;
		String strsql = "select count(1) c from task;";
		SqlHelper sqlHelper = new SqlHelper();
		List<Object> para = null;
		Connection conn = SqlHelper.getConn();
		PreparedStatement pstmt = conn.prepareStatement(strsql);
		ResultSet rs = sqlHelper.excuteSelect(conn, pstmt, strsql, para);
		while (rs.next()) {
			result =rs.getInt("c");			
		}
		conn.close();
		rs.close();
		pstmt.close();
		return result;
	}

	public AddTaskVO DetailTask(String taskid) {
		AddTaskVO result = new AddTaskVO();
		String strsql = "select * from user;";
		SqlHelper sqlHelper = new SqlHelper();
		List<Object> para = null;
		Connection conn = SqlHelper.getConn();
		PreparedStatement pstmt = null;

		if (taskid != null) {
			String strsql_task = "select * from task where id=" + taskid;
			try {
				pstmt = conn.prepareStatement(strsql_task);

			} catch (SQLException e) {
				e.printStackTrace();
			}
			ResultSet rs = sqlHelper.excuteSelect(conn, pstmt, strsql, para);
			Task task = new Task();
			try {
				while (rs.next()) {
					task.setTitle(rs.getString("title"));
					task.setStarttime(rs.getTimestamp("starttime"));
					task.setDescription(rs.getString("description"));
					task.setEndtime(rs.getTimestamp("endtime"));
					task.setExecutor(rs.getString("executor"));
					task.setId(rs.getInt("id"));
					task.setSponsor(rs.getString("sponsor"));
					task.setExecutendtime(rs.getTimestamp("executendtime"));
				}
				result.setTask(task);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			pstmt = conn.prepareStatement(strsql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = sqlHelper.excuteSelect(conn, pstmt, strsql, para);
		List<User> users = new ArrayList<User>();
		try {
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserCode(rs.getString("usercode"));
				user.setUserName(rs.getString("username"));
				user.setAddTime(rs.getTimestamp("addtime"));
				user.setUpdateTime(rs.getTimestamp("updatetime"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (taskid != null) {
			List<User> userList = new ArrayList<User>();
			for (User item : users) {
				if (result.getTask().getExecutor().equals(item.getUserCode())) {
					userList.add(item);
				}
			}
			result.setUsers(userList);
		} else {
			result.setUsers(users);
		}
		return result;
	}
	
	public EditTaskVO EditTask(String taskid) {
		EditTaskVO result = new EditTaskVO();
		//String strsql = "select * from user;";
		SqlHelper sqlHelper = new SqlHelper();
		List<Object> para = null;
		Connection conn = SqlHelper.getConn();
		PreparedStatement pstmt = null;

		if (taskid != null) {
			String strsql_task = "select * from task where id=" + taskid;
			try {
				pstmt = conn.prepareStatement(strsql_task);

			} catch (SQLException e) {
				e.printStackTrace();
			}
			ResultSet rs = sqlHelper.excuteSelect(conn, pstmt, null, para);
			Task task = new Task();
			try {
				while (rs.next()) {
					task.setTitle(rs.getString("title"));
					task.setStarttime(rs.getTimestamp("starttime"));
					task.setDescription(rs.getString("description"));
					task.setEndtime(rs.getTimestamp("endtime"));
					task.setExecutor(rs.getString("executor"));
					task.setId(rs.getInt("id"));
					task.setSponsor(rs.getString("sponsor"));
					task.setExecutendtime(rs.getTimestamp("executendtime"));
					task.setExecutestatus(rs.getString("executestatus"));
					task.setType(rs.getString("type"));
				}
				result.setTask(task);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public List<User> getAllUser(){
		
		String strsql = "select * from user;";
		SqlHelper sqlHelper = new SqlHelper();
		List<Object> para = null;
		Connection conn = SqlHelper.getConn();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(strsql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ResultSet rs = sqlHelper.excuteSelect(conn, pstmt, null, para);
		List<User> users = new ArrayList<User>();
		try {
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserCode(rs.getString("usercode"));
				user.setUserName(rs.getString("username"));
				user.setAddTime(rs.getTimestamp("addtime"));
				user.setUpdateTime(rs.getTimestamp("updatetime"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	public User Login(String usercode) {
		// String strsql = "select * from user;";
		SqlHelper sqlHelper = new SqlHelper();
		List<Object> para = null;
		Connection conn = SqlHelper.getConn();
		PreparedStatement pstmt = null;
		String strsql_task = "select * from user where usercode='" + usercode+"'";
		try {
			pstmt = conn.prepareStatement(strsql_task);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = sqlHelper.excuteSelect(conn, pstmt, "", para);
		User user = new User();
		try {
			while (rs.next()) {
				user.setUserName(rs.getString("username"));
				user.setUserCode(rs.getString("usercode"));
				user.setPassword(rs.getString("password"));
				user.setId(rs.getInt("id"));
				user.setAddTime(rs.getTimestamp("addtime"));
				user.setUpdateTime(rs.getTimestamp("updatetime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

}
