package com.sh.view.result;

//import com.sh.inventory.model.dto.CategoryDto;
import com.sh.model.dto.InventoryDto;

import java.util.List;

/**
 * 요청 결과 출력 메소드 모음 (static)
 */
public class InventoryResultView {
    /**
     * n건의 창고조회
     * - 최소한 비어있는 리스트 반환 (조회결과가 0행인 경우)
     * @param list
     */
    public static void displayInventoryList(List<InventoryDto> list) {
        if(list.isEmpty()) {
            System.out.println("> 😅😅😅 조회된 창고가 없습니다. 😅😅😅");
        }
        else {
            System.out.println("----------------------------------------------------------------------------------------------");
            System.out.printf("%-10s\t%-10s\t%-10s\n", "InventoryId", "Location", "Capacity");
            System.out.println("----------------------------------------------------------------------------------------------");
            for(InventoryDto inventoryDto : list) {
                System.out.printf("%-10s\t%-10s\t%-10s\n",
                        inventoryDto.getInventoryId(),
                        inventoryDto.getLocation(),
                        inventoryDto.getCapacity()
                    );
            }
            System.out.println("----------------------------------------------------------------------------------------------");
        }
    }

    /**
     * 조회결과 없는 경우 null이 전달된다.
     * @param inventoryDto
     */
    public static void displayInventory(InventoryDto inventoryDto) {
        if(inventoryDto == null) {
            System.out.println("> 😅😅😅 조회된 창고가 없습니다. 😅😅😅");
        }
        else {
            System.out.println("----------------------------------------------------------------------------------------------");
            System.out.println("InventoryId : " + inventoryDto.getInventoryId());
            System.out.println("Location : " + inventoryDto.getLocation());
            System.out.println("Capacity : " + inventoryDto.getCapacity());
            System.out.println("----------------------------------------------------------------------------------------------");
        }
    }

    public static void displayResult(String type, int result) {
        System.out.println("> 📢📢📢 " + type + " " + (result > 0 ? "성공!" : "실패!") + " 📢📢📢");
    }

}
