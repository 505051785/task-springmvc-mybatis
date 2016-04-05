package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class test {

	public static void main(String[] args) {
		// System.out.print("test");
		test aa=new test();
		long startTime=System.currentTimeMillis(); 
		aa.insertdata();
		long endTime=System.currentTimeMillis();
		System.out.print((endTime-startTime)+"end");
	}


		private  void insertdata() {
			Connection conn = SqlHelper.getConn();
			try {
				PreparedStatement statement = conn
						.prepareStatement("INSERT INTO test(testid,testname) VALUES(?,?)");
				conn.setAutoCommit(false);
				for (int i = 1; i <= 100000; i++) {
					statement.setInt(1, i);
					statement.setString(2, "gaf");
					statement.addBatch();
					if (i % 100 == 0) {
						statement.executeBatch();
						conn.commit();
					}
				}
				//statement.executeBatch();
				//conn.commit();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


}
