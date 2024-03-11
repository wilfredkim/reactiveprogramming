package com.wilfred.reactiveprogramming.reactiveprogramming.services;

import com.wilfred.reactiveprogramming.reactiveprogramming.domains.Review;
import reactor.core.publisher.Flux;

import java.util.List;

public class ReviewService {

    public Flux<Review> getReview(long bookId) {
        var reviews = List.of(
                new Review("Great Book", 1, 1, 9.8),
                new Review("Good Stuff", 2, 2, 9.8)

        );
        return Flux.fromIterable(reviews);
    }
}
