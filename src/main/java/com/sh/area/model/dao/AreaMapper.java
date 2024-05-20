package com.sh.area.model.dao;

import com.sh.area.model.dto.AreaDto;
import com.sh.inventory.model.dto.InventoryDto;

import java.util.List;

public interface AreaMapper {
    List<AreaDto> findAllArea();
    AreaDto findAreaByAreaId(int areaId);
    List<AreaDto> findAreaByAreaName(String areaName);
    List<AreaDto> findAreaByCapacity(int capacity);
    int insertArea(AreaDto areaDto);
    int updateArea(AreaDto areaDto);
    int deleteArea(int areaId);

    List<AreaDto> findAreaByInventoryId(int inventoryId);
    List<InventoryDto> findAllInventory();
}
