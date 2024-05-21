package com.sh.model.service;

import com.sh.exception.RequestException;
import com.sh.model.dao.ExWarehousingDao;
import com.sh.model.dao.InWarehousingDao;
import com.sh.model.dto.OrderDto;
import com.sh.model.dto.bookDto.Book;
import com.sh.model.dto.publishserDto.PublisherManager;
import com.sh.model.entity.ExWarehousing;
import com.sh.model.entity.Order;
import com.sh.model.entity.PublisherManagerDto;
import com.sh.model.entity.Status;
import com.sh.model.service.bookService.BookService;
import com.sh.model.service.publisherService.PublisherService;
import org.apache.ibatis.session.SqlSession;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class ExWarehousingService {

    private final BookService bookService = new BookService();

    private final PublisherManagerService publisherManagerService = new PublisherManagerService();

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

    public List<ExWarehousing> findExWarehousingByPublisherManagerId(int publisherManagerId) {
        SqlSession sqlSession = getSqlSession();
        ExWarehousingDao exWarehousingDao = sqlSession.getMapper(ExWarehousingDao.class);

        List<ExWarehousing> exWarehousingList = exWarehousingDao.findExWarehousingByPublisherManager(publisherManagerId);
        sqlSession.close();
        return exWarehousingList;
    }

    public List<ExWarehousing> findExWarehousingByPublisherIdAndStatus(int publisherId, Status status) {
        SqlSession sqlSession = getSqlSession();
        ExWarehousingDao exWarehousingDao = sqlSession.getMapper(ExWarehousingDao.class);

        List<ExWarehousing> exWarehousingList = exWarehousingDao.findExWarehousingByPublisherIdAndStatus(publisherId, status);
        sqlSession.close();
        return exWarehousingList;
    }

    public List<ExWarehousing> findExWarehousingByPublisherManagerIdAndStatus(int publisherManagerId, Status status) {
        SqlSession sqlSession = getSqlSession();
        ExWarehousingDao exWarehousingDao = sqlSession.getMapper(ExWarehousingDao.class);

        List<ExWarehousing> exWarehousingList = exWarehousingDao.findExWarehousingByPublisherManagerIdAndStatus(publisherManagerId, status);
        sqlSession.close();
        return exWarehousingList;
    }

    public void insertExWarehousing(HashMap<String, Integer> orders, int publisherManagerId, String address, String receiver, String receiverPhone, String receiverEmail) {
        ExWarehousing exWarehousing = new ExWarehousing();

        exWarehousing.setPublisherManagerId(publisherManagerId);
        exWarehousing.setDate(new Timestamp(System.currentTimeMillis()));
        exWarehousing.setStatus(Status.PENDING);
        exWarehousing.setAddress(address);
        exWarehousing.setReceiver(receiver);
        exWarehousing.setReceiverPhone(receiverPhone);
        exWarehousing.setReceiverEmail(receiverEmail);

        PublisherManagerDto publisherManager = publisherManagerService.findByPublisherManagerId(publisherManagerId);

        int publishesrId = publisherManager.getPublisherId();

        // iterate over orders
        List<Order> orderList = new ArrayList<>();
        for (String ISBN : orders.keySet()) {
            int quantity = orders.get(ISBN);
            // db query to find bookId using ISBN
            Book book = bookService.findBookByISBN(ISBN);

            if (book == null) {
                throw new RequestException("등록되지 않은 도서 입니다.");
            }
            if (book.getPublisherId() != publishesrId) {
                throw new RequestException("해당 출판사의 도서가 아닙니다.");
            }
            int bookId = book.getBookId();
            
            Order order = new Order();
            order.setQuantity(quantity);
            order.setBookId(bookId);

            orderList.add(order);

        }
        exWarehousing.setOrderList(orderList);

        SqlSession sqlSession = getSqlSession();
        ExWarehousingDao exWarehousingDAO = sqlSession.getMapper(ExWarehousingDao.class);


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
