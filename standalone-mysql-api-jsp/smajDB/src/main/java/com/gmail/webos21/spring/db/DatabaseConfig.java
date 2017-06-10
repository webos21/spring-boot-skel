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

package com.gmail.webos21.spring.db;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(value = DatabasePropertiesImpl.class)
public abstract class DatabaseConfig {

	@Autowired
	private DatabasePropertiesImpl dbProps;

	@Bean
	public abstract BasicDataSource dataSource();

	protected void configureDataSource(BasicDataSource dataSource) {
		dataSource.setDriverClassName(dbProps.getDriverClassName());
		dataSource.setUrl(dbProps.getUrl());
		dataSource.setUsername(dbProps.getUserName());
		dataSource.setPassword(dbProps.getPassword());
		dataSource.setMaxActive(dbProps.getMaxActive());
		dataSource.setMaxIdle(dbProps.getMaxIdle());
		dataSource.setMinIdle(dbProps.getMinIdle());
		dataSource.setMaxWait(dbProps.getMaxWait());
		dataSource.setTestOnBorrow(dbProps.isTestOnBorrow());
		dataSource.setValidationQuery(dbProps.getValidationQuery());
		dataSource.setTestOnReturn(false);
	}
}
