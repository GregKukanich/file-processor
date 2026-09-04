package com.example.file_processor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

@Service
public class SqsConsumerService {
    private final SqsClient sqsClient;
    private final String queueUrl;

    public SqsConsumerService(SqsClient sqsClient, @Value("${aws.sqs.queue-url}") String queueUrl) {
        this.sqsClient = sqsClient;
        this.queueUrl = queueUrl;
    }

    public void deleteMessage(String receiptHandle) {
        DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest
                .builder()
                .queueUrl(queueUrl)
                .receiptHandle(receiptHandle)
                .build();
        sqsClient.deleteMessage(deleteMessageRequest);
    }

    public List<Message> receiveMessages() {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest
                .builder()
                .queueUrl(queueUrl)
                .build();

        return sqsClient.receiveMessage(receiveMessageRequest).messages();
    }
}

