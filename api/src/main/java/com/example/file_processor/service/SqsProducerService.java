package com.example.file_processor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class SqsProducerService {
    private final SqsClient sqsClient;
    private final String queueUrl;

    public SqsProducerService(SqsClient sqsClient, @Value("${aws.sqs.queue-url}") String queueUrl) {
        this.sqsClient = sqsClient;
        this.queueUrl = queueUrl;
    }

    public void sendMessage(String messageBody) {
        SendMessageRequest sendMessageRequest = SendMessageRequest
                .builder()
                .queueUrl(queueUrl)
                .messageBody(messageBody)
                .build();

        sqsClient.sendMessage(sendMessageRequest);
    }
}
