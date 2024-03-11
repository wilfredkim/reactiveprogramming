package com.wilfred.reactiveprogramming.reactiveprogramming.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookInfo {
    private long bookId;
    private String title;
    private String author;
    private  String ISBN;
}
