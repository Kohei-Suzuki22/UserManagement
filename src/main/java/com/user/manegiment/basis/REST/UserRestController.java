package com.user.manegiment.basis.REST;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.user.manegiment.basis.entity.MasterUser;
import com.user.manegiment.basis.form.UserDetailForm;
import com.user.manegiment.basis.service.user.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;

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
