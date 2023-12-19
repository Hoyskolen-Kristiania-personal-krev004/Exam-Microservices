package no.exam.contractservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.contract.name}")
    private String contractQueue;
    @Value("${rabbitmq.exchange.name}")
    private String contractExchange;
    @Value("${rabbitmq.binding.routing.key}")
    private String contractKey;


    @Bean
    public Queue contractQueue(){
        return new Queue(contractQueue);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(contractExchange);
    }
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(contractQueue()).to(exchange()).with(contractKey);
    }
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    @Transactional
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
