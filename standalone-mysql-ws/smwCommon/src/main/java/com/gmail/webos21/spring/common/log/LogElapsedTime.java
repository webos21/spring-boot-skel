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

public final class LogElapsedTime {

	  private long start = System.nanoTime();

	  /**
	   * Resets and returns elapsed time in milliseconds.
	   */
	  public long reset() {
	    long now = System.nanoTime();
	    try {
	      return now - start;
	    } finally {
	      start = now;
	    }
	  }

	 public String getRunningTime() {
		 return String.format("%1.9f", (double)reset() / 1000000000.0);
	  }
	  
	 public static String getRunningTime(long start) {
		long now = System.nanoTime();
		
		return String.format("%1.9f", (double)(now - start) / 1000000000.0);
	}
	 
}
