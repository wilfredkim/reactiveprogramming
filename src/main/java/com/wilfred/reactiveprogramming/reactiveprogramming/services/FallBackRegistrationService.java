package com.wilfred.reactiveprogramming.reactiveprogramming.services;

import com.wilfred.reactiveprogramming.reactiveprogramming.domains.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FallBackRegistrationService {

    @RabbitListener(queues = {"q.fall-back-registration"})
    public void onRegistrationFailure(Student student){
        log.info("Executing fallback for failed registration {}", student);
    }
}
