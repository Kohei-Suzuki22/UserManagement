package com.user.manegiment.basis.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.manegiment.basis.entity.MasterUser;
import com.user.manegiment.basis.form.UserListForm;
import com.user.manegiment.basis.service.user.UserService;

@Controller
@RequestMapping("/user")
public class UserListController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/list")
	public String getUserList(Model model, @ModelAttribute UserListForm userListForm) {
		
		MasterUser masterUser = modelMapper.map(userListForm, MasterUser.class);	
		List<MasterUser> userList = userService.getUsers(masterUser);
		model.addAttribute("userList",userList);
		
		return "user/list";
	}
	
	
	@PostMapping("/list")
	public String postUserList(Model model, @ModelAttribute UserListForm userListForm) {
		
		 MasterUser masterUser = modelMapper.map(userListForm, MasterUser.class);
		 List<MasterUser> userList = userService.getUsers(masterUser);
		  
		  model.addAttribute("userList", userList);
		
		return "user/list";
	}

	
}
