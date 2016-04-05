package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class testthread {

	public static void main(String[] args) {
		// System.out.print("test");
		testthread aa = new testthread();
		try {
			aa.insertdata();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void insertdata() throws SQLException {
		final Connection conn = SqlHelper.getConn();
		final PreparedStatement statement = conn
				.prepareStatement("INSERT INTO test(testid,testname) VALUES(?,?)");
		conn.setAutoCommit(false);
		ExecutorService exec = Executors.newFixedThreadPool(1);
		for (int j = 1; j <= 1; j++) {
			exec.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println("start: " + Thread.currentThread().getName()+":"+System.currentTimeMillis());
					for (int i = 1; i <= 100000; i++) {
						try {
							statement.setInt(1, i);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							statement.setString(2, "gaf");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							statement.addBatch();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (i % 100 == 0) {
							try {
								statement.executeBatch();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								conn.commit();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}

					System.out.println("end: " + Thread.currentThread().getName()+":"+System.currentTimeMillis());
				}
			});

		}

	}

}
