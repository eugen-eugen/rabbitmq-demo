package de.moldawski.rabbit.nativee;

import org.springframework.amqp.rabbit.connection.PooledChannelConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class Configurations {
	@Bean
	PooledChannelConnectionFactory pcf() throws Exception {
	    ConnectionFactory rabbitConnectionFactory = new ConnectionFactory();
	    rabbitConnectionFactory.setHost("rammit15672-default.apps-crc.testing");
	    rabbitConnectionFactory.setPort(31672);
	    rabbitConnectionFactory.setUsername("admin");
	    rabbitConnectionFactory.setPassword("admin");
	    PooledChannelConnectionFactory pcf = new PooledChannelConnectionFactory(rabbitConnectionFactory);

	    return pcf;
	}
}
