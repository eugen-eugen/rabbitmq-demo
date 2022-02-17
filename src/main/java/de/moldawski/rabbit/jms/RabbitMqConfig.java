package de.moldawski.rabbit.jms;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.destination.BeanFactoryDestinationResolver;

import com.rabbitmq.jms.admin.RMQConnectionFactory;
import com.rabbitmq.jms.admin.RMQDestination;

@Configuration
public class RabbitMqConfig {

  @Bean
  public ConnectionFactory jmsConnectionFactory() {
    RMQConnectionFactory connectionFactory = new RMQConnectionFactory();
    connectionFactory.setUsername("admin");
    connectionFactory.setPassword("admin");
    connectionFactory.setVirtualHost("/");
    connectionFactory.setHost("rammit15672-default.apps-crc.testing");
    connectionFactory.setPort(31672);
    return connectionFactory;
  }
  @Bean (name="destination")
  public Destination jmsDestination() {
      RMQDestination jmsDestination = new RMQDestination();
      jmsDestination.setDestinationName("myex");
      jmsDestination.setAmqp(true);
      jmsDestination.setAmqpExchangeName("myex");
      jmsDestination.setAmqpRoutingKey("queue");
      jmsDestination.setDeclared(true);
      return jmsDestination;
  }
  
  @Bean (name="mytopicex")
  public Destination getTopic() {
      RMQDestination jmsDestination = new RMQDestination();
      jmsDestination.setDestinationName("mytopicex");
      jmsDestination.setAmqp(true);
      jmsDestination.setAmqpExchangeName("mytopicex");
      jmsDestination.setAmqpRoutingKey("queue");
      jmsDestination.setQueue(true);
      jmsDestination.setAmqpQueueName("queue");
      jmsDestination.setDeclared(true);
      return jmsDestination;
  }

  @Bean
  public BeanFactoryDestinationResolver beanFactoryDestinationResolver() {
	  return new BeanFactoryDestinationResolver();
  } 
  
  @Bean
  public JmsListenerContainerFactory<?> jmsFactory(ConnectionFactory connectionFactory,
          DefaultJmsListenerContainerFactoryConfigurer configurer, BeanFactoryDestinationResolver resolver) {
      DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
      configurer.configure(factory, connectionFactory);
      factory.setDestinationResolver(resolver);
      return factory;
  }
}