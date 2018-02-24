package com.riyeyuedu.dao;

import com.riyeyuedu.entity.ChapterEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by 阳溢 on 2018/1/5.
 */
@Repository
public class ChapterDao {

    public Boolean addChapter(SqlSession sqlSession, ChapterEntity chapter) {
        int addNum = sqlSession.insert("chapter.insertChapter", chapter);
        return addNum == 1;
    }

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

    public Boolean chapterAllowed(SqlSession sqlSession, ChapterEntity chapter) {
        int updateNum = sqlSession.update("chapter.chapterAllowed", chapter);
        return updateNum == 1;
    }
}
