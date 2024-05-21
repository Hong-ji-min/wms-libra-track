package com.sh.view;

import com.sh.controller.PublisherManagerController;
import com.sh.model.entity.MemberDto;
import com.sh.model.entity.PublisherManagerDto;

import java.util.Scanner;

import static com.sh.WMSApplication.getScanner;
import static com.sh.WMSApplication.*;
//홍지민 작업 시작
public class PublisherManagerView {
    Scanner sc = getScanner();
    PublisherManagerController publisherManagerController = new PublisherManagerController();

    BookMenuView bookMenuView = new BookMenuView();

    PublisherView publisherView = new PublisherView();

    public void choicePublisherMenu(MemberDto memberDto) {
        String choicePublisherMenu = """
                📖📖📖출판사 매니저 메뉴📖📖📖
                =============================
                1. 회원 정보 관리
                2. 도서 관리
                3. 입고 관리
                4. 출고 관리
                5. 출판(사) 관리
                0. 나가기
                =============================
                입력 : 
                """;
        PublisherManagerDto publisherManagerDto = publisherManagerController.findPublisherManagerInfo(memberDto);

        if (publisherManagerDto != null) {
            PUB_MANAGER_ID = publisherManagerDto.getPublisherManagerId();
            PUB_ID = publisherManagerDto.getPublisherId();
        }

        while (true) {

            if(publisherManagerDto == null) {
                publisherManagerDto = publisherManagerController.findPublisherManagerInfo(memberDto);

                if (publisherManagerDto != null) {
                    PUB_MANAGER_ID = publisherManagerDto.getPublisherManagerId();
                    PUB_ID = publisherManagerDto.getPublisherId();
                }
            }
            System.out.print(choicePublisherMenu);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    publisherManagement(memberDto);
                    break;
                case 2 :
                    if (publisherManagerDto == null) {
                        System.out.println("출판사 정보가 없습니다. 출판사 정보를 먼저 등록해주세요.");
                        break;
                    }
                    bookMenuView.showMenu();
                    break;
                case 3 :
                    if (publisherManagerDto == null) {
                        System.out.println("출판사 정보가 없습니다. 출판사 정보를 먼저 등록해주세요.");
                        break;
                    }
                    EX = false;
                    InWarehousingView.inWarehousingPublisherMainMenu(); break;
                case 4 :
                    if (publisherManagerDto == null) {
                        System.out.println("출판사 정보가 없습니다. 출판사 정보를 먼저 등록해주세요.");
                        break;
                    }
                    EX = true;
                    ExWarehousingView.exWarehousingPublisherMainMenu(); break;
                case 5:
                    publisherView.publisherMenuView();
                    break;
                case 0 :
                    return;
                default:
                    System.out.print("잘못된 입력입니다. 다시 입력해주세요 : ");
            }
        }
    }

    private void publisherManagement(MemberDto memberDto) {
        String publihserManagement = ("""
                ===================
                ℹ️ 회원 정보 관리 ℹ️
                ===================
                 1. 회원 정보 조회
                 2. 회원 정보 수정
                 3. 출판사 등록
                 0. 나가기
                ===================
                """);
        while (true) {
            System.out.print(publihserManagement);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    publisherManagerController.findPublisherManagerInfo(memberDto);
                    break;
                case 2:
                    publisherManagerController.updatePublisherManager(updatePublisherManager(memberDto));
                    break;
                case 3:
                    System.out.println("출판사 등록을 시작합니다.");
                    System.out.println("출판사를 입력해주세요.");
                    String publisher = sc.next();
                    publisherManagerController.insertPublisher(memberDto, publisher);

                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못 입력된 값입니다. 다시 입력해주세요!");
            }
        }
    }

    private MemberDto updatePublisherManager(MemberDto memberDto) {
        System.out.println("""
                ============================
                 ✏️️✏️️✏️회원 정보 수정✏️✏️️✏️
                ============================
                """);
        System.out.print("1. 이름 입력: ");
        String name = sc.next();

        System.out.print("2. 아이디 입력 : ");
        String username = sc.next();

        System.out.print("3. 비밀 번호 입력 : ");
        String password = sc.next();

        System.out.print("4. 연락처 입력: ");
        String phoneNumber = sc.next();

        System.out.print("5. 이메일 주소 입력 : ");
        String email = sc.next();

        return new MemberDto(memberDto.getMemberId(), name, username, password, memberDto.getRole() ,phoneNumber, email);
    }
}
//홍지민 작업 끝
