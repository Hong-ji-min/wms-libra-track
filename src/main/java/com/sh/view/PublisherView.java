package com.sh.view;

import com.sh.controller.publisher.PublisherController;
import com.sh.model.dto.publishserDto.Publisher;
import com.sh.model.dto.publishserDto.PublisherManager;
import com.sh.view.BookMenuView;

import java.util.Scanner;

import static com.sh.WMSApplication.PUB_ID;
import static com.sh.WMSApplication.getScanner;

/**
 * 신규 출판사 등록
 * 출판사 정보 수정
 * 출판 정보 삭제
 * 출판사 정보 조회
 */
public class PublisherView
{
    PublisherController publisherController = new PublisherController();
    BookMenuView bookMenuView = new BookMenuView();
    Scanner scanner = getScanner();

    PublisherManager publisherManager;

    /**
     * 아이디 , 비밀번호 로그인하고 , 해당 로그인된 정보에 따라서 출판사의 정보 가져오게 해야될거 같은데
     */
    public void insertPublisherView() {
        System.out.println("출판사 등록을 도와드리겠습니다.☺️");
        publisherController.insertPublisher(input());
    }

    public Publisher input() {
        System.out.println("출판사의 정보를 정확하게 입력해주세요 !");
        System.out.println("===============================");
        System.out.print("출판사 이름 : ");
        String name = scanner.next();
        System.out.print("출판사 이메일 : ");
        String email = scanner.next();
        System.out.print("출판사 대표자 번호 : ");
        String phoneNumber = scanner.next();
        System.out.print("출판사 사업자 번호 : ");
        String businessNumber = scanner.next();

        return new Publisher(0, name, email, phoneNumber, businessNumber);

    }
    public void publisherMenuView( ){
        String menu = """
          1.출판 관리 (책 관리)
          2.출판사 관리
          0. 종료 
          """;

        while (true) {
            System.out.println(menu);
            System.out.println("메뉴를 선택해주세요 : ");
            int n = scanner.nextInt();
            switch (n) {
                case 1 : bookMenuView.showMenu();
                    break;
                case 2 : publisherMenu();
                    break;
                case 0 :
                    return;
                default :
                    System.out.println("번호를 정확하게 입력해주세요.");
                    break;
            }
        }
    }
    public void publisherMenu() {
        String menu = """
                ======================
                1. 출판사 정보 수정 
                2. 출판사 정보 삭제 
                3. 출판사 정보 조회
                0. 종료
                ======================
                """;
        while (true) {
            System.out.println(menu);
            System.out.println("원하시는 메뉴를 선택해주세요!");
            System.out.print("메뉴 : ");
            int n = scanner.nextInt();
            switch(n) {
                case 1 : publisherController.updatePublisher(inputUpdate(PUB_ID));//출판사 정보 수정
                    break;
                case 2 :
                    publisherController.deletePublisher(PUB_ID);//출판사 삭제
                    break;
                case 3 :
                    publisherController.findPublisherInformation(PUB_ID);//출판사 정보 조회
                    break;
                case 0 : return;
                default :
                    System.out.println("번호를 정확하게 입력해주세요.");
                    break;

            }

        }
    }

    private Publisher inputUpdate(int id) {
        System.out.println("수정하실 출판사 정보를 입력해주세요.");
        System.out.print("출판사 이름 : ");
        String name = scanner.next();
        System.out.print("출판사 이메일 : ");
        String email = scanner.next();
        System.out.print("출판사 대표자 번호 : ");
        String phoneNumber = scanner.next();
        System.out.print("출판사 사업자 번호 : ");
        String businessNumber = scanner.next();
        return new Publisher(id, name, email, phoneNumber, businessNumber);

    }

}
