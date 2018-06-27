package com.example.demo;

import org.springframework.integration.annotation.ServiceActivator;

public class Flow1Handler {
    
    @ServiceActivator
    public void handle(String payload) {
        System.out.println("Flow1Handler - " + payload);
    }
    
}
