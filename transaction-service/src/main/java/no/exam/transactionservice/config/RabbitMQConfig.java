package no.exam.transactionservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.transaction.name}")
    private String transactionQueue;
    @Value("${rabbitmq.exchange.name}")
    private String transactionExchange;
    @Value("${rabbitmq.binding.routing.key}")
    private String transactionKey;


    @Bean
    public Queue transactionQueue(){
        return new Queue(transactionQueue);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(transactionExchange);
    }
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(transactionQueue()).to(exchange()).with(transactionKey);
    }
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate transactionTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
