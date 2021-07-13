package com.sm.controller;

import com.sm.constant.QueueConstant;
import com.sm.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sqs")
public class SQSController {

    private static final Logger LOG = LoggerFactory.getLogger(SQSController.class);

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String sqsEndPoint;

    @GetMapping
    public void sendMessage() {
        // for sending text message
          queueMessagingTemplate.send(sqsEndPoint, MessageBuilder.withPayload("hello from Spring Boot").build());

        // for sending employee
        //queueMessagingTemplate.convertAndSend(sqsEndPoint, new Employee(1L,"hello world"));
    }

    /**
     *
     *  getting text message
     */
//    @SqsListener(value=QueueConstant.SQS_QUEUE,deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
//    public void getMessage(String message) {
//        LOG.info("Message from SQS Queue - "+message);
//    }

    /**
     *
     * directly getting employee object
     */
    @SqsListener(value= QueueConstant.SQS_QUEUE ,deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void getMessage(Employee employee) {
        LOG.info("Message from SQS Queue - "+employee.getName());
    }

    /**
     * getting dead letter queue messages
     * @param message
     */
    @SqsListener(value=QueueConstant.SQS_DLQ_QUEUE, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void getMessage(String message) {
        LOG.info("Message from SQS Queue - "+message);
    }
}