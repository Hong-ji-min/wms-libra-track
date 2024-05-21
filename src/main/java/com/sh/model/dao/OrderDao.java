package com.sh.model.dao;

import com.sh.model.dto.OrderAreaDetailDto;
import com.sh.model.dto.OrderDto;
import com.sh.model.entity.Order;

import java.util.List;

public interface OrderDao {

    int insertOrder(Order order);

    List<OrderDto> findOrderByInWarehousingId(int inWarehousingId);

    OrderDto findOrderByOrderId(int orderId);

    int findPublisherIdByOrderId(int orderId);

    OrderAreaDetailDto findOrderAreaDetailByOrderId(int orderId);

    List<OrderDto> findOrdersByExWarehousingId(int exWarehousingId);


}
