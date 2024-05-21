package com.sh.controller;

//import com.sh.common.ErrorView;
//import com.sh.common.error.ErrorCode;
//import com.sh.inventory.model.dto.CategoryDto;
import com.sh.model.dto.AreaDto;
import com.sh.model.service.AreaService;
import com.sh.view.error.AreaErrorView;
import com.sh.view.result.AreaResultView;
import com.sh.model.dto.InventoryDto;

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
public class AreaController {
    private AreaService areaService = new AreaService();

    public void findAllArea() {
        try {
            // n개의 InventoryDto 반환 : List<InventoryDto>
            List<AreaDto> list = areaService.findAllArea();
            AreaResultView.displayAreaList(list);
        } catch (Exception e) {
            AreaErrorView.displayError("처리 중 오류가 발생하였습니다. 다시 시도해주세요.");
        }
    }

    public void findAreaByAreaId(int areaId) {
        try {
            // 0~1개의 InventoryDto 반환 : InventoryDto
            AreaDto areaDto = areaService.findAreaByAreaId(areaId);
            AreaResultView.displayArea(areaDto);
        } catch (Exception e) {
            AreaErrorView.displayError("처리 중 오류가 발생하였습니다. 다시 시도해주세요.");
        }
    }
    public void findByLocationAndAreaName(String location, String areaName) {
        try {
            // 0~1개의 InventoryDto 반환 : InventoryDto
            AreaDto areaDto = areaService.findByLocationAndAreaName(location, areaName);
            AreaResultView.displayArea(areaDto);
        } catch (Exception e) {
            AreaErrorView.displayError("처리 중 오류가 발생하였습니다. 다시 시도해주세요.");
        }
    }

    public AreaDto findReservedAreaByAreaId(int areaId) {
        try {
            return areaService.findAreaByAreaId(areaId);
        } catch (Exception e) {
            AreaErrorView.displayError("처리 중 오류가 발생하였습니다. 다시 시도해주세요.");
            return null;
        }
    }

    public void insertArea(AreaDto areaDto) {
        try {
            int result = areaService.insertArea(areaDto);
            AreaResultView.displayResult("구역 등록", result);
        } catch (Exception e) {
            AreaErrorView.displayError("처리 중 오류가 발생하였습니다. 다시 시도해주세요.");
        }
    }

    public void updateArea(AreaDto areaDto) {
        try {
            int result = areaService.updateArea(areaDto);
            AreaResultView.displayResult("구역 수정", result);
        } catch (Exception e) {
            AreaErrorView.displayError("처리 중 오류가 발생하였습니다. 다시 시도해주세요.");
        }
    }

    public void updateReserved(AreaDto areaDto) {
        try {
            int result = areaService.updateReserved(areaDto);
            AreaResultView.displayResult("구역 reserved 수정", result);
        } catch (Exception e) {
            AreaErrorView.displayError("처리 중 오류가 발생하였습니다. 다시 시도해주세요.");
        }
    }

    public void deleteArea(int areaId) {
        try {
            int result = areaService.deleteArea(areaId);
            AreaResultView.displayResult("구역 삭제", result);
        } catch (Exception e) {
            AreaErrorView.displayError("처리 중 오류가 발생하였습니다. 다시 시도해주세요.");
        }
    }

    public void findAreaByInventoryId(int categoryCode) {
        try {
            List<AreaDto> list = areaService.findAreaByInventoryId(categoryCode);
            AreaResultView.displayAreaList(list);
        } catch (Exception e) {
            AreaErrorView.displayError("처리 중 오류가 발생하였습니다. 다시 시도해주세요.");
        }
    }

    public void findAllInventory() {
        try {
            List<InventoryDto> inventoryList = areaService.findAllInventory();
            AreaResultView.displayInventoryList(inventoryList);
        } catch (Exception e) {
            AreaErrorView.displayError("처리 중 오류가 발생하였습니다. 다시 시도해주세요.");
        }
    }

}
