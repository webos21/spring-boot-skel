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

package com.gmail.webos21.spring.svc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.webos21.spring.db.domain.TestUser;
import com.gmail.webos21.spring.db.mapper.TestUserDao;
import com.gmail.webos21.spring.svc.RestConsts;
import com.gmail.webos21.spring.svc.model.ResponseWrapper;
import com.gmail.webos21.spring.svc.model.TestUserData;

@Service
public class TestService {

	@Autowired
	private TestUserDao tuDao;

	public ResponseWrapper getUserList() throws Exception {
		List<TestUser> tuList = tuDao.findAllByMap(null);
		ArrayList<TestUserData> tudList = new ArrayList<TestUserData>();

		for (TestUser tu : tuList) {
			TestUserData tud = new TestUserData(tu);
			tudList.add(tud);
		}

		return new ResponseWrapper(RestConsts.SC_OK, RestConsts.SM_OK, tudList);
	}
}
