package com.wilfred.reactiveprogramming.reactiveprogramming.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review {
    private  String comments;
    private  long bookId;
    private  long reviewId;
    private  double ratings;
}
