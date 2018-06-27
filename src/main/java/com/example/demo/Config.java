package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

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
                               .transform("payload + payload")
                               .routeToRecipients(r -> r
                                       .recipientFlow(new Flow1Selector(), f -> f.handle(new Flow1Handler()))
                                       .recipientFlow(new Flow2Selector(), f -> f.handle(new Flow2Handler()))
                                       .defaultOutputChannel("nullChannel"))
                               .get();
    }
    
}
