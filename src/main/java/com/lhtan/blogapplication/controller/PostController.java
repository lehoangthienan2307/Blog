package com.lhtan.blogapplication.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lhtan.blogapplication.entity.AccountEntity;
import com.lhtan.blogapplication.entity.PostEntity;
import com.lhtan.blogapplication.service.AccountService;
import com.lhtan.blogapplication.service.PostService;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/posts/{id}")
	public String getPost(@PathVariable Long id, Model model)
	{
		Optional<PostEntity> optionalPost = postService.getById(id);
		if (optionalPost.isPresent())
		{
			PostEntity post = optionalPost.get();
			model.addAttribute("post", post);
			return "post";
		} else {
			return "404";
		}
	}
	
	@GetMapping("/posts/new")
	@PreAuthorize("isAuthenticated()")
	public String createNewPost(Model model)
	{
		
		PostEntity post = new PostEntity();
		model.addAttribute("post", post);
		return "post_new";
		
	}
	
	@PostMapping("/posts/new")
	@PreAuthorize("isAuthenticated()")
	public String saveNewPost(@ModelAttribute PostEntity post, Principal principal)
	{
		
        AccountEntity account = accountService.findByEmail(principal.getName()).orElseThrow(() -> new IllegalArgumentException("Account not found"));
        post.setAccount(account);
        postService.save(post);
		return "redirect:/posts/" + post.getId();
	}
	
	@GetMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getPostForEdit(@PathVariable Long id, Model model, Principal principal) {
        Optional<PostEntity> optionalPost = postService.getById(id);
        if (optionalPost.isPresent()) {
            PostEntity post = optionalPost.get();
            
            if (!post.getAccount().getEmail().equals(principal.getName()))
            {
            	return "error";
            }
            model.addAttribute("post", post);
            return "post_edit";
        } else {
            return "404";
        }
    }
	@PostMapping("/posts/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@PathVariable Long id, PostEntity post, BindingResult result, Model model) {

        Optional<PostEntity> optionalPost = postService.getById(id);
        if (optionalPost.isPresent()) {
            PostEntity existingPost = optionalPost.get();

            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());


            postService.save(existingPost);
        }

        return "redirect:/posts/" + post.getId();
    }
	
	@GetMapping("/posts/{id}/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deletePost(@PathVariable Long id) {

        Optional<PostEntity> optionalPost = postService.getById(id);
        if (optionalPost.isPresent()) {
        	PostEntity post = optionalPost.get();

            postService.delete(post);
            return "redirect:/";
        } else {
            return "404";
        }
    }
	
}
