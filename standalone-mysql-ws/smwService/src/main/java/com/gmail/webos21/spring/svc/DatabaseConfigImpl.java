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

package com.gmail.webos21.spring.svc;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.gmail.webos21.spring.common.mybatis.MybatisLogInterceptor;
import com.gmail.webos21.spring.db.DatabaseConfig;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = { "com.gmail.webos21.spring.db.mapper" }, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class DatabaseConfigImpl extends DatabaseConfig {

	@Autowired
	private ApplicationContext applicationContext;

	@Bean(destroyMethod = "close")
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		configureDataSource(dataSource);
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource());
		transactionManager.setGlobalRollbackOnParticipationFailure(false);
		return transactionManager;
	}

	@Bean(name = "masterSqlSessionFactory")
	public SqlSessionFactory masterSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setTypeAliasesPackage("com.gmail.webos21.spring.db.domain");
		sessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis/mybatis-config.xml"));
		sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/mapper/**/*.xml"));
		sessionFactoryBean.setDatabaseIdProvider(getDatabaseIdProvider());
		sessionFactoryBean.setPlugins(new Interceptor[] { new MybatisLogInterceptor() });
		return sessionFactoryBean.getObject();
	}

	private Properties getDatabaseIdProperties() {
		Properties p = new Properties();

		p.put("MySQL", "mysql");
		p.put("PostgreSQL", "pgsql");
		p.put("SQL Server", "mssql");
		p.put("DB2", "db2");
		p.put("Oracle", "oracle");

		return p;
	}

	private DatabaseIdProvider getDatabaseIdProvider() {
		DatabaseIdProvider didp = new VendorDatabaseIdProvider();
		didp.setProperties(getDatabaseIdProperties());
		return didp;
	}
}
