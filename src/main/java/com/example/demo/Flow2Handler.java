package com.example.demo;

import org.springframework.integration.annotation.ServiceActivator;

public class Flow2Handler {
    
    @ServiceActivator
    public void handle2(String payload) {
        System.out.println("Flow2Handler - " + payload);
    }
    
}
