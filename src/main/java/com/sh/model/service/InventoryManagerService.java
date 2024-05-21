package com.sh.model.service;

import com.sh.model.dao.InventoryManagerMapper;
import com.sh.model.entity.InventoryManagerDto;
import com.sh.model.entity.MemberDto;
import org.apache.ibatis.session.SqlSession;

import java.sql.Timestamp;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class InventoryManagerService {


    public InventoryManagerDto findInventorymanager(MemberDto memberDto) {
        SqlSession sqlSession = getSqlSession();
        InventoryManagerMapper inventoryManagerMapper = sqlSession.getMapper(InventoryManagerMapper.class);

        InventoryManagerDto inventoryManagerDto = inventoryManagerMapper.findInventorymanager(memberDto);
        sqlSession.close();

        return inventoryManagerDto;
    }
}

