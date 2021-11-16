package com.osdb.octipod.controller;

import com.osdb.octipod.model.HelloObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

	@RequestMapping(value = "/hello-world", method = {RequestMethod.PUT, RequestMethod.GET//, RequestMethod.OPTIONS
	}
	)
	@CrossOrigin(origins = {"http://domain21.com"}//, methods = {RequestMethod.DELETE, RequestMethod.POST}
	)
	HelloObject hello() {
		HelloObject helloObject = new HelloObject(1L, "My hello-world message");
		return helloObject;
	}

}


