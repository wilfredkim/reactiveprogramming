package com.wilfred.reactiveprogramming.reactiveprogramming.services;

import com.wilfred.reactiveprogramming.reactiveprogramming.domains.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

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

    public Flux<Integer> testConcat() {
        Flux<Integer> flux = Flux.range(1, 100);
        Flux<Integer> flux2 = Flux.range(101, 200);
        return Flux.merge(flux, flux);
    }

    public Flux<Tuple2<Integer, Integer>> testZip() {
        Flux<Integer> flux = Flux.range(1, 100);
        Flux<Integer> flux2 = Flux.range(101, 200);
        return Flux.zip(flux, flux);
    }

    public static Flux<Tuple2<Integer, Integer>> test2Zip() {
        Flux<Integer> flux = Flux.range(1, 100);
        Mono<Integer> mono= Mono.just(101);
        return Flux.zip(flux, mono);
    }

    public static void main(String[] args) {
        System.out.println(test2Zip().subscribe());
    }
}
