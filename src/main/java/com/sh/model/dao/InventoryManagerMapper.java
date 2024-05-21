package com.sh.model.dao;

import com.sh.model.entity.InventoryManagerDto;
import com.sh.model.entity.MemberDto;

public interface InventoryManagerMapper {

    int insertInventoryManager(InventoryManagerDto inventoryManagerDto);


    InventoryManagerDto findInventorymanager(MemberDto memberDto);
}