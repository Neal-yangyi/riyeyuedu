package com.riyeyuedu.dao;

import com.riyeyuedu.entity.BookCaseEntity;
import com.riyeyuedu.entity.ReaderEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ReaderDao {
    public Boolean addReader(SqlSession sqlSession, ReaderEntity readerEntity) {
        int insertNum = sqlSession.insert("reader.insertReader", readerEntity);
        return insertNum == 1;
    }

    public Boolean changePassword(SqlSession sqlSession, ReaderEntity readerEntity) {
        int updateNum = sqlSession.update("reader.updatePassword", readerEntity);
        return updateNum == 1;
    }

    public ReaderEntity getReader(SqlSession sqlSession, ReaderEntity reader) {
        return sqlSession.selectOne("reader.getReader", reader);
    }

    public ReaderEntity getReaderByReaderName(SqlSession sqlSession, String readerName) {
        return sqlSession.selectOne("reader.getReaderByName", readerName);
    }

    public String getReaderRoleByRid(SqlSession sqlSession, int rid) {
        return sqlSession.selectOne("reader.getReaderRoleByRid", rid);
    }

    public Boolean updateAvatar(SqlSession sqlSession, ReaderEntity reader) {
        int updateNum = sqlSession.update("reader.updateAvatar", reader);
        return updateNum == 1;
    }
}
