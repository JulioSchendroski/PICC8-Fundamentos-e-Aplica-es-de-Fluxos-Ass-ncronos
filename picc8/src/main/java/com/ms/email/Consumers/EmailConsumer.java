package com.ms.email.Consumers;
import com.ms.email.Service.EmailService;
import com.ms.email.Dto.EmailDto;
import com.ms.email.Model.EmailModel;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Component
public class EmailConsumer {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = ("${spring.rabbitmq.queue}"))
    public void listen(@Payload EmailDto emailDto){
        EmailModel emailModel = new EmailModel();

        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
        
        System.out.println("Email Status: " + emailModel.getStatusEmail().toString());
    }

}
