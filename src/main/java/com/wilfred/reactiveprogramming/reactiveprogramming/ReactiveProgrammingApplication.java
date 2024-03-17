package com.wilfred.reactiveprogramming.reactiveprogramming;

import com.wilfred.reactiveprogramming.reactiveprogramming.domains.Student;
import com.wilfred.reactiveprogramming.reactiveprogramming.services.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReactiveProgrammingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveProgrammingApplication.class, args);
    }

    @Bean

    public CommandLineRunner commandLineRunner(StudentService studentService) {
        return args -> {
            for (int i = 0; i < 100; i++) {
                studentService.save(
                        Student.builder().
                                firstname("Wilfred " + i).lastname("Kim " + i).age(i).admissionNumber(i + "").othernames("Tuwei " + i)
                                .email("wilfredkim" + i + "@gmail.com").
                                build()
                ).subscribe();
            }
        };
    }

}
