package com.wilfred.reactiveprogramming.reactiveprogramming.services;

import com.wilfred.reactiveprogramming.reactiveprogramming.domains.Student;
import com.wilfred.reactiveprogramming.reactiveprogramming.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;

    public Flux<Student> findAll() {
        return repository.findAll();
    }

    public Mono<Student> findById(Long id) {
        return repository.findById(id);
    }

    public Mono<Student> save(Student student) {
        return repository.save(student);
    }
}
