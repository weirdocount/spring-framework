package com.weirdo;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class SpringTest {

	@Test
	public void testSimpleLoad() {
		Resource resource = new ClassPathResource("spring.xml");
		BeanFactory beanFactory = new XmlBeanFactory(resource);
		WeirdoBean weirdoBean = beanFactory.getBean(WeirdoBean.class);
		weirdoBean.say();
	}
}
