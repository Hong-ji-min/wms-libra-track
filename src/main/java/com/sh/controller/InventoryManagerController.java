package com.sh.controller;


import com.sh.model.entity.InventoryManagerDto;
import com.sh.model.entity.MemberDto;
import com.sh.model.service.InventoryManagerService;


public class InventoryManagerController {
    private InventoryManagerService inventoryManagerService = new InventoryManagerService();

    public InventoryManagerDto findInventoryManagerInfo(MemberDto memberDto) {
        InventoryManagerDto inventoryManagerDto = inventoryManagerService.findInventorymanager(memberDto);

        return inventoryManagerDto;
    }

}