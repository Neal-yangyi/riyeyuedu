package com.riyeyuedu.service;

import com.riyeyuedu.dao.BookCaseDao;
import com.riyeyuedu.entity.BookCaseEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookCaseService {
    private SqlSession sqlSession;

    private BookCaseDao bookCaseDao;

    public BookCaseService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Autowired
    public void setBookCaseDao(BookCaseDao bookCaseDao) {
        this.bookCaseDao = bookCaseDao;
    }

    public Boolean addBookCase(BookCaseEntity bookCase) {
        return bookCaseDao.addBookCase(sqlSession, bookCase);
    }

    public Boolean deleteBookCase(BookCaseEntity bookCase) {
        return bookCaseDao.deleteBookCase(sqlSession, bookCase);
    }
}
