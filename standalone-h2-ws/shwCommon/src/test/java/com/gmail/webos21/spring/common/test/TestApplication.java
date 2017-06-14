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

package com.gmail.webos21.spring.common.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.gmail.webos21.spring.common")
public class TestApplication {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public Simple getSimple() {
		return new Simple("cmjo", "조철민");
	}

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	private class Simple {
		private String id;
		private String name;

		public Simple(String id, String name) {
			this.id = id;
			this.name = name;
		}

		@SuppressWarnings("unused")
		public String getId() {
			return id;
		}

		@SuppressWarnings("unused")
		public void setId(String id) {
			this.id = id;
		}

		@SuppressWarnings("unused")
		public String getName() {
			return name;
		}

		@SuppressWarnings("unused")
		public void setName(String name) {
			this.name = name;
		}

	}
}
