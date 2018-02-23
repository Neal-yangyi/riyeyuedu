package com.riyeyuedu.controller;

import com.riyeyuedu.entity.BookCaseEntity;
import com.riyeyuedu.entity.ReaderEntity;
import com.riyeyuedu.service.BookCaseService;
import com.riyeyuedu.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class BookCaseController {
    private BookCaseService bookCaseService;

    private NovelService novelService;

    @Autowired
    public void setNovelService(NovelService novelService) {
        this.novelService = novelService;
    }

    @Autowired
    public void setBookCaseService(BookCaseService bookCaseService) {
        this.bookCaseService = bookCaseService;
    }

    @RequestMapping(value = "/user/addBookCase/{nid}")
    @Transactional
    public ModelAndView addBookCase(@PathVariable Long nid, HttpSession session) {
        ModelAndView mv = new ModelAndView("bookCase");
        ReaderEntity reader = (ReaderEntity) session.getAttribute("reader");

        BookCaseEntity bookCase = new BookCaseEntity();
        bookCase.setNid(nid);
        bookCase.setRid(reader.getRid());

        if (novelService.getOneInBookCase(bookCase) == null) {
            bookCaseService.addBookCase(bookCase);
        }

        mv.addObject("novel", novelService.getNovelInBookCase(reader.getRid()));
        return mv;
    }

    @RequestMapping(value = "/bookCase")
    public ModelAndView getBookCase(HttpSession session) {
        ModelAndView mv = new ModelAndView("bookCase");
        ReaderEntity reader = (ReaderEntity) session.getAttribute("reader");
        if (reader != null) {
            mv.addObject("novel", novelService.getNovelInBookCase(reader.getRid()));
        } else {
            mv.addObject("novel", null);
        }

        return mv;
    }

    @RequestMapping(value = "/user/deleteBookCase/{nid}")
    public ModelAndView deleteBookCase(@PathVariable Long nid, HttpSession session) {
        ModelAndView mv = new ModelAndView("bookCase");
        ReaderEntity reader = (ReaderEntity) session.getAttribute("reader");
        BookCaseEntity bookCase = new BookCaseEntity();
        bookCase.setNid(nid);
        bookCase.setRid(reader.getRid());

        bookCaseService.deleteBookCase(bookCase);

        mv.addObject("novel", novelService.getNovelInBookCase(reader.getRid()));

        return mv;
    }
}
