package com.user.manegiment.basis.REST.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.user.manegiment.basis.REST.entity.RestResult;
import com.user.manegiment.basis.entity.MasterUser;
import com.user.manegiment.basis.form.SignupForm;
import com.user.manegiment.basis.form.UserDetailForm;
import com.user.manegiment.basis.form.UserListForm;
import com.user.manegiment.basis.form.validation.GroupOrder;
import com.user.manegiment.basis.service.user.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@PostMapping("/signup/rest")
	public RestResult restSignup(@Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult, Locale locale) {
		
		Map<String, String> errors = new HashMap<>();
		
		if(bindingResult.hasErrors()) {
			
			for(FieldError error: bindingResult.getFieldErrors()) {
				String message = messageSource.getMessage(error,locale);
				errors.put(error.getField(), message);
			}
			
			return new RestResult(90, errors);
		}
		
		try {
			
			MasterUser user = modelMapper.map(form, MasterUser.class);
			userService.signup(user);
			
		}catch(Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		 return new RestResult(0, errors);
		
	}
	
	@GetMapping("/getList/rest")
	public List<MasterUser> getListRest(UserListForm form){
		
		List<MasterUser> userList = new ArrayList<>();
		
		try {
			MasterUser user = modelMapper.map(form, MasterUser.class);
			userList.addAll(userService.getUsers(user));
		}catch(Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return userList;
	}

	@PutMapping("/update")
	public HttpStatus updateUser(UserDetailForm form) {
		
	
		try {
			MasterUser user = modelMapper.map(form, MasterUser.class);
			userService.updateUserOne(user);
			
		}catch(Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return HttpStatus.OK;
		
	}
	
	@DeleteMapping("/delete")
	public HttpStatus deleteUser(UserDetailForm form) {
		
		try {
			userService.deleteUserOne(form.getUserId());
		}catch(Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return HttpStatus.OK;
	}
}
