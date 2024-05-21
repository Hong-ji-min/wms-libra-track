package com.sh.view;

import com.sh.controller.InventoryManagerController;
import com.sh.model.entity.InventoryManagerDto;
import com.sh.model.entity.MemberDto;

import java.util.Scanner;

import static com.sh.WMSApplication.*;


public class InventoryManagerView {

    Scanner sc = getScanner();

    BookMenuView bookMenuView = new BookMenuView();

    InventoryManagerController inventoryManagerController = new InventoryManagerController();

    public void inventoryManagerMainView(MemberDto memberDto) {
        String choicePublisherMenu = """
                📖📖📖출판사 매니저 메뉴📖📖📖
                =============================
                1. 입고 관리
                2. 출고 관리
                0. 나가기
                =============================
                입력 : 
                """;
        InventoryManagerDto inventoryManagerDto = inventoryManagerController.findInventoryManagerInfo(memberDto);


        if (inventoryManagerDto != null) {
            INVEN_MANAGER_ID = inventoryManagerDto.getInventoryManagerId();
        }

        while (true) {

            if(inventoryManagerDto == null) {
                inventoryManagerDto = inventoryManagerController.findInventoryManagerInfo(memberDto);

                if (inventoryManagerDto != null) {
                    INVEN_MANAGER_ID = inventoryManagerDto.getInventoryManagerId();
                }
            }
            System.out.print(choicePublisherMenu);
            int choice = sc.nextInt();

            switch (choice) {
                case 1 :
                    if (inventoryManagerDto == null) {
                        System.out.println("창고 정보가 없습니다. 창고 정보를 먼저 등록해주세요.");
                        break;
                    }
                    EX = false;
                    InWarehousingView.inWarehousingInventoryManagerMainMenu(); break;
                case 2 :
                    if (inventoryManagerDto == null) {
                        System.out.println("창고 정보가 없습니다. 창고 정보를 먼저 등록해주세요.");
                        break;
                    }
                    EX = true;
                    ExWarehousingView.exWarehousingInventoryManagerMainMenu(); break;
                case 0 :
                    return;
                default:
                    System.out.print("잘못된 입력입니다. 다시 입력해주세요 : ");
            }
        }
    }



}
