/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.concurrencysample;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.platform.runner.JUnitPlatform;
import static org.junit.Assert.*;


//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertSame;


@RunWith(JUnitPlatform.class)
public class SingletonRepoTests {

    SingletonRepo singletonrepo = null;


    @BeforeAll
    public static void init(){
        SingletonRepo.getInstance().clearHashMap();
        System.out.println("Before All init() method called");
    }
     
    @BeforeEach
    public void initEach(){
        System.out.println("Before Each initEach() method called");
    }


    @DisplayName("Singleton Test")
    @Test
    public void SingletonRepoPatternTest() throws Exception {
        SingletonRepo singletonrepo = SingletonRepo.getInstance();
        assertNotNull(singletonrepo);

        SingletonRepo singletonrepo2 = SingletonRepo.getInstance();
        assertSame(singletonrepo, singletonrepo2);
    }

    @DisplayName("HashMap SingleTest")
    @Test
    public void SingletonRepoTestsThreadSingleTest() throws Exception {

        SingletonRepo.getInstance().clearHashMap();
        BasicSynchronization basicsynchronizationThread1 = new BasicSynchronization(2000);
        basicsynchronizationThread1.start();
        while( true){
            Thread.State state1 = basicsynchronizationThread1.getState(); 
            if(state1 == Thread.State.TERMINATED ) break;
        }
      
        assertEquals(basicsynchronizationThread1.getCount(),SingletonRepo.getInstance().getMapCount());
    } 


    @DisplayName("HashMap repeatedTest")
    @RepeatedTest(value = 100, name = "{displayName} - repetition {currentRepetition} of {totalRepetitions}")
    public void SingletonRepoTestsThreadConcurrencyTest() throws Exception {
        int thrad_count = 8;
        int make_count =0 ;
        SingletonRepo.getInstance().clearHashMap();
        System.out.println("Start Concurrency Make Error Test");

        BasicSynchronization[] basicsynchronizationThreadList = new BasicSynchronization[thrad_count];

        
        while( SingletonRepo.getInstance().getMapCount() != 0)
            SingletonRepo.getInstance().clearHashMap();


        for (int i = 0; i < thrad_count; i++) {
            basicsynchronizationThreadList[i] = new BasicSynchronization(500);
            basicsynchronizationThreadList[i].setCount(0);
            basicsynchronizationThreadList[i].start();
        }

        while (true) {
            boolean doing = false;
            
            for (int i = 0; i < basicsynchronizationThreadList.length; i++) {
                if (basicsynchronizationThreadList[i].getState() != Thread.State.TERMINATED)
                    doing = true;
            }
            if (!doing)
                break;
        }
        for (int i = 0; i < basicsynchronizationThreadList.length; i++) {
            make_count += basicsynchronizationThreadList[i].getCount();
        }
        System.out.println(make_count | SingletonRepo.getInstance().getMapCount());

//        assertTrue(make_count == SingletonRepo.getInstance().getMapCount() -1 || make_count == SingletonRepo.getInstance().getMapCount());

        assertEquals( make_count ,SingletonRepo.getInstance().getMapCount());
        
    }
    
}