package com.kennedy;

import software.amazon.awssdk.services.sns.SnsClient;

public class App {

    public static void main(String[] args) {
        String topicArn = "arn:aws:sns:us-east-1:195365165857:topic-01";

        SnsClient snsClient = Client.getClient();

        SnsService.listSNSTopics(snsClient);

        // SnsService.pubMsgToTopic(snsClient, "Messagem 1", topicArn);

        // SnsService.createTopic(snsClient, "topic-02");

        // SnsService.deleteTopic(snsClient, topicArn);

        snsClient.close();

    }

}
