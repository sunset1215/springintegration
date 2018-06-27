package com.example.demo;

import org.springframework.integration.transformer.GenericTransformer;

public class MyTransformer implements GenericTransformer<String, String> {
    
    @Override
    public String transform(String s) {
        return s + s;
    }
    
}
