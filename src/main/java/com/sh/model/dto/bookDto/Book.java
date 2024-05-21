package com.sh.model.dto.bookDto;

import com.sh.model.entity.BookArea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Integer bookId;
    private String title;
    private String ISBN;
    private Integer publisherId;
    private Integer genreId;
    private Integer price;
    private LocalDateTime pubDate;
    private String author;
    private Integer pages;
    private String size;

    private List<BookArea> bookAreaList;

//    private Genre genre;

    public Book(String ISBN, int price, int pages, String size) {
        this.ISBN = ISBN;
        this.price = price;
        this.pages = pages;
        this.size = size;
    }

}


