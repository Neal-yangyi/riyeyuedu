package com.riyeyuedu.service;

import com.riyeyuedu.dao.PostDao;
import com.riyeyuedu.entity.PostEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostService {
    private SqlSession sqlSession;

    private PostDao postDao;

    public PostService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    public Boolean addPost(PostEntity post) {
        return postDao.addPost(sqlSession, post);
    }

    public List<Map<String, Object>> getPostByNid(Long nid) {
        return postDao.getPostByNid(sqlSession, nid);
    }

    public Boolean addCommentNum(Long pid) {
        return postDao.addCommentNum(sqlSession, pid);
    }

    public Boolean reduceCommentNum(Long pid) {
        return postDao.reduceCommentNUm(sqlSession, pid);
    }

    public Boolean addLikeNum(Long pid) {
        return postDao.addLikeNum(sqlSession, pid);
    }

    public Boolean reduceLikeNum(Long pid) {
        return postDao.reduceLikeNum(sqlSession, pid);
    }
}
