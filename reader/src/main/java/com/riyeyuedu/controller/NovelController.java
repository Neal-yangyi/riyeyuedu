package com.riyeyuedu.controller;

import com.riyeyuedu.entity.AuthorEntity;
import com.riyeyuedu.entity.ChapterEntity;
import com.riyeyuedu.entity.LabelEntity;
import com.riyeyuedu.entity.NovelEntity;
import com.riyeyuedu.service.AuthorService;
import com.riyeyuedu.service.ChapterService;
import com.riyeyuedu.service.LabelService;
import com.riyeyuedu.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 阳溢 on 2018/1/5.
 */
@Controller
public class NovelController {
    private NovelService novelService;

    private AuthorService authorService;

    private ChapterService chapterService;

    private LabelService labelService;

    @Autowired
    public void setChapterService(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @Autowired
    public void setNovelService(NovelService novelService) {
        this.novelService = novelService;
    }

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setLabelService(LabelService labelService) {
        this.labelService = labelService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ModelAndView getAllNovel() {
        ModelAndView mv = new ModelAndView("riye");
        mv.addObject("clickNovel", novelService.getNovelByClick());
        mv.addObject("scoreNovel", novelService.getNovelByScore());
        mv.addObject("newNovel", novelService.getNovelByNew());
        mv.addObject("recentNovel", novelService.getNovelByRecent());
        mv.addObject("classNovel1", novelService.getNovelByLid(1));
        mv.addObject("classNovel2", novelService.getNovelByLid(2));
        mv.addObject("classNovel3", novelService.getNovelByLid(3));
        mv.addObject("classNovel4", novelService.getNovelByLid(4));
        mv.addObject("classNovel5", novelService.getNovelByLid(5));
        mv.addObject("classNovel6", novelService.getNovelByLid(6));

        return mv;
    }

    @RequestMapping(value = "/novel/class/{lid}", method = RequestMethod.GET)
    public ModelAndView getNovelByLid(@PathVariable int lid) {
        ModelAndView mv = new ModelAndView("class");
        mv.addObject("labelNovel", novelService.getNovelByLid(lid));
        mv.addObject("labelRecentNovel", novelService.getNovelByLidRecent(lid));
        mv.addObject("labelScoreNovel", novelService.getNovelByLidScore(lid));
        mv.addObject("label", labelService.getLabelByLid(lid));

        return mv;
    }

    @RequestMapping(value = "/novel/title/{title}", method = RequestMethod.GET)
    public ModelAndView getNovelByTitle(@PathVariable String title) {
        ModelAndView mv = new ModelAndView("directory");
        NovelEntity novel = novelService.getNovelByTitle(title);
        AuthorEntity author = authorService.getAuthorByNovel(novel);
        LabelEntity label = labelService.getLabelByNid(novel.getNid());
        ChapterEntity chapter = chapterService.getChapterByCid(novel.getRecentChapterId());
        mv.addObject("label", label);
        mv.addObject("novel", novel);
        mv.addObject("author", author);
        mv.addObject("directory", chapterService.getDirectoryByNid(novel.getNid()));
        mv.addObject("recent_chapter", chapter);

        return mv;
    }

    @RequestMapping(value = "/novel/{nid}", method = RequestMethod.GET)
    public ModelAndView getDirectory(@PathVariable Long nid) {
        ModelAndView mv = new ModelAndView("directory");
        NovelEntity novel = novelService.getNovelByNid(nid);
        AuthorEntity author = authorService.getAuthorByNovel(novel);
        novelService.addClickNum(nid);
        ChapterEntity chapter = chapterService.getChapterByCid(novel.getRecentChapterId());
        mv.addObject("directory", chapterService.getDirectoryByNid(nid));
        mv.addObject("novel", novel);
        mv.addObject("author", author);
        mv.addObject("recent_chapter", chapter);
        mv.addObject("label", labelService.getLabelByNid(novel.getNid()));
        return mv;
    }

    @RequestMapping(value = "/novel/complete", method = RequestMethod.GET)
    public ModelAndView getCompleteNovel() {
        ModelAndView mv = new ModelAndView("complete");
        mv.addObject("novel", novelService.getNovelByState(1));

        return mv;
    }

    @RequestMapping(value = "/novel/rankingList", method = RequestMethod.GET)
    public ModelAndView getNovelRankingList() {
        ModelAndView mv = new ModelAndView("rankingList");
        mv.addObject("scoreNovel", novelService.getNovelByScore());

        return mv;
    }

    @RequestMapping(value = "/novel/bookCase/{rid}", method = RequestMethod.GET)
    public ModelAndView getNovelInBookCase(@PathVariable int rid) {
        ModelAndView mv = new ModelAndView("bookCase");
        mv.addObject("novel", novelService.getNovelInBookCase(rid));

        return mv;
    }

    @RequestMapping(value = "/novel/search", method = RequestMethod.GET)
    public ModelAndView searchBook(@RequestParam("key") String key) {
        ModelAndView mv = new ModelAndView("searchResult");
        mv.addObject("novel", novelService.search(key));

        return mv;
    }
}
