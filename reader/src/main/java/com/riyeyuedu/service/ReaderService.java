package com.riyeyuedu.service;

import com.riyeyuedu.dao.ReaderDao;
import com.riyeyuedu.entity.ReaderEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderService {
    private SqlSession sqlSession;

    private ReaderDao readerDao;

    public ReaderService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Autowired
    public void setReaderDao(ReaderDao readerDao) {
        this.readerDao = readerDao;
    }

    public Boolean addReader(ReaderEntity reader) {
        return  readerDao.addReader(sqlSession, reader);
    }

    public Boolean changePassword(ReaderEntity reader) {
        return readerDao.changePassword(sqlSession, reader);
    }

    public ReaderEntity getReader(ReaderEntity reader) {
        return readerDao.getReader(sqlSession, reader);
    }

    public Boolean updateAvatar(ReaderEntity reader) {
        return readerDao.updateAvatar(sqlSession, reader);
    }
}
