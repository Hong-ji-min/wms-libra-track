package com.sh;

import com.sh.model.dao.BookDAO;
import com.sh.model.dao.ExWarehousingDao;
import com.sh.model.dto.AreaInventoryDto;
import com.sh.model.dto.bookDto.Book;
import com.sh.model.entity.BookArea;
import com.sh.model.service.BookAreaService;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.sh.common.MyBatisTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;

public class BookTest {
    SqlSession sqlSession;
    BookDAO bookDAO;

    @BeforeEach
    void setUp() {
        this.sqlSession = getSqlSession();
        this.bookDAO = this.sqlSession.getMapper(BookDAO.class);
    }

    @AfterEach
    void tearDown() {

        this.sqlSession.rollback();
        this.sqlSession.close();
    }

    @Test
    void test() {
        Book book = bookDAO.findBookByISBN("12345678911");
        int quantity = 0;
        for (BookArea bookArea : book.getBookAreaList()) {
            quantity += bookArea.getQuantity();
        }
        assertThat(quantity).isEqualTo(50);
        BookAreaService bookAreaService = new BookAreaService();

        AreaInventoryDto areaInventoryDto = bookAreaService.findAreaInventoryByBookAreaId(book.getBookAreaList().get(0).getBookAreaId());

        System.out.println(areaInventoryDto.getAreaName());

    }

}
