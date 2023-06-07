package com.example.concurrencysample;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.codeguruprofilerjavaagent.Profiler;



public class ConcurrencyCheckout {

     

    public static void main(final String[] args) {
        // Start the profiler
        Profiler.builder().profilingGroupName("concurrencysample-profiler").withHeapSummary(true).build().start();
         
        int thrad_count = 5;
        int make_count =0 ;
        System.out.println("================================");
        System.out.println("Start Concurrency Make Erro Test");
        System.out.println("================================\n");

        BasicSynchronization[] basicsynchronizationThreadList = new BasicSynchronization[8];

        for (int i = 0; i < 8; i++) {
            basicsynchronizationThreadList[i] = new BasicSynchronization(0);
            basicsynchronizationThreadList[i].start();
        }
        BasicCheckMap basiccheckmapthread1 = new BasicCheckMap(0);
        basiccheckmapthread1.start();

        BasicCheckMap basiccheckmapthread2 = new BasicCheckMap(0);
        basiccheckmapthread2.start();


    }

}
