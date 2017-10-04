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

package com.gmail.webos21.spring.skel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.webos21.spring.skel.SvcConsts;
import com.gmail.webos21.spring.skel.db.domain.IdVal;
import com.gmail.webos21.spring.skel.db.repositories.IdValRepository;
import com.gmail.webos21.spring.skel.model.IdValData;
import com.gmail.webos21.spring.skel.model.ResponseWrapper;

@Service
public class TestService {

	@Autowired
	private IdValRepository testRepo;

	public ResponseWrapper getItemList() throws Exception {
		List<IdVal> tuList = testRepo.findAll();
		ArrayList<IdValData> tudList = new ArrayList<IdValData>();

		for (IdVal tu : tuList) {
			IdValData tud = new IdValData(tu);
			tudList.add(tud);
		}

		return new ResponseWrapper(SvcConsts.SC_OK, SvcConsts.SM_OK, tudList);
	}

	public ResponseWrapper addItem(IdValData item) throws Exception {

		IdVal newRow = new IdVal();
		newRow.setValue(item.getValue());

		testRepo.save(newRow);

		List<IdVal> tuList = testRepo.findAll();
		ArrayList<IdValData> tudList = new ArrayList<IdValData>();

		for (IdVal tu : tuList) {
			IdValData tud = new IdValData(tu);
			tudList.add(tud);
		}

		return new ResponseWrapper(SvcConsts.SC_OK, SvcConsts.SM_OK, tudList);
	}

}
