package com.wilfred.reactiveprogramming.reactiveprogramming.domains;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "students")
public class Student {
    @Id
    private Long id;
    private String firstname;
    private String lastname;
    private String othernames;
    private String admissionNumber;
    private String email;
    private int age;
}
