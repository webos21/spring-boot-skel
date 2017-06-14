/*
 * Copyright 2017 Cheolmin Jo (webos21@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gmail.webos21.spring.db.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.webos21.spring.db.DatabaseConfigImpl;
import com.gmail.webos21.spring.db.domain.TestUser;
import com.gmail.webos21.spring.db.mapper.TestUserDao;

@Transactional
@Rollback
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class, DatabaseConfigImpl.class })
@WebAppConfiguration
public class TestUserDaoTest {

	@Autowired
	private TestUserDao testDao;

	@Test
	public void testAllMethod() throws Exception {

		// #01 : Check the current rows
		HashMap<String, Object> qMap = new HashMap<String, Object>();
		int preAllSize = testDao.selectCount(qMap);
		System.out.println("Table('TestUser') Rows = " + preAllSize);

		// #02 : Create new row
		TestUser newRow = new TestUser();

		newRow.setId("tester");
		newRow.setName("Tester");

		testDao.register(newRow);

		// #03 : Find newly inserted row and Check
		TestUser foundRow = testDao.findById(newRow.getId());

		System.out.println("id           = " + foundRow.getId());
		System.out.println("name         = " + foundRow.getName());

		assertEquals("tester", foundRow.getId());
		assertEquals("Tester", foundRow.getName());

		// #04 : Find all data again and Check
		List<TestUser> postAll = testDao.findAllByMap(qMap);
		assertEquals((preAllSize + 1), postAll.size());

		for (TestUser aRow : postAll) {
			if ("tester".equals(aRow.getId())) {
				foundRow = aRow;
			}
		}

		System.out.println("id           = " + foundRow.getId());
		System.out.println("name         = " + foundRow.getName());

		return;
	}

}
