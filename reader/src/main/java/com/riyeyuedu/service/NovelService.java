package com.riyeyuedu.service;

import com.riyeyuedu.dao.NovelDao;
import com.riyeyuedu.entity.BookCaseEntity;
import com.riyeyuedu.entity.NovelEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 阳溢 on 2018/1/5.
 */
@Service
public class NovelService {
    private SqlSession sqlSession;

    private NovelDao novelDao;

    public NovelService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Autowired
    public void setNovelDao(NovelDao novelDao) {
        this.novelDao = novelDao;
    }

    public boolean addNovel(NovelEntity novel) {
        return novelDao.addNovel(sqlSession, novel);
    }

    public List<NovelEntity> getAllNovel() {
        return novelDao.getAllNovel(sqlSession);
    }

    public NovelEntity getNovelByNid (Long nid) {
        return novelDao.getNovelByNid(sqlSession, nid);
    }

    public List<NovelEntity> getNnovelByAid (int aid) {
        return novelDao.getNovelByAid(sqlSession, aid);
    }

    public void addClickNum(Long nid) {
        novelDao.addClickNum(sqlSession, nid);
    }

    public List<Map<String, Object>> getNovelByLid(int lid) {
        return novelDao.getNovelByLid(sqlSession, lid);
    }

    public List<Map<String, Object>> getNovelByRecent() {
        return novelDao.getNovelByRecent(sqlSession);
    }

    public List<Map<String, Object>> getNovelByScore() {
        return novelDao.getNovelByScore(sqlSession);
    }

    public List<Map<String, Object>> getNovelByNew() {
        return novelDao.getNovelByNew(sqlSession);
    }

    public List<Map<String, Object>> getNovelByClick() {
        return novelDao.getNovelByClick(sqlSession);
    }

    public NovelEntity getNovelByTitle(String title) {
        return novelDao.getNovelByTitle(sqlSession ,title);
    }

    public List<Map<String, Object>> getNovelByLidRecent(int lid) {
        return novelDao.getNovelByLidRecent(sqlSession, lid);
    }

    public List<Map<String, Object>> getNovelByLidScore(int lid) {
        return novelDao.getNovelByLidScore(sqlSession, lid);
    }

    public List<Map<String, Object>> getNovelByState(int state) {
        return novelDao.getNovelByState(sqlSession, state);
    }

    public List<NovelEntity> getNovelInBookCase(int rid) {
        return novelDao.getNovelInBookCase(sqlSession, rid);
    }

    public List<Map<String, Object>> search(String key) {
        List<Map<String, Object>> novels = novelDao.getNovelByTitleWidly(sqlSession, key);
        if (novels.size() == 0) {
            novels = novelDao.getNovelByAuthorName(sqlSession, key);
        }
        return novels;
    }

    public NovelEntity getOneInBookCase(BookCaseEntity bookCase) {
        return novelDao.getOneInBookCase(sqlSession, bookCase);
    }

    public List<Map<String, Object>> getNovelByAuthorName(String authorName) {
        return novelDao.getNovelByAuthorName(sqlSession, authorName);
    }

    public List<Map<String, Object>> getNotAllowedNovel() {
        return novelDao.getNotAllowedNovel(sqlSession);
    }
}
