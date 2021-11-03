package com.osdb.octipod.controller;

import com.osdb.octipod.model.HelloObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

	@GetMapping(value = "/hello") //, produces = MediaType.APPLICATION_JSON_VALUE
	HelloObject hello() {
		HelloObject helloObject = new HelloObject("Hello message", 1);
		return helloObject;
	}

	@GetMapping("/hello2")
	public Map<String, Object> greeting() {
		return Collections.singletonMap("Map:", new HelloObject("Hello message", 2));
	}

}


