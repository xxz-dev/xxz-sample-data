package main.java.com.amazon.samplelib;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AwsCredentials {

    public void hardCodedAccessKey() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("aaa", "bbb");
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }

    public void hardCodedSecretKey() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("ccc", "ddd");
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }

    public void notHardCoded() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(
                System.getenv().get("access-key"), System.getenv().get("secret-key"));
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }
}
