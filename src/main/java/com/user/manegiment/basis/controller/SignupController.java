package com.user.manegiment.basis.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.manegiment.basis.service.UserService;

@Controller
@RequestMapping("/user")
public class SignupController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/signup")
	public String getSignup(Model model, Locale locale){
		
		model.addAttribute("genderMap", userService.getGenderMap(locale));
		
		return "user/signup";
	}
	
	@PostMapping("/signup")
	public String postSignup() {
		return "redirect:/login";
	}
}	
