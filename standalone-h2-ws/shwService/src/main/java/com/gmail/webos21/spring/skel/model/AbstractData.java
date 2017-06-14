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

package com.gmail.webos21.spring.skel.model;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * AbstractData를 상속받은 클래스들을 <code>@XmlSeeAlso({ConfigData.class, ...})</code>에 모두 적어주어야 한다.
 */
@XmlSeeAlso({ IdValData.class })
public abstract class AbstractData {

}
