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

package com.gmail.webos21.spring.db.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gmail.webos21.spring.db.domain.TestUser;

@Mapper
public interface TestUserDao {

	public TestUser findById(String id);

	public List<TestUser> findAllByMap(HashMap<String, Object> queryMap);

	public int selectCount(HashMap<String, Object> queryMap);

	public void register(TestUser aRow);

	public void update(TestUser aRow);

	public void updateByMap(HashMap<String, Object> queryMap);

	public int deleteById(String id);

}
