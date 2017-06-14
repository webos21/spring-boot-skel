package com.gmail.webos21.spring.skel.db.test;

import java.lang.management.ManagementFactory;

import javax.management.ObjectName;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
@ActiveProfiles("test")
@TestPropertySource(properties = { "spring.jmx.enabled:true", "spring.datasource.jmx-enabled:true" })
public class JmxTest {

	@Test
	public void testJmx() throws Exception {
		Assertions.assertThat(
				ManagementFactory.getPlatformMBeanServer().queryMBeans(new ObjectName("jpa.sample:type=ConnectionPool,*"), null))
				.hasSize(1);
	}

}
