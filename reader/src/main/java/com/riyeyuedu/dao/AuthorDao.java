package com.riyeyuedu.dao;

import com.riyeyuedu.entity.AuthorEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorDao {
    public AuthorEntity getAuthorByAid(SqlSession sqlSession, int aid) {
        return sqlSession.selectOne("author.getAuthorByAid", aid);
    }

    public AuthorEntity getAuthorByAuthorName(SqlSession sqlSession, AuthorEntity author) {
        return sqlSession.selectOne("author.getAuthorByAuthorName", author);
    }

    public Boolean addAuthor(SqlSession sqlSession, AuthorEntity author) {
        int addNum = sqlSession.insert("author.insertAuthor", author);
        return addNum == 1;
    }
}
