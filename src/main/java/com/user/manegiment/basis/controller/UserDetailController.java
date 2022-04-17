package com.user.manegiment.basis.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.manegiment.basis.entity.MasterUser;
import com.user.manegiment.basis.form.UserDetailForm;
import com.user.manegiment.basis.form.validation.GroupOrder;
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

		MasterUser user = userService.getUserOne(userId);
		user.setPassword(null);

		form = modelMapper.map(user, UserDetailForm.class);
		
		form.setSalaryList(user.getSalaryList());
		

		model.addAttribute("userDetailForm", form);
		return "/user/detail";

	}

	@PostMapping(value = "/detail", params = "update")
	public String updateUser(Model model, @ModelAttribute @Validated(GroupOrder.class) UserDetailForm form,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("validationError", bindingResult.getAllErrors());
			return getUserDetail(model, form.getUserId(), form);
		}

		MasterUser user = modelMapper.map(form, MasterUser.class);

		userService.updateUserOne(user);

		return getUserDetail(model, form.getUserId(), form);
	}

	@PostMapping(value = "/detail", params = "delete")
	public String deleteUser(UserDetailForm form) {

		userService.deleteUserOne(form.getUserId());

		// serviceImpl
		return "redirect:/user/list";
	}
}
