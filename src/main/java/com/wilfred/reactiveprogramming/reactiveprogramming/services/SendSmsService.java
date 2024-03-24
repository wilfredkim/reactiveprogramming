package com.wilfred.reactiveprogramming.reactiveprogramming.services;

import com.wilfred.reactiveprogramming.reactiveprogramming.domains.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendSmsService {
    @RabbitListener(queues = "q.send-sms")
    public void sendSms(Student request) {

        log.info("Sending sms to {} ", request.getMobileNumber());
    }

}
