package com.riyeyuedu.service;

import com.riyeyuedu.dao.ChapterDao;
import com.riyeyuedu.entity.ChapterEntity;
import com.riyeyuedu.entity.RecordEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 阳溢 on 2018/1/5.
 */
@Service
public class ChapterService {
    private SqlSession sqlSession;

    private ChapterDao chapterDao;

    public ChapterService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Autowired
    public void setChapterDao(ChapterDao chapterDao) {
        this.chapterDao = chapterDao;
    }

    public Boolean addChapter(ChapterEntity chapter) {
        return chapterDao.addChapter(sqlSession, chapter);
    }

    public List<ChapterEntity> getDirectoryByNid(Long nid) {
        return chapterDao.getDirectoryByNid(sqlSession, nid);
    }

    public ChapterEntity getChapterByCid(Long cid) {
        return chapterDao.getChapterByCid(sqlSession, cid);
    }

    public ChapterEntity getChapterByRecordNid(Long nid) {
        return chapterDao.getChapterByRecordNid(sqlSession, nid);
    }

    public ChapterEntity getNewChapter(Long nid) {
        return chapterDao.getNewChapter(sqlSession, nid);
    }

    public Boolean chapterAllowed(ChapterEntity chapter) {
        return chapterDao.chapterAllowed(sqlSession, chapter);
    }
}
