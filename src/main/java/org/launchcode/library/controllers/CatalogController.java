package org.launchcode.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CatalogController extends AbstractController {
    @RequestMapping(value = "/catalog")
    public String blogIndex(Model model) {
        model.addAttribute("catalogs", bookDao.findAll());

        // TODO - fetch posts and pass to template

        return "catalog";
    }
}
