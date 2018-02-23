package com.riyeyuedu.service;

import com.riyeyuedu.dao.LabelDao;
import com.riyeyuedu.entity.LabelEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelService {
    private SqlSession sqlSession;

    private LabelDao labelDao;

    public LabelService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Autowired
    public void setLabelDao(LabelDao labelDao) {
        this.labelDao = labelDao;
    }

    public LabelEntity getLabelByNid(Long nid) {
        return labelDao.getLabelByNid(sqlSession, nid);
    }

    public LabelEntity getLabelByLid(int lid) {
        return labelDao.getLabelByLid(sqlSession, lid);
    }
}
