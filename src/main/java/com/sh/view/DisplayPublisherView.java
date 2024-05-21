package com.sh.view;

import com.sh.model.dto.publishserDto.Publisher;

public class DisplayPublisherView {

    public static void displayBook(Publisher publisher) {
        if (publisher == null) {
            System.out.println("해당 ISBN에 맞는 책이 없습니다.");
        }
    }

    public static void deleteResupt(String type , int result)
    {
        if (result > 0) {
            System.out.println("출판사가 " + type + "성공적으로 삭제되었습니다. 그동안 이용해주셔서 감사합니다.🥲");
        }
    }


    public static void displayResult(String type , int result, Publisher publisher){
        if(result > 0)
            System.out.println("책이 성공적으로 " + type + "되었습니다.😇");
        else{
            System.out.println("책을" + type + "하지 못하였습니다. 책의 정보를 다시 입력해주세요 ");
        }
        displayPublisherInformation(publisher);
    }


    public static void displayPublisherInformation(Publisher publisher) {
        if (publisher == null) {
            System.out.println("등록된 출판사 정보가 없습니다. 출판사 등록을 먼저하시길 바랍니다.");
        }
        else {
            System.out.println("정상적으로 출판사가 등록되었습니다.😀");
            System.out.println("출판사의 정보입니다 ! ");
            System.out.println("------------------");
            System.out.println("출판사 이름 : " + publisher.getName());
            System.out.println("출판사 이메일 : " + publisher.getEmail());
            System.out.println("출판사 대표자 번호 : " + publisher.getPhoneNumber());
            System.out.println("출판사 사업자 번호 : " + publisher.getBusinessNumber());
        }

    }
}
