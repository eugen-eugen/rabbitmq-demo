package de.moldawski.rabbit.nativee;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	  static final String topicExchangeName = "mytopicex";

	  static final String queueName = "queue";



//	  @Bean
//	  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//	      MessageListenerAdapter listenerAdapter) {
//	    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//	    container.setConnectionFactory(connectionFactory);
//	    container.setQueueNames(queueName);
//	    container.setMessageListener(listenerAdapter);
//	    return container;
//	  }

	  @RabbitListener(queues = queueName)
	  public void receiveMessage(Message message) throws RuntimeException, IOException, ClassNotFoundException {
//		  ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(message.getBody()));
//		  Object o= ois.readObject();
		  System.out.println("Received <" + message + ">");
	  }
	  
//	  @Bean
//	  MessageListenerAdapter listenerAdapter(Receiver receiver) {
//	    return new MessageListenerAdapter(receiver, "receiveMessage");
//	  }

	  public static void main(String[] args) throws InterruptedException {
	    SpringApplication.run(DemoApplication.class, args);
	  }
}
