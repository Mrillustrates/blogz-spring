package org.launchcode.blogz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController extends AbstractController {

	@RequestMapping(value = "/")
	public String index(Model model){
		// TODO - fetch users and pass to template
		//userDao.findAll();
		model.addAttribute("users", userDao.findAll());
		return "index";
	}
	
	@RequestMapping(value = "/blog")
	public String blogIndex(Model model) {
		model.addAttribute("posts", postDao.findAll());
		
		// TODO - fetch posts and pass to template
		//.getPosts();
		return "blog";
	}
	
}
