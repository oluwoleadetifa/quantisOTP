package com.idquantique.quantis.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.*;


//import com.amazon.awssdk.services.ses.SesClient;

@Service
public class EmailService {
//
//    @Autowired
//    private SesClient sesClient;
//
//    public void sendOTP(String recipientEmail, String otp) {
//        String subject = "Your OTP";
//        String body = "Your OTP is " + otp;
//
//        SendEmailRequest request = SendEmailRequest.builder()
//                .destination(Destination.builder().toAddresses(recipientEmail).build())
//                .message(Message.builder()
//                        .subject(Content.builder().data(subject).build())
//                        .body(Body.builder().text(Content.builder().data(body).build()).build())
//                        .build())
//                .source("your-sender-email@example.com") // Replace with your verified sender email
//                .build();
//
//        SendEmailResponse response = sesClient.sendEmail(request);
//        System.out.println("Email sent! Message ID: " + response.messageId());
//    }
}
