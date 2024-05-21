package com.sh.controller;

import com.sh.model.entity.MemberDto;
import com.sh.model.service.MemberService;
import com.sh.view.result.MemberResultView;

public class MemberController {
    MemberService memberService = new MemberService();

    public int  insertAdminUser(MemberDto memberDto) {
        int result = memberService.insertAdminUser(memberDto);
        MemberResultView.displayResult("Admin User 회원 가입", result);
        return result;
    }

    public int insertPublisherMember(MemberDto memberDto) {
        int result = memberService.insertPublisherMember(memberDto);
        MemberResultView.displayResult("출판사 매니저 회원 가입", result);
        return result;
    }

    public MemberDto insertInventoryManager(MemberDto memberDto) {
        memberService.insertInventoryMember(memberDto);
        return memberDto;
    }


    public MemberDto loginCheck(String id, String password) {
        return memberService.loginCheck(id, password);
    }

}
