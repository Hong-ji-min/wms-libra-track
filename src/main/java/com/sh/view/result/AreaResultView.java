package com.sh.view.result;

//import com.sh.inventory.model.dto.CategoryDto;
import com.sh.model.dto.AreaDto;
import com.sh.model.dto.InventoryDto;

import java.util.List;

/**
 * 요청 결과 출력 메소드 모음 (static)
 */
public class AreaResultView {
    /**
     * n건의 창고조회
     * - 최소한 비어있는 리스트 반환 (조회결과가 0행인 경우)
     * @param list
     */
    public static void displayAreaList(List<AreaDto> list) {
        if(list.isEmpty()) {
            System.out.println("> 😅😅😅 조회된 구역이 없습니다. 😅😅😅");
        }
        else {
            System.out.println("----------------------------------------------------------------------------------------------");
            System.out.printf("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\n", "AreaId", "InventoryId", "PublisherId", "AreaName", "Capacity", "Reserved", "Quantity");
            System.out.println("----------------------------------------------------------------------------------------------");
            for(AreaDto areaDto : list) {
                System.out.printf("%-10s\t%-10s\t%-10s\t%-10s\t%-10d\t%-10d\t%-10d\n",
                        areaDto.getAreaId(),
                        areaDto.getInventoryId(),
                        areaDto.getPublisherId(),
                        areaDto.getAreaName(),
                        areaDto.getCapacity(),
                        areaDto.getReserved(),
                        areaDto.getQuantity()
                    );
            }
            System.out.println("----------------------------------------------------------------------------------------------");
        }
    }

    /**
     * 조회결과 없는 경우 null이 전달된다.
     * @param areaDto
     */
    public static void displayArea(AreaDto areaDto) {
        if(areaDto == null) {
            System.out.println("> 😅😅😅 조회된 구역이 없습니다. 😅😅😅");
        }
        else {
            System.out.println("----------------------------------------------------------------------------------------------");
            System.out.println("AreaId : " + areaDto.getAreaId());
            System.out.println("InventoryId : " + areaDto.getInventoryId());
            System.out.println("PublisherId : " + areaDto.getPublisherId());
            System.out.println("AreaName : " + areaDto.getAreaName());
            System.out.println("Capacity : " + areaDto.getCapacity());
            System.out.println("Reserved : " + areaDto.getReserved());
            System.out.println("Quantity : " + areaDto.getQuantity());
            System.out.println("----------------------------------------------------------------------------------------------");
        }
    }

    public static void displayResult(String type, int result) {
        System.out.println("> 📢📢📢 " + type + " " + (result > 0 ? "성공!" : "실패!") + " 📢📢📢");
    }

    public static void displayInventoryList(List<InventoryDto> inventoryList) {
        System.out.print("> ");
        for (int i = 0; i < inventoryList.size(); i++) {
            InventoryDto inventoryDto = inventoryList.get(i);
            System.out.printf("%-10d %-10s %-10s", inventoryDto.getInventoryId(), inventoryDto.getLocation(), inventoryDto.getCapacity());
        }
        System.out.println();
    }
}
