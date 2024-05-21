package com.sh.view;

import com.sh.controller.OrderController;
import com.sh.exception.AreaException;
import com.sh.exception.StockException;
import com.sh.model.dto.OrderAreaDetailDto;
import com.sh.model.dto.OrderDto;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.sh.WMSApplication.EX;
import static com.sh.WMSApplication.PUB_MANAGER_ID;
import static com.sh.WMSApplication.getScanner;

@AllArgsConstructor
public class OrderView {

    private static final OrderController orderController = new OrderController();

    public static List<Integer> ids = new ArrayList<>();

    private static final Scanner scanner = getScanner();


    public static void orderDetailPublisherMenu() {
        String menu = """
                ======================
                1. 입고 완료된 주문 상세 조회
                0. 뒤로 가기
                ======================
                입력 : 
                """;
        while(true) {
            System.out.println(menu);
            String choice = OrderView.scanner.next();
            switch (choice) {
                case "1":
                    System.out.printf("입고 주문 정보 INDEX를 입력해주세요. (입고 주문 정보 INDEX : %s)\n", "1 - " + ids.size());
                    orderController.findOrderAreaDetailByOrderId(ids.get(Integer.parseInt(scanner.next()) - 1));
                    break;
                case "0":
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    public static void exOrderDetailPublisherMenu() {
        String menu = """
                ======================
                1. 출고 완료된 주문 상세 조회
                0. 뒤로 가기
                ======================
                입력 : 
                """;
        while(true) {
            System.out.println(menu);
            String choice = OrderView.scanner.next();
            switch (choice) {
                case "1":
                    System.out.printf("출고 주문 정보 INDEX를 입력해주세요. (출고 주문 정보 INDEX : %s)\n", "1 - " + ids.size());
                    orderController.findOrderAreaDetailByOrderId(ids.get(Integer.parseInt(scanner.next()) - 1));
                    break;
                case "0":
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    public static void orderDetailInventoryMenu() {
        String menu = """
                ======================
                1. 입고 주문별 구역 상세 정보 조회
                2. 입고 주문별 구역 배정
                3. 입고 주문별 입하 처리
                0. 뒤로 가기
                ======================
                입력 : 
                """;
        while(true) {
            try {
                System.out.println(menu);
                String choice = OrderView.scanner.next();
                switch (choice) {
                    case "1":
                        System.out.printf("입고 주문 정보 INDEX를 입력해주세요. (입고 주문 정보 INDEX : %s)\n",  "1 - " + ids.size());
                        orderController.findOrderAreaDetailByOrderId(ids.get(Integer.parseInt(scanner.next()) - 1));
                        break;
                    case "2":
                        System.out.printf("입고 주문 정보 INDEX를 입력해주세요. (입고 주문 정보 INDEX : %s)\n",  "1 - " + ids.size());
                        int idx = Integer.parseInt(scanner.next()) - 1;
                        System.out.println("창고 위치를 입력해주세요.");
                        String location = scanner.next();
                        System.out.println("입고 구역명을 입력해주세요.");
                        String areaName = scanner.next();
                        orderController.reserveOrder(ids.get(idx), location, areaName, true);
                        break;
                    case "3":
                        System.out.printf("입고 주문 정보 INDEX를 입력해주세요. (입고 정보 INDEX : %s)\n",  "1 - " + ids.size());
                        orderController.completeOrder(ids.get(Integer.parseInt(scanner.next()) - 1), true);
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                }
            } catch (StockException | AreaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void exOrderDetailInventoryMenu() {
        String menu = """
                ======================
                1. 출고 주문별 구역 상세 정보 조회
                2. 출고 주문별 구역 배정
                3. 출고 주문별 입하 처리
                0. 뒤로 가기
                ======================
                입력 : 
                """;
        while(true) {
            try {
                System.out.println(menu);
                String choice = OrderView.scanner.next();
                switch (choice) {
                    case "1":
                        System.out.printf("출고 주문 정보 INDEX를 입력해주세요. (출고 주문 정보 INDEX : %s)\n", "1 - " + ids.size());
                        orderController.findOrderAreaDetailByOrderId(ids.get(Integer.parseInt(scanner.next()) - 1));
                        break;
                    case "2":
                        System.out.printf("출고 주문 정보 INDEX를 입력해주세요. (출고 주문 정보 INDEX : %s)\n", "1 - " + ids.size());
                        int idx = Integer.parseInt(scanner.next()) - 1;
                        System.out.println("창고 위치를 입력해주세요.");
                        String location = scanner.next();
                        System.out.println("출고 구역명을 입력해주세요.");
                        String areaName = scanner.next();
                        orderController.reserveOrder(ids.get(idx), location, areaName, false);
                        break;
                    case "3":
                        System.out.printf("출고 주문 정보 INDEX를 입력해주세요. (출고 정보 INDEX : %s)\n", "1 - " + ids.size());
                        orderController.completeOrder(ids.get(Integer.parseInt(scanner.next()) - 1), false);
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                }
            } catch (StockException | AreaException e) {
                System.out.println(e.getMessage());
            }
        }
    }



    public static void displayOrderDto(List<OrderDto> orderList) {
        ids.clear();
        if (orderList.isEmpty()) {
            System.out.println("주문 정보가 없습니다.");
        } else {
            System.out.println("-----------------------------------------------");
            System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n", "idx", "Order Id", "title", "author", "ISBN", "quantity");
            System.out.println("-----------------------------------------------");
            int idx = 1;
            for(OrderDto order : orderList) {
                ids.add(order.getOrderId());
                System.out.printf("%d\t%d\t%s\t%s\t%s\t%d\n",
                        idx,
                        order.getOrderId(),
                        order.getTitle(),
                        order.getAuthor(),
                        order.getISBN(),
                        order.getQuantity()
                );
                idx += 1;
            }
            System.out.println("-----------------------------------------------");
            if(PUB_MANAGER_ID != 0) { //  출판사
                if (EX) {
                    exOrderDetailPublisherMenu();
                } else {
                    orderDetailPublisherMenu();
                }
            } else { // 창고
                if(EX) { // 출고
                    exOrderDetailInventoryMenu();
                } else { // 입고
                    orderDetailInventoryMenu();
                }
            }


        }


    }


    public static void displayOrderAreaDetail(OrderAreaDetailDto orderAreaDetailDto) {
        if(orderAreaDetailDto == null) {
            System.out.println("주문 정보가 없습니다.");
        } else {
            System.out.println("----------------------------");
            System.out.println("Title : " + orderAreaDetailDto.getTitle());
            System.out.println("ISBN : " + orderAreaDetailDto.getISBN());
            System.out.println("Quantity : " + orderAreaDetailDto.getQuantity());
            System.out.println("Location : " + orderAreaDetailDto.getLocation());
            System.out.println("Area Name : " + orderAreaDetailDto.getAreaName());
            System.out.println("----------------------------");
        }
    }
}
