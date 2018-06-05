package org.launchcode.library.controllers;

import org.launchcode.library.models.Book;
import org.launchcode.library.models.Librarian;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BookController extends AbstractController {

    @RequestMapping(value = "/catalog/newbook", method = RequestMethod.GET)
    public String newBookForm() {
        return "newbook";

    }

    @RequestMapping(value = "/catalog/newbook", method = RequestMethod.POST)
    public String newBook(HttpServletRequest request, Model model) {

        // TODO - implement newPost
        //my code starts


        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String author = request.getParameter("author");
        Integer pubdate = Integer.valueOf(request.getParameter("pubdate"));


        Librarian editor = getUserFromSession(request.getSession());
        String user = editor.getUsername();

        if (title.isEmpty()) {
            return "newbook";
        }
        if (description.isEmpty()) {
            return "newbook";
        }

        Book newEntry = new Book(isbn, title, description, author, pubdate, editor);

        bookDao.save(newEntry);
        int id = newEntry.getUid();


        //setUserInSession(session, author);

        return ("redirect:/catalog/" + user + "/" + id);

    }
}
