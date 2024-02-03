package br.com.victor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.victor.dto.NotificationDTO;
import br.com.victor.entities.enums.TypeTransaction;
import br.com.victor.exceptions.OfflineServiceException;

@Service
public class NotificationService {
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendCallback(TypeTransaction type, Double value) {
        String url = "https://webhook.site/5981b231-e73a-4f86-b71a-44fb57df7292";

        NotificationDTO notification = new NotificationDTO("A transaction has been made on your account!", type, value);

        ResponseEntity<String> response = restTemplate.postForEntity(url, notification, String.class);

        if (response.getStatusCode().isError()) {
            throw new OfflineServiceException("The notification service is down!");
        }
    }

    public void sendEmail(TypeTransaction type, Double value) {
        SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setTo("5981b231-e73a-4f86-b71a-44fb57df7292@email.webhook.site");
        simpleMail.setText(String.format("Your %s of R$ %.2f was successful", type.toString(), value));
        simpleMail.setSubject("Transaction successfully completed!");
        simpleMail.setFrom(System.getenv("JAVAMAILSENDER_EMAIL"));
        javaMailSender.send(simpleMail);
    }
}
