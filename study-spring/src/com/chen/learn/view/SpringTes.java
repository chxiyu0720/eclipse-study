package com.chen.learn.view;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chen.learn.bean.UserInfo;

public class SpringTes {
	
	@Test
	public void testSpring() {
		//创建spring的IOC容器
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//从IOC中获取bean
		UserInfo info = (UserInfo) app.getBean("userinfo");
		
		System.out.println(info.getUsername());
	}
}
