package com.kennedy;

import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.CreateTopicRequest;
import software.amazon.awssdk.services.sns.model.CreateTopicResponse;
import software.amazon.awssdk.services.sns.model.DeleteTopicRequest;
import software.amazon.awssdk.services.sns.model.DeleteTopicResponse;
import software.amazon.awssdk.services.sns.model.ListTopicsRequest;
import software.amazon.awssdk.services.sns.model.ListTopicsResponse;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;
import software.amazon.awssdk.services.sns.model.Topic;

public class SnsService {

    // lista todos os topicos
    public static void listSNSTopics(SnsClient snsClient) {
        try {
            ListTopicsRequest request = ListTopicsRequest.builder().build();
            ListTopicsResponse result = snsClient.listTopics(request);

            for (Topic t : result.topics()) {
                System.out.println(t.topicArn());
            }

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }

    // envia uma mensagem para um topico
    public static void pubMsgToTopic(SnsClient snsClient, String message, String topicArn) {
        try {

            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .topicArn(topicArn)
                    .build();

            PublishResponse result = snsClient.publish(request);

            System.out.println("MSG ID =>" + result.messageId() + "\nMessage sent. status is "
                    + result.sdkHttpResponse().statusCode());

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }

    // cria um topico
    public static void createTopic(SnsClient snsClient, String topicName) {
        try {
            CreateTopicRequest request = CreateTopicRequest.builder()
                    .name(topicName)
                    .build();

            CreateTopicResponse result = snsClient.createTopic(request);

            System.out.println(result.topicArn());
        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }

    // deleta um topico
    public static void deleteTopic(SnsClient snsClient, String topicArn) {
        try {
            DeleteTopicRequest request = DeleteTopicRequest.builder()
                    .topicArn(topicArn)
                    .build();

            DeleteTopicResponse result = snsClient.deleteTopic(request);

            System.out.println("Status was = " + result.sdkHttpResponse().statusCode());
        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
