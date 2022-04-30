package com.kennedy;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsAsyncClient;
import software.amazon.awssdk.services.sns.SnsClient;

public class Client {

    public static SnsClient getClient() {

        return SnsClient.builder().region(Region.US_EAST_1).build();

    }

}
