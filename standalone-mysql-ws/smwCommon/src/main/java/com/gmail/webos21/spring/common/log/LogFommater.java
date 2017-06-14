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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * @class StringUtil.java
 * @Description 	
 * @see
 *
 */
public class LogFommater {
	public static final String PRE = "PRE";
	public static final String POST = "POST";

	/**
	 * Method getSizeString.
	 *
	 * <pre>
	 * <br>
	 * </pre>
	 *
	 * @param a
	 * @param size
	 * @param preFix
	 * @return String
	 */
	public static String getSizeString(int a, int size, String preFix) {
		StringBuffer rvalue = new StringBuffer();

		for (double i = Math.pow(10, (size - 1)); i >= 1; i = i / 10) {
			if (a < i) {
				rvalue.append(preFix);
			}
			else {
				rvalue.append(a);
				break;
			}

		}
		return rvalue.toString();
	}

    /**
	 *
	 * @param format
	 * 			ex> format : yyyyMMddHHmmssSSS
	 * @return
	 */
	public static String getCurrentTime(String format) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);

		return dateFormat.format(calendar.getTime());
	}

	public static String long2ZeroFillString(long value, int length) {
		String returnString = new String();
		String valueString = new String();
		Long m_Long;
		int i = 0;

		m_Long = new Long(value);
		valueString = m_Long.toString();
		if (valueString.length() > length) {
			return null;
		}

		if (valueString.length() == length) {
			return valueString;
		}

		for (i = 0; i < length - valueString.length(); i++) {
			returnString += "0";
		}

		returnString += valueString;
		return returnString;
	}

    public static String hex(String string) {
        StringBuilder buf = new StringBuilder(200);
        for (char ch: string.toCharArray()) {
            if (buf.length() > 0)
                buf.append(' ');
            buf.append(String.format("%04x", (int) ch));
        }
        return buf.toString();
    }

    public static String sha1(String string) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(string.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String randomString(String source, int length) {
        Random random = new Random();
        int strlength = source.length();
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < length; i++) {
            result.append(source.charAt(random.nextInt(strlength)));
        }

        return result.toString();
    }

}