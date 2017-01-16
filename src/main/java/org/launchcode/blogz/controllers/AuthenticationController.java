 package org.launchcode.blogz.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.launchcode.blogz.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController extends AbstractController {
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupForm() {
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(HttpServletRequest request, Model model) {	
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verify = request.getParameter("verify");
    	HttpSession session = request.getSession();
    	
		if(!User.isValidUsername(username)){
			//System.out.println("Invalid usernames");
			return "signup";
		}
		if(!User.isValidPassword(password)) {
			//System.out.println("invalid password");
			return "signup";
		}
		if(!password.equals(verify)){
			//System.out.println("Invalid password");
			return "signup";
		}
		
		User newUser = new User(username,password);
		userDao.save(newUser);
		setUserInSession(session, newUser); 
		
		return "redirect:blog/newpost";
	}
    	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		
		// TODO - implement login
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User logs = userDao.findByUsername(username);
    	HttpSession session = request.getSession();

		
		if(logs.isMatchingPassword(password)){
			
			setUserInSession(session, logs); 
			return "redirect:blog/newpost";

		}
		{
			return "login";

		}

}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
        request.getSession().invalidate();
		return "redirect:/";
	}
}
