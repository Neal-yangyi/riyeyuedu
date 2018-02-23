package com.riyeyuedu.controller;

import com.riyeyuedu.entity.ReaderEntity;
import com.riyeyuedu.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class RecordController {
    private RecordService recordService;

    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @RequestMapping(value = "/record", method = RequestMethod.GET)
    public ModelAndView getRecordByRid(HttpSession session) {
        ModelAndView mv = new ModelAndView("record");
        ReaderEntity reader;
        if ((reader = (ReaderEntity)session.getAttribute("reader")) != null) {
            mv.addObject("record", recordService.getRecordByRid(reader.getRid()));
        } else {
            mv.addObject("record", null);
        }

        return mv;
    }
}
