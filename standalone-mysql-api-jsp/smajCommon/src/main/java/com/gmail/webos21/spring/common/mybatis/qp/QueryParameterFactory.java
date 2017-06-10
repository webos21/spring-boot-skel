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

import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

public class QueryParameterFactory {
	private Object parameter;
	private String property;

	public static GeneralQueryParameter getQueryParameter(Object parameter,
			String property) {
		return new QueryParameterFactory(parameter, property)
				.getQueryParameter();
	}

	private QueryParameterFactory(Object parameter, String property) {
		this.parameter = parameter;
		this.property = property;
	}

	private GeneralQueryParameter getQueryParameter() {
		if (PropertyUtils.isReadable(parameter, property)) {
			return new PropertiyQueryParameter(parameter, property);
		}
		if (parameter instanceof Map) {
			return new MapQueryParameter(parameter, property);
		}
		return new EtcQueryParameter(parameter, property);
	}
}
