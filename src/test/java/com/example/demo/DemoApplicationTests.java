package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void testFlow() {
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		DirectChannel start = (DirectChannel) context.getBean("start");
		
		Message msg = new Message() {
			@Override
			public Object getPayload() {
				return "PayPal";
			}
			
			@Override
			public MessageHeaders getHeaders() {
				return null;
			}
		};
		
		start.send(msg);
		
		msg = new Message() {
			@Override
			public Object getPayload() {
				return "Hello";
			}
			
			@Override
			public MessageHeaders getHeaders() {
				return null;
			}
		};
		
		start.send(msg);
		
		msg = new Message() {
			@Override
			public Object getPayload() {
				return "";
			}
			
			@Override
			public MessageHeaders getHeaders() {
				return null;
			}
		};
		
		start.send(msg);
	}

}
