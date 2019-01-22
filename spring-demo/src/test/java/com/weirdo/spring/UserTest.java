package com.weirdo.spring;

import com.weirdo.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
	public static void main(String[] args) {
		String xmlPath = "classpath:spring.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		UserService userService = applicationContext.getBean(UserService.class);
		String result = userService.sayHello();
		System.out.println(result);
	}
}
