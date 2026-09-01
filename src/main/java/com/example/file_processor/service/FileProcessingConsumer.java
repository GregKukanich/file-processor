package com.example.file_processor.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.model.Message;

import java.io.IOException;
import java.util.List;

@Service
public class FileProcessingConsumer {

    private final SqsService sqsService;
    private final FileHandler fileHandler;

    public FileProcessingConsumer(SqsService sqsService, FileHandler fileHandler) {
        this.sqsService = sqsService;
        this.fileHandler = fileHandler;
    }

    @Scheduled(fixedRate = 5000)
    public void consumeMessages() {
        List<Message> messages = sqsService.receiveMessages();
        if (messages.isEmpty()) {
            return;
        }

        Message message = messages.get(0);
        try {
            fileHandler.handleFileParsing(message.body(), message.receiptHandle());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
