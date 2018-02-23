package com.riyeyuedu.dao;

import com.riyeyuedu.entity.BookCaseEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class BookCaseDao {
    public Boolean addBookCase(SqlSession sqlSession, BookCaseEntity bookCase) {
        int addNum = sqlSession.insert("bookCase.insertBookCase", bookCase);
        return addNum == 1;
    }

    public Boolean deleteBookCase(SqlSession sqlSession, BookCaseEntity bookCase) {
        int deleteNum = sqlSession.delete("bookCase.deleteBookCase", bookCase);
        return deleteNum == 1;
    }
}
