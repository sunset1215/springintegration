package com.example.demo;

import org.springframework.integration.core.GenericSelector;

public class Flow1Selector implements GenericSelector<String> {
    
    @Override
    public boolean accept(String s) {
        System.out.println("Flow1 - payload - " + s);
        return s.startsWith("P");
    }
    
}
