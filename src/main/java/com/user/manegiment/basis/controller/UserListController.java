package com.user.manegiment.basis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.manegiment.basis.entity.MasterUser;
import com.user.manegiment.basis.service.user.UserService;

@Controller
@RequestMapping("/user")
public class UserListController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	public String getUserList(Model model) {
		
		
		List<MasterUser> userList = userService.getUsers();
		model.addAttribute("userList",userList);
		
		return "user/list";
	}

	
}
