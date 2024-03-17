package com.wilfred.reactiveprogramming.reactiveprogramming.repository;

import com.wilfred.reactiveprogramming.reactiveprogramming.domains.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {

    Flux<Student> findAllByFirstnameIgnoreCase(String firstname);
}
