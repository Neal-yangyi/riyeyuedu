package com.riyeyuedu.service;

import com.riyeyuedu.dao.RecordDao;
import com.riyeyuedu.entity.RecordEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecordService {
    private SqlSession sqlSession;

    private RecordDao recordDao;

    public RecordService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Autowired
    public void setRecordDao(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    public Boolean addRecord(RecordEntity record) {
        return recordDao.addRecord(sqlSession, record);
    }

    public List<Map<String, Object>> getRecordByRid(int rid) {
        return recordDao.getRecordByRid(sqlSession, rid);
    }

    public Boolean deleteRecord(Long recordId) {
        return recordDao.deleteRecord(sqlSession, recordId);
    }

    public RecordEntity getOldestRecord(int rid) {
        return recordDao.getOldestRecord(sqlSession, rid);
    }

    public RecordEntity getRecordByNid(RecordEntity record) {
        return recordDao.getRecordByNid(sqlSession, record);
    }

    public Boolean updateRecord(RecordEntity record) {
        return recordDao.updateRecord(sqlSession, record);
    }
}
