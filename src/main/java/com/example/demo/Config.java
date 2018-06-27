package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

import java.util.Objects;

@Configuration
@EnableIntegration
public class Config {
    
    @Bean(name = "start")
    public DirectChannel start() {
        return new DirectChannel();
    }
    
    @Bean
    public IntegrationFlow flow1() {
        return IntegrationFlows.from(start())
                               .filter(String.class, f -> !f.isEmpty(), filterEndpointSpec -> filterEndpointSpec.discardChannel("discardChannel"))
                               .transform(String.class, new MyTransformer())
                               .filter(String.class, Objects::nonNull, filterEndpointSpec -> filterEndpointSpec.discardChannel("discardChannel"))
                               .filter(Object.class, s -> s instanceof String)
                               .routeToRecipients(r -> r
                                       .recipientFlow(new Flow1Selector(), f -> f.handle(new Flow1Handler()))
                                       .recipientFlow(new Flow2Selector(), f -> f.handle(new Flow2Handler()))
                                       .defaultOutputChannel("discardChannel"))
                               .get();
    }
    
    @Bean(name = "discardChannel")
    public DirectChannel discardChannel() {
        return new DirectChannel();
    }
    
    @ServiceActivator(inputChannel = "discardChannel")
    public void handleDiscards(String s) {
        System.out.println("Discard flow - " + s);
    }
    
}
