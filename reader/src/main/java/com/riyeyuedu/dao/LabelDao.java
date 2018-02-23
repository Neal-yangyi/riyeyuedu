package com.riyeyuedu.dao;

import com.riyeyuedu.entity.LabelEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class LabelDao {
    public LabelEntity getLabelByNid(SqlSession sqlSession, Long nid) {
        return sqlSession.selectOne("label.getLabelByNid", nid);
    }

    public LabelEntity getLabelByLid(SqlSession sqlSession, int lid) {
        return sqlSession.selectOne("label.getLabelByLid", lid);
    }
}
