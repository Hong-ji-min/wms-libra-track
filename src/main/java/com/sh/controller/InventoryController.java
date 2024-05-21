package com.sh.controller;

//import com.sh.common.ErrorView;
//import com.sh.common.error.ErrorCode;
//import com.sh.inventory.model.dto.CategoryDto;
import com.sh.model.dto.InventoryDto;
import com.sh.model.service.InventoryService;
import com.sh.view.error.InventoryErrorView;
import com.sh.view.result.InventoryResultView;

import java.util.List;

/**
 * <pre>
 * Controller
 * - mvc구조의 제어부
 * - view단으로 부터 사용자 요청 수집
 * - 사용자 요청에 적합한 Service 선정
 * - Service에 전달할 데이터를 가공 및 요청
 * - 응답에 대한 view처리
 * - 예외처리 (service, dao처리중에 발생된 예외수집, 로깅, 사용자 전달)
 * </pre>
 */
public class InventoryController {
    private InventoryService inventoryService = new InventoryService();

    public void findAllInventory() {
        try {
            // n개의 InventoryDto 반환 : List<InventoryDto>
            List<InventoryDto> list = inventoryService.findAllInventory();
            InventoryResultView.displayInventoryList(list);
        } catch (Exception e) {
            InventoryErrorView.displayError("처리 중 오류가 발생하였습니다. 다시 시도해주세요.");
        }
    }

    public void findInventoryByInventoryId(int inventoryId) {
        try {
            // 0~1개의 InventoryDto 반환 : InventoryDto
            InventoryDto inventoryDto = inventoryService.findInventoryByInventoryId(inventoryId);
            InventoryResultView.displayInventory(inventoryDto);
        } catch (Exception e) {
            InventoryErrorView.displayError("처리 중 오류가 발생하였습니다. 다시 시도해주세요.");
        }
    }

    public void insertInventory(InventoryDto inventoryDto) {
        try {
            int result = inventoryService.insertInventory(inventoryDto);
            InventoryResultView.displayResult("창고 등록", result);
        } catch (Exception e) {
            InventoryErrorView.displayError("처리 중 오류가 발생하였습니다. 다시 시도해주세요.");
        }
    }

    public void updateInventory(InventoryDto inventoryDto) {
        try {
            int result = inventoryService.updateInventory(inventoryDto);
            InventoryResultView.displayResult("창고 수정", result);
        } catch (Exception e) {
            InventoryErrorView.displayError("처리 중 오류가 발생하였습니다. 다시 시도해주세요.");
        }
    }

    public void deleteInventory(int inventoryId) {
        try {
            int result = inventoryService.deleteInventory(inventoryId);
            InventoryResultView.displayResult("창고 삭제", result);
        } catch (Exception e) {
            InventoryErrorView.displayError("처리 중 오류가 발생하였습니다. 다시 시도해주세요.");
        }
    }

}
