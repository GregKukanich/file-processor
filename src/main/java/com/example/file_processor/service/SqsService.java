package com.example.file_processor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class SqsService {
    private final SqsClient sqsClient;
    private final String queueUrl;

    public SqsService(SqsClient sqsClient, @Value("${aws.sqs.queue-url}") String queueUrl) {
        this.sqsClient = sqsClient;
        this.queueUrl = queueUrl;
    }

    public void sendMessage(String s3Key) {
        SendMessageRequest sendMessageRequest = SendMessageRequest
                .builder()
                .queueUrl(queueUrl)
                .messageBody(s3Key)
                .build();

        sqsClient.sendMessage(sendMessageRequest);
    }
}
