package com.sh.model.service;

import com.sh.model.dao.InventoryManagerMapper;
import com.sh.model.dao.MemberMapper;
import com.sh.model.entity.InventoryManagerDto;
import com.sh.model.entity.MemberDto;
import org.apache.ibatis.session.SqlSession;

import java.sql.Timestamp;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class MemberService {

    //홍지민 작업 시작
    // AdminUser 회원가입
    public int insertAdminUser(MemberDto memberDto) {
        SqlSession sqlSession = getSqlSession();
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        try {
            int result = memberMapper.insertMember(memberDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException("Failed to add member", e);
        } finally {
            sqlSession.close();
        }
    }

    // 출판사 매니저 회원 가입
    public int insertPublisherMember(MemberDto memberDto) {
        SqlSession sqlSession = getSqlSession();
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        try {
            int result = memberMapper.insertMember(memberDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException("Failed to add member", e);
        } finally {
            sqlSession.close();
        }
    }
    //홍지민 작업 끝

    public int insertInventoryMember(MemberDto memberDto) {
        SqlSession sqlSession = getSqlSession();
        try  {
            MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
            int result = memberMapper.insertMember(memberDto);

            InventoryManagerMapper inventoryManagerMapper = sqlSession.getMapper(InventoryManagerMapper.class);
            InventoryManagerDto inventoryManagerDto = new InventoryManagerDto(new Timestamp(System.currentTimeMillis()), 0, 10, memberDto.getMemberId());
            int result2 = inventoryManagerMapper.insertInventoryManager(inventoryManagerDto);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Failed to selectMember", e);
        } finally {
            sqlSession.close();
        }
    }

    public MemberDto loginCheck(String id, String password) {
        SqlSession sqlSession = getSqlSession();

        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        MemberDto memberDto = memberMapper.loginCheck(id, password);
        sqlSession.close();
        return memberDto;

    }

}