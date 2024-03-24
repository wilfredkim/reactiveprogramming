package com.wilfred.reactiveprogramming.reactiveprogramming.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {
    private final CachingConnectionFactory cachingConnectionFactory;

    @Bean
    public Declarables createDeadLetterSchema(){
        return new Declarables(
                new DirectExchange("x.registration-failure"),
                new Queue("q.fall-back-registration"),
                new Binding("q.fall-back-registration", Binding.DestinationType.QUEUE,"x.registration-failure", "fall-back", null)
        );
    }
    @Bean
    public Queue createSaveStudentQueue() {
       return new Queue("q.STUDENTS_QUEUE");
        /*return QueueBuilder.durable("q.STUDENTS_QUEUE")
                .withArgument("x-dead-letter-exchange","x.registration-failure")
                .withArgument("x-dead-letter-routing-key","fall-back")
                .build();*/
    }
    @Bean
    public Declarables createPostRegistartionSchema(){

        return new Declarables(
                new FanoutExchange("x.post-registration"),
                new Queue("q.send-email" ),
                new Queue("q.send-sms"),
                new Binding("q.send-email", Binding.DestinationType.QUEUE, "x.post-registration", "send-email", null),
                new Binding("q.send-sms", Binding.DestinationType.QUEUE, "x.post-registration", "send-sms", null));

    }

    @Bean
    public Jackson2JsonMessageConverter converter(){
        return  new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter){
        RabbitTemplate restTemplate= new RabbitTemplate(cachingConnectionFactory);
        restTemplate.setMessageConverter( converter);
        return restTemplate;
    }

    @Bean
    public RetryOperationsInterceptor retryInterceptor(){
        return RetryInterceptorBuilder.stateless().maxAttempts(3)
                .backOffOptions(2000, 2.0, 100000)
                .build();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, cachingConnectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        factory.setAdviceChain(retryInterceptor());
        return factory;
    }




}
