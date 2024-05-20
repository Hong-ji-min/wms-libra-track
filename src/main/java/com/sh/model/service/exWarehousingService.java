package com.sh.model.service;

import com.sh.model.dao.ExWarehousingDao;
import com.sh.model.dao.InWarehousingDao;
import com.sh.model.entity.ExWarehousing;
import com.sh.model.entity.InWarehousing;
import com.sh.model.entity.Order;
import com.sh.model.entity.Status;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class exWarehousingService {

    public List<ExWarehousing> findExWarehousingByStatus(Status status) {
        SqlSession sqlSession = getSqlSession();
        ExWarehousingDao exWarehousingDao = sqlSession.getMapper(ExWarehousingDao.class);

        List<ExWarehousing> exWarehousingList = exWarehousingDao.findExWarehousingByStatus(status);
        sqlSession.close();

        return exWarehousingList;
    }

    public void updateExWarehousingStatus(int exWarehousingId, int inventoryManagerId, Status status) {
        SqlSession sqlSession = getSqlSession();
        ExWarehousingDao exWarehousingDAO = sqlSession.getMapper(ExWarehousingDao.class);

        try {
            exWarehousingDAO.updateExWarehousingStatus(exWarehousingId, inventoryManagerId, status);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public List<ExWarehousing> findExWarehousingByPublisher(int publisherId) {
        SqlSession sqlSession = getSqlSession();
        ExWarehousingDao exWarehousingDAO = sqlSession.getMapper(ExWarehousingDao.class);

        List<ExWarehousing> exWarehousingList = exWarehousingDAO.findExWarehousingByPublisher(publisherId);
        sqlSession.close();

        return exWarehousingList;
    }

    public void insertExWarehousing(HashMap<String, Integer> orders, int publisherManagerId) {
        ExWarehousing exWarehousing = new ExWarehousing();

        exWarehousing.setPublisherManagerId(publisherManagerId);
        exWarehousing.setStatus(Status.PENDING);

        SqlSession sqlSession = getSqlSession();
        ExWarehousingDao exWarehousingDAO = sqlSession.getMapper(ExWarehousingDao.class);

        // iterate over orders
        List<Order> orderList = new ArrayList<>();
        for (String ISBN : orders.keySet()) {
            int quantity = orders.get(ISBN);
            // db query to find bookId using ISBN
            // int bookId = bookService.findBookIdByPublisherIdAndISBN(publisherId, ISBN);
            int bookId = 1;
            Order order = new Order();
            order.setQuantity(quantity);
            order.setBookId(bookId);

            orderList.add(order);

        }
        exWarehousing.setOrderList(orderList);

        try {
            int exWarehousingId = exWarehousingDAO.insertExWarehousing(exWarehousing);
            exWarehousingDAO.insertOrders(exWarehousing);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
}
