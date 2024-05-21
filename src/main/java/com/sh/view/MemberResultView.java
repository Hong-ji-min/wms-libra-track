package com.sh.view;

import com.sh.model.entity.PublisherManagerDto;

import java.util.Scanner;

public class MemberResultView {
    private static Scanner sc = new Scanner(System.in);

    public static void displayResult(String type, int result) {
        System.out.println("📢📢📢 " + type + " " + (result > 0 ? "성공" : "실패") + "📢📢📢");
        System.out.println();
    }

    public static void displayPublisherManager(PublisherManagerDto publisherManagerDto) {
        if (publisherManagerDto == null) System.out.println("조회되지 않는 회원 정보입니다.");
        System.out.println("ℹℹ회원 정보ℹℹ");
        System.out.printf(
                "1. 회원 ID : %d \n" +
                "2. 이름 :  %s \n" +
                "3. 아이디 : %s \n" +
                "4. 비밀 번호 :  %s \n" +
                "5. 구분 : %s \n" +
                "6. 개인 핸드폰 : %s \n" +
                "7. 이메일주소 : %s \n" +
                "8. 출판사 매니저 ID : %s \n" +
                "9. 출판사 ID : %s \n",
                publisherManagerDto.getMemberDto().getMemberId(),
                publisherManagerDto.getMemberDto().getName(),
                publisherManagerDto.getMemberDto().getUserName(),
                publisherManagerDto.getMemberDto().getPassword(),
                publisherManagerDto.getMemberDto().getRole(),
                publisherManagerDto.getMemberDto().getPhoneNumber(),
                publisherManagerDto.getMemberDto().getEmail(),
                publisherManagerDto.getPublisherManagerId(),
                publisherManagerDto.getPublisherId()
        );
    }

}
