package com.pratice.JournalApp_SpringBoot_Project.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {


    @Autowired
    private JavaMailSender javaMailSender;



    public void sendEmail(String to, String subject, String body) {
        // Implement email sending logic here using javaMailSender
        // For example, you can create a SimpleMailMessage and send it


        try {

            SimpleMailMessage simpleMailMessage =new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);
            javaMailSender.send(simpleMailMessage);

        }catch (Exception e)
        {
            e.printStackTrace();
            log.info("Error sending email: " + e.getMessage());
        }

    }




}
