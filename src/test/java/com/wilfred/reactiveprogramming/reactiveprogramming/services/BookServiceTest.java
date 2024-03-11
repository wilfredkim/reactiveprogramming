package com.wilfred.reactiveprogramming.reactiveprogramming.services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private BookInfoService bookInfoService = new BookInfoService();
    private ReviewService reviewService = new ReviewService();
    private BookService bookService = new BookService(bookInfoService, reviewService);

    @Test
    void getBooks() {
        var books =bookService.getBooks();
        StepVerifier.create(books).assertNext(book -> {
            assertEquals("River Between",book.getBookInfo().getTitle());
            assertEquals(2,book.getReviews().size());
        });
    }
}