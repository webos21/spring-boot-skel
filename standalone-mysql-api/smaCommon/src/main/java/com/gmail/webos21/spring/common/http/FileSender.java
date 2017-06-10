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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class FileSender {

	public static void setHeader4FileOut(HttpServletResponse response, File file, String fname) {
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + fname);
		response.setHeader("Content-Length", "" + file.length());
	}

	public static void sendFile(InputStream in, OutputStream out) throws IOException {
		BufferedInputStream bin = null;
		BufferedOutputStream bos = null;
		try {
			bin = new BufferedInputStream(in);
			bos = new BufferedOutputStream(out);
			byte[] buf = new byte[4096];
			int read = 0;
			while ((read = bin.read(buf)) != -1) {
				bos.write(buf, 0, read);
			}
		} catch (Exception e) {
			throw new IOException("transport error : " + e, e);
		} finally {
			bos.flush();
			bos.close();
			bin.close();
		}
	}
}
