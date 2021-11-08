package com.osdb.octipod.controller;

import com.osdb.octipod.model.HelloObject;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
//@CrossOrigin
@RequestMapping("/api/v1")
public class HelloController {

	@GetMapping(value = "/hello") //, produces = MediaType.APPLICATION_JSON_VALUE
	HelloObject hello() {
		HelloObject helloObject = new HelloObject(1L, "Hello message");
		return helloObject;
	}

	@RequestMapping(value = "/hello2", method = {RequestMethod.POST})
	@CrossOrigin(origins = "http://domain21.com") // allows ALSO
	public Map<String, Object> greeting() {
		return Collections.singletonMap("Map:", new HelloObject(2L, "Hello message"));
	}

}


