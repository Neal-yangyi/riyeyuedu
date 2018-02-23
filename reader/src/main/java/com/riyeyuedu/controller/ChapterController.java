package com.riyeyuedu.controller;

import com.riyeyuedu.entity.*;
import com.riyeyuedu.service.ChapterService;
import com.riyeyuedu.service.LabelService;
import com.riyeyuedu.service.NovelService;
import com.riyeyuedu.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

/**
 * Created by 阳溢 on 2018/1/5.
 */
@Controller
public class ChapterController {
    private ChapterService chapterService;

    private NovelService novelService;

    private LabelService labelService;

    private RecordService recordService;

    @Autowired
    public void setChapterService(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @Autowired
    public void setNovelService(NovelService novelService) {
        this.novelService = novelService;
    }

    @Autowired
    public void setLabelService(LabelService labelService) {
        this.labelService = labelService;
    }

    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @RequestMapping(value = "/chapter/{nid}/{cid}", method = RequestMethod.GET)
    @Transactional
    public ModelAndView getChapter(@PathVariable Long cid, @PathVariable Long nid, HttpSession session) {
        ModelAndView mv = new ModelAndView("chapter");
        NovelEntity novel = novelService.getNovelByNid(nid);
        List<ChapterEntity> directory = chapterService.getDirectoryByNid(novel.getNid());
        ChapterEntity chapter = chapterService.getChapterByCid(cid);

        int index;
        ReaderEntity reader;
        ChapterEntity lastChapter = null;
        ChapterEntity nextChapter = null;

        if (chapter.getCid() == directory.get(0).getCid()) {
            nextChapter = directory.get(1);
        } else if (chapter.getCid() == directory.get(directory.size() - 1).getCid()) {
            lastChapter = directory.get(directory.size() - 2);
        } else {
            lastChapter = chapterService.getChapterByCid(chapter.getCid() - 1);
            nextChapter = chapterService.getChapterByCid(chapter.getCid() + 1);
        }

        if ((reader = (ReaderEntity)session.getAttribute("reader")) != null) {
            RecordEntity record = new RecordEntity();
            record.setNid(nid);
            record.setRid(reader.getRid());

            if (recordService.getRecordByNid(record) != null) {
                recordService.deleteRecord(recordService.getRecordByNid(record).getRecordId());
            }

            record.setCid(cid);

            recordService.addRecord(record);

            if (recordService.getRecordByRid(reader.getRid()) != null && recordService.getRecordByRid(reader.getRid()).size() > 20) {
                recordService.deleteRecord(recordService.getOldestRecord(reader.getRid()).getRecordId());
            }
        }

        mv.addObject("chapter", chapter);
        mv.addObject("novel", novel);
        mv.addObject("label", labelService.getLabelByNid(nid));
        mv.addObject("lastChapter", lastChapter);
        mv.addObject("nextChapter", nextChapter);

        return mv;
    }

    @RequestMapping(value = "/user/chapter/continueRead/{nid}")
    public void continueRead(@PathVariable Long nid, HttpServletResponse response) throws IOException {
        String address;
        ChapterEntity chapter = chapterService.getChapterByRecordNid(nid);

        if (chapter == null) {
            address = "/novel/" + nid.toString();
        } else {
            address = "/chapter/" + nid.toString() + "/" + chapterService.getChapterByRecordNid(nid).getCid().toString();
        }

        response.sendRedirect(address);
    }
}
