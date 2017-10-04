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

package com.gmail.webos21.spring.skel.db.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.gmail.webos21.spring.skel.db.domain.IdVal;
import com.gmail.webos21.spring.skel.db.repositories.IdValRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
@Transactional
@Rollback
public class IdValTest {

	@Autowired
	private IdValRepository idValRepository;

	@Test
	public void testAllMethod() throws Exception {

		// #01 : Check the current rows
		long preAllSize = idValRepository.count();
		System.out.println("Table('id_val') Rows = " + preAllSize);

		// #02 : Create new row
		IdVal newRow = new IdVal();

		newRow.setValue("Tester");

		idValRepository.save(newRow);

		// #03 : Find newly inserted row and Check
		IdVal foundRow = idValRepository.findOne(newRow.getId());

		System.out.println("id           = " + foundRow.getId());
		System.out.println("val          = " + foundRow.getValue());

		assertEquals(newRow.getId(), foundRow.getId());
		assertEquals("Tester", foundRow.getValue());

		// #04 : Find all data again and Check
		List<IdVal> postAll = idValRepository.findAll();
		assertEquals((preAllSize + 1), postAll.size());

		for (IdVal aRow : postAll) {
			if (newRow.getId() == aRow.getId()) {
				foundRow = aRow;
			}
		}

		System.out.println("id           = " + foundRow.getId());
		System.out.println("val          = " + foundRow.getValue());

		return;
	}
}
