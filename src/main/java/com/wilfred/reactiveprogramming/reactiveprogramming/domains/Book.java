package com.wilfred.reactiveprogramming.reactiveprogramming.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    private BookInfo bookInfo;
    private List<Review> reviews;
}
