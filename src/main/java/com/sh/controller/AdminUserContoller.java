package com.sh.controller;

import com.sh.model.entity.MemberDto;
import com.sh.model.service.AdminUserService;
import com.sh.view.result.MemberResultView;

public class AdminUserContoller {
    AdminUserService adminUserService = new AdminUserService();
    public void findAdminUserInfo(MemberDto memberDto) {
        MemberDto adminUser = adminUserService.findAdminUserInfo(memberDto);
        MemberResultView.displayAdminUser(adminUser);
    }

    public void updateAdminUserInfo(MemberDto memberDto) {
        int result  = adminUserService.updateAdminUserInfo(memberDto);
        MemberResultView.displayResult("Admin User 정보 수정", result);


    }
}
