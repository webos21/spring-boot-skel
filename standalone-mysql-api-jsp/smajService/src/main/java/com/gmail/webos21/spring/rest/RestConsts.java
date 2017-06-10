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

package com.gmail.webos21.spring.rest;

public final class RestConsts {

	public static final int TO_XML = 0;
	public static final int TO_JSON = 1;

	public static final int SC_OK = 200;
	public static final int SC_BAD_REQUEST = 400;
	public static final int SC_UNAUTHORIZED = 401;
	public static final int SC_NOT_FOUND = 404;
	public static final int SC_INTERNAL_SERVER_ERROR = 500;

	public static final String SM_OK = "OK";
	public static final String SM_BAD_REQUEST = "Bad Request";
	public static final String SM_UNAUTHORIZED = "Unauthorized";
	public static final String SM_NOT_FOUND = "Not Found";
	public static final String SM_INTERNAL_SERVER_ERROR = "Internal Server Error";

	public static final String ALG_RSA = "RSA/ECB/PKCS1Padding";
	public static final String ALG_HASH = "SHA1PRNG";
	public static final int PKI_KEY_LEN = 1024;

	public static final String CHARSET = "UTF-8";

}
