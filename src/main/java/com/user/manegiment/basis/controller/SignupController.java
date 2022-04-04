package com.user.manegiment.basis.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.manegiment.basis.form.SignupForm;
import com.user.manegiment.basis.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class SignupController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/signup")
	public String getSignup(Model model, Locale locale, @ModelAttribute SignupForm form){
		
		model.addAttribute("genderMap", userService.getGenderMap(locale));
		
		return "user/signup";
	}
	
	@PostMapping("/signup")
	public String postSignup(@ModelAttribute SignupForm form) {
		
		log.info(form.toString());
		
		return "redirect:/login";
	}
}	
