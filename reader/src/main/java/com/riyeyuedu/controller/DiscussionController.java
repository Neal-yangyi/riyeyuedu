package com.riyeyuedu.controller;

import com.riyeyuedu.entity.PostEntity;
import com.riyeyuedu.entity.ReaderEntity;
import com.riyeyuedu.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/discussion")
public class DiscussionController{
    private PostService postService;

    private Long nid;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/{nid}", method = RequestMethod.GET)
    public ModelAndView toDiscussion(@PathVariable Long nid) {
        ModelAndView mv = new ModelAndView("discussion");
        mv.addObject("discussion", postService.getPostByNid(nid));
        this.nid = nid;

        return mv;
    }

    @RequestMapping(value = "/putPost", method = RequestMethod.POST)
    public String put(HttpSession session, @RequestParam("theme") String theme, @RequestParam("content") String content) {
        PostEntity post = new PostEntity();
        ReaderEntity reader = (ReaderEntity) session.getAttribute("reader");

        post.setTheme(theme);
        post.setContent(content);
        post.setRid(reader.getRid());
        post.setNid(this.nid);
        post.setCommentNum(0);
        post.setLikeNum(0);
        post.setLevel(0);

        postService.addPost(post);
        return "discussion";
    }
}
