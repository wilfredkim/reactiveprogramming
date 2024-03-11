package com.wilfred.reactiveprogramming.reactiveprogramming.services;

import com.wilfred.reactiveprogramming.reactiveprogramming.domains.Book;
import com.wilfred.reactiveprogramming.reactiveprogramming.domains.Review;
import com.wilfred.reactiveprogramming.reactiveprogramming.exceptions.BookException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class BookService {

    private BookInfoService bookInfoService;
    private ReviewService reviewService;

    public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
        this.bookInfoService = bookInfoService;
        this.reviewService = reviewService;
    }

    public Flux<Book> getBooks(){
        var allBooks = bookInfoService.getBooks();
        return allBooks.flatMap(bookInfo -> {
            Mono<List<Review>> reviews =reviewService.getReview(bookInfo.getBookId()).collectList();
            return  reviews.map(reviews1 -> new Book(bookInfo,reviews1));
        }).onErrorMap(
                throwable -> {
                    log.info("Exception::::::::::::"+throwable);
                    return new BookException("Exception occurred while fetching books!");
                }
        ).log();
    }
}
