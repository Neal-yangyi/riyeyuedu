package com.riyeyuedu.dao;

import com.riyeyuedu.entity.PostEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PostDao {
    public Boolean addPost(SqlSession sqlSession, PostEntity post) {
        int addNum = sqlSession.insert("post.insertPost", post);
        return addNum == 1;
    }

    public List<Map<String, Object>> getPostByNid(SqlSession sqlSession, Long nid) {
        return sqlSession.selectList("post.selectPostByNid", nid);
    }

    public Boolean addCommentNum(SqlSession sqlSession, Long pid) {
        int updateNum = sqlSession.update("post.addCommentNum", pid);
        return updateNum == 1;
    }

    public Boolean reduceCommentNUm(SqlSession sqlSession, Long pid) {
        int updateNum = sqlSession.update("post.reduceCommentNum", pid);
        return updateNum == 1;
    }

    public Boolean addLikeNum(SqlSession sqlSession, Long pid) {
        int updateNum = sqlSession.update("post.addLikeNum", pid);
        return updateNum == 1;
    }

    public Boolean reduceLikeNum(SqlSession sqlSession, Long pid) {
        int updateNum = sqlSession.update("post.reduceLikeNum", pid);
        return updateNum == 1;
    }
}
