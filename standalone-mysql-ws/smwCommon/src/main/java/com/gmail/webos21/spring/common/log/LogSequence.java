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

import java.util.concurrent.atomic.AtomicLong;

/**
 * API 로그
 *
 * @author bbaeggar
 *
 */
public class LogSequence {

	private final static LogSequence instance = new LogSequence();
	private final String dateFormat = "yyyyMMddHHmmss";
	private final AtomicLong sequence = new AtomicLong();
	private int sessionKeyLength = 30;

	public static LogSequence getInstance() {
		return instance;
	}

	public synchronized String getSessionKey() {

		return LogFommater.getCurrentTime(dateFormat)
				+ LogFommater.long2ZeroFillString(nextVal(), sessionKeyLength
						- dateFormat.length());
	}

	public synchronized long nextVal() {
		return sequence.incrementAndGet();
	}

}
