package com.wilfred.reactiveprogramming.reactiveprogramming.controller;

import com.wilfred.reactiveprogramming.reactiveprogramming.domains.Student;
import com.wilfred.reactiveprogramming.reactiveprogramming.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    Mono<Student> save(@RequestBody Student student) {
        rabbitTemplate.convertAndSend("","q.STUDENTS_QUEUE", student);
        return studentService.save(student);
    }

    @GetMapping
    Flux<Student> getList() {
        return studentService.findAll();
    }
}
