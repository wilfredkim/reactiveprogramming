package com.wilfred.reactiveprogramming.reactiveprogramming.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilfred.reactiveprogramming.reactiveprogramming.domains.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMQConsumer {
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = {"q.STUDENTS_QUEUE"}, containerFactory = "rabbitListenerContainerFactory")
    public void onUserRegistration(Student event) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            log.info("Receiving Message from Queue: {}", mapper.writeValueAsString(event));
            rabbitTemplate.convertAndSend("x.post-registration","", event);
           // throw new RuntimeException("Intentional failed!");

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
