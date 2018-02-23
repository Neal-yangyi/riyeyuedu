package com.riyeyuedu.dao;

import com.riyeyuedu.entity.RecordEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RecordDao {
    public Boolean addRecord(SqlSession sqlSession, RecordEntity record) {
        int insertNum = sqlSession.insert("record.insertRecord", record);
        return insertNum == 1;
    }

    public List<Map<String, Object>> getRecordByRid(SqlSession sqlSession, int rid) {
        return sqlSession.selectList("record.getRecordByRid", rid);
    }

    public Boolean deleteRecord(SqlSession sqlSession, Long recordId) {
        int deleteNum = sqlSession.delete("record.deleteRecord", recordId);
        return deleteNum == 1;
    }

    public RecordEntity getOldestRecord(SqlSession sqlSession, int rid) {
        return sqlSession.selectOne("record.getOldestRecord", rid);
    }

    public RecordEntity getRecordByNid(SqlSession sqlSession, RecordEntity record) {
        return sqlSession.selectOne("record.getRecordByNid", record);
    }

    public Boolean updateRecord(SqlSession sqlSession, RecordEntity record) {
        int updateNum = sqlSession.update("record.updateRecord", record);
        return updateNum == 1;
    }
}
