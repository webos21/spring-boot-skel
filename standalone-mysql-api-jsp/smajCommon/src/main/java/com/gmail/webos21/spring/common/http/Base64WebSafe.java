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

package com.gmail.webos21.spring.common.http;

public class Base64WebSafe {

	private static final int DEF_FLAG = Base64.URL_SAFE | Base64.NO_PADDING | Base64.NO_WRAP;

	public static String encode(byte[] input) {
		return Base64.encodeToString(input, DEF_FLAG);
	}

	public static byte[] decode(String b64str) {
		return Base64.decode(b64str, DEF_FLAG);
	}

}
