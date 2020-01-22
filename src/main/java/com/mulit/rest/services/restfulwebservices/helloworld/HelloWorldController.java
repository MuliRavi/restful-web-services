package com.mulit.rest.services.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

//Controller
//1) To Handle Rest Requests --@RestController

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class HelloWorldController {
				// GET
				// URI - /hello-world
				// method- "Hello World"
			
				// 2) First call this as controller and then we need to map get uri of
				// /hello-world
				// 3) what is the request method that we want to use --RequestMethod.GET
				// 4) To map the URi path="/hello-world"
				// @RequestMapping(method=RequestMethod.GET,path="/hello-world")
				
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";

	}

					// Used for returning a bean object
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
	//throw new RuntimeException("Some Error has happend! Contact custormer support at ***_***");
		return new HelloWorldBean("Hello World");

	}

					// "/hello-world/path-variable/invaiableName"
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));

	}
}
