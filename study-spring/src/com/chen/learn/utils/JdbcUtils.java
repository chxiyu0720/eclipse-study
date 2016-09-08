package com.chen.learn.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * mysql数据库连接工具类
 * 
 * @author chenxiaoyu
 * @email  1524904743@qq.com
 * @date   2016年9月8日 上午10:22:45
 * @package com.chen.learn.utils
 *
 */
public class JdbcUtils {
	
	private static Properties prop;
	
	//静态代码块，只在加载运行一次
	static {
		//加载配置文件
		InputStream io = JdbcUtils.class.getClassLoader().getResourceAsStream("dbConfig.properties");
		//读取配置文件内容到properties对象中
		prop = new Properties();
		try {
			prop.load(io);
			//加载驱动类
			Class.forName(prop.getProperty("driverClassName"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取mysql数据库连接
	 * @return
	 */
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return con;
	}
}
