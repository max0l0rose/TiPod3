package com.osdb.octipod.controller;

import com.osdb.octipod.model.HelloObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

	@RequestMapping(value = "/hello-world", method = {RequestMethod.PUT, RequestMethod.GET}) //, produces = MediaType.APPLICATION_JSON_VALUE
	@CrossOrigin(origins = {"http://domain2.com:8081"})
	HelloObject hello() {
		HelloObject helloObject = new HelloObject(1L, "Hello-world message");
		return helloObject;
	}
}


