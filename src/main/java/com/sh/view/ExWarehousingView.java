package com.sh.view;

import com.sh.controller.ExWarehousingController;
import com.sh.controller.OrderController;
import com.sh.exception.StockException;
import com.sh.model.entity.ExWarehousing;
import com.sh.model.entity.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

import static com.sh.WMSApplication.*;

@Getter
@Setter
public class ExWarehousingView {
    private  static List<Integer> ids = new ArrayList<>();

    private static Scanner scanner = getScanner();

    private static OrderView orderView;

    public static ExWarehousingController exWarehousingController = new ExWarehousingController();

    public static OrderController orderController = new OrderController();

    // 출판사 직원 메뉴
    public static void exWarehousingPublisherMainMenu() {
        String exWarehousingMenu = """
                📦📦📦 출고 관리 📦📦📦
                ======================
                1. 출고 정보 조회
                2. 출고 정보 등록(요청)
                0. 뒤로 가기
                ======================
                입력 : 
                """;
        while (true) {
            try {
                System.out.println(exWarehousingMenu);
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        exWarehousingRead();
                        break;
                    case "2":
                        exWarehousingRegister();
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }

    }

    public static void exWarehousingRegister() {
        System.out.println("출고 정보를 등록합니다.");
        HashMap<String, Integer> orders = new HashMap<>();
        String address = null;
        String receiver = null;
        String receiverPhone = null;
        String receiverEmail = null;

        while(true) {
            System.out.println("도서 ISBN 번호를 입력해주세요. (exit 입력시 종료)");
            String isbn = scanner.nextLine();
            if(Objects.equals(isbn, "exit")) {
                break;
            }
            System.out.println("도서 출고 수량을 입력해주세요.");
            int quantity = Integer.parseInt(scanner.nextLine());
            orders.put(isbn, quantity);
            System.out.println("주소를 입력해주세요.");
            address = scanner.nextLine();
            System.out.println("수취인 성함을 입력해주세요.");
            receiver = scanner.nextLine();
            System.out.println("수취인 전화번호를 입력해주세요.");
            receiverPhone = scanner.nextLine();
            System.out.println("수취인 이메일을 입력해주세요");
            receiverEmail = scanner.nextLine();
        }

        exWarehousingController.insertExWarehousing(orders, PUB_MANAGER_ID, address, receiver, receiverPhone, receiverEmail);

        System.out.println("출고 정보 등록이 완료되었습니다.");

    }

    public static void exWarehousingRead() {
        System.out.println("출고 정보를 조회합니다.");

        String readMenu = """
                📦📦📦 출고 정보 조회 📦📦📦
                ======================
                1. 출판사 전체 출고 정보 조회
                2. 출판사 출고 상태별 출고 정보 조회
                3. 나의 전체 출고 정보 조회
                4. 나의 출고 상태별 출고 정보 조회
                0. 뒤로 가기
                ======================
                입력 : 
                """;

        while(true) {
            System.out.println(readMenu);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    exWarehousingController.findByPublisherId(PUB_ID);
                    break;
                case "2":
                    exWarehousingController.findByPublisherIdAndStatus(PUB_ID, Status.valueOf(scanner.nextLine()));
                    break;
                case "3":
                    exWarehousingController.findByPublisherManager(PUB_MANAGER_ID);
                    break;
                case "4":
                    exWarehousingController.findByPublisherManagerAndStatus(PUB_MANAGER_ID, Status.valueOf(scanner.nextLine()));
                    break;
                case "0":
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }


    }

    public static void displayExWarehousing(List<ExWarehousing> exWarehousing) {
        ids.clear();
        if (exWarehousing.isEmpty()) {
            System.out.println("출고 정보가 없습니다.");
        } else {
            System.out.println("-----------------------------------------------");
            System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", "idx", "Address", "Receiver", "Receiver Phone", "Receiver Email", "Date", "Order List", "Status");
            System.out.println("-----------------------------------------------");
            int idx = 1;
            for(ExWarehousing ex : exWarehousing) {
                ids.add(ex.getExWarehousingId());
                System.out.printf("%d\t%d\t%s\t%s\t%s\t%s\t%s\t%s\n",
                        idx,
                        ex.getExWarehousingId(),
                        ex.getAddress(),
                        ex.getReceiver(),
                        ex.getReceiverPhone(),
                        ex.getReceiverEmail(),
                        ex.getDate(),
                        ex.getStatus()
                );
                idx += 1;
            }
            System.out.println("-----------------------------------------------");

            if(PUB_MANAGER_ID != 0) {
                exWarehousingPublisherReadMenu();
            } else {
                exWarehousingInventoryManagerReadMenu();
            }
        }
    }
    public static void exWarehousingPublisherReadMenu() {

        System.out.println("출고 상세 정보를 조회합니다.");

        String menu = """
                📦📦📦 출고 정보 상세 조회 📦📦📦
                ======================
                1. 출고 정보 상세 조회
                0. 뒤로 가기
                ======================
                입력 : 
                """;

        while(true) {
            System.out.println(menu);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.printf("출고 정보 ID를 입력해주세요. (출고 정보 ID : %s)\n", "1 - " + ids.size());
                    orderController.findOrderByExWarehousingId(ids.get(Integer.parseInt(scanner.nextLine()) - 1));
                    break;
                case "0":
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }

        }
    }

    // 출고 관리자 메뉴
    public static void exWarehousingInventoryManagerMainMenu() {
        String exWarehousingMenu = """
                📦📦📦 출고 관리 📦📦📦
                ======================
                1. 출고 상태별 조회
                0. 뒤로 가기
                ======================
                입력 : 
                """;
        while (true) {
            try {
                System.out.println(exWarehousingMenu);
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        // 잘못 선택된 status 입력시 재입력
                        System.out.println("출고 상태를 입력해주세요. (PENDING, ACCEPTED, REJECTED, COMPLETED)");
                        Status status;
                        try {
                            status = Status.valueOf(scanner.nextLine());
                        } catch (Exception exception) {
                            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                            break;
                        }
                        exWarehousingController.findExWarehousingByStatus(status);
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                }
            } catch (StockException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }

    }

    public static void exWarehousingInventoryManagerReadMenu() {
        String exWarehousingMenu = """
                📦📦📦 출고 내역 관리 📦📦📦
                ======================
                1. 출고 정보 상세 조회
                2. 출고 상태 변경
                0. 뒤로 가기
                ======================
                입력 : 
                """;
        while(true) {
            try {
                System.out.println(exWarehousingMenu);
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        System.out.printf("출고 정보 ID를 입력해주세요. (출고 정보 ID : %s)\n", "1 - " + ids.size());
                        orderController.findOrderByExWarehousingId(ids.get(Integer.parseInt(scanner.nextLine()) - 1));
                        break;
                    case "2":
                        System.out.printf("출고 정보 ID를 입력해주세요. (출고 정보 ID : %s)\n", "1 - " + ids.size());
                        int index = ids.get(Integer.parseInt(scanner.nextLine()) - 1);
                        System.out.println("출고 상태를 입력해주세요. (ACCEPTED, REJECTED, COMPLETED)");
                        Status status;
                        try {
                            status = Status.valueOf(scanner.nextLine());
                        } catch (Exception exception) {
                            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                            break;
                        }
                        exWarehousingController.updateExWarehousingStatus(index, INVEN_MANAGER_ID, status);
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                }
            } catch (StockException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }

    }
}
