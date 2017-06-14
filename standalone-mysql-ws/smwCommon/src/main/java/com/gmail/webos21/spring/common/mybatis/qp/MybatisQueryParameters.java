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

package com.gmail.webos21.spring.common.mybatis.qp;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.mapping.ParameterMapping;

public class MybatisQueryParameters {
	private Object parameter;
	private List<String> propertys;
	
	public static List<Object> getParameterList(QueryParameterVo queryParameterVo) {
		return new MybatisQueryParameters(queryParameterVo.getParameter(), queryParameterVo.getParameterMappings()).getParameterList();
	}
	
	private MybatisQueryParameters(Object parameter, List<ParameterMapping> parameterMappings) {
		this.parameter = parameter;
		this.setPropertys(parameterMappings);
	}
	
	private void setPropertys(List<ParameterMapping> parameterMappings) {
		propertys = new ArrayList<String>();
		for(int i = 0;i<parameterMappings.size();i++) {
			String property = parameterMappings.get(i).getProperty();
			propertys.add(property);
		}
	}
	
	private List<Object> getParameterList() {
		List<Object> parameterList = new ArrayList<Object>();
		for(int i = 0;i<propertys.size();i++) {
			Object parameter = getParameter(propertys.get(i));
			parameterList.add(parameter);
		}
		return parameterList;
	}
	
	private Object getParameter(String property) {
		GeneralQueryParameter queryParameter = QueryParameterFactory.getQueryParameter(parameter, property);
		return queryParameter.getParameter();
	}
}
