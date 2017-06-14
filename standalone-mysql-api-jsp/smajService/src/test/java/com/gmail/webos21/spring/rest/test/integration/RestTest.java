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

package com.gmail.webos21.spring.rest.test.integration;

import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.webos21.spring.db.DatabaseConfigImpl;
import com.gmail.webos21.spring.db.domain.TestUser;
import com.gmail.webos21.spring.svc.RestApplication;

@Transactional
@Rollback
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = { RestApplication.class, DatabaseConfigImpl.class })
public class RestTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getUserListTest() throws Exception {
		MockHttpServletRequestBuilder mreqBuilder = MockMvcRequestBuilders.get("/skel/test");

		ResultActions ract = mockMvc.perform(mreqBuilder);

		ract.andExpect(MockMvcResultMatchers.status().isOk());
		ract.andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON_UTF8));
	}

	@SuppressWarnings("unused")
	private String generateTestData() {
		TestUser tu = new TestUser();

		tu.setId("tester");
		tu.setName("Tester");

		ObjectMapper mapper = new ObjectMapper();
		// mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

		String strReq = null;
		try {
			strReq = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tu);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("!!!!! ORIGINAL JSON !!!!!\n" + strReq);

		return strReq;
	}
}
