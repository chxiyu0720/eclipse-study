package com.chen.learn.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import com.chen.learn.utils.JdbcUtils;
/*
	这是测试文件
*/
public class JdbcTest {
	
	@Test
	public void conTest() {
		Connection con = JdbcUtils.getConnection();
		System.out.println(con);
	}
	
	/**
	 * 测试查询
	 * pst.executeQuery()执行查询语句，其返回值是ResultSet
	 */
	@Test
	public void test1() {
		Connection con = JdbcUtils.getConnection();
		String sql = "select * from userinfo where deleted = 0 and username = ?";
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, "oooo");
			ResultSet set = pst.executeQuery();
			while (set.next()) {
				System.out.println(set.getString("password"));
				if (set.isLast()) {
					return;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 测试
	 * prep.execute()可以执行修改、添加和删除语句，其返回值是boolean类型
	 * 返回true：表示执行的是查询语句，可以使用getResultSet()获取查询到的ResultSet对象
	 * 返回false：表示执行的是修改、添加和删除的语句，可以使用getUpdateCount()获取受影响的记录条数
	 */
	@Test
	public void adduser() {
		Connection con = JdbcUtils.getConnection();
//		String sql = "insert into userinfo(username, password, sex, age, create_time) values(?, ?, ?, ?, NOW())";
//		String sql = "update userinfo set username = ?, password = ?, sex = ?, age = ?, create_time = NOW() where id = ?";
		String sql = "select * from userinfo where deleted = 0 and username = ?";
		try {
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, "小黄");
//			prep.setString(2, "abc123");
//			prep.setString(3, "女");
//			prep.setInt(4, 22);
//			prep.setInt(5, 15);
			boolean result = prep.execute();
			if (result) {
				ResultSet a = prep.getResultSet();
				while (a.next()) {
					System.out.println(a.getString("username") + " ======== " + a.getString("password"));
					if (a.isLast()) {
						return;
					}
				}
			}else {
				int a = prep.getUpdateCount();
				System.out.println(a);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
