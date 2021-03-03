package com.example.concurrencysample;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.platform.runner.JUnitPlatform;
import static org.junit.Assert.*;



@RunWith(JUnitPlatform.class)
public class BasicCheckMapTests {
    
    


    @DisplayName("BasicCheckMap Test")
    @Test
    public void BasicCheckMapTest() throws Exception {
        BasicCheckMap basiccheckmapthread1 = new BasicCheckMap(0);
        basiccheckmapthread1.start();
    }

}
