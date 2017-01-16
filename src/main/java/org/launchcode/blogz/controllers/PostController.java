package org.launchcode.blogz.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.launchcode.blogz.models.Post;
import org.launchcode.blogz.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostController extends AbstractController {

	@RequestMapping(value = "/blog/newpost", method = RequestMethod.GET)
	public String newPostForm() {
		return "newpost";
	}
	
	@RequestMapping(value = "/blog/newpost", method = RequestMethod.POST)
	public String newPost(HttpServletRequest request, Model model) {
		
		// TODO - implement newPost
		//my code starts 
		
		
		String title = request.getParameter("title");
		String body = request.getParameter("body");
    	User author = getUserFromSession(request.getSession());
    	String user = author.getUsername();
    			
    	if(title.isEmpty()) {
			return "newpost";
		}
		if(body.isEmpty()){
			return "newpost";
		}
		Post newEntry = new Post(title,body, author);
		
		postDao.save(newEntry);
		int id = newEntry.getUid();
		
		
		//setUserInSession(session, author); 
		
		return ("redirect:/blog/"+ user + "/"+ id); // TODO - this redirect should go to the new post's page  		
		//return "redirect:post;"
		//my code ends
	}
		
	//		return "newpost";
		//}
	
	@RequestMapping(value = "/blog/{username}/{uid}", method = RequestMethod.GET)
	public String singlePost(@PathVariable String username, @PathVariable int uid, Model model) {
		
		// TODO - implement singlePost
		//User postCreator = userDao.findByUsername(username);
		
		//model.addAttribute
		Post entry = postDao.findByUid(uid);
		int postId = entry.getUid();
		
		//String user = postCreator.getUsername();
		
		
		if(postId >=1){
			model.addAttribute("post", entry);
	
		}
	
		return "post";
	}
	
	@RequestMapping(value = "/blog/{username}", method = RequestMethod.GET)
	public String userPosts(@PathVariable String username, Model model) {
		
		// TODO - implement userPosts
		User user= userDao.findByUsername(username);
		
		int findUser = user.getUid();
		//Post postCreator = postDao.findByAuthorId(findUser);
		List<Post> posts = user.getPosts();
		//Post entry = postDao.findByUid(findUser);
		//List<Post> entry = postDao.findByAuthor(findUser);
	
		if(findUser>=1 ){ 
			model.addAttribute("posts", posts);
		}
		return "blog";	
		
		}
		
	
}
