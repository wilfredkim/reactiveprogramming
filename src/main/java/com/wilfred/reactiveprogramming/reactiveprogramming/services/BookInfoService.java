package com.wilfred.reactiveprogramming.reactiveprogramming.services;

import com.wilfred.reactiveprogramming.reactiveprogramming.domains.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookInfoService {
    public Flux<BookInfo> getBooks() {
        var books = List.of(
                new BookInfo(1, "River Between", "Ngugi Wa Thiongo", "23222IBSN"),
                new BookInfo(2, "River and the source", "Okolla", "232q22IBSN")
        );
        return Flux.fromIterable(books);
    }

    public Mono<BookInfo> getBookById(Long id) {
        var book = new BookInfo(id, "River Between", "Ngugi Wa Thiongo", "23222IBSN");
        return Mono.just(book);
    }
}
