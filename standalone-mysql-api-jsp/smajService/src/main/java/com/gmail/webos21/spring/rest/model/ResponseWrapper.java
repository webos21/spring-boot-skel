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

package com.gmail.webos21.spring.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlRootElement(name = "response")
@JsonInclude(Include.NON_NULL)
// @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResponseWrapper {

	private int statusCode;
	private String statusMsg;
	private AbstractData data;
	private List<? extends AbstractData> dataList;

	public ResponseWrapper() {
	}

	public ResponseWrapper(int resultCode, String resultMsg) {
		this.statusCode = resultCode;
		this.statusMsg = resultMsg;
	}

	public ResponseWrapper(int resultCode, String resultMsg, AbstractData data) {
		this.statusCode = resultCode;
		this.statusMsg = resultMsg;
		this.data = data;
	}

	public ResponseWrapper(int resultCode, String resultMsg, List<? extends AbstractData> dataList) {
		this.statusCode = resultCode;
		this.statusMsg = resultMsg;
		this.dataList = dataList;
	}

	public int getStatustCode() {
		return statusCode;
	}

	public void setStatusCode(int resultCode) {
		this.statusCode = resultCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String resultMsg) {
		this.statusMsg = resultMsg;
	}

	/**
	 * <code>application/json</code>형태의 출력은 변수명을 따른다. <code>application/xml</code>형태의 출력은 각 클래스의 <code>@XmlRootElement(name="")</code>의
	 * name을 따른다.
	 */
	@XmlElementRef
	public AbstractData getData() {
		return data;
	}

	public void setData(AbstractData responseData) {
		this.data = responseData;
	}

	@XmlElementRef
	public List<? extends AbstractData> getDataList() {
		return dataList;
	}

	public void setDataList(List<? extends AbstractData> dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return "ResponseWrapper [statusCode=" + statusCode + ", statusMsg=" + statusMsg + ", data=" + data + ", dataList=" + dataList
				+ "]";
	}

}
