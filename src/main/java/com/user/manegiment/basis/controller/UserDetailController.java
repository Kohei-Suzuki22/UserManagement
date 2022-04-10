package com.user.manegiment.basis.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.manegiment.basis.entity.MasterUser;
import com.user.manegiment.basis.form.UserDetailForm;
import com.user.manegiment.basis.service.user.UserService;

@Controller
@RequestMapping("/user")
public class UserDetailController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/detail/{userId:.+}")
	public String getUserDetail(Model model, @PathVariable String userId, UserDetailForm form) {
		
		
		MasterUser user = userService.getOne(userId);
		user.setPassword(null);
		
		form = modelMapper.map(user, UserDetailForm.class);
		
		model.addAttribute("userDetailForm",form);
		return "user/detail";
		
	}
}
