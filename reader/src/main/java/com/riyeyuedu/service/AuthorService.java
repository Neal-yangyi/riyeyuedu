package com.riyeyuedu.service;

import com.riyeyuedu.dao.AuthorDao;
import com.riyeyuedu.entity.AuthorEntity;
import com.riyeyuedu.entity.NovelEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private SqlSession sqlSession;

    private AuthorDao authorDao;

    public AuthorService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Autowired
    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public AuthorEntity getAuthorByNovel(NovelEntity novel) {
        int aid = novel.getAid();
        return authorDao.getAuthorByAid(sqlSession, aid);
    }

    public Boolean addAuthor(AuthorEntity author) {
        return authorDao.addAuthor(sqlSession, author);
    }

    public AuthorEntity getAuthorByAid(int aid) {
        return authorDao.getAuthorByAid(sqlSession, aid);
    }

    public AuthorEntity getAuthorByAuthorName(AuthorEntity author) {
        return authorDao.getAuthorByAuthorName(sqlSession, author);
    }
}
