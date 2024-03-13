package com.wilfred.reactiveprogramming.reactiveprogramming.services;

import com.wilfred.reactiveprogramming.reactiveprogramming.domains.Book;
import com.wilfred.reactiveprogramming.reactiveprogramming.domains.Review;
import com.wilfred.reactiveprogramming.reactiveprogramming.exceptions.BookException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;

@Slf4j
public class BookService {

    private BookInfoService bookInfoService;
    private ReviewService reviewService;

    public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
        this.bookInfoService = bookInfoService;
        this.reviewService = reviewService;
    }

    public Flux<Book> getBooks() {
        var allBooks = bookInfoService.getBooks();
        return allBooks.flatMap(bookInfo -> {
            Mono<List<Review>> reviews = reviewService.getReview(bookInfo.getBookId()).collectList();
            return reviews.map(reviews1 -> new Book(bookInfo, reviews1));
        }).onErrorMap(
                throwable -> {
                    log.info("Exception::::::::::::" + throwable);
                    return new BookException("Exception occurred while fetching books!");
                }
        ).log();
    }

    public Flux<Book> getBooksRetry() {
        var allBooks = bookInfoService.getBooks();
        return allBooks.flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReview(bookInfo.getBookId()).collectList();
                    return reviews.map(reviews1 -> new Book(bookInfo, reviews1));
                }).onErrorMap(
                        throwable -> {
                            log.info("Exception::::::::::::" + throwable);
                            return new BookException("Exception occurred while fetching books!");
                        }
                ).retry(3)

                .log();
    }

    public Flux<Book> getBooksRetryWhen() {
        var retrySpecs = Retry.backoff(3, Duration.ofMillis(1000))
                .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) -> Exceptions.propagate(retrySignal.failure())))
                ;
        var allBooks = bookInfoService.getBooks();
        return allBooks.flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReview(bookInfo.getBookId()).collectList();
                    return reviews.map(reviews1 -> new Book(bookInfo, reviews1));
                }).onErrorMap(
                        throwable -> {
                            log.info("Exception::::::::::::" + throwable);
                            return new BookException("Exception occurred while fetching books!");
                        }
                ).retryWhen(retrySpecs)

                .log();
    }
}
