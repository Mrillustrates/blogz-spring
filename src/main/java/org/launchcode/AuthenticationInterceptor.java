package org.launchcode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.launchcode.library.controllers.AbstractController;
import org.launchcode.library.models.Librarian;
import org.launchcode.library.models.dao.LibrarianDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    LibrarianDao librarianDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        List<String> authPages = Arrays.asList("/catalog/newbook");

        // Require sign-in for auth pages
        if ( authPages.contains(request.getRequestURI()) ) {

        	boolean isLoggedIn = false;
        	Librarian librarian;
            Integer userId = (Integer) request.getSession().getAttribute(AbstractController.userSessionKey);

            if (userId != null) {
            	librarian = librarianDao.findByUid(userId);
            	
            	if (librarian!= null) {
            		isLoggedIn = true;
            	}
            }

            // If user not logged in, redirect to login page
            if (!isLoggedIn) {
                response.sendRedirect("/login");
                return false;
            }
        }

        return true;
    }

}