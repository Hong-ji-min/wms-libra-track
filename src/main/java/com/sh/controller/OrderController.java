package com.sh.controller;


import com.sh.exception.AreaException;
import com.sh.model.dto.AreaDto;
import com.sh.model.service.AreaService;
import com.sh.exception.StockException;

import com.sh.model.dto.OrderAreaDetailDto;
import com.sh.model.dto.OrderDto;
import com.sh.model.service.BookAreaService;
import com.sh.model.service.OrderAreaService;
import com.sh.model.service.OrderService;
import com.sh.view.OrderView;

import java.util.List;

public class OrderController {

    private OrderService orderService = new OrderService();

    private AreaService areaService = new AreaService();

    private BookAreaService bookAreaService = new BookAreaService();

    private OrderAreaService orderAreaService = new OrderAreaService();


    public OrderDto findOrderByOrderId(int orderId) {
        return orderService.findOrderByOrderId(orderId);
    }

    public List<OrderDto> findOrderByInWarehousingId(int inWarehousingId) {
        List<OrderDto> orderDtoList = orderService.findOrderByInWarehousingId(inWarehousingId);
        OrderView.displayOrderDto(orderDtoList);
        return orderDtoList;
    }

    public void reserveOrder(int orderId, String location, String areaName, boolean inWarehousing) {

        AreaDto area = areaService.findByLocationAndAreaName(location, areaName);

        OrderDto order = orderService.findOrderByOrderId(orderId);

        int quantity = order.getQuantity();

        if(!inWarehousing) {
            quantity *= -1;
        }
        // 구역 소유자 확인
        if (area.getPublisherId() != orderService.findPublisherIdByOrderId(orderId)) {
            throw new StockException("해당 구역에 대한 권한이 없습니다.");
        }

        // 입고 시 구역 수용량 확인
        if (inWarehousing && quantity + area.getReserved() + area.getQuantity() > area.getCapacity()) {
            throw new StockException("수용량을 초과하였습니다.");
        }

        if (orderAreaService.findOrderAreaByOrderId(orderId) != null) {
            throw new AreaException("이미 배정된 구역이 있습니다.");
        }

        //입/출고 요청된 수량만큼 업데이트
        int bookAreaId = bookAreaService.reserveBookArea(area.getAreaId(),order.getBookId(), quantity, areaName);

        area.setReserved(area.getReserved() + quantity);

        areaService.updateArea(area);

        //int bookAreaId = 1;

        orderAreaService.insertOrderArea(bookAreaId, orderId);

    }

    // 입고 내역의 배정된 구역 확인
    // 제목, ISBN, 수량, 창고 위치, 구역 이름
    public OrderAreaDetailDto findOrderAreaDetailByOrderId(int orderId) {
        OrderAreaDetailDto orderAreaDetailDto = orderService.findOrderAreaDetailByOrderId(orderId);
        OrderView.displayOrderAreaDetail(orderAreaDetailDto);
        return orderAreaDetailDto;
    }

    public void completeOrder(int orderId, boolean inWarehousing) {

        OrderDto order = orderService.findOrderByOrderId(orderId);
        // Area quantity (reserved -= order.quantity) update

        int quantity = orderService.findOrderByOrderId(orderId).getQuantity();

        if(!inWarehousing) {
            quantity *= -1;
        }

        AreaDto area = bookAreaService.findAreaByOrderId(orderId);

        area.setReserved(area.getReserved() - quantity);
        area.setQuantity(area.getQuantity() + quantity);

        areaService.updateArea(area);

        // BookArea quantity (reserved -=  order.quantity)update
        int bookId = order.getBookId();
        bookAreaService.updateQuantity(area.getAreaId(), bookId, quantity);

    }

    // 출고 내역의 상세 정보 조회
    public List<OrderDto> findOrderByExWarehousingId(int exWarehousingId) {
        List<OrderDto> orderDtoList = orderService.findOrdersByExWarehousingId(exWarehousingId);
        OrderView.displayOrderDto(orderDtoList);
        return orderDtoList;
    }
}
