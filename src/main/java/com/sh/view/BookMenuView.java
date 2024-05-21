package com.sh.view;

import com.sh.controller.book.BookController;

import com.sh.controller.publisher.PublisherController;
import com.sh.model.dto.bookDto.Book;
import com.sh.view.result.DisplayResultView;

import java.time.LocalDateTime;
import java.util.Scanner;

import static com.sh.WMSApplication.PUB_ID;


public class BookMenuView {
    BookController bookController = new BookController();
    PublisherController publisherController = new PublisherController();

    Scanner scanner = new Scanner(System.in);
    public void showMenu() {

        String menu = """
        ======================
        1. 출판 등록된 책 조회하기
        2. 책 출판 등록 하기  
        3. ISBN코드로 도서검색하기 
        4. 도서 상세정보 수정 
        5. 도서 상세정보 삭제
        0. 종료
        ======================
        """;
        while (true) {
            System.out.println(menu);
            System.out.println("원하시는 메뉴를 선택해주세요!");
            System.out.print("메뉴 : ");
            int n  = scanner.nextInt();

            switch (n) {
                case 1 : bookController.findAll(PUB_ID); // 출판 등록된 모든 도서 확인하기
                    break;
                case 2 :
                    bookController.insertBook(input(PUB_ID)); //도서정보 입력하기
                    break;
                case 3 : bookController.findBookByISBN(inputISBN());// ISBN으로 책 찾기
                    break;
                case 4 : bookController.updateInforamtion(inputUpdate()); //도서 상세 정보 수정
                    break;
                case 5 : bookController.deleteBook(inputISBN()); // 도서 삭제
                    break;
                case 0 : return;
                default :
                    System.out.println("숫자를 다시 입력해주세요!");
                    break;
            }
        }
    }
    public void inventoryManagerBookMenu() {

        String menu = """
        ======================
        1. 도서 재고 검색
        2. 도서 위치 검색
        0. 종료
        ======================
        """;
        while (true) {
            System.out.println(menu);
            System.out.println("원하시는 메뉴를 선택해주세요!");
            int n  = scanner.nextInt();

            switch (n) {
                case 1 : bookController.findBookByISBN(inputISBN());// ISBN으로 책 찾기
                    break;
                case 2 :
                    bookController.findBookLocation(inputISBN()); //도서 위치 찾기
                    break;
                case 0 : return;
                default :
                    System.out.println("숫자를 다시 입력해주세요!");
                    break;
            }
        }
    }
    private Book inputUpdate() {
        System.out.print("수정하시고 싶은 책의 ISBN코드를 입력해주세요 : ");
        String ISBN = scanner.next();
        Book book =  bookController.findBookByISBN(ISBN);
        DisplayResultView.displayBook(book);
        System.out.println("수정하실 책의 정보를 입력해주세요 !");
        System.out.print("가격 : ");
        int price = scanner.nextInt();
        System.out.print("책 페이지 : ");
        int pages = scanner.nextInt();
        System.out.print("책 사이즈 : ");
        String size = scanner.next();
        return new Book(ISBN,price,pages,size);
    }

    public String inputISBN() {
        System.out.print("ISBN 정보를 입력해주세요 : ");
        return scanner.next();
    }

    //책 정보 입력
    public Book input(int publisherId) {
        int id = publisherId;
        System.out.println("================================");
        System.out.println("책의 정보 입력");
        System.out.print("책 이름 : ");
        String title = scanner.next();
        String ISBN;
        while (true) {
            System.out.print("ISBN 코드 : ");
            ISBN = scanner.next();
            if (duplicateISBN(ISBN)){//중복된게 존재
                System.out.println("ISBN코드를 다시 입력해주세요.");
                continue;
            }
            else break;
        }
        System.out.print("책 장르 : ");
        String genreName = scanner.next();
        int genreId = getGenreId(genreName);
        System.out.print("책 가격 : ");
        int price = scanner.nextInt();
        LocalDateTime pubDate = LocalDateTime.now();// 수정
        System.out.print("책 저자 : ");
        String author = scanner.next();
        System.out.print("책 페이지 수 : ");
        int pages = scanner.nextInt();
        System.out.print("책 크기 : ");
        String size = scanner.next();

        System.out.println("================================");

        Book book = new Book();

        book.setAuthor(author);
        book.setTitle(title);
        book.setISBN(ISBN);
        book.setPublisherId(id);
        book.setGenreId(genreId);
        book.setPrice(price);
        book.setPubDate(pubDate);
        book.setPages(pages);
        book.setSize(size);

        return book;
    }

    //이름 입력하고 -> 이름에 해당하는 genreId값 반환하는 메소드
    public int getGenreId(String genreName) {
        return bookController.getGenreId(genreName);
    }

    //출판사 이름에 해당하는 publisherId값 호출
    public int getPublisherId(String publisherName){
        return publisherController.getPublisherId(publisherName);
    }

    public boolean duplicateISBN(String ISBN) {
        boolean b = bookController.duplicateISBN(ISBN);
        return b;
    }

}

