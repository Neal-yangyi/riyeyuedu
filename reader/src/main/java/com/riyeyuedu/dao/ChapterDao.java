package com.riyeyuedu.dao;

import com.riyeyuedu.entity.ChapterEntity;
import com.riyeyuedu.entity.RecordEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by 阳溢 on 2018/1/5.
 */
@Repository
public class ChapterDao {

    public List<ChapterEntity> getDirectoryByNid(SqlSession sqlSession, Long nid) {
        return sqlSession.selectList("chapter.getDirectoryByNid", nid);
    }

    public ChapterEntity getChapterByCid(SqlSession sqlSession, Long cid) {
        return sqlSession.selectOne("chapter.getChapterByCid", cid);
    }

    public ChapterEntity getChapterByRecordNid(SqlSession sqlSession, Long nid) {
        return sqlSession.selectOne("chapter.getChapterByRecordNid", nid);
    }

    public ChapterEntity getNewChapter(SqlSession sqlSession, Long nid) {
        return sqlSession.selectOne("chapter.getNewChapter", nid);
    }
}
