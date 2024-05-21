package com.sh.model.service;

import com.sh.model.dao.AreaMapper;
import com.sh.model.dto.AreaDto;
import com.sh.model.dto.InventoryDto;
import com.sh.model.dto.OrderAreaDetailDto;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class AreaService {
    public List<AreaDto> findAllArea() {
        SqlSession sqlSession = getSqlSession();
        AreaMapper areaMapper = sqlSession.getMapper(AreaMapper.class);
        List<AreaDto> list = areaMapper.findAllArea();
        sqlSession.close();
        return list;
    }

    public AreaDto findAreaByAreaId(int areaId) {
        SqlSession sqlSession = getSqlSession();
        AreaMapper areaMapper = sqlSession.getMapper(AreaMapper.class);
        AreaDto areaDto = areaMapper.findAreaByAreaId(areaId);
        sqlSession.close();
        return areaDto;
    }
    public AreaDto findByLocationAndAreaName(String location, String areaName) {
        SqlSession sqlSession = getSqlSession();
        AreaMapper areaMapper = sqlSession.getMapper(AreaMapper.class);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("location", location);
        map.put("areaName", areaName);
        AreaDto areaDto = areaMapper.findByLocationAndAreaName(map);
        sqlSession.close();
        return areaDto;
    }

    public int insertArea(AreaDto areaDto) {
        SqlSession sqlSession = getSqlSession();
        AreaMapper areaMapper = sqlSession.getMapper(AreaMapper.class);
        try {
            // dao 메세지 전달
            int result = areaMapper.insertArea(areaDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }


    public int updateArea(AreaDto areaDto) {
        SqlSession sqlSession = getSqlSession();
        AreaMapper areaMapper = sqlSession.getMapper(AreaMapper.class);
        try {
            // dao 메세지 전달
            int result = areaMapper.updateArea(areaDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    public int deleteArea(int areaId) {
        SqlSession sqlSession = getSqlSession();
        AreaMapper areaMapper = sqlSession.getMapper(AreaMapper.class);
        try {
            // dao 메세지 전달
            int result = areaMapper.deleteArea(areaId);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    public List<AreaDto> findAreaByInventoryId(int inventoryId) {
        SqlSession sqlSession = getSqlSession();
        AreaMapper menuMapper = sqlSession.getMapper(AreaMapper.class);
        List<AreaDto> list = menuMapper.findAreaByInventoryId(inventoryId);
        sqlSession.close();
        return list;
    }

    public List<InventoryDto> findAllInventory() {
        SqlSession sqlSession = getSqlSession();
        AreaMapper menuMapper = sqlSession.getMapper(AreaMapper.class);
        List<InventoryDto> inventoryList = menuMapper.findAllInventory();
        sqlSession.close();
        return inventoryList;
    }
    public int updateReserved(AreaDto areaDto) {
        SqlSession sqlSession = getSqlSession();
        AreaMapper areaMapper = sqlSession.getMapper(AreaMapper.class);
        try {
            // dao 메세지 전달
            int result = areaMapper.updateReserved(areaDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }


}
