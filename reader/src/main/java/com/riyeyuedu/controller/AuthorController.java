package com.riyeyuedu.controller;

import com.riyeyuedu.entity.AuthorEntity;
import com.riyeyuedu.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(value = "/registerAuthor", method = RequestMethod.POST)
    @ResponseBody
    public String registerAuthor(AuthorEntity author, HttpSession session) {
        if (authorService.getAuthorByAuthorName(author) == null) {
            authorService.addAuthor(author);
            session.setAttribute("author", author);
            return "1";
        } else {
            return "0";
        }
    }

    @RequestMapping(value = "/toAuthorHome", method = RequestMethod.GET)
    public String toAuthorHome() {
        return "authorHome";
    }

    @RequestMapping(value = "/toRegisterAuthor", method = RequestMethod.GET)
    public String toRegisterAuthor() {
        return "registerAuthor";
    }
}
