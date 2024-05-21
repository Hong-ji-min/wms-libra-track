package com.sh.view;

import com.sh.controller.InventoryController;
import com.sh.model.dto.InventoryDto;

import java.util.Scanner;

import static com.sh.WMSApplication.getScanner;

/**
 * <pre>
 * View단 
 * - 사용자인터페이스 담당
 * - 콘솔창고 제공, 사용자입력값 처리, 요청에 대한 응답 출력
 * - 사용자 요청에 따른 컨트롤러단에 메시지 전송
 * </pre> 
 */
public class InventoryView {
    private InventoryController inventoryController = new InventoryController();
    private Scanner sc = getScanner();
    public void inventoryMenu() {
        String menu = """
            ==============================================================================================
            1. 전체 창고 조회
            2. 창고 코드로 창고 조회
            3. 창고 등록
            4. 창고 수정
            5. 창고 삭제
            0. 종료
            ==============================================================================================
            입력 : """;
        while(true) {
            System.out.print(menu);
            String choice = sc.next();
            switch (choice) {
                case "1" : inventoryController.findAllInventory(); break;
                case "2" : inventoryController.findInventoryByInventoryId(inputInventoryId("조회")); break;
                case "3" : inventoryController.insertInventory(inputInventory()); break;
                case "4" : inventoryController.updateInventory(inputInventoryUpdated()); break;
                case "5" : inventoryController.deleteInventory(inputInventoryId("삭제")); break;
                case "0" : return;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

    private InventoryDto inputInventoryUpdated() {
        inventoryController.findAllInventory();
        System.out.println("> ✏️✏️✏️ 수정할 창고 정보를 작성해주세요. ✏️✏️✏️");
        System.out.println("> 수정하고 싶은 창고의 코드 : ");
        int inventoryId = sc.nextInt();
        System.out.println("> 수정된 창고 위치 : ");
        String location = sc.next();
        System.out.println("> 수정된 창고 용량 : ");
        int capacity = sc.nextInt();
        return new InventoryDto(inventoryId, location, capacity);
    }

    private InventoryDto inputInventory() {
        System.out.println("> ✏️✏️✏️ 등록할 창고 정보를 작성해주세요. ✏️✏️✏️");
        System.out.println("> 창고 위치 : ");
        String location = sc.next();
        System.out.println("> 창고 용량 : ");
        int capacity = sc.nextInt();
        return new InventoryDto(0, location, capacity);
    }

    private int inputInventoryId(String type) {
        inventoryController.findAllInventory();
        System.out.printf("> %s할 창고코드 : ", type);
        return sc.nextInt();
    }


    public void inventoryManagerMenu() {
        String menu = """
            ==============================================================================================
            1. 전체 창고 조회
            2. 창고 코드로 창고 조회
            0. 종료
            ==============================================================================================
            입력 : """;
        while(true) {
            System.out.print(menu);
            String choice = sc.next();
            switch (choice) {
                case "1" : inventoryController.findAllInventory(); break;
                case "2" : inventoryController.findInventoryByInventoryId(inputInventoryId("조회")); break;
                case "0" : return;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }
}
