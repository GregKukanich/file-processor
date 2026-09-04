package com.example.file_processor.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.model.Message;

import java.io.IOException;
import java.util.List;

@Service
public class FileProcessingConsumer {

    private final SqsConsumerService sqsConsumerService;
    private final FileHandlerWorker fileHandlerWorker;

    public FileProcessingConsumer(SqsConsumerService sqsConsumerService, FileHandlerWorker fileHandlerWorker) {
        this.sqsConsumerService = sqsConsumerService;
        this.fileHandlerWorker = fileHandlerWorker;
    }

    @Scheduled(fixedRate = 5000)
    public void consumeMessages() {
        List<Message> messages = sqsConsumerService.receiveMessages();
        if (messages.isEmpty()) {
            return;
        }

        Message message = messages.getFirst();
        try {
            fileHandlerWorker.handleFileParsing(message.body(), message.receiptHandle());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
