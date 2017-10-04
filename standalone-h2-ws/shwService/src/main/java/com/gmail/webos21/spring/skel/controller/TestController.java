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

package com.gmail.webos21.spring.skel.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gmail.webos21.spring.common.log.GeneralLogInfo;
import com.gmail.webos21.spring.common.log.GeneralLogThreadLocal;
import com.gmail.webos21.spring.skel.SvcConsts;
import com.gmail.webos21.spring.skel.model.IdValData;
import com.gmail.webos21.spring.skel.model.ResponseWrapper;
import com.gmail.webos21.spring.skel.service.TestService;

@Controller
@RequestMapping("/skel/test")
public class TestController {

	@Autowired
	private TestService testSvc;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseWrapper getList(HttpServletResponse res) throws Exception {
		GeneralLogInfo generalLogInfo = GeneralLogThreadLocal.currentGeneralLogInfo();

		ResponseWrapper aRes = null;
		try {
			aRes = testSvc.getItemList();
		} catch (Exception e) {
			e.printStackTrace();
			ResponseWrapper rw = new ResponseWrapper(SvcConsts.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			generalLogInfo.setResponse(rw);
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return rw;
		}

		generalLogInfo.setResponse(aRes);
		return aRes;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseWrapper addItem(@RequestBody IdValData item, HttpServletResponse res) throws Exception {
		GeneralLogInfo generalLogInfo = GeneralLogThreadLocal.currentGeneralLogInfo();

		System.out.println("ID = " + item.getId());
		System.out.println("VALUE = " + item.getValue());

		ResponseWrapper aRes = null;
		try {
			aRes = testSvc.addItem(item);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseWrapper rw = new ResponseWrapper(SvcConsts.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			generalLogInfo.setResponse(rw);
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return rw;
		}

		generalLogInfo.setResponse(aRes);
		return aRes;
	}

}
