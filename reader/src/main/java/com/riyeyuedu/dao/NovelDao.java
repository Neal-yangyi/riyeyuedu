package com.riyeyuedu.dao;

import com.riyeyuedu.entity.BookCaseEntity;
import com.riyeyuedu.entity.NovelEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 阳溢 on 2018/1/5.
 */
@Repository
public class NovelDao {

    public boolean addNovel(SqlSession sqlSession, NovelEntity novel) {
        int insertNum = sqlSession.insert("novel.insertNovel", novel);
        return insertNum == 1;
    }

    public NovelEntity getNovelByNid(SqlSession sqlSession, long nid) {
        return sqlSession.selectOne("novel.getNovelByNid", nid);
    }

    public List<Map<String, Object>> getNovelByState(SqlSession sqlSession, int state) {
        return sqlSession.selectList("novel.getNovelByState", state);
    }

    public List<NovelEntity> getNovelByAid(SqlSession sqlSession, int aid) {
        return sqlSession.selectList("novel.getNovelByAid", aid);
    }

    public List<NovelEntity> getAllNovel(SqlSession sqlSession) {
        return sqlSession.selectList("novel.getAllNovel");
    }

    public void addClickNum (SqlSession sqlSession, long nid) {
        sqlSession.update("novel.addClickNum", nid);
    }

    public List<Map<String, Object>> getNovelByLid(SqlSession sqlSession, int lid) {
        return sqlSession.selectList("novel.getNovelByLid", lid);
    }

    public List<Map<String, Object>> getNovelByRecent(SqlSession sqlSession) {
        return sqlSession.selectList("novel.getNovelByRecent");
    }

    public List<Map<String, Object>> getNovelByScore(SqlSession sqlSession) {
        return sqlSession.selectList("novel.getNovelByScore");
    }

    public List<Map<String, Object>> getNovelByNew(SqlSession sqlSession) {
        return sqlSession.selectList("novel.getNovelByNew");
    }

    public List<Map<String, Object>> getNovelByClick(SqlSession sqlSession) {
        return sqlSession.selectList("novel.getNovelByClick");
    }

    public NovelEntity getNovelByTitle(SqlSession sqlSession, String title) {
        return sqlSession.selectOne("novel.getNovelByTitle", title);
    }

    public List<Map<String, Object>> getNovelByLidRecent(SqlSession sqlSession, int lid) {
        return sqlSession.selectList("novel.getNovelByLidRecent", lid);
    }

    public List<Map<String, Object>> getNovelByLidScore(SqlSession sqlSession, int lid) {
        return sqlSession.selectList("novel.getNovelByLidScore", lid);
    }

    public List<NovelEntity> getNovelInBookCase(SqlSession sqlSession, int rid) {
        return sqlSession.selectList("novel.getNovelInBookCase", rid);
    }

    public List<Map<String, Object>> getNovelByTitleWidly(SqlSession sqlSession, String key) {
        return sqlSession.selectList("novel.getNovelByTitleWidly", key);
    }

    public List<Map<String, Object>> getNovelByAuthor(SqlSession sqlSession, String key) {
        return sqlSession.selectList("novel.getNovelByAuthor", key);
    }

    public NovelEntity getOneInBookCase(SqlSession sqlSession, BookCaseEntity bookCase) {
        return sqlSession.selectOne("novel.getOneInBookCase", bookCase);
    }
}
