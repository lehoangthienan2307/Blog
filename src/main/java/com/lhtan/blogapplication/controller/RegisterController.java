package com.lhtan.blogapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lhtan.blogapplication.entity.AccountEntity;
import com.lhtan.blogapplication.service.AccountService;

@Controller
public class RegisterController {
	@Autowired
	private AccountService accountService;
	@GetMapping("/register")
	public String getRegisterPage(Model model)
	{
		AccountEntity account = new AccountEntity();
		model.addAttribute("account", account);
		return "register";
	}
	
	@PostMapping("/register")
	public String registerNewUser(@ModelAttribute AccountEntity account)
	{
		accountService.save(account);
		return "redirect:/"; 
	}
}
