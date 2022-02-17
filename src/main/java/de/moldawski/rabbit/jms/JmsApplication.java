package de.moldawski.rabbit.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJms
@EnableScheduling
public class JmsApplication {
	  public static void main(String[] args) throws Exception {
		    SpringApplication.run(JmsApplication.class, args);
		  }
}
