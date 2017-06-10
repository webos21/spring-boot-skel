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

package com.gmail.webos21.spring.common.mybatis;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmail.webos21.spring.common.log.GeneralLogInfo;
import com.gmail.webos21.spring.common.log.GeneralLogThreadLocal;

@Intercepts({ @Signature(type = StatementHandler.class, method = "update", args = { Statement.class }),
		@Signature(type = StatementHandler.class, method = "query", args = { Statement.class, ResultHandler.class }) })
public class MybatisLogInterceptor implements Interceptor {

	private Logger logger = LoggerFactory.getLogger(MybatisLogInterceptor.class);

	public Object intercept(Invocation invocation) throws Throwable {
		String sql = MybatisQueryLog.getSql(invocation);
		logger.info("sql : {" + formatLog(sql) + "}");
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}

	private String formatLog(String sql) {
		final StringBuilder logBuilder = new StringBuilder();
		final GeneralLogInfo generalLogInfo = GeneralLogThreadLocal.currentGeneralLogInfo();

		if (generalLogInfo != null && generalLogInfo.getSessionKey() != null) {
			logBuilder.append("\n\t[sessionKey : ");
			logBuilder.append(generalLogInfo.getSessionKey());
			logBuilder.append("]");
		}
		logBuilder.append("\n\t\t");
		logBuilder.append(sql);
		logBuilder.append("\n");

		return logBuilder.toString();

	}
}
