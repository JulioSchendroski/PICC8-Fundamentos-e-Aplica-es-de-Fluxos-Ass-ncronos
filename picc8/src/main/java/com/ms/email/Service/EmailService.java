package com.ms.email.Service;
import com.ms.email.Enum.StatusEmail;
import com.ms.email.Model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.email.Repository.EmailRepository;


import org.springframework.mail.javamail.JavaMailSender;;

@Service
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Transactional
    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDataTime(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SETN);
        } catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }
    }

    public Page<EmailModel> findAll(Pageable pageable){
        return emailRepository.findAll(pageable);
    }

    public Optional<EmailModel> findById(UUID emailId){
        return emailRepository.findById(emailId);
    }
}
