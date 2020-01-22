package com.mulit.rest.services.restfulwebservices.user;

import java.net.URI;
import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	@Autowired
	private UserDaoService userDaoService;

	@GetMapping("/users")
	public List<User> retriveAllUsers() {
		return userDaoService.findAll();
	}

	@GetMapping("/users/{id}")
	public Resource<User> retriveUser(@PathVariable int id) {
		User user = userDaoService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		// "all-users"ServerPath +"/users"
		// hateoas
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;

	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

}
