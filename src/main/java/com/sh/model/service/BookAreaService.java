package com.sh.model.service;

import com.sh.model.dto.AreaDto;
import com.sh.exception.StockException;

import com.sh.model.dao.BookAreaDao;
import com.sh.model.dto.AreaInventoryDto;
import com.sh.model.dto.OrderAreaDetailDto;
import com.sh.model.entity.BookArea;
import org.apache.ibatis.session.SqlSession;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class BookAreaService {

    // 입고 허가를 위한 구역 배정
    public int  reserveBookArea( int areaId, int bookId, int quantity, String areaName) {

        SqlSession sqlSession = getSqlSession();
        BookAreaDao bookAreaDao = sqlSession.getMapper(BookAreaDao.class);

        BookArea bookArea = bookAreaDao.findBookAreaByAreaIdAndBookId(areaId, bookId);

        int book_area_id = 0;
        try {
            if (bookArea == null) {
                if (quantity < 0) {
                    throw new StockException("재고가 부족합니다.");
                }
                bookArea = new BookArea();
                bookArea.setAreaId(areaId);
                bookArea.setBookId(bookId);
                bookArea.setQuantity(0);
                bookArea.setReserved(quantity);
                bookAreaDao.insertBookArea(bookArea);
                book_area_id = bookArea.getBookAreaId();
            } else {
                // 출고 시 선택한 구역에 재고 수량 확인
                if (quantity < 0 && bookArea.getQuantity() +
                        (Math.min(bookArea.getReserved(), 0))  < -quantity) {
                    throw new StockException("재고가 부족합니다.");
                }
                book_area_id = bookArea.getBookAreaId();
                bookArea.setReserved(bookArea.getReserved() + quantity);
                bookAreaDao.updateBookArea(bookArea);
            }
            sqlSession.commit();
        } catch (Exception e) {
                sqlSession.rollback();
                throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
        return book_area_id;

    }



    // 입고 후 배정된 구역 업데이트
    public void updateQuantity(int areaId, int bookId, int quantity) {
        SqlSession sqlSession = getSqlSession();
        BookAreaDao bookAreaDao = sqlSession.getMapper(BookAreaDao.class);
        try {
            BookArea bookArea = bookAreaDao.findBookAreaByAreaIdAndBookId(areaId, bookId);
            bookArea.setQuantity(bookArea.getQuantity() + quantity);
            bookArea.setReserved(bookArea.getReserved() - quantity);
            bookAreaDao.updateBookArea(bookArea);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }


    }

    public AreaDto findAreaByOrderId(int orderId) {
        SqlSession sqlSession = getSqlSession();
        BookAreaDao bookAreaDao = sqlSession.getMapper(BookAreaDao.class);
        AreaDto area = bookAreaDao.findAreaByOrderId(orderId);
        sqlSession.close();
        return area;
    }

    public AreaInventoryDto findAreaInventoryByBookAreaId(int bookAreaId){
        SqlSession sqlSession = getSqlSession();
        BookAreaDao bookAreaDao = sqlSession.getMapper(BookAreaDao.class);
        AreaInventoryDto areaInventoryDto = bookAreaDao.findAreaInventoryByBookAreaId(bookAreaId);
        sqlSession.close();
        return areaInventoryDto;
    }
}
