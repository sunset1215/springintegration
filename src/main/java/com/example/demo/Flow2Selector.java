package com.example.demo;

import org.springframework.integration.core.GenericSelector;

public class Flow2Selector implements GenericSelector<String> {
    
    @Override
    public boolean accept(String s) {
        System.out.println("Flow2 - payload - " + s);
        return s.startsWith("Pa");
    }
    
}
