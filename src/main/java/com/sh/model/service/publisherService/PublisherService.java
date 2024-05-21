package com.sh.model.service.publisherService;

import com.sh.common.error.ErrorCode;
import com.sh.model.dao.PublisherDAO;
import com.sh.model.dto.publishserDto.Publisher;
import org.apache.ibatis.session.SqlSession;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class PublisherService {

    //출판사 이름을 통해서 출판사 Idr값 반환 받기
    public int getPublisherId(String publisherName) {
        SqlSession sqlSession = getSqlSession();
        PublisherDAO publisherDAO = sqlSession.getMapper(PublisherDAO.class);
        int publisherId = publisherDAO.getPublisherId(publisherName);
        sqlSession.close();
        return publisherId;
    }

    //출판사 등록
    public int insertPublisher(Publisher publisher) {
        SqlSession sqlSession = getSqlSession();
        PublisherDAO publisherDAO = sqlSession.getMapper(PublisherDAO.class);
        try {
            int result = publisherDAO.insertPublisher(publisher);
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally{
            sqlSession.close();
        }
    }

    //출판사 내용 수정
    public int updatePublisher(Publisher publisher) {
        SqlSession sqlSession = getSqlSession();
        PublisherDAO publisherDAO = sqlSession.getMapper(PublisherDAO.class);
        try {
            int result = publisherDAO.updatePublisher(publisher);
            sqlSession.commit();
            return publisherDAO.updatePublisher(publisher);
        } catch (Exception e) {
           sqlSession.rollback();
            throw new RuntimeException(e);
        }finally {
            sqlSession.close();
        }
    }

    //로그인한 출판사의 정보 찾기
    public Publisher findPublisherInformation(int publisherId) {
        SqlSession sqlSession = getSqlSession();
        PublisherDAO publisherDAO = sqlSession.getMapper(PublisherDAO.class);
        try {
            return publisherDAO.findPublisherInformation(publisherId);
        } catch (Exception e) {
            sqlSession.close();
            throw new RuntimeException(e);
        } finally{
            sqlSession.close();
        }
    }

    public int deletePublisher(int id) {
        SqlSession sqlSession = getSqlSession();
        PublisherDAO publisherDAO = sqlSession.getMapper(PublisherDAO.class);
        try {
            return publisherDAO.deletePublisher(id);
        } catch (Exception e) {
            sqlSession.commit();
            throw new RuntimeException(e);
        } finally{
            sqlSession.close();
        }

    }


    public Publisher findByName(String publisher) {

        SqlSession sqlSession = getSqlSession();
        PublisherDAO publisherDAO = sqlSession.getMapper(PublisherDAO.class);
        try {
            return publisherDAO.findByName(publisher);
        } catch (Exception e) {
            sqlSession.close();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
}
