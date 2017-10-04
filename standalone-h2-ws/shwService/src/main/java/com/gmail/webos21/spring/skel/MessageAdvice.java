package com.gmail.webos21.spring.skel;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MessageAdvice {

	@ModelAttribute("message")
	public String message() {
		return "Hello World";
	}

}
