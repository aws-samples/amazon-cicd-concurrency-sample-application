package com.example.concurrencysample;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.*;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

public class SingletonRepo {
    private static String ACCESS_KEY = "";
    private static String SECERET_KEY = "";
    private HashMap<Integer, Concurrency> map = new HashMap<>();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static SingletonRepo singletonrepo = null;
    static AWSCredentials ac;
    static AmazonDynamoDB dynamodb;

    private SingletonRepo() {
    }

    public synchronized static SingletonRepo getInstance() {
        if (singletonrepo == null) {
            singletonrepo = new SingletonRepo();

            ac = new BasicAWSCredentials(ACCESS_KEY, SECERET_KEY);

            dynamodb = AmazonDynamoDBClientBuilder
                    .standard()
            .withRegion("us-west-2")
            .withCredentials(new AWSStaticCredentialsProvider(ac) )
            .build();
            System.out.println("singleton repo instance created");
        }
        return singletonrepo;
    }
    public Concurrency get(int product_code){
        return map.get(product_code);
    }

    public Concurrency putKey(int product_code ) {           
        Date date = Util.getRandomDate();
        if (map.containsKey(product_code)) {
            return map.get(product_code);
        }else{
            return map.put(product_code, new Concurrency(product_code,"test" , date));
        } 
    }
    public void delKey(int product_code ) { 
        if (map.containsKey(product_code)) {
            map.remove(product_code);
        }
    }
    public int getMapCount() {
        return map.size();
    }
    public synchronized void clearHashMap() {
        map.clear();
        System.out.println("ClearMap!");
    }
    public Concurrency getKey(int product_number) {
        return map.get(product_number);  
    }
}