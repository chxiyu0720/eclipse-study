package com.chen.learn.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.chen.learn.bean.UserInfo;
import com.chen.learn.utils.JdbcUtils;
/*
	这是测试文件
*/
public class ClientTest {
	
	@Test
	public void conTest() {
		Connection con = JdbcUtils.getConnection();
		System.out.println(con);
	}
	
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
}
