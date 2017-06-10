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

package com.gmail.webos21.spring.common.log;

import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoggingInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String sessionKey = LogSequence.getInstance().getSessionKey();

		GeneralLogInfo generalLogInfo = GeneralLogThreadLocal.newGeneralLogInfo();
		generalLogInfo.setSessionKey(sessionKey);
		generalLogInfo.setLogStartNanoTime(System.nanoTime());

		requestLogging(request, sessionKey);

		return super.preHandle(request, response, handler);
	}

	private final void requestLogging(HttpServletRequest request, String sessionKey) {
		final StringBuilder message = new StringBuilder();

		message.append(String.format("HTTP Request\n\t [sessionKey : %1$s] \n", sessionKey));
		message.append(String.format("\t [method] %1$s \n", request.getMethod()));
		message.append(String.format("\t [url] %1$s \n", request.getRequestURI()));
		message.append(String.format("\t [headers]"));

		for (final String headerName : Collections.list(request.getHeaderNames())) {
			for (final String headerValue : Collections.list(request.getHeaders(headerName))) {
				message.append(String.format(" %s=%s", headerName, headerValue));
			}
		}
		message.append("\n");
		message.append(String.format("\t [parameters] "));
		for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements();) {
			String _paramName = params.nextElement();
			message.append(String.format("%s=%s&", _paramName, request.getParameter(_paramName)));
		}

//		if ("POST".equalsIgnoreCase(request.getMethod()) || "PUT".equalsIgnoreCase(request.getMethod())) {
//			String contentType = request.getContentType();
//			if (contentType != null && (contentType.startsWith("application/json") || contentType.startsWith("application/xml"))) {
//				message.append("\n\t [body] \n");
//				try {
//					BufferedReader br = request.getReader();
//					br.mark(10000);
//
//					String line;
//					do {
//						line = br.readLine();
//						message.append(line).append("\n");
//					} while (line != null);
//					br.reset();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		logger.info(message.toString());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		log(request, response);

		super.afterCompletion(request, response, handler, ex);
	}

	private final void log(HttpServletRequest request, HttpServletResponse response) {
		final StringBuilder message = new StringBuilder();

		GeneralLogInfo generalLogInfo = GeneralLogThreadLocal.currentGeneralLogInfo();

		String runningTime = (generalLogInfo.getRunningTime() == null) ? LogElapsedTime.getRunningTime(generalLogInfo
				.getLogStartNanoTime()) : generalLogInfo.getRunningTime();

		String sessionKey = generalLogInfo.getSessionKey();
		Object targetResonse = generalLogInfo.getResponse();

		message.append(String.format("HTTP Response\n\t [sessionKey : %1$s / runningTime : %2$s] \n", sessionKey, runningTime));
		message.append(String.format("\t [status] %1$d \n", response.getStatus()));
		message.append(String.format("\t [headers]"));

		for (final String headerName : response.getHeaderNames()) {
			for (final String headerValue : response.getHeaders(headerName)) {
				message.append(String.format(" %s=%s", headerName, headerValue));
			}
		}

		if (targetResonse != null) {
			message.append("\n");
			message.append(String.format("\t [body] \n"));
			ObjectMapper mapper = new ObjectMapper();

			try {
				message.append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(targetResonse));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		logger.info(message.toString());
	}

}
