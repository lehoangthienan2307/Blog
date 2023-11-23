package com.lhtan.blogapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lhtan.blogapplication.entity.PostEntity;
import com.lhtan.blogapplication.service.PostService;

@Controller
public class HomeController {
	@Autowired
	private PostService postService;
	
	@GetMapping("/")
	public String home(Model model)
	{
		List<PostEntity> posts = postService.getAll();
		model.addAttribute("posts", posts);
		return "home";
	}
}
